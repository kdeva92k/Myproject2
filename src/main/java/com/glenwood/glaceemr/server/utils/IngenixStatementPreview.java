/*
 * parsing the Ingenix example to Hashtable
 * 
 * */

package com.glenwood.glaceemr.server.utils;


import java.io.File;
import java.util.Hashtable;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class IngenixStatementPreview 
{
	 public Hashtable parsingXML(String fname) throws Exception
	 {	 
		 Hashtable statement=new Hashtable(); 	 
		 DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		 DocumentBuilder builder = builderFactory.newDocumentBuilder();	 
		 Document doc=builder.parse(new File(fname)); 
		 NodeList stmts = doc.getElementsByTagName("statement");
		 //int flag;
		 //GlaceOutLoggingStream.console(stmts.getLength());
		 
		 for(int j=0;j<stmts.getLength();j++)
		 {
			 NodeList stmtelement=stmts.item(j).getChildNodes();
			 Hashtable st=new Hashtable();
			 for(int i=0;i<stmtelement.getLength();i++)
			 { 
				 if(stmtelement.item(i).getNodeName().equals("lineitems"))
				 {
					 NodeList lineitems=stmts.item(j).getChildNodes().item(i).getChildNodes();
					 Vector<Hashtable> lineitem=new Vector<Hashtable>();
					 for(int k=0;k<lineitems.getLength();k++)
					 {
						 NodeList lineitemelement=stmts.item(j).getChildNodes().item(i).getChildNodes().item(k).getChildNodes();
						 Hashtable litem=new Hashtable();
						 //GlaceOutLoggingStream.console(lineitemelement.getLength());
						 for(int l=0;l<lineitemelement.getLength();l++)
						 {
							 if(lineitemelement.item(l).hasChildNodes())
								 litem.put(lineitemelement.item(l).getNodeName(), lineitemelement.item(l).getFirstChild().getNodeValue());
							 else
								 litem.put(lineitemelement.item(l).getNodeName(), "");
							 //GlaceOutLoggingStream.console(lineitemelement.item(l).getNodeName()); 
							 //GlaceOutLoggingStream.console(lineitemelement.item(l).getFirstChild().getNodeValue());
						 }
						 lineitem.add(litem);
					 }
					 statement.put("lineitem"+String.valueOf(j),lineitem);
				 }	 
				 else							 
				 {
					 
					 if(stmtelement.item(i).hasChildNodes())						 
						 st.put(stmtelement.item(i).getNodeName(), stmtelement.item(i).getFirstChild().getNodeValue());
					 else
						 st.put(stmtelement.item(i).getNodeName(),"");
					 //if(stmtelement.item(i).getNodeName().equals("custom8"))
					 //GlaceOutLoggingStream.console(stmtelement.item(i).getNodeName());  
					 //GlaceOutLoggingStream.console(stmtelement.item(i).getFirstChild().getNodeValue());
				 }
		 	 
			 }	 
		 
			 statement.put("stmt"+String.valueOf(j),st);
	     	 // GlaceOutLoggingStream.console(billingstatus);
		 	}
		 	
		 return statement;	 
	 	}
	 public Hashtable singleAccPreview(Hashtable statements,String accno)throws Exception{
			
			Hashtable rst=new Hashtable();
			for(int i=0;i<statements.size()/2;i++)
			{
				Hashtable stmt=(Hashtable)statements.get("stmt"+String.valueOf(i));
				Vector lineitem=(Vector)statements.get("lineitem"+String.valueOf(i));
				if(stmt.get("accountno").toString().trim().equals(accno)){
					
					rst.put("stmt0", stmt);
					rst.put("lineitem0",lineitem);
				}
			}
			return rst;
		  }
}

