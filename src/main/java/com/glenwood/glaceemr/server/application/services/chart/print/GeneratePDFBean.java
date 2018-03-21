package com.glenwood.glaceemr.server.application.services.chart.print;


import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;


/*To generate PDF file based on HTML inputs given*/
@Component
public class GeneratePDFBean {

	//To generate PDF
	public void generatePDF(String filename,String headerHTML,String patientHeaderPage1HTML,String patientHeaderHTML,
			String footerHTML,String leftHeader,String contentHTML,int pageVariant,Rectangle pdfPageSize,int pageOrientation,int headerRowCount) throws DocumentException, IOException{

		Document  document=  new Document();
		document.setMargins(50, 45, 20, 60);
		document.setMarginMirroring(false);
		FileOutputStream file= new FileOutputStream(filename+".pdf");
		PdfWriter writer= PdfWriter.getInstance(document,file);

		//To set header and footer for pages
		HTMLHeaderFooter header=new HTMLHeaderFooter(patientHeaderHTML, footerHTML,pageVariant,pageOrientation,pdfPageSize,headerRowCount);

		writer.setPageEvent(header);

		//Set page orientation
		if(pageOrientation==1){
			document.setPageSize(pdfPageSize.rotate());
		}else{
			document.setPageSize(pdfPageSize);
		}

		ElementList headerEList=XMLWorkerHelper.parseToElementList(headerHTML, null);
		ElementList pheaderEList=XMLWorkerHelper.parseToElementList(patientHeaderPage1HTML, null);
		try{
		document.open();

		if(writer.getPageNumber()==1){
			for (Element e : headerEList) {
				document.add(e);
			}
			for (Element e : pheaderEList) {
				document.add(e);
			}
		}

		org.jsoup.nodes.Document xhtmldoc = Jsoup.parseBodyFragment(contentHTML);
		xhtmldoc.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml); //This will ensure the validity
		xhtmldoc.outputSettings().charset("UTF-8");
		contentHTML=xhtmldoc.toString();
		
		if(!leftHeader.equals("")){

			PdfPTable table=new PdfPTable(2);
			table.setWidthPercentage(100);
			table.setWidths(new int[]{1,4});
			table.setSplitLate(false);
			PdfPCell cell= new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			ElementList leftHeaderEL=XMLWorkerHelper.parseToElementList(leftHeader, null);
			for (Element e : leftHeaderEL) {
				cell.addElement(e);
			}
			table.addCell(cell);
			cell= new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			ElementList content=XMLWorkerHelper.parseToElementList(contentHTML, null);
			for (Element e : content) {
				cell.addElement(e);
			}
			table.addCell(cell);
			document.add(table);
			
		}else{
			ElementList content=XMLWorkerHelper.parseToElementList(contentHTML, null);
			for (Element e : content) {
				document.add(e);
			}
		}
		document.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	
}