package com.glenwood.glaceemr.server.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
public class HtmlTransformer{
	private DocumentBuilderFactory factory = null;
	private DocumentBuilder	builder = null;
	private Document document = null;
	private NodeList bills = null;
	private int billsCount = 0;
	private int currentBill	= 0;
	private StringBuffer xslData = new StringBuffer("");
	private StringBuffer xmlData = new StringBuffer("");
	private InputStream xslStream = null;
	private Source xslIn = new StreamSource();
	private Source xmlIn = new StreamSource();
	private String outputMethod = "html";
	private HttpServletRequest request = null;
	private String xslpath = "";

	public HtmlTransformer(){
	}


	/**
	 * @return Returns the outputMethod.
	 */
	public String getOutputMethod() {
		return outputMethod;
	}
	/**
	 * @param outputMethod The outputMethod to set.
	 */
	public void setOutputMethod(String outputMethod) {
		this.outputMethod = outputMethod;
	}
	/**
	 * @return Returns the xmlData.
	 */
	public StringBuffer getXmlData() {
		return xmlData;
	}
	/**
	 * @param xmlData The xmlData to set.
	 */
	public void setXmlData(StringBuffer xmlData) {
		this.xmlData = xmlData;
		setXmlIn(new StreamSource(xmlData.toString()));
	}
	/**
	 * @return Returns the xmlIn.
	 */
	public Source getXmlIn() {
		return xmlIn;
	}
	/**
	 * @param xmlIn The xmlIn to set.
	 */
	public void setXmlIn(Source xmlSource) {
		this.xmlIn = xmlSource;
	}
	/**
	 * @return Returns the xslData.
	 */
	public StringBuffer getXslData() {
		return xslData;
	}
	/**
	 * @param xslData The xslData to set.
	 */
	public void setXslData(StringBuffer xslData) {
		this.xslData = xslData;
		setXslIn(new StreamSource(xslData.toString()));
	}
	/**
	 * @return Returns the xslIn.
	 */
	public Source getXslIn() {
		return xslIn;
	}
	/**
	 * @param xslIn The xslIn to set.
	 */

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletRequest getRequest() {
		return this.request;
	}


	public void setXslIn(Source xslIn) {
		this.xslIn = xslIn;
	}
	public void setXslData1(String xslPath){
		try{
			this.xslpath = xslPath;
			xslData=new StringBuffer();
			URL url	= new URL("file:///" + this.getRequest().getSession().getServletContext().getRealPath(xslPath));
			
			InputStreamReader fhWnd	= new InputStreamReader(url.openStream());
			int retVal	= -1;
			do{
				char[] data = new char[100];
				retVal = fhWnd.read(data,0,100);
				if(retVal==-1)
					break;
				xslData.append(data,0,retVal);
			}while(true);
			fhWnd.close();
			fhWnd = null;
//			GlaceOutLoggingStream.console("xslData.toString() =" + xslData.toString());
			setXslIn(new StreamSource(new java.io.StringReader(xslData.toString())));
		}catch(java.io.FileNotFoundException e){
//			GlaceOutLoggingStream.console("Exception Occured billPreview.readXSL()	:	"+e.getMessage());
		}catch(java.io.IOException e){
//			GlaceOutLoggingStream.console("Exception Occured billPreview.readXSL()	:	"+e.getMessage());
		}

	}
	
	public void setXslData(String fileName) {

		StringBuilder result = new StringBuilder("");

		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("result::::::::::::::"+result);
		this.xslData=new StringBuffer();
		this.xslData.append(result.toString());
		setXslIn(new StreamSource(new java.io.StringReader(xslData.toString())));
			
		
	  }

	public void setXslRealData(String xslPath){
		try{
			this.xslpath = xslPath;
			xslData=new StringBuffer();
			FileInputStream inputStream=new FileInputStream(xslPath);
			InputStreamReader fhWnd	= new InputStreamReader(inputStream);
			int retVal	= -1;
			do{
				char[] data = new char[100];
				retVal = fhWnd.read(data,0,100);
				if(retVal==-1)
					break;
				xslData.append(data,0,retVal);
			}while(true);
			fhWnd.close();
			fhWnd = null;
//			GlaceOutLoggingStream.console("xslData.toString() =" + xslData.toString());
			setXslIn(new StreamSource(new java.io.StringReader(xslData.toString())));
		}catch(java.io.FileNotFoundException e){
//			GlaceOutLoggingStream.console("Exception Occured billPreview.readXSL()	:	"+e.getMessage());
		}catch(java.io.IOException e){
//			GlaceOutLoggingStream.console("Exception Occured billPreview.readXSL()	:	"+e.getMessage());
		}

	}

	public void setXmlData(String xmlPath)throws Exception{
		try{
			URL url	= new URL(xmlPath);
			InputStreamReader fhWnd	= new InputStreamReader(url.openStream());
			int retVal	= -1;
			do{
				char[] data = new char[100];
				retVal = fhWnd.read(data,0,100);
				if(retVal==-1)
					break;
				xmlData.append(data,0,retVal);
			}while(true);
			fhWnd.close();
			fhWnd = null;
//			GlaceOutLoggingStream.console("xmlData.toString() =" + xmlData.toString());
			setXmlIn(new StreamSource(new java.io.StringReader(xmlData.toString())));
		}catch(java.io.FileNotFoundException e){
			throw e;
		}catch(java.io.IOException e){
			throw e;
		}
	}

	public void setXmlRealData(String xmlPath)throws Exception{
		try{

			FileInputStream inputStream=new FileInputStream(xmlPath);
			InputStreamReader fhWnd	= new InputStreamReader(inputStream);
			int retVal	= -1;
			do{
				char[] data = new char[100];
				retVal = fhWnd.read(data,0,100);
				if(retVal==-1)
					break;
				xmlData.append(data,0,retVal);
			}while(true);
			fhWnd.close();
			fhWnd = null;
//			GlaceOutLoggingStream.console("xmlData.toString() =" + xmlData.toString());
			setXmlIn(new StreamSource(new java.io.StringReader(xmlData.toString())));
		}catch(java.io.FileNotFoundException e){
			throw e;
		}catch(java.io.IOException e){
			throw e;
		}
	}

	public void setXmlString(String xmlString)throws Exception{
		setXmlIn(new StreamSource(new java.io.StringReader(xmlString)));
	}

	public String transform()throws Exception{
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			factory.setURIResolver(createURIResolver());
			Templates template = factory.newTemplates(xslIn);
			Transformer xformer = template.newTransformer();
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			OutputStream hndWrite =	byteStream;
			Result result =	new javax.xml.transform.stream.StreamResult(hndWrite);
			xformer.setOutputProperty(OutputKeys.METHOD,getOutputMethod());
			xformer.transform(xmlIn, result);
			factory	= null;
			template = null;
			xformer	= null;
			
			return byteStream.toString();

		}catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}catch (TransformerException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "";
   }

    protected URIResolver createURIResolver(){
        return new URIResolver() {

            public Source resolve(String href, String base) throws TransformerException
            {
                return getSource(href);
            }
        };
    }

    public Source getSource(Object source){
    	try{
    		URL url;
    		url = this.createURL((String)source,this.request);
    		return new StreamSource(url.openStream(), url.toExternalForm());
    	}catch(Exception e){

    	}
    	return new SAXSource();
	}

	public URL createURL(String uri, HttpServletRequest request)throws MalformedURLException{
		if(uri.indexOf(":") >= 0)
		    return new URL(uri);
		else
		    return this.getResourceURL(uri, request);
	}

    public URL getResourceURL(String uri, HttpServletRequest request)throws MalformedURLException
    {
    	if(uri.charAt(0) != '/')
        {
            String path = this.xslpath;
            if(path.length() > 0)
            {
                int index = path.lastIndexOf('/');
                if(index >= 0)
                {
                    String prefix = path.substring(0, index + 1);
                    uri = prefix + uri;
                }
            }
        }
        return request.getSession().getServletContext().getResource(uri);
    }

}