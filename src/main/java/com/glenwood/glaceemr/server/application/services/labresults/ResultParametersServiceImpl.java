package com.glenwood.glaceemr.server.application.services.labresults;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.persistence.criteria.Predicate;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.glenwood.glaceemr.server.application.Bean.LabEntriesParameterBean;
import com.glenwood.glaceemr.server.application.Bean.LabParametersBean;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.FileDetails;
import com.glenwood.glaceemr.server.application.models.FileDetails_;
import com.glenwood.glaceemr.server.application.models.FileName;
import com.glenwood.glaceemr.server.application.models.FileName_;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTest;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTest_;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTestmapping;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTestmapping_;
import com.glenwood.glaceemr.server.application.models.Hl7Laborders;
import com.glenwood.glaceemr.server.application.models.Hl7Laborders_;
import com.glenwood.glaceemr.server.application.models.Hl7ResultInbox;
import com.glenwood.glaceemr.server.application.models.Hl7ResultInbox_;
import com.glenwood.glaceemr.server.application.models.Hl7Unmappedresults;
import com.glenwood.glaceemr.server.application.models.Hl7Unmappedresults_;
import com.glenwood.glaceemr.server.application.models.LabEntries;
import com.glenwood.glaceemr.server.application.models.LabEntriesParameter;
import com.glenwood.glaceemr.server.application.models.LabEntriesParameter_;
import com.glenwood.glaceemr.server.application.models.LabEntries_;
import com.glenwood.glaceemr.server.application.models.LabParameterCode;
import com.glenwood.glaceemr.server.application.models.LabParameterCode_;
import com.glenwood.glaceemr.server.application.models.LabParameters;
import com.glenwood.glaceemr.server.application.models.LabParameters_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.repositories.EncounterEntityRepository;
import com.glenwood.glaceemr.server.application.repositories.FileDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.FileNameRepository;
import com.glenwood.glaceemr.server.application.repositories.Hl7ExternalTestMappingRepository;
import com.glenwood.glaceemr.server.application.repositories.Hl7ExternalTestRepository;
import com.glenwood.glaceemr.server.application.repositories.Hl7UnmappedResultsRepository;
import com.glenwood.glaceemr.server.application.repositories.LabEntriesParameterRepository;
import com.glenwood.glaceemr.server.application.repositories.LabParametersRepository;
import com.glenwood.glaceemr.server.application.services.investigation.InvestigationSummaryService;
import com.glenwood.glaceemr.server.application.specifications.InvestigationSpecification;
import com.glenwood.glaceemr.server.application.specifications.LabResultsSpecification;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.google.common.base.Optional;
import com.google.common.base.Strings;


/**
 * @author yasodha
 * 
 * InvestigationSummaryServiceImpl gives the data required for 
 * investigation summary
 */

@Service
public class ResultParametersServiceImpl implements ResultParametersService {

	@Autowired
	LabParametersRepository labParametersRepository;

	@Autowired
	LabEntriesParameterRepository labEntriesParametersRepository;

	@Autowired
	Hl7UnmappedResultsRepository unmappedRepository;

	@Autowired
	Hl7ExternalTestRepository externalTestRepository;

	@Autowired
	Hl7ExternalTestMappingRepository externalTestMapRepository;

	@Autowired
	InvestigationSummaryService investigationService;

	@Autowired
	FileDetailsRepository fileDetailsRepository;

	@Autowired
	FileNameRepository fileNameRepository;

	@Autowired
	EncounterEntityRepository encounterRepository;

	@Autowired
	EntityManagerFactory emf ;
	
	@PersistenceContext
	EntityManager em;

	Session session;

	private String patientId;

	private String userId;

	private int encnId;
	private String sharedFolderPath;

