/*
 * 
 * Generate the pdf for patient statement preview like as Ingenix Preivew  
 * 
 * */
package com.glenwood.glaceemr.server.utils;



import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Section;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;


	public class GeneratePDF 
	{
		String tempname;
		String stmtType="";
		private String ensptstmtImage = null;
		public GeneratePDF(String stmtimage){
			this.ensptstmtImage = stmtimage;
		}
        public void setTempFileName(String fname)
        {
        	tempname = fname;
        }
        public void setStmtType(String stmtType){
        	this.stmtType = stmtType; 
        }
		public void writeFile(Hashtable statements)throws Exception   // This method is used for all type of Bill not only NN
		   {		  
			Document document = new Document(PageSize.A4, 9, -7, -5, 20);// page	// size,left,top,right,bottom
			document.setMarginMirroring(true);
			String pdfFileName=tempname;
			PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(pdfFileName));
			document.open();
			Font f=FontFactory.getFont(FontFactory.COURIER, 10, Font.NORMAL, new Color(0, 0 ,0));
			Font thead=FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new Color(0, 0 ,0));
			Font ffoo=FontFactory.getFont(FontFactory.COURIER, 9, Font.NORMAL, new Color(0, 0 ,0));
			Font fitem=FontFactory.getFont(FontFactory.COURIER,8, Font.NORMAL, new Color(0, 0 ,0));
			Font flast=FontFactory.getFont(FontFactory.COURIER,8, Font.BOLD, new Color(0, 0 ,0));
			Font fhead=FontFactory.getFont(FontFactory.COURIER,8, Font.BOLD, new Color(10, 10, 255));
			int totalSize=0;
			if(this.stmtType.equalsIgnoreCase("IM")){
				totalSize = statements.size();
			}else{
				totalSize = statements.size()/2;
			}
				for(int i=0;i<totalSize;i++)
				{					
					Hashtable stmt=(Hashtable)statements.get("stmt"+String.valueOf(i));
					String billingStatus=stmt.get("custom1").toString().trim();
					Paragraph p=new Paragraph("\n");
					Chapter chapter = new Chapter(p,1);
					chapter.setNumberDepth(0);
					Section sec=chapter.addSection(p);
					sec.add(new Paragraph("\n"));
					sec.add(new Paragraph(getSpace(8)+stmt.get("from1").toString().trim(),f));				
					sec.add(new Paragraph(getSpace(8)+stmt.get("from2").toString().trim(),f));
					sec.add(new Paragraph(getSpace(8)+stmt.get("from3").toString().trim(),f));
					sec.add(new Paragraph(getSpace(8)+stmt.get("from4").toString().trim(),f));
						
					sec.add(new Paragraph(getSpace(55)+stmt.get("stmtdate").toString().trim()+
							getSpace(5)+stmt.get("amountdue").toString().trim()+
							getSpace(10	)+stmt.get("accountno").toString().trim()
							,f));
					
					sec.add(new Paragraph(getLine(3)));
					
					Table tsp = new Table(2,4);
					tsp.setWidth(98f);
					tsp.setAlignment(0);
					tsp.setBorder(0);
					for(int row=1;row<=4;row++){
					    p=new Paragraph(getSpace(8)+stmt.get("sendto"+row).toString().trim(),f);

					    p.setAlignment(0);
					    
					    Cell csp11 = new Cell(p);
					    csp11.setBorder(0);
					    tsp.addCell(csp11);
					    
					    p=new Paragraph(stmt.get("from"+row).toString().trim(),f);
					    p.setAlignment(0);
					    
					    Cell csp12 = new Cell(p);
					    csp12.setBorder(0);
					    tsp.addCell(csp12);
					}
					sec.add(tsp);
					sec.add(new Paragraph(getLine(2)));
					Vector lineitem=null;
					if(!billingStatus.equalsIgnoreCase("intermediatestatement"))
						 lineitem=(Vector)statements.get("lineitem"+String.valueOf(i));
						
					if(billingStatus.equalsIgnoreCase("finalstatement")||billingStatus.equalsIgnoreCase("intermediatestatement"))
					{
					
						sec.add(new Paragraph(getLine(1)));
						
						p=new Paragraph(stmt.get("custom16").toString().trim(),thead);
						p.setAlignment(1);
						sec.add(p);
						sec.add(new Paragraph(getLine(2)));
						sec.add(new Paragraph("Dear Sir/Madam,".trim(),ffoo));
						sec.add(new Paragraph(getLine(1)));
						
						Table tp = new Table(1,1);
						tp.setWidth(98f);
						tp.setAlignment(0);
						tp.setBorder(0);
						
						p=new Paragraph(stmt.get("note3").toString().trim(),ffoo);
						p.setAlignment(0);
						
						Cell cc1 = new Cell(p);
						cc1.setBorder(0);
						tp.addCell(cc1);
						sec.add(tp);
						
						
						
						
						sec.add(new Paragraph(getLine(1)));
						if(!billingStatus.equalsIgnoreCase("intermediatestatement"))
						{
						Table t = new Table(4,lineitem.size());
							
							t.setWidth(98f);
							t.setAlignment	(0);
							t.setPadding(1f);
							t.setBorder(0);
							for(int l=1;l<=4;l++)
							{
								Cell c1 = new Cell(new Paragraph(stmt.get("lineitem"+String.valueOf(l) +"label").toString().trim(),fhead));
								c1.setBorderColor(new Color(212,211,212));
								c1.setBackgroundColor(new Color(212,211,212));
								t.addCell(c1);
								//t.ALIGN_RIGHT= 1;
							}
							for(int l=0;l<lineitem.size();l++)
							{	
								
								Hashtable litem = (Hashtable)lineitem.get(l);
																		
								for(int k=1;k<=litem.size();k++)
								{
									String colString="";
									if(k==3){
										colString =  "$"+litem.get("col"+String.valueOf(k)).toString().trim();
									}else{
										colString =  litem.get("col"+String.valueOf(k)).toString().trim();
									}
									Cell c2=new Cell(new Paragraph(colString,fitem));
									
									if(l%2==0)
									{	
									c2.setBackgroundColor(new Color(220,230,255));						
									c2.setBorderColor(new Color(220,230,255));
									}
									else
									{
										c2.setBackgroundColor(new Color(226,237,255));
										c2.setBorderColor(new Color(226,237,255));
									}
									t.addCell(c2);												
								}
							}
							sec.add(t);
						}
						sec.add(new Paragraph(getLine(1)));
						
						StringTokenizer str=new StringTokenizer(stmt.get("note4").toString().trim(),".,");
						p=new Paragraph(str.nextToken().trim()+"."+getLine(2),ffoo);
						p.setAlignment(0);						
						sec.add(p);
						p=new Paragraph(str.nextToken().trim()+","+getLine(1),ffoo);
						p.setAlignment(0);						
						sec.add(p);	
						p=new Paragraph(str.nextToken().trim(),ffoo);
						p.setAlignment(0);						
						sec.add(p);
						sec.add(new Paragraph(stmt.get("note1").toString().trim(),flast));
					}
					
					//   for NN,EE,NRS
	 				else {
					Table t = new Table(11,lineitem.size());
					t.setWidth(98f);
					//t.setTableFitsPage(true);
					t.setAlignment(0);
					t.setPadding(0.5f);
					//t.setBorderColor(new Color(255,255,255));
					t.setBorder(0);
		//			t.setSpacing(1);
					for(int l=1;l<=8;l++)
					{
					Cell c1 = new Cell(new Paragraph(stmt.get("lineitem"+String.valueOf(l) +"label").toString().trim(),fhead));
					if(l==2)
						c1.setColspan(3);
					if(l==3)
						c1.setColspan(2);
					c1.setBorderColor(new Color(212,211,212));
					c1.setBackgroundColor(new Color(212,211,212));
					//c1.setHeader(true);
					t.addCell(c1);
					}
									
					for(int l=0;l<lineitem.size();l++)
					{	
						//GlaceOutLoggingStream.console(lineitem.size());
						Hashtable litem = (Hashtable)lineitem.get(l);
						for(int k=1;k<=litem.size();k++)
						{
							Cell c2=new Cell(new Paragraph(litem.get("col"+String.valueOf(k)).toString().trim(),fitem));
							if(k==2)
								c2.setColspan(3);
							if(k==3)
								c2.setColspan(2);
							if(l%2==0)
							{	
							c2.setBackgroundColor(new Color(220,230,255));
							//c2.setBackgroundColor(new Color(250,250,255));
							c2.setBorderColor(new Color(220,230,255));
							}
							else
							{
								c2.setBackgroundColor(new Color(226,237,255));
								c2.setBorderColor(new Color(226,237,255));
							}
							t.addCell(c2);
							
							//bw.write(litem.get("col1").toString());
						}
					}	
					sec.add(t);
					
					
					sec.add(new Paragraph(getLine(3)));
					
					
					
					Table tt=new Table(2,1);
					
					//tt.setWidth(5f);
					tt.setBorder(1);
					tt.setAlignment(2);
					tt.setPadding(5f);
					p=new Paragraph("Please pay this amount",fitem);
					p.setAlignment(1);
				     Cell c1 = new Cell(p);
					//c1.setBorder(1);
					c1.setBorderColor(new Color(0,0,0));
					c1.setBackgroundColor(new Color(212,211,212));	
					
					tt.addCell(c1);
					c1 = new Cell(new Paragraph(stmt.get("amountdue").toString().trim(),fitem));
					c1.setBorderColor(new Color(0,0,0));
					c1.setBackgroundColor(new Color(255	,255,255));				
					tt.addCell(c1);
					//GlaceOutLoggingStream.console(stmt.get("sendto1"));
					Table foo =new Table(4,1);
					foo.setWidth(98f);
					foo.setAlignment(0);
					foo.setBorder(0);
					foo.setPadding(2f);
					if(billingStatus.equalsIgnoreCase("firststatement")||billingStatus.equalsIgnoreCase("pastdue")||billingStatus.equalsIgnoreCase("intermediatestatement"))
					{	
						c1=new Cell(new Paragraph(stmt.get("note3").toString().trim(),flast));
						c1.setBorder(0);
						//c1.setWidth(60f);
						c1.setColspan(3);
						foo.addCell(c1);
						foo.insertTable(tt);
						sec.add(foo);
					}
					else if(billingStatus.equalsIgnoreCase("noresponse"))
					{
						
						c1=new Cell(new Paragraph(getSpace(10),flast));
						//c1.add(new Paragraph(stmt.get("custom12").toString().trim(),flast));
						c1.setBorder(0);	
						//c1.setWidth(60f);
						c1.setColspan(3);
						foo.addCell(c1);					
						foo.insertTable(tt);
						sec.add(foo);
						t = new Table(1,3);
						t.setAlignment(0);
						t.setBorder(0);
						/*String inote=stmt.get("custom11").toString().trim();
						c1=new Cell(new Paragraph(inote,flast));					
						c1.setBorder(0);	
						t.addCell(c1);*/
						c1=new Cell(new Paragraph(stmt.get("custom12").toString().trim(),fitem));					
						c1.setBorder(0);	
						t.addCell(c1);
						c1=new Cell(new Paragraph(stmt.get("custom13").toString().trim(),fitem));					
						c1.setBorder(0);	
						t.addCell(c1);
						c1=new Cell(new Paragraph(stmt.get("custom14").toString().trim(),flast));					
						c1.setBorder(0);	
						t.addCell(c1);
						sec.add(t);
						
					}
					
					//foo.insertTable(tt);
					//sec.add(foo);
					
					
					tt=new Table(4,3);
					
					tt.setWidth(80f);
					tt.setAlignment(0);
					tt.setPadding(3);
					c1 = new Cell(new Paragraph(stmt.get("custom2").toString().trim(),fitem));							
					//c1.setBorderColor(new Color(212,211,212));
					//c1.setBackgroundColor(new Color(212,211,212));	
					c1.setColspan(4);
					tt.addCell(c1);
					
					c1 = new Cell(new Paragraph(stmt.get("custom3").toString().trim(),fitem));							
					//c1.setBorderColor(new Color(212,211,212));
					//c1.setBackgroundColor(new Color(212,211,212));				
					tt.addCell(c1);
					c1 = new Cell(new Paragraph(stmt.get("custom4").toString().trim(),fitem));
					//c1.setBorderColor(new Color(255,255,255));
					//c1.setBackgroundColor(new Color(255	,255,255));				
					tt.addCell(c1);				
					c1 = new Cell(new Paragraph(stmt.get("custom7").toString().trim(),fitem));
								
					//c1.setBorderColor(new Color(212,211,212));
					//c1.setBackgroundColor(new Color(212,211,212));				
					tt.addCell(c1);
					c1 = new Cell(new Paragraph(stmt.get("custom8").toString().trim(),fitem));
					//c1.setBorderColor(new Color(255,255,255));
					//c1.setBackgroundColor(new Color(255	,255,255));
					
					tt.addCell(c1);
					c1 = new Cell(new Paragraph(stmt.get("custom5").toString().trim(),fitem));							
					//c1.setBorderColor(new Color(212,211,212));
					//c1.setBackgroundColor(new Color(212,211,212));				
					tt.addCell(c1);
					c1 = new Cell(new Paragraph(stmt.get("custom6").toString().trim(),fitem));
					//c1.setBorderColor(new Color(255,255,255));
					//c1.setBackgroundColor(new Color(255	,255,255));				
					tt.addCell(c1);				
					c1 = new Cell(new Paragraph(stmt.get("custom9").toString().trim(),fitem));
									
					//c1.setBorderColor(new Color(212,211,212));
					//c1.setBackgroundColor(new Color(212,211,212));				
					tt.addCell(c1);
					c1 = new Cell(new Paragraph(stmt.get("custom10").toString().trim(),fitem));
					//c1.setBorderColor(new Color(255,255,255));
					//c1.setBackgroundColor(new Color(255	,255,255));
					
					tt.addCell(c1);
					
					

					//GlaceOutLoggingStream.console(stmt.get("custom11").toString().trim());
					
					Table billOffice = new Table(1,3);
					//billOffice.setWidth(20f);
					
					c1 = new Cell(new Paragraph(stmt.get("custom16").toString().trim().toUpperCase(),ffoo));
					c1.setBorder(0);
					billOffice.addCell(c1);
					
					c1 = new Cell(new Paragraph(stmt.get("note2").toString().trim().toUpperCase(),ffoo));
					c1.setBorder(0);
					billOffice.addCell(c1);
					
					c1 = new Cell(new Paragraph("ACCOUNT NO: "+stmt.get("accountno").toString().trim().toUpperCase(),ffoo));
					c1.setBorder(0);
					billOffice.addCell(c1);
					//GlaceOutLoggingStream.console(stmt.get("sendto1"));
					Table footTable = new Table(2,1);
					
					footTable.insertTable(tt);
					footTable.setWidth(98f);
					footTable.setAlignment(0);
					footTable.setPadding(3);
					footTable.setBorder(0);
					
					footTable.insertTable(billOffice);
					sec.add(footTable);
					
					sec.add(new Paragraph(stmt.get("note1").toString().trim(),flast));
					}	
					sec.setNumberDepth(0);
					document.add(chapter);
				}	
			 document.close();
			 writer.close();
				
		   }
		 
		 
		public void createPDF(File textFile) throws Exception
		{
			
				String pdfFontName="Arial";
				BufferedReader reader = null;
				int lineCnt = 0;
				String line = "";
				Document document = new Document(PageSize.A4, 9, -7, -5, 20);// page
				// size,left,top,right,bottom
				document.setMarginMirroring(true);
				String textFileName = textFile.getAbsolutePath();
				/*String pdfFileName = textFileName.substring(0, textFileName
						.lastIndexOf(".tmp"))
						+ ".pdf";*/
				String pdfFileName=tempname;
				PdfWriter writer = PdfWriter.getInstance(document,
						new FileOutputStream(pdfFileName));
				reader = new BufferedReader(new FileReader(textFile));
				document.open();
				
				
			
				Table t = new Table(1,3);
				
				t.setBorderColor(new Color(255, 255, 255));
				t.setPadding(5);
				t.setSpacing(5);
				t.setBorderWidth(1);
				Cell c1 = new Cell("header1");
				//c1.setBorderColor(new Color(255,255,255));
				/*c1.setHeader(true);
				t.addCell(c1);
				c1 = new Cell("Header2");
				t.addCell(c1);
				c1 = new Cell("Header3");
				t.addCell(c1);
				t.endHeaders();*/
				t.setBorder(0);
				t.addCell(c1);
			
				t.addCell(c1);

				t.addCell(c1);
				Paragraph p=new Paragraph();
				p.setAlignment(0);
				p.add(t);
				
				Chapter chapter1 = new Chapter(p,1);
				Chapter chapter2 = new Chapter(p, 2);
				chapter1.setNumberDepth(0);
				chapter2.setNumberDepth(0);
				document.add(chapter1);
				document.add(chapter2);
				document.close();
				reader.close();
				writer.close();
				//textFile.delete();
			//	return addWaterMark(pdfFileName);			
		}
		
			public void addWaterMark(String pdfFileName)throws Exception
			{
		            PdfReader reader = new PdfReader(tempname);
		            int n = reader.getNumberOfPages();
		            PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(pdfFileName));
		            int i = 0;
		            PdfContentByte under;
		            Image img = Image.getInstance(ensptstmtImage);
		            img.setAbsolutePosition(-10,-35);
		            while (i < n) {
		              i++;
		              under = stamp.getUnderContent(i);
		              under.addImage(img);
		            }
		            stamp.close();
		            reader.close();
			}
			public void delTempFile(String fname)throws Exception
			{
			  File tempfile = new File(fname);
			  if(tempfile.exists())
				  tempfile.delete();
			}
			
			public String getSpace(int no)throws Exception
			{
				String str="";
				 for(int i=0;i<no;i++)
					 str+=" ";
				return str;
			}
			public String getLine(int no)throws Exception
			{
				String str="";
				 for(int i=0;i<no;i++)
					 str+="\n";
				return str;
			}

	}