	/**
	 * Method to get result parameters
	 * @param resultXML
	 * @param labCompName
	 * @return
	 */
	@Override
	public String getResultParam(String paramXML, String labCompName) {
		String resultParam = "";
		String paramName = "";
		String paramValue = "";
		String paramDate = "";
		String paramStatus = "";
		String paramNotes = "";
		String paramResultStatus = "";
		String paramUnits = "";
		String normalRange 	= "";
		String paramCode	= "";
		String paramCodeSys = "";
		String paramLabLocationCode = "";
		String paramValuetype = "";
		String paramEncryptionType = "";
		String encryptedFileContent = "";
		try {
			if( paramXML != "" ) {
				DocumentBuilderFactory documentbuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = documentbuilderFactory.newDocumentBuilder();
				InputSource is = new InputSource(new ByteArrayInputStream(paramXML.getBytes()));
				Document document = docBuilder.parse(is);
				NodeList list =document.getElementsByTagName("PARAM");
				for(int paramCount=0;paramCount<list.getLength();paramCount++){
					Node paramNode = list.item(paramCount);
					paramName	= "";
					paramValue	= "";
					paramDate	= "";
					paramStatus	= "";
					paramNotes	= "";
					paramResultStatus = "";
					paramUnits	= "";
					normalRange	= "";
					paramCode	= "";
					paramCodeSys= "";
					paramLabLocationCode="";
					paramValuetype="";
					paramEncryptionType="";
					encryptedFileContent="";
					if(paramNode.getAttributes().getNamedItem("displayname") != null)
						paramName = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("displayname").getNodeValue())).or("");
					if((paramNode.getAttributes().getNamedItem("value") != null) && (!Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("value").getNodeValue())).or("").trim().equals("")))
						paramValue = paramNode.getAttributes().getNamedItem("value").getNodeValue();
					else if((paramNode.getAttributes().getNamedItem("startRange") != null) && (paramNode.getAttributes().getNamedItem("obsSymbol") != null) && (paramNode.getAttributes().getNamedItem("endRange") != null))
						paramValue = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("startRange").getNodeValue())).or(" ") + Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("obsSymbol").getNodeValue())).or(" ") + Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("endRange").getNodeValue())).or(" ");
					if(paramNode.getAttributes().getNamedItem("paramDate") != null)
						paramDate = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("paramDate").getNodeValue())).or("");
					if(paramNode.getAttributes().getNamedItem("status") != null)
						paramStatus = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("status").getNodeValue())).or("");
					if(paramNode.getAttributes().getNamedItem("Notes") != null)
						paramNotes = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("Notes").getNodeValue())).or(" ");
					if(paramNode.getAttributes().getNamedItem("observedresultstatus") != null)
						paramResultStatus = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("observedresultstatus").getNodeValue())).or("");

					// For Microbiology results , the parameter value will be in OBX-5.9 filed instead of OBX-5.1  (From HL7 2.5.1 version onwards)  
					if((paramNode.getAttributes().getNamedItem("codedvalue") != null) && (paramNode.getAttributes().getNamedItem("valueType") != null)){
						if((paramNode.getAttributes().getNamedItem("valueType").getNodeValue().equalsIgnoreCase("CE")||paramNode.getAttributes().getNamedItem("valueType").getNodeValue().equalsIgnoreCase("CWE"))&&!paramNode.getAttributes().getNamedItem("codedvalue").getNodeValue().equals("")){
							paramValue = paramNode.getAttributes().getNamedItem("codedvalue").getNodeValue();
						}
					}
					if(paramNode.getAttributes().getNamedItem("valueType") != null){
						if(paramNode.getAttributes().getNamedItem("valueType").getNodeValue().equalsIgnoreCase("SN")){
							if(paramNode.getAttributes().getNamedItem("sn1value")!=null){
								paramValue += paramNode.getAttributes().getNamedItem("sn1value").getNodeValue().trim();
							}
							if(paramNode.getAttributes().getNamedItem("sn2value")!=null){
								paramValue += paramNode.getAttributes().getNamedItem("sn2value").getNodeValue().trim();
							}
						}
					}
					if(!paramValue.equals("")){
						if(paramNode.getAttributes().getNamedItem("snunits") != null)
							paramUnits = paramNode.getAttributes().getNamedItem("snunits").getNodeValue().trim();
						if(paramUnits.trim().equals("")){
							if((paramNode.getAttributes().getNamedItem("units") != null) && (!Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("units"))).or("").equals("")))
								paramUnits = paramNode.getAttributes().getNamedItem("units").getNodeValue();
							else if(paramNode.getAttributes().getNamedItem("alternateUnits") != null)
								paramUnits = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("alternateUnits").getNodeValue())).or("");
						}
					}
					if(paramNode.getAttributes().getNamedItem("minmax") != null)
						normalRange = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("minmax").getNodeValue())).or("");
					if(paramNode.getAttributes().getNamedItem("code") != null)
						paramCode = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("code").getNodeValue())).or("");
					if(paramNode.getAttributes().getNamedItem("codeSystem") != null)
						paramCodeSys = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("codeSystem").getNodeValue())).or("").trim();
					if(paramCodeSys.equals(""))
						paramCodeSys = labCompName;
					else if(paramCodeSys.equalsIgnoreCase("L"))
						paramCodeSys = "LOINC";
					if(paramNode.getAttributes().getNamedItem("valueType") != null)
						if(paramNode.getAttributes().getNamedItem("valueType").getNodeValue().toString().trim().contains("HISTO"))
							continue;
					if(paramNode.getAttributes().getNamedItem("paramLabLocationCode") != null)
						paramLabLocationCode = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("paramLabLocationCode").getNodeValue())).or("");
					if((paramNode.getAttributes().getNamedItem("valuetype") != null) && (!Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("valuetype").getNodeValue())).or("").trim().equals("")))
						paramValuetype = paramNode.getAttributes().getNamedItem("valuetype").getNodeValue();
					if((paramNode.getAttributes().getNamedItem("paramEncryptionType") != null))
						paramEncryptionType=Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("paramEncryptionType").getNodeValue())).or("");
					if(paramValuetype.equalsIgnoreCase("ED") && paramEncryptionType.equalsIgnoreCase("Base64")){
						if((paramNode.getAttributes().getNamedItem("paramEncryptionValue") != null)){
							encryptedFileContent= Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("paramEncryptionValue").getNodeValue())).or(""); 
						}
						if(encryptedFileContent.equals("")&&paramNode.getAttributes().getNamedItem("alternateparamEncryptionValue") != null){
							encryptedFileContent = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("alternateparamEncryptionValue").getNodeValue())).or("");
						}
					}
					paramName	= replaceXMLChars(paramName);
					paramValue	= replaceXMLChars(paramValue).replaceAll("\\\\T\\\\", "&").replaceAll("#@#.br#@#","\n");
					paramStatus	= replaceXMLChars(paramStatus);
					paramNotes	= replaceXMLChars(paramNotes).replaceAll("\\\\T\\\\", "&").replaceAll("#@#.br#@#","\n").replaceAll("<BR>","\n");
					paramResultStatus = replaceXMLChars(paramResultStatus);
					paramCode	= replaceXMLChars(paramCode);
					paramUnits	= replaceXMLChars(paramUnits);
					normalRange	= replaceXMLChars(normalRange);
					paramCodeSys= replaceXMLChars(paramCodeSys);
					resultParam += "-1|~|-1|~|"+paramName+" |~|"+paramValue+" |~|"+paramDate+" |~|"+paramStatus+" |~|"+paramNotes+" |~|"+paramResultStatus+" |~|"+paramUnits+" |~|"+normalRange+" |~|"+paramCode+" |~|"+paramCodeSys+" |~|"+paramLabLocationCode+" |~|"+paramValuetype+" |~|"+paramEncryptionType+" |~|"+encryptedFileContent+" @#@";
				}
				resultParam = getDuplicateParameters(resultParam);
			}
		} catch( Exception e ) {
			e.printStackTrace();
		}
		return resultParam;
	}

	/**
	 * Method to replace XML characters
	 * @param xStr
	 * @return
	 */
	public String replaceXMLChars( String xStr ) {
		xStr = xStr.replaceAll("&amp;", "&");
		xStr = xStr.replaceAll("##", "<");
		xStr = xStr.replaceAll("~~", ">");
		xStr = xStr.replaceAll("&quot;", "\"");
		xStr = xStr.replaceAll("&apos;", "'");
		return xStr;
	}

	/**
	 * Method to get duplicate parameters
	 * @param paramString
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String 	getDuplicateParameters(String paramString){
		String paramArr[] = paramString.split("@#@");
		String paramCode = "",resultParam = "",paramNotes = "";
		Hashtable table = new Hashtable();
		for(int i=0;i<paramArr.length;i++){
			paramCode = paramArr[i].split("[|]")[20].trim();
			paramNotes = paramArr[i].split("[|]")[12];
			if(table.containsKey(paramCode)){
				continue;
			}
			String duplicateparamNotes	= getDuplicateParameternotes(paramString,paramCode,i);
			if(!duplicateparamNotes.equalsIgnoreCase("")){
				paramNotes += "!@!"+duplicateparamNotes;
			}
			resultParam += "-1|~|-1|~|"+paramArr[i].split("[|]")[4]+" |~|"+paramArr[i].split("[|]")[6]+" |~|"+paramArr[i].split("[|]")[8]+" |~|"+paramArr[i].split("[|]")[10]+" |~|"+paramNotes+" |~|"+paramArr[i].split("[|]")[14]+" |~|"+paramArr[i].split("[|]")[16]+" |~|"+paramArr[i].split("[|]")[18]+" |~|"+paramArr[i].split("[|]")[20]+" |~|"+paramArr[i].split("[|]")[22]+" |~|"+paramArr[i].split("[|]")[24]+" |~|"+paramArr[i].split("[|]")[26]+" |~|"+paramArr[i].split("[|]")[28]+" |~|"+paramArr[i].split("[|]")[30]+" @#@";	
			table.put(paramCode,paramNotes);
		}
		return resultParam;
	}

	/**
	 * Method to get duplicate parameter notes
	 * @param paramString
	 * @param paramcode
	 * @param index
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String 	getDuplicateParameternotes(String paramString,String paramcode,int index){
		String paramArr[] = paramString.split("@#@");
		String paramCode="",value="", emptyString = "";
		Hashtable table = new Hashtable();
		for(int i=index+1;i<paramArr.length;i++){
			paramCode = paramArr[i].split("[|]")[20].trim();
			value = paramArr[i].split("[|]")[4]+"		"+paramArr[i].split("[|]")[6]+"		"+ paramArr[i].split("[|]")[12]+"!@!";
			if( paramCode.equals(paramcode) && table.containsKey(paramCode) ) {
				String paramNotes	= table.get(paramCode).toString();
				paramNotes += value;
				table.put(paramCode,paramNotes);			
			} else if( paramCode.equals(paramcode) ) {
				table.put(paramcode,value);
			}
		}
		if( table.containsKey(paramcode) ) {
			return table.get(paramcode).toString();
		} else {
			return emptyString;
		}	
	}

	/**
	 * Method to get parameters and values from result XML
	 * @param resultXML
	 * @param map
	 * @param testDetailId
	 */
	@Override
	public void getParameterValues(String resultXML, List<Parameters> map,String testDetailId) {
		String paramName	= "";
		String paramValue	= "";
		String paramStatus	= "";
		String paramNotes	= "";
		String paramUnits	= "";
		String normalRange	= "";
		String paramCode	= "";
		String codingSystem	= "";
		String paramValueType = "";
		String paramValuetype = "";
		String paramcodedValue = "";
		String paramResultStatus = "";
		String paramEncryptionType = "";
		String encryptedFileContent = "";
		List<Parameters> tempMap = new ArrayList<Parameters>();
		try {
			if(!resultXML.equals(null) && !resultXML.equals("null") && !resultXML.equals("")) {
				DocumentBuilderFactory documentbuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = documentbuilderFactory.newDocumentBuilder();
				InputSource is = new InputSource(new ByteArrayInputStream(resultXML.getBytes()));
				Document document = docBuilder.parse(is);
				NodeList list = document.getElementsByTagName("PARAM");
				for( int i = 0 ; i < list.getLength() ; i++ ) {
					Parameters paramObj = new Parameters();
					Node paramNode = list.item(i);
					paramName	= "";
					paramValue	= "";
					paramStatus	= "";
					paramNotes	= "";
					paramResultStatus = "";
					paramUnits	= "";
					normalRange	= "";
					paramCode	= "";
					codingSystem = "";

					if(paramNode.getAttributes().getNamedItem("displayname") != null)
						paramName = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("displayname").getNodeValue())).or("");
					if((paramNode.getAttributes().getNamedItem("value") != null) && (!Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("value").getNodeValue())).or("").trim().equals("")))
						paramValue = paramNode.getAttributes().getNamedItem("value").getNodeValue();
					else if((paramNode.getAttributes().getNamedItem("startRange") != null) && (paramNode.getAttributes().getNamedItem("obsSymbol") != null) && (paramNode.getAttributes().getNamedItem("endRange") != null))
						paramValue = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("startRange").getNodeValue())).or(" ") + Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("obsSymbol").getNodeValue())).or(" ") + Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("endRange").getNodeValue())).or(" ");					
					if((paramNode.getAttributes().getNamedItem("codedvalue") != null) && (!Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("codedvalue").getNodeValue())).or("").trim().equals("")))
						paramcodedValue = paramNode.getAttributes().getNamedItem("codedvalue").getNodeValue();
					if((paramNode.getAttributes().getNamedItem("valueType") != null) && (!Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("valueType").getNodeValue())).or("").trim().equals("")))
						paramValueType = paramNode.getAttributes().getNamedItem("valueType").getNodeValue();					
					if(paramValueType.trim().equalsIgnoreCase("CWE")||paramValueType.trim().equalsIgnoreCase("CE")){
						if(paramNode.getAttributes().getNamedItem("codedvalue") != null){
							paramValue = paramNode.getAttributes().getNamedItem("codedvalue").getNodeValue().trim();
							if(paramNode.getAttributes().getNamedItem("sn1value") != null){
								paramcodedValue = paramNode.getAttributes().getNamedItem("sn1value").getNodeValue().trim() + " ("+paramcodedValue+") ";
							}
						}
					}
					if(paramValueType.trim().equalsIgnoreCase("SN")){
						if(paramNode.getAttributes().getNamedItem("sn1value") != null){
							paramValue += paramNode.getAttributes().getNamedItem("sn1value").getNodeValue().trim();
						}
						if(paramNode.getAttributes().getNamedItem("sn2value") != null){
							paramValue += paramNode.getAttributes().getNamedItem("sn2value").getNodeValue().trim();
						}
					}

					if(paramNode.getAttributes().getNamedItem("status") != null)
						paramStatus = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("status").getNodeValue())).or("");
					if(paramNode.getAttributes().getNamedItem("Notes") != null)
						paramNotes = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("Notes").getNodeValue())).or(" ");					
					if(paramNotes.length()>150) {
						String temp = paramNotes.substring(0, 150);
						paramNotes = paramNotes.substring(0, temp.lastIndexOf(' '))+"!@!"+paramNotes.substring(temp.lastIndexOf(' '));
					}					
					if(paramNode.getAttributes().getNamedItem("observedresultstatus") != null)
						paramResultStatus = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("observedresultstatus").getNodeValue())).or("");
					if(!paramValue.equals("")){
						if(paramNode.getAttributes().getNamedItem("snunits") != null)
							paramUnits = paramNode.getAttributes().getNamedItem("snunits").getNodeValue().trim();	
						if(paramUnits.equals("")){
							if((paramNode.getAttributes().getNamedItem("units") != null) && (!Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("units"))).or("").equals("")))
								paramUnits = paramNode.getAttributes().getNamedItem("units").getNodeValue();
							else if(paramNode.getAttributes().getNamedItem("alternateUnits") != null)
								paramUnits = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("alternateUnits").getNodeValue())).or("");
						}
					}
					if(paramNode.getAttributes().getNamedItem("minmax") != null)
						normalRange = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("minmax").getNodeValue())).or("");
					if(paramNode.getAttributes().getNamedItem("code") != null)
						paramCode = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("code").getNodeValue())).or("");
					if(paramNode.getAttributes().getNamedItem("valueType") != null)
						if(paramNode.getAttributes().getNamedItem("valueType").getNodeValue().toString().trim().contains("HISTO"))
							continue;

					if(paramNode.getAttributes().getNamedItem("codeSystem") != null)
						codingSystem = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("codeSystem").getNodeValue())).or("").trim();
					if((paramNode.getAttributes().getNamedItem("valuetype") != null) && (!Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("valuetype").getNodeValue())).or("").trim().equals("")))
						paramValuetype = paramNode.getAttributes().getNamedItem("valuetype").getNodeValue();
					if((paramNode.getAttributes().getNamedItem("paramEncryptionType") != null))
						paramEncryptionType = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("paramEncryptionType").getNodeValue())).or("");
					if(paramValuetype.equalsIgnoreCase("ED") && paramEncryptionType.equalsIgnoreCase("Base64")){
						if((paramNode.getAttributes().getNamedItem("paramEncryptionValue") != null)){
							encryptedFileContent = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("paramEncryptionValue").getNodeValue())).or(""); 
						}
						if(encryptedFileContent.equals("") && paramNode.getAttributes().getNamedItem("alternateparamEncryptionValue") != null) {
							encryptedFileContent = Optional.fromNullable(Strings.emptyToNull("" + paramNode.getAttributes().getNamedItem("alternateparamEncryptionValue").getNodeValue())).or("");
						}
					}
					paramName = replaceXMLChars(paramName);					//observedResultStatus
					paramValue = replaceXMLChars(paramValue).replaceAll("\\\\T\\\\", "&").replaceAll("#@#.br#@#","\n");					//	P - Preliminary result, final not yet obtained.
					paramStatus	= replaceXMLChars(paramStatus);					//	X - Procedure cannot be done, Result canceled due to Non-Performance (TNP).
					paramNotes = replaceXMLChars(paramNotes).replaceAll("\\\\T\\\\", "&").replaceAll("#@#.br#@#","\n");					// 	N - Procedure completed but will not be reported or Result canceled (DNR).
					paramResultStatus = replaceXMLChars(paramResultStatus);		//	F - Result complete and verified.
					paramCode = replaceXMLChars(paramCode);					//	S - Partial Results (Will Follow).
					paramUnits = replaceXMLChars(paramUnits);					//	C - Corrected Result.
					normalRange = replaceXMLChars(normalRange);
					codingSystem = replaceXMLChars(normalRange);
					
					LabParametersBean labParamsListData=labParamListData(paramCode, codingSystem);
					boolean isflowsheet = false;
					String flowsheeturl = "";
					if(labParamsListData!=null) {
						isflowsheet	= Boolean.parseBoolean(Optional.fromNullable(Strings.emptyToNull("" + labParamsListData.getLabParametersIsflowsheetneeded())).or("false"));
						flowsheeturl = Optional.fromNullable(Strings.emptyToNull("" + labParamsListData.getLabParametersFlowsheeturl())).or("");
					}
					String	fileScanId = "-1";
					String isPdf = "0";
					String	paramEntryId = "-1";
					LabEntriesParameterBean entriesParamListData=entriesParamListData(testDetailId, paramName.replaceAll("'",""));

					if(entriesParamListData!=null) {
						
						isPdf = Optional.fromNullable(Strings.emptyToNull("" + entriesParamListData.getLabEntriesParameterIspdf())).or("-1");
						fileScanId = Optional.fromNullable(Strings.emptyToNull("" + entriesParamListData.getLabEntriesParameterFilenameScanid())).or("-1");
						paramEntryId = Optional.fromNullable(Strings.emptyToNull("" + entriesParamListData.getLabEntriesParameterId())).or("-1");
						int categoryId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + getScanGroupId(testDetailId))).or("-1"));
						paramObj.setCategoryId("" + categoryId);
						paramObj.setIsPDF(isPdf);
						paramObj.setParamEntryId(paramEntryId);
						paramObj.setFileScanId(fileScanId);
					} else {
						paramObj.setCategoryId("-1");
						paramObj.setIsPDF("-1");
						paramObj.setParamEntryId("-1");
						paramObj.setFileScanId("-1");
					}
					paramObj.setDisplayName(paramName);
					paramObj.setValue(paramValue);
					paramObj.setParamStatus(paramStatus);
					paramObj.setParamNotes(paramNotes);
					paramObj.setParamCode(paramCode);
					paramObj.setUnits(paramUnits);
					paramObj.setNormalRange(normalRange);
					paramObj.setIsFlowsheet("" + isflowsheet);
					paramObj.setFlowsheetURL(flowsheeturl);
					paramObj.setParamCodedValue(paramcodedValue);
					paramObj.setParamValueType(paramValueType);
					paramObj.setTestDetailId(testDetailId);
					tempMap.add(i, paramObj);
					map.add(i, paramObj);
				}
			}
		} catch( Exception e ) {
			e.printStackTrace();
		}	
	}

	private LabEntriesParameterBean entriesParamListData(
			String testDetailId, String displayName) {
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<LabEntriesParameter> root = cq.from(LabEntriesParameter.class);
			Join<LabEntriesParameter, LabParameters> join = root.join(LabEntriesParameter_.labParametersTable,JoinType.INNER);
			Predicate checkTestId = builder.equal(root.get(LabEntriesParameter_.labEntriesParameterTestdetailid), testDetailId);
			Predicate checkIsActive = builder.equal(root.get(LabEntriesParameter_.labEntriesParameterIsactive), true);
			Predicate checkDisplayName = builder.like(join.get(LabParameters_.labParametersDisplayname), displayName);

			cq.multiselect(builder.construct(LabEntriesParameterBean.class, root.get(LabEntriesParameter_.labEntriesParameterIspdf),
					root.get(LabEntriesParameter_.labEntriesParameterFilenameScanid),
					root.get(LabEntriesParameter_.labEntriesParameterId)));		
			cq.where(checkTestId, checkIsActive, checkDisplayName);

			List<Object> inboxList=em.createQuery(cq).getResultList();
			LabEntriesParameterBean eachObj=new LabEntriesParameterBean();
			if(!inboxList.isEmpty())
			eachObj=(LabEntriesParameterBean) inboxList.get(0);
			return eachObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			em.close();
		}
	}

	public LabParametersBean labParamListData(String paramCode,
			String codingSystem) {
		
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<LabParameters> root = cq.from(LabParameters.class);
			Join<LabParameters, LabParameterCode> join = root.join(LabParameters_.labParameterCodeTable,JoinType.INNER);
			Predicate checkCode = builder.equal(join.get(LabParameterCode_.labParameterCodeValue), paramCode);
			Predicate checkSystem = builder.equal(join.get(LabParameterCode_.labParameterCodeSystem), codingSystem);
			cq.multiselect(builder.construct(LabParametersBean.class, root.get(LabParameters_.labParametersIsflowsheetneeded),
					root.get(LabParameters_.labParametersFlowsheeturl)));		
			cq.where(checkCode,checkSystem);
			List<Object> resultList =em.createQuery(cq).getResultList();
			
			LabParametersBean eachObj=new LabParametersBean();
			if(!resultList.isEmpty())
				 eachObj=(LabParametersBean) resultList.get(0);
			return eachObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			em.close();
		}
	}

	/**
	 * Method to get scan group id
	 * @param testDetailId
	 * @return
	 */
	private String getScanGroupId(String testDetailId) {
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<LabEntries> root = cq.from(LabEntries.class);
			cq.select(root.get(LabEntries_.labEntriesScangroupId));
			cq.where(builder.equal(root.get(LabEntries_.labEntriesTestdetailId),testDetailId));
			String scanGroupId = "" + em.createQuery(cq).getFirstResult();
			return scanGroupId;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Method to get lab location details
	 * @param resultXML
	 * @param labCompany
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Hashtable getLabLocationDetails(String resultXML, String labCompany) throws Exception {
		Hashtable labLocDetailHash = new Hashtable();
		//Lab Company - Director and Address details
		String cliaNumber	= "";
		String labDirector	= "";
		String labLocName	= "";				//Name of lab company where test is performed.
		String labLocAddr	= "";
		String labLocCity	= "";
		String labLocState	= "";
		String labLocZip	= "";
		String labLocCode = "";
		String labDirectorPhoneNumber ="";
		try{
			if(resultXML != ""){
				DocumentBuilderFactory documentbuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = documentbuilderFactory.newDocumentBuilder();
				InputSource is = new InputSource(new ByteArrayInputStream(resultXML.getBytes()));
				Document document = docBuilder.parse(is);
				NodeList list = document.getElementsByTagName("LABLOCATION");
				for(int lablocCount=0;lablocCount<list.getLength();lablocCount++){
					Hashtable locationDetail = new Hashtable();
					cliaNumber	= "";
					labDirector	= "";
					labLocName	= "";				
					labLocAddr	= "";
					labLocCity	= "";
					labLocState	= "";
					labLocZip	= "";
					labLocCode = "";
					labDirectorPhoneNumber ="";
					Node labLocNode = list.item(lablocCount);
					if(labLocNode.getAttributes().getNamedItem("labloccode") != null){
						labLocCode = Optional.fromNullable(Strings.emptyToNull("" + labLocNode.getAttributes().getNamedItem("labloccode").getNodeValue().trim())).or("");
					}
					if(labLocNode.getAttributes().getNamedItem("clianumber") != null)
						cliaNumber = Optional.fromNullable(Strings.emptyToNull("" + labLocNode.getAttributes().getNamedItem("clianumber").getNodeValue())).or("");
					if(labLocNode.getAttributes().getNamedItem("labdirector") != null)
						labDirector = Optional.fromNullable(Strings.emptyToNull("" + labLocNode.getAttributes().getNamedItem("labdirector").getNodeValue())).or("");
					else if(labLocNode.getAttributes().getNamedItem("labdirectorfname") != null && labLocNode.getAttributes().getNamedItem("labdirectorlname")!=null && labLocNode.getAttributes().getNamedItem("labdirectormi") != null && labLocNode.getAttributes().getNamedItem("labdirectorsuffix") != null)
						labDirector = Optional.fromNullable(Strings.emptyToNull("" + formatName(labLocNode.getAttributes().getNamedItem("labdirectorfname").getNodeValue(), labLocNode.getAttributes().getNamedItem("labdirectorlname").getNodeValue(), labLocNode.getAttributes().getNamedItem("labdirectormi").getNodeValue(), labLocNode.getAttributes().getNamedItem("labdirectorsuffix").getNodeValue(), 1))).or("");
					if(labLocNode.getAttributes().getNamedItem("labname") != null)
						labLocName = Optional.fromNullable(Strings.emptyToNull("" + labLocNode.getAttributes().getNamedItem("labname").getNodeValue())).or("");
					if(labLocNode.getAttributes().getNamedItem("labaddress") != null)
						labLocAddr = Optional.fromNullable(Strings.emptyToNull("" + labLocNode.getAttributes().getNamedItem("labaddress").getNodeValue())).or("");
					if(labLocNode.getAttributes().getNamedItem("labcity") != null)
						labLocCity = Optional.fromNullable(Strings.emptyToNull("" + labLocNode.getAttributes().getNamedItem("labcity").getNodeValue())).or("");
					if(labLocNode.getAttributes().getNamedItem("labstate") != null)
						labLocState = Optional.fromNullable(Strings.emptyToNull("" + labLocNode.getAttributes().getNamedItem("labstate").getNodeValue())).or("");
					if(labLocNode.getAttributes().getNamedItem("labzip") != null)
						labLocZip = Optional.fromNullable(Strings.emptyToNull("" + labLocNode.getAttributes().getNamedItem("labzip").getNodeValue())).or("");
					if(labLocNode.getAttributes().getNamedItem("labdirectorphonenumber") != null)
						labDirectorPhoneNumber = Optional.fromNullable(Strings.emptyToNull("" + labLocNode.getAttributes().getNamedItem("labdirectorphonenumber").getNodeValue())).or("");
					locationDetail.put("labLocCode",labLocCode);
					locationDetail.put("cliaNumber",cliaNumber);
					locationDetail.put("labDirector",labDirector);
					locationDetail.put("labLocName",labLocName);
					locationDetail.put("labLocAddr",labLocAddr);
					locationDetail.put("labLocCity",labLocCity);
					locationDetail.put("labLocState",labLocState);
					locationDetail.put("labLocZip",labLocZip);
					locationDetail.put("labDirectorPhoneNumber",labDirectorPhoneNumber);
					labLocDetailHash.put(labLocCode, locationDetail);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return labLocDetailHash;
	}

	/**
	 * Method to format name
	 * @param firstName
	 * @param lastName
	 * @param middleName
	 * @param type 1: <First name> <MI>[.] <Last name> <crediantials>
	 * 	      type 2: <Last name> <crediantials>[.], <First name> <MI>[.]
	 * @return
	 */
	public static String formatName( String firstName , String lastName, String middleName ,String crediantials ,int  type ){
		StringBuffer name = new StringBuffer();
		middleName = Optional.fromNullable(Strings.emptyToNull("" + middleName)).or("").trim();
		crediantials= Optional.fromNullable(Strings.emptyToNull("" + crediantials)).or("").trim();
		firstName = Optional.fromNullable(Strings.emptyToNull("" + firstName)).or("");
		lastName = Optional.fromNullable(Strings.emptyToNull("" + lastName)).or("");

		switch (type){
		case 1:

			if(firstName.length()>0){
				name.append(firstName.substring(0, 1).toUpperCase()).append(firstName.substring(1).toLowerCase()).append(" ");
			}
			if(middleName.length()==1)
			{
				name.append(middleName.substring(0, 1).toUpperCase()).append(".").append(" ");
			}
			else if(middleName.length()>1)
			{
				name.append(middleName.substring(0, 1).toUpperCase()).append(middleName.substring(1).toLowerCase()).append(" ");
			}
			if(lastName.length()>0){
				name.append(lastName.substring(0, 1).toUpperCase()).append(lastName.substring(1).toLowerCase()).append(" ");
			}
			if (crediantials.length()>1)
			{
				name.append(crediantials.toUpperCase());
				if(crediantials.charAt(crediantials.length()-1)!='.')
					name.append(".");
			}
			//GlaceOutLoggingStream.console(name);
			break;

		case 2:
			if(lastName.length()>0){
				name.append(lastName.substring(0, 1).toUpperCase()).append(lastName.substring(1).toLowerCase());
			}

			if (crediantials.length()>1)
			{
				name.append(" ");
				name.append(crediantials.toUpperCase());
				if(crediantials.charAt(crediantials.length()-1)!='.')
					name.append(".");
			}
			if((firstName.length()>0 || middleName.length()>0) && (lastName.length()>0 ||crediantials.length()>0)){
				name.append(",");
			}
			if(firstName.length()>0){
				name.append(" ");
				name.append(firstName.substring(0, 1).toUpperCase()).append(firstName.substring(1).toLowerCase());
			}
			if(middleName.length()==1)
			{
				name.append(" ").append(middleName.substring(0, 1).toUpperCase()).append(".");
			}
			else if(middleName.length()>1)
			{
				name.append(" ").append(middleName.substring(0, 1).toUpperCase()).append(middleName.substring(1).toLowerCase()).append(" ");
			}
			//GlaceOutLoggingStream.console(name);
			break;
		}
		return name.toString();
	}

	/**
	 * Method to get local test detail id
	 * @param labCompanyId
	 * @param testcode
	 * @param testname
	 * @return
	 */
	@Override
	public Integer getLocalTestId(Integer labCompanyId, String testCode, String testName) {
		Integer testId = -1;
		try {
			int externalTestMapId =  Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + getHl7externalTestId(labCompanyId, testCode))).or("-1"));
			if( externalTestMapId == -1 ) {
				testCode = testCode.replaceAll("[a-zA-Z]", "").replaceAll("=", "");
				externalTestMapId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + getHl7externalTestId(labCompanyId, testCode))).or("-1"));	
			}
			if(externalTestMapId != -1) {
				EntityManager em = emf.createEntityManager();
				try {
					CriteriaBuilder builder = em.getCriteriaBuilder();
					CriteriaQuery<Object> cq = builder.createQuery();
					Root<Hl7ExternalTestmapping> root = cq.from(Hl7ExternalTestmapping.class);
					cq.select(root.get(Hl7ExternalTestmapping_.hl7ExternalTestmappingLocaltestid));
					cq.where(builder.and(builder.equal(root.get(Hl7ExternalTestmapping_.hl7ExternalTestmappingExternaltestid), externalTestMapId), builder.equal(root.get(Hl7ExternalTestmapping_.hl7ExternalTestmappingLabcompanyid), labCompanyId)));
					testId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + em.createQuery(cq).getFirstResult())).or("-1"));
				} catch(Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					em.close();
				}
			}
		} catch( Exception e ) {
			e.printStackTrace();
		}
		return testId;
	}

	/**
	 * Method to get hl7 external test id
	 * @param labCompanyId
	 * @param testCode
	 * @return
	 */
	public Integer getHl7externalTestId(Integer labCompanyId, String testCode) {
		Integer testId = -1;
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7ExternalTest> root = cq.from(Hl7ExternalTest.class);
			cq.select(root.get(Hl7ExternalTest_.hl7ExternalTestId));
			cq.where(builder.and(builder.or(builder.like(builder.concat(root.get(Hl7ExternalTest_.hl7ExternalTestCode),builder.coalesce(root.get(Hl7ExternalTest_.hl7ExternalTestSuffix),"")) , testCode),builder.like(root.get(Hl7ExternalTest_.hl7ExternalTestCode), testCode)), builder.equal(root.get(Hl7ExternalTest_.hl7ExternalTestLabcompanyid), labCompanyId)));
			testId = em.createQuery(cq).getFirstResult();
			return testId;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Method to get hl7 external test code
	 * @param labCompanyId
	 * @param testCode
	 * @return
	 */
	public String getHl7externalTestCode(Integer labCompanyId, String testCode, String testName) {
		String extTestCode = "-1";
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7ExternalTest> root = cq.from(Hl7ExternalTest.class);
			cq.select(root.get(Hl7ExternalTest_.hl7ExternalTestCode));
			cq.where(builder.and(builder.or(builder.like(builder.concat(root.get(Hl7ExternalTest_.hl7ExternalTestCode),builder.coalesce(root.get(Hl7ExternalTest_.hl7ExternalTestSuffix),"")) , testCode),builder.like(root.get(Hl7ExternalTest_.hl7ExternalTestCode), testCode), builder.like(root.get(Hl7ExternalTest_.hl7ExternalTestName), testName)), builder.equal(root.get(Hl7ExternalTest_.hl7ExternalTestLabcompanyid), labCompanyId)));
			extTestCode = "" + em.createQuery(cq).getFirstResult();
			return extTestCode;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Find testDetailId if received test is already ordered for patient
	 * @param accountNo
	 * @param chartId
	 * @param labAccessionNo
	 * @param testCode
	 * @param testId
	 * @param testName
	 * @param orderedDate
	 * @param orderId
	 * @return
	 */
	@Override
	public Integer getTestDetailId(String accountNo, Integer chartId, String labAccessionNo, String testCode, Integer testId, String testName, String orderedDate, String orderId) {
		int testDetId = -1;
		int encounterId = -1;		
		try {
			DateFormat parser = new SimpleDateFormat("MM/dd/yyyy"); 
			Date date = (Date) parser.parse(orderedDate);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			orderedDate = formatter.format(date);
			if( orderId != "" ) {
				int chkpatientid = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(getPatientId(accountNo))).or("-1"));
				if( chkpatientid != -1 ) {
					encounterId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(encounterFromLabOrder(chkpatientid, orderId))).or("-1"));
					if( encounterId != -1 ) {
						testDetId = externalTestIdFromExternalMapping(chkpatientid, orderId, encounterId, testCode);
					}
				}
			}
			if( testDetId == -1 ) {
				if( !labAccessionNo.equals("") && !testCode.equals("") ) {
					testDetId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + testIdFromUnmappedResults(accountNo, orderedDate, labAccessionNo, testCode) )).or("-1"));
				}				
				if( testDetId == -1) {
					if(testId == -1) {		//TestdetailId shouldnot be compared with testname and date. For some tests such as Lipid Panel, Each parameter are sent in separate OBR segments.
						EntityManager em = emf.createEntityManager();
						try {
							CriteriaBuilder builder = em.getCriteriaBuilder();
							CriteriaQuery<Object> cq = builder.createQuery();
							Root<LabEntries> root = cq.from(LabEntries.class);
							Join<LabEntries, Encounter> join = root.join(LabEntries_.encounter, JoinType.INNER);
							cq.select(root.get(LabEntries_.labEntriesTestdetailId));
							cq.where(builder.and(builder.equal(root.get(LabEntries_.labEntriesOrdOn), Timestamp.valueOf(orderedDate)),
									builder.equal(join.get(Encounter_.encounterChartid), chartId),
									builder.like(root.get(LabEntries_.labEntriesTestDesc), testName)));
							List<Object> testdetailid = em.createQuery(cq).getResultList();
							if ( testdetailid.size() > 0 )
								testDetId = Integer.parseInt("" + testdetailid.get(0));	
						} catch(Exception e) {
							e.printStackTrace();
							return null;
						} finally {
							em.close();
						}
					} else {
						EntityManager em = emf.createEntityManager();
						try {
							CriteriaBuilder builder = em.getCriteriaBuilder();
							CriteriaQuery<Object> cq = builder.createQuery();						
							Root<LabEntries> root = cq.from(LabEntries.class);
							Join<LabEntries, Encounter> join = root.join(LabEntries_.encounter, JoinType.INNER);
							cq.select(root.get(LabEntries_.labEntriesTestdetailId));
							cq.where(builder.and(builder.equal(root.get(LabEntries_.labEntriesOrdOn), Timestamp.valueOf(orderedDate)),
									builder.equal(join.get(Encounter_.encounterChartid), chartId),
									builder.equal(root.get(LabEntries_.labEntriesTestId), testId)));						
							List<Object> testdetailid = em.createQuery(cq).getResultList();
							if ( testdetailid.size() > 0 )
								testDetId = Integer.parseInt("" + testdetailid.get(0));
						} catch(Exception e) {
							e.printStackTrace();
							return null;
						} finally {
							em.close();
						}
					}
					List<Hl7Unmappedresults> unmappedList = unmappedRepository.findAll(LabResultsSpecification.checkAccessionAndTestId(labAccessionNo, testDetId));
					long testcount = unmappedList.size();
					if( testcount > 0 )
						testDetId = -1;
				}
			}
		} catch( Exception e ) {
			e.printStackTrace();
		}
		this.patientId = getPatientId(accountNo);
		this.encnId = encounterId;
		return testDetId;
	}

	/**
	 * Method to get patient id from PatientRegistration
	 * @param accountNo
	 */
	private String getPatientId(String accountNo) {
		String patientId = "-1";
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<PatientRegistration> root = cq.from(PatientRegistration.class);
			cq.select(root.get(PatientRegistration_.patientRegistrationId));
			cq.where(builder.equal(root.get(PatientRegistration_.patientRegistrationAccountno), accountNo));
			patientId = "" + em.createQuery(cq).getFirstResult();
			return patientId;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Method to get encounterId from hl7 lab orders
	 * @param patientId
	 * @param orderId
	 * @return
	 */
	private String encounterFromLabOrder(Integer patientId, String orderId) {
		String encounterId = "-1";
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7Laborders> root = cq.from(Hl7Laborders.class);
			cq.select(root.get(Hl7Laborders_.hl7LabordersEncId));
			cq.where(builder.and(builder.equal(root.get(Hl7Laborders_.hl7LabordersReqid), Integer.parseInt(orderId)),builder.equal(root.get(Hl7Laborders_.hl7LabordersPatientId), patientId)));
			encounterId = "" + em.createQuery(cq).getFirstResult();
			return encounterId;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Method to get testId from hl7 external test mapping
	 * @param patientId
	 * @param orderId
	 * @param testCode 
	 * @return
	 */
	private Integer externalTestIdFromExternalMapping(Integer patientId, String orderId, Integer encounterId, String testCode) {		
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder labOrder = em.getCriteriaBuilder();
			CriteriaQuery<Object> orderQuery = labOrder.createQuery();
			Root<Hl7Laborders> orderRoot = orderQuery.from(Hl7Laborders.class);
			orderQuery.select(orderRoot.get(Hl7Laborders_.hl7LabordersTestId));
			orderQuery.where(labOrder.and(labOrder.equal(orderRoot.get(Hl7Laborders_.hl7LabordersReqid), 
					Integer.parseInt(orderId)),labOrder.equal(orderRoot.get(Hl7Laborders_.hl7LabordersPatientId), patientId)));

			CriteriaBuilder testBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Object> testQuery = testBuilder.createQuery();
			Root<LabEntries> testRoot = testQuery.from(LabEntries.class);
			testQuery.select(testRoot.get(LabEntries_.labEntriesTestdetailId));
			testQuery.where(testBuilder.and(testBuilder.equal(testRoot.get(LabEntries_.labEntriesEncounterId), encounterId), 
					testRoot.get(LabEntries_.labEntriesTestdetailId).in(em.createQuery(orderQuery).getResultList())));
			List<Object> testDetailIdList = em.createQuery(testQuery).getResultList(); 

			CriteriaBuilder entry = em.getCriteriaBuilder();
			CriteriaQuery<Object> entryQuery = entry.createQuery();
			Root<LabEntries> entryRoot = entryQuery.from(LabEntries.class);
			entryQuery.select(entryRoot.get(LabEntries_.labEntriesTestId));
			entryQuery.where(entry.and(entryRoot.get(LabEntries_.labEntriesTestdetailId).in(em.createQuery(orderQuery).getResultList())), entry.equal(entryRoot.get(LabEntries_.labEntriesEncounterId), encounterId));

			CriteriaBuilder externalMapping = em.getCriteriaBuilder();
			CriteriaQuery<Object> mappingQuery = externalMapping.createQuery();
			Root<Hl7ExternalTestmapping> mappingRoot = mappingQuery.from(Hl7ExternalTestmapping.class);
			mappingQuery.select(mappingRoot.get(Hl7ExternalTestmapping_.hl7ExternalTestmappingExternaltestid));
			mappingQuery.where(mappingRoot.get(Hl7ExternalTestmapping_.hl7ExternalTestmappingLocaltestid).in(em.createQuery(entryQuery).getResultList()));

			CriteriaBuilder codeBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Object> codeQuery = codeBuilder.createQuery();
			Root<Hl7ExternalTest> codeRoot = codeQuery.from(Hl7ExternalTest.class);
			codeQuery.select(codeRoot.get(Hl7ExternalTest_.hl7ExternalTestCode));
			codeQuery.where(codeRoot.get(Hl7ExternalTest_.hl7ExternalTestId).in(em.createQuery(mappingQuery).getResultList()));
			List<Object> codeList = em.createQuery(codeQuery).getResultList();
			Integer externalTestId = -1;
			for (int i = 0; i < testDetailIdList.size() ; i++) {
				if( testCode.equals(codeList.get(i))) {
					externalTestId = Integer.parseInt("" + testDetailIdList.get(i));
				}
			}		
			return externalTestId;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	private String testIdFromUnmappedResults(String accountNo, String orderedDate, String labAccessionNo, String testCode) {
		String testId = "-1";
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7Unmappedresults> root = cq.from(Hl7Unmappedresults.class);
			Join<Hl7Unmappedresults, Hl7ResultInbox> join = root.join(Hl7Unmappedresults_.hl7ResultInbox, JoinType.INNER);
			cq.select(root.get(Hl7Unmappedresults_.hl7UnmappedresultsTestdetailId));
			cq.where(builder.and(builder.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsAccountno), accountNo),builder.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsOrdDate), Timestamp.valueOf(orderedDate)),
					builder.equal(join.get(Hl7ResultInbox_.hl7ResultInboxLabaccessionno), labAccessionNo), builder.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsExtTestcode), testCode)));
			cq.orderBy(builder.desc(root.get(Hl7Unmappedresults_.hl7UnmappedresultsTestdetailId)));
			testId = "" + em.createQuery(cq).getFirstResult();
			return testId;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Method to get test id
	 * @param chartId
	 * @param testDetailId
	 * @return testId
	 */
	public Integer getLabTestId(Integer chartId, String testDetailId) {
		int testId = -1;
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<LabEntries> root = cq.from(LabEntries.class);
			root.join(LabEntries_.encounter, JoinType.INNER);
			cq.select(root.get(LabEntries_.labEntriesTestId));
			cq.where(builder.and(builder.equal(root.get(LabEntries_.labEntriesTestdetailId), testDetailId),
					builder.equal(root.get(LabEntries_.labEntriesChartid), chartId)));
			testId = em.createQuery(cq).getFirstResult();
			return testId;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}


	/**
	 * Method to insert or update config table
	 * @param chartId
	 * @param testName
	 * @param testDetailId
	 * @param testCode
	 * @param labcompId
	 * @throws Exception
	 */
	@Override
	public void insertConfigTbl(String chartId,String testName,String testDetailId,String testCode,int labcompId) throws Exception {
		int localTestId = getLabTestId(Integer.parseInt(chartId),testDetailId);
		String externalTestCode =  HUtil.Nz(getHl7externalTestCode(labcompId, testCode, testName),"-1");
		if(externalTestCode.equalsIgnoreCase("-1")) {
			testCode = testCode.replaceAll("[a-zA-Z]", "").replaceAll("=", "");
			externalTestCode =  HUtil.Nz(getHl7externalTestCode(labcompId, testCode, testName),"-1");
		}
		if(externalTestCode.equalsIgnoreCase("-1")) {
			Hl7ExternalTest externalTest = new Hl7ExternalTest();
			externalTest.setHl7ExternalTestLabcompanyid(labcompId);
			externalTest.setHl7ExternalTestIsactive(true);
			externalTest.setHl7ExternalTestCode(testCode);
			externalTest.setHl7ExternalTestName(testName);
			externalTestRepository.save(externalTest);
		} else {
			List<Hl7ExternalTest> externalTestList = externalTestRepository.findAll(LabResultsSpecification.checkExternalTestName(testName, labcompId));
			for( Hl7ExternalTest externalTest : externalTestList ) {
				externalTest.setHl7ExternalTestIsactive(true);
				externalTest.setHl7ExternalTestCode(testCode);
				externalTestRepository.saveAndFlush(externalTest);
			}
		}
		int externalTestMapId =  Integer.parseInt(HUtil.Nz(getHl7externalTestId(labcompId, testCode),"-1"));
		if(externalTestMapId==-1) {
			testCode = testCode.replaceAll("[a-zA-Z]", "").replaceAll("=", "");
			externalTestMapId =  Integer.parseInt(HUtil.Nz(getHl7externalTestId(labcompId, testCode),"-1"));	
		}
		if(externalTestMapId != -1) {
			String islocalTestId = HUtil.Nz(localTestId,"-1");	
			if(islocalTestId.equalsIgnoreCase("-1") && localTestId != -1 ) {
				try {
					Hl7ExternalTestmapping externalTest = new Hl7ExternalTestmapping();
					externalTest.setHl7ExternalTestmappingLocaltestid(localTestId);
					externalTest.setHl7ExternalTestmappingExternaltestid(externalTestMapId);
					externalTest.setHl7ExternalTestmappingLabcompanyid(labcompId);
					externalTestMapRepository.save(externalTest);
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					List<Hl7ExternalTestmapping> testMappingList = externalTestMapRepository.findAll(LabResultsSpecification.verifyTestIdAndCompId(localTestId, labcompId));
					for( Hl7ExternalTestmapping testMapping : testMappingList ) {
						testMapping.setHl7ExternalTestmappingExternaltestid(externalTestMapId);
						externalTestMapRepository.saveAndFlush(testMapping);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Method to import parameters from file details
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void importParameters(String chartId,String testDetailId, String paramString, Hashtable labLocCodeDetails,String rootPath, String resultFileName,int status,int orderedBy) throws NumberFormatException, Exception {
		investigationService.DeleteParameters(Integer.parseInt(testDetailId));
		Vector paramEntryIdVect = investigationService.SaveParameters(Integer.parseInt(chartId), Integer.parseInt(testDetailId), paramString,labLocCodeDetails,rootPath,resultFileName);
		investigationService.addLabAlert(Integer.parseInt(this.patientId), this.encnId, Integer.parseInt(testDetailId), status,1, orderedBy);
		for( int j = 0;j < paramEntryIdVect.size() ;j++ ) {
			int fileScanId = Integer.parseInt(HUtil.Nz(investigationService.getFileScanId(Integer.parseInt("" + paramEntryIdVect.get(j))), "-1"));
			if( fileScanId != -1 ) {
				List<FileDetails> fileList = fileDetailsRepository.findAll(InvestigationSpecification.checkFileScanId(fileScanId));
				for( FileDetails fileData : fileList ) {
					fileData.setFiledetailsEntityid(Integer.parseInt("" + paramEntryIdVect.get(j)));
					fileDetailsRepository.saveAndFlush(fileData);
				}
			}
		}
	}

	/**
	 * Method to find the encounters open
	 */
	@Override
	public Integer findEncounters(Integer chartId, String ordereddate, Integer encounterType) {
		int encounterId = -1;
		try {
			EntityManager em = emf.createEntityManager();
			try {
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<Object> cq = builder.createQuery();
				Root<Encounter> root = cq.from(Encounter.class);
				cq.select(root.get(Encounter_.encounterId));
				cq.where(builder.and(builder.equal(root.get(Encounter_.encounterChartid), chartId),
						builder.equal(root.get(Encounter_.encounterType), encounterType),
						builder.equal(root.get(Encounter_.encounterDate), Timestamp.valueOf(ordereddate))));
				cq.orderBy(builder.desc(root.get(Encounter_.encounterId)));
				List<Object> encounterIdList = em.createQuery(cq).getResultList();
				if ( encounterIdList.size() > 0 )
					encounterId = Integer.parseInt("" + encounterIdList.get(0));
			} catch(Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				em.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return encounterId;
	}

	/**
	 * Method to create new external encounter for the result
	 */
	@Override
	public Integer addNewExternalEncounter(String orderDate, Integer chartId, String userId) {
		this.userId = userId;
		if(chartId.equals("-1")) {			
			chartId = createChart();
		}
		short encounterType = 3, encounterStatus = 2, visitType = -1;
		Timestamp createdDate = new Timestamp(new Date().getTime());
		Encounter encounter = new Encounter();
		encounter.setEncounterChartid(chartId);
		encounter.setEncounterDate(Timestamp.valueOf(orderDate));
		encounter.setEncounterType(encounterType);
		encounter.setEncounterReason(26);
		encounter.setEncounterServiceDoctor(null);
		encounter.setEncounterChiefcomp("");
		encounter.setEncounterComments(""); 
		encounter.setEncounterRoom(null); 
		encounter.setEncounterStatus(encounterStatus);
		encounter.setEncounterIsportal(0);
		encounter.setEncounterSeverity(null);
		encounter.setEncounterCreatedBy(Long.parseLong(this.userId));
		encounter.setEncounterCreatedDate(createdDate);
		encounter.setEncounterDocResponse("");
		encounter.setEncounterChargeable(false);
		encounter.setEncounterCommentScribble("");
		encounter.setEncounterResponseScribble(""); 
		encounter.setEncounterInsReview(-1);
		encounter.setEncounterInsReviewComment("");
		encounter.setEncounterPos(-1);
		encounter.setEncounterRefDoctor(-1l);
		encounter.setEncounterMultireasonId("");
		encounter.setEncounterVisittype(visitType); 
		encounter.setEncounterBillingcomments("");
		encounter.setEncounterAssProvider("");
		encounter.setEncounterModifiedon(createdDate);
		encounter.setEncounterModifiedby(Integer.parseInt(this.userId));
		encounter.setEncounterRoomIsactive(false);
		encounter.setEncounterAlreadySeen(false);
		encounter.setEncounterMedReview(-1);
		encounter.setEncounterUserLoginId(-1);
		encounter.setEncounterAlertId(-1l);
		encounter.setEncounterAccessStatus(false);
		encounter.setEncounterIsinstruction(false);
		encounter.setTransitionOfCare(false);
		encounter.setSummaryOfCare(false);
		Encounter enc = encounterRepository.saveAndFlush(encounter);	
		return enc.getEncounterId();
	}

	private Integer createChart() {
		Integer chartId = -1;
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Chart> root = cq.from(Chart.class);
			cq.select(root.get(Chart_.chartId));
			cq.where(builder.and(builder.equal(root.get(Chart_.chartPatientid), this.patientId)));
			List<Object> chartIdList = em.createQuery(cq).getResultList();
			if ( chartIdList.size() > 0 )
				chartId = Integer.parseInt("" + chartIdList.get(0));
			return chartId;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public String getAccountNo(String hl7FileId, List<Integer> status) {
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7ResultInbox> root = cq.from(Hl7ResultInbox.class);
			cq.select(root.get(Hl7ResultInbox_.hl7ResultInboxAccountno));
			cq.where(builder.and(builder.equal(root.get(Hl7ResultInbox_.hl7ResultInboxId), hl7FileId),root.get(Hl7ResultInbox_.hl7ResultInboxStatus).in(status)));
			return "" + em.createQuery(cq).getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public Integer getResultStatus(String hl7FileId) {
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7ResultInbox> root = cq.from(Hl7ResultInbox.class);
			cq.select(root.get(Hl7ResultInbox_.hl7ResultInboxStatus));
			cq.where(builder.equal(root.get(Hl7ResultInbox_.hl7ResultInboxId), hl7FileId));
			return Integer.parseInt("" + em.createQuery(cq).getSingleResult());
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public String getIsReviewed(String hl7FileId) {
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7ResultInbox> root = cq.from(Hl7ResultInbox.class);
			cq.select(root.get(Hl7ResultInbox_.hl7ResultInboxReviewed));
			cq.where(builder.equal(root.get(Hl7ResultInbox_.hl7ResultInboxId), hl7FileId));
			return "" + em.createQuery(cq).getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public String getUnmappedOrderedDate(String hl7FileId) {
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7Unmappedresults> root = cq.from(Hl7Unmappedresults.class);
			cq.select(root.get(Hl7Unmappedresults_.hl7UnmappedresultsOrdDate));
			cq.where(builder.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsFilewiseId), Integer.parseInt(hl7FileId)));
			return "" + em.createQuery(cq).getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("resource")
	@Override
	public String getDataFromFile(String sharedFolderPath, String fileName) throws Exception {
		this.sharedFolderPath = sharedFolderPath;
		fileName = "PDF_" + fileName.substring(0, fileName.lastIndexOf("_"))+".txt";
		File file = new File(sharedFolderPath + "/HL7/UnAttachedDocs/" + fileName);
		char[] buffer = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			buffer = new char[(int)file.length()];
			int i = 0;
			int c = bufferedReader.read();
			while (c != -1) {
				buffer[i++] = (char)c;
				c = bufferedReader.read();
			}
		} catch (Exception e) { }
		file.delete();
		return new String(buffer);
	}

	@Override
	public void putPDFAttacmentEntry(int encounterId,int patientID,String chartId,int testDetailId,String resultFileName,String rootPath,String encryptedData)throws Exception{
		resultFileName = resultFileName.substring(0,resultFileName.lastIndexOf("_"));
		this.sharedFolderPath = rootPath;
		try{
			investigationService.decodeToPDF(encryptedData, rootPath + "/Attachments/" + patientID + "/",resultFileName);
			int categoryId = Integer.parseInt(HUtil.Nz(getCategoryId(testDetailId), "1"));
			Date date = new Date();
			FileDetails fileDetails = new FileDetails();
			fileDetails.setFiledetailsFlag(2);
			fileDetails.setFiledetailsDescription("PDF Report");
			fileDetails.setFiledetailsCreationdate(new Timestamp(date.getTime()));
			fileDetails.setFiledetailsCreatedby(-100);
			fileDetails.setFiledetailsLastmodifiedon(new Timestamp(date.getTime()));
			fileDetails.setFiledetailsLastmodifiedby(-100);
			fileDetails.setFiledetailsEncounterid(encounterId);
			fileDetails.setFiledetailsPatientid(patientID);
			fileDetails.setFiledetailsChartid(Integer.parseInt(chartId));
			fileDetails.setFiledetailsEntityid(testDetailId);
			fileDetails.setFiledetailsCategoryid(categoryId);
			fileDetailsRepository.saveAndFlush(fileDetails);
			int filedetailsId = fileDetails.getFiledetailsId();
			FileName fileName = new FileName();
			fileName.setFilenameScanid(filedetailsId);
			fileName.setFilenameName(resultFileName + ".pdf");
			fileName.setFilenameFileextension(".pdf");
			fileName.setFilenameIsactive(true);
			fileNameRepository.saveAndFlush(fileName);
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}

	private Integer getCategoryId(int testDetailId) {
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<LabEntries> root = cq.from(LabEntries.class);
			cq.select(root.get(LabEntries_.labEntriesScangroupId));
			cq.where(builder.equal(root.get(LabEntries_.labEntriesTestdetailId), testDetailId));
			return Integer.parseInt("" + em.createQuery(cq).getSingleResult());
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public void createFolder(String folderLoc) throws Exception {
		File ff = new File( folderLoc );
		if(!ff.exists()) {
			ff.mkdirs();
		}
	}

	@Override
	public boolean moveFile(String sourcePath, String desPath, String sourcefileName, boolean appendDate) throws Exception {
		this.sharedFolderPath = desPath;
		InputStream in = null;              
		OutputStream out = null;
		byte[] buf = new byte[1024];
		int len;
		File sourceFile = null;
		File destFile     = null;
		boolean fileMoved = false;
		try {
			if(appendDate) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
				desPath = desPath + "/" +sdf.format(new Date());
			}
			createFolder(desPath);
			sourceFile     = new File(sourcePath + "/" + sourcefileName);
			destFile     = new File(desPath + "/" + sourcefileName);
			in = new FileInputStream(sourceFile);
			out = new FileOutputStream(destFile);
			while((len = in.read(buf)) > 0){
				out.write(buf, 0, len);
			}
			in.close();
			in = null;
			out.close();
			out = null;
			fileMoved = deletefile(sourceFile);
		} catch( Exception e ) {
			fileMoved = false;
			e.printStackTrace();
		} finally {
			try {
				if(in != null)
					in.close();
				if(out !=null)
					out.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileMoved;
	}

	public boolean deletefile(File fileToDel) {
		boolean success = false;
		try {
			success = fileToDel.delete();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public long saveFileDetail(FileDetailBean filedetail) throws Exception {
		if ( filedetail.getId() == -1 ) {
			return createFileDetail(filedetail);
		} else {
			return updateFileDetail(filedetail);
		}		
	}

	public long createFileDetail(FileDetailBean bean) throws Exception {
		int index = bean.getFileNames().size();
		long filedetailid = -1;
		if( index > 0 ) {				
			FileDetails fileDetails = new FileDetails();
			fileDetails.setFiledetailsFlag(bean.getFlag());
			fileDetails.setFiledetailsDescription(bean.getDescription());
			fileDetails.setFiledetailsComments(bean.getComments());
			fileDetails.setFiledetailsCreationdate(new Timestamp(new Date().getTime()));
			fileDetails.setFiledetailsCreatedby(bean.getCreatedBy());
			fileDetails.setFiledetailsLastmodifiedon(new Timestamp(new Date().getTime()));
			fileDetails.setFiledetailsLastmodifiedby(bean.getModifiedBy());
			fileDetails.setFiledetailsEncounterid(bean.getEncounterId());
			fileDetails.setFiledetailsChartid(bean.getChartId());
			fileDetails.setFiledetailsPatientid(bean.getPatientId());
			fileDetails.setFiledetailsEntityid(bean.getEntityId());
			fileDetails.setFiledetailsType("" + bean.getTypeId());
			fileDetails.setFiledetailsCategoryid(bean.getCategoryId());
			fileDetails.setFiledetailsUserdate(bean.getUserdate());
			fileDetails.setFiledetailsBuffer3(Boolean.parseBoolean((bean.isBuffer3())?"true":"null"));
			fileDetails.setFiledetailsFaxreferenceid(bean.getFaxreferenceid());
			fileDetails.setFiledetailsScantype(bean.getFileScantype());
			fileDetailsRepository.saveAndFlush(fileDetails);
			int id = Integer.parseInt(HUtil.Nz(getMaxFileDetailId(bean.getCategoryId(), bean.getChartId(), bean.getPatientId(), bean.getEncounterId(), bean.getEntityId()),"-1"));					
			FileBean filebean = null;				
			for( int i = 0 ; i < index ; i++ ) {
				filebean = (FileBean)bean.getFileNames().get(i);
				filebean.setFileDetailId(id);
				createFile(filebean, (i + 1));
			}
		}	
		return filedetailid;
	}

	private int getMaxFileDetailId(int categoryId, int chartId, int patientId, int encounterId, int entityId) {
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<FileDetails> root = cq.from(FileDetails.class);
			cq.select(builder.max(root.get(FileDetails_.filedetailsId)));
			cq.where(builder.and(builder.equal(root.get(FileDetails_.filedetailsCategoryid), categoryId),
					builder.equal(root.get(FileDetails_.filedetailsChartid), chartId),
					builder.equal(root.get(FileDetails_.filedetailsEncounterid), encounterId),
					builder.equal(root.get(FileDetails_.filedetailsEntityid), entityId),
					builder.equal(root.get(FileDetails_.filedetailsPatientid), patientId)));
			return Integer.parseInt("" + em.createQuery(cq).getSingleResult());
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			em.close();
		}
	}

	public void createFile(FileBean bean, int orderby) throws Exception {		
		if( bean.isActive() ) {
			Date date = new Date();
			FileName fileName = new FileName();
			fileName.setFilenameScanid(bean.getFileDetailId());
			fileName.setFilenameName(bean.getFileName());
			fileName.setFilenameFileextension(bean.getFileExtension());
			fileName.setFilenameFilesize(Integer.parseInt("" + bean.getFileSize()));
			fileName.setFilenameCreatedon(new Timestamp(date.getTime()));
			fileName.setFilenameCreatedby(bean.getCreatedBy());
			fileName.setFilenameModifiedon(new Timestamp(date.getTime()));
			fileName.setFilenameModifiedby(bean.getModifiedBy());
			fileName.setFilenameIsactive(true);
			fileName.setFilenameOrderby(orderby);
			fileNameRepository.saveAndFlush(fileName);
		}
	}

	public long updateFileDetail(FileDetailBean bean) throws Exception {
		long filedetailid = -1;
		try {
			filedetailid = bean.getId();
			if( bean.isActive() ) {					
				int index = bean.getFileNames().size();
				if( index > 0 ) {
					List<FileDetails> fileDetailsList = fileDetailsRepository.findAll(LabResultsSpecification.verifyFileDetailId(bean.getId()));
					for( FileDetails fileData : fileDetailsList ) {
						fileData.setFiledetailsDescription(bean.getDescription());
						fileData.setFiledetailsComments(bean.getComments());
						fileData.setFiledetailsUserdate(bean.getUserdate());
						fileData.setFiledetailsLastmodifiedon(new Timestamp(new Date().getTime()));
						fileData.setFiledetailsLastmodifiedby(bean.getModifiedBy());
						fileDetailsRepository.saveAndFlush(fileData);
					}
					List<FileName> fileNameList = fileNameRepository.findAll(LabResultsSpecification.checkScanId(bean.getId()));
					for( FileName nameData : fileNameList ) {
						nameData.setFilenameIsactive(false);
						fileNameRepository.saveAndFlush(nameData);
					}   
					FileBean filebean = null;
					for( int i = 0 ; i < index ; i++ ) {
						filebean = (FileBean)bean.getFileNames().get(i);
						updateFile(filebean, bean.getPatientId(), (i + 1));
					}
					int deletedCount = Integer.parseInt(HUtil.Nz(getFileCount(bean.getId()),"-1"));
					List<FileName> deleteList = fileNameRepository.findAll(Specifications.where(LabResultsSpecification.checkScanId(bean.getId())).and(LabResultsSpecification.checkFileIsActive()));
					for( FileName deleteData : deleteList ) {
						fileNameRepository.delete(deleteData);
					}
					int num = Integer.parseInt(HUtil.Nz(getFileCount(bean.getId()),"-1"));
					try {
						if( deletedCount != 0 ) {
							//				    		EventLog.LogEvent(AuditTrail.GLACE_LOG,AuditTrail.ScansAndFiles,AuditTrail.VIEWED,Integer.parseInt(HUtil.Nz(session.getAttribute("parent_event").toString(),"-1")),AuditTrail.SUCCESS,deletedCount +" Files has been deleted from Group - "+bean.getId(),Integer.parseInt(HUtil.Nz(session.getAttribute("loginId").toString(),"-1")),request.getRemoteUser(),request.getRemoteAddr(),bean.getPatientId(),bean.getChartId(),-1,AuditTrail.USER_LOGIN,request,dbUtils,"Documents deleted from Group - "+bean.getId());
						}
					} catch( Exception e ) {
						e.printStackTrace();
					}
					if( num == 0 ) {
						deleteFileDetails(bean.getId());
					}				
				} else if( index == 0 ) {
					deleteFileName(bean.getId());
					deleteFileDetails(bean.getId());
				}
			} else {
				deleteFileName(bean.getId());
				deleteFileDetails(bean.getId());				
			}
		} catch( Exception e ) {
			throw e;
		}	
		return filedetailid;
	}

	private void deleteFileName(int id) {
		List<FileName> fileNameList = fileNameRepository.findAll(LabResultsSpecification.checkScanId(id));
		for( FileName nameData : fileNameList ) {
			fileNameRepository.delete(nameData);
		}
	}

	private void deleteFileDetails(int id) {
		List<FileDetails> deleteFileList = fileDetailsRepository.findAll(LabResultsSpecification.verifyFileDetailId(id));
		for( FileDetails deleteFileDetails : deleteFileList ) {
			fileDetailsRepository.delete(deleteFileDetails);
		}
	}
	private int getFileCount(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<FileName> root = cq.from(FileName.class);
			cq.select(builder.count(root.get(FileName_.filenameId)));
			cq.where(builder.and(builder.equal(root.get(FileName_.filenameScanid), id),
					builder.equal(root.get(FileName_.filenameIsactive), false)));
			return Integer.parseInt("" + em.createQuery(cq).getSingleResult());
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			em.close();
		}
	}

	private int getFileNameId(String name, int id) {
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<FileName> root = cq.from(FileName.class);
			cq.select(root.get(FileName_.filenameId));
			cq.where(builder.and(builder.equal(root.get(FileName_.filenameScanid), id),
					builder.equal(root.get(FileName_.filenameName), name)));
			return Integer.parseInt("" + em.createQuery(cq).getSingleResult());
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			em.close();
		}
	}

	public void updateFile(FileBean bean, int patid, int orderby) throws Exception {
		if( bean.isActive() ) {
			String id = HUtil.Nz(getFileNameId(bean.getFileName(), bean.getFileDetailId()),"-1");
			if( id.equals("-1") ) {
				Date date = new Date();
				FileName fileName = new FileName();
				fileName.setFilenameScanid(bean.getFileDetailId());
				fileName.setFilenameName(bean.getFileName());
				fileName.setFilenameFileextension(bean.getFileExtension());
				fileName.setFilenameFilesize(Integer.parseInt("" + bean.getFileSize()));
				fileName.setFilenameCreatedon(new Timestamp(date.getTime()));
				fileName.setFilenameCreatedby(bean.getCreatedBy());
				fileName.setFilenameModifiedon(new Timestamp(date.getTime()));
				fileName.setFilenameModifiedby(bean.getModifiedBy());
				fileName.setFilenameIsactive(true);
				fileName.setFilenameOrderby(orderby);
				fileNameRepository.saveAndFlush(fileName);
			} else {
				if( bean.getFileName().indexOf(".tif") != -1 ) {
					String fname = bean.getFileName();
					fname = fname.substring(0,fname.indexOf(".tif"));
					fname = fname + ".png";
					String path = this.sharedFolderPath + "/patientinfo/" + patid + "/" + fname;
					File file = new File(path);
					List<FileName> fileNameList = fileNameRepository.findAll(Specifications.where(LabResultsSpecification.checkScanId(bean.getFileDetailId())).and(LabResultsSpecification.checkFileName(bean.getFileName())));
					if( file.exists() ) {
						for( FileName fileName : fileNameList ) {
							Timestamp timeStamp = new Timestamp(new Date().getTime());
							fileName.setFilenameModifiedon(timeStamp);
							fileName.setFilenameModifiedby(bean.getModifiedBy());
							fileName.setFilenameIsactive(true);
							fileName.setFilenameOrderby(orderby);
							fileName.setFilenameName(fname);
							fileNameRepository.saveAndFlush(fileName);
						}
					} else {
						for( FileName fileName : fileNameList ) {
							Timestamp timeStamp = new Timestamp(new Date().getTime());
							fileName.setFilenameModifiedon(timeStamp);
							fileName.setFilenameModifiedby(bean.getModifiedBy());
							fileName.setFilenameIsactive(false);
							fileName.setFilenameOrderby(orderby);
							fileNameRepository.saveAndFlush(fileName);
						}
					}
					file = null;
					fname = null;
				} else {
					List<FileName> fileNameList = fileNameRepository.findAll(Specifications.where(LabResultsSpecification.checkScanId(bean.getFileDetailId())).and(LabResultsSpecification.checkFileName(bean.getFileName())));
					for( FileName fileName : fileNameList ) {
						Timestamp timeStamp = new Timestamp(new Date().getTime());
						fileName.setFilenameModifiedon(timeStamp);
						fileName.setFilenameModifiedby(bean.getModifiedBy());
						fileName.setFilenameIsactive(true);
						fileName.setFilenameOrderby(orderby);
						fileNameRepository.saveAndFlush(fileName);
					}
				}				
			}
		}
	}
}
