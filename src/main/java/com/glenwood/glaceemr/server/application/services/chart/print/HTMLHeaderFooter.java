package com.glenwood.glaceemr.server.application.services.chart.print;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/**To set header and footer of PDF file */

public class HTMLHeaderFooter extends PdfPageEventHelper {

	protected ElementList footer;
	protected ElementList patientHeader;

	int pageVariant;
	int pagenumber=0;

	//Default height and width set to Letter
	float height=612;
	float width=792;

	PdfTemplate total;
	protected int rows;

	public HTMLHeaderFooter(String patientHeaderHTML, String footerHTML,
			int pageVariant,int pageOrientation,Rectangle pageSize,int headerRowCount) throws IOException {

		rows=(int)headerRowCount;
		footer = XMLWorkerHelper.parseToElementList(footerHTML, null);
		patientHeader=XMLWorkerHelper.parseToElementList(patientHeaderHTML, null);
		this.pageVariant=pageVariant;

		//Landscape or portrait
		if(pageOrientation==0){
			this.height=pageSize.getHeight();
			this.width=pageSize.getWidth();
		}else{
			this.height=pageSize.getWidth();
			this.width=pageSize.getHeight();
		}
	}

	public void onOpenDocument(PdfWriter writer, Document document) {
		total = writer.getDirectContent().createTemplate(30, 16);
	}

	public void onStartPage(PdfWriter writer, Document document) {
		pagenumber++;

		//To set margin height after printing patient header
		int top=(rows*15)+65;

		document.setMargins(50, 50, top, 60);
		document.setMarginMirroring(false);
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		try {
			Rectangle rect = new Rectangle(30, 30, width, height);

			ColumnText ct = new ColumnText(writer.getDirectContent());
			ct.setSimpleColumn(Float.parseFloat("30.0"), Float.parseFloat("30.0"), 
					width-40, height-20);

			//Adds patient header from page 2 on
			if(writer.getPageNumber()!=1){

				for (Element e : patientHeader) {
					ct.addElement(e);
				}
			}
			ct.go();

			//Adds footer to all pages
			ct.setSimpleColumn(36, 20, width-10, 50);

			for (Element e : footer) {
				ct.addElement(e);
			}
			ct.go();

			//To generate page numbers based on style selected
			if(pageVariant==1){
				ColumnText.showTextAligned(writer.getDirectContent(),
						Element.ALIGN_LEFT, new Phrase(String.format("%d", writer.getPageNumber()),new Font(Font.FontFamily.HELVETICA, 8)),
						rect.getLeft() ,
						rect.getBottom() - 18, 0);
			}else if(pageVariant==2){
				ColumnText.showTextAligned(writer.getDirectContent(),
						Element.ALIGN_CENTER, new Phrase(
								String.format("%d",  writer.getPageNumber()),new Font(Font.FontFamily.HELVETICA, 8)),
						(rect.getLeft() + rect.getRight()) / 2,
						rect.getBottom() - 18, 0);
			}else if(pageVariant==3){
				ColumnText.showTextAligned(writer.getDirectContent(),
						Element.ALIGN_RIGHT, new Phrase(
								String.format("%d", writer.getPageNumber()),new Font(Font.FontFamily.HELVETICA, 8)),
						rect.getRight()-5,
						rect.getBottom() - 18, 0);
			}else if(pageVariant==4){
				ColumnText.showTextAligned(writer.getDirectContent(),
						Element.ALIGN_LEFT, new Phrase(
								String.format("Page %d", writer.getPageNumber()),new Font(Font.FontFamily.HELVETICA, 8)),
						rect.getLeft() ,
						rect.getBottom() - 18, 0);
			}else if(pageVariant==5){
				ColumnText.showTextAligned(writer.getDirectContent(),
						Element.ALIGN_CENTER, new Phrase(
								String.format("Page %d", writer.getPageNumber()),new Font(Font.FontFamily.HELVETICA, 8)),
						(rect.getLeft() + rect.getRight()) / 2,
						rect.getBottom() - 18, 0);
			}else if(pageVariant==6){
				ColumnText.showTextAligned(writer.getDirectContent(),
						Element.ALIGN_RIGHT, new Phrase(
								String.format("Page %d", writer.getPageNumber()),new Font(Font.FontFamily.HELVETICA, 8)),
						rect.getRight()-8,
						rect.getBottom() - 18, 0);
			}else if(pageVariant==7 || pageVariant==8 || pageVariant==9){
				PdfPTable table = new PdfPTable(2);
				if(pageVariant==7){
					table.setWidths(new int[]{11,89});
				}else if(pageVariant==8){
					table.setWidths(new int[]{50,50});
				}else if(pageVariant==9){
					table.setWidths(new int[]{97,2});
				}
				table.setTotalWidth(527);
				table.setLockedWidth(true);
				table.getDefaultCell().setFixedHeight(20);
				table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber()),new Font(Font.FontFamily.HELVETICA, 8)));
				table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				PdfPCell cell = new PdfPCell(Image.getInstance(total));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				table.writeSelectedRows(0, -1, rect.getLeft()+15, rect.getBottom()+10, writer.getDirectContent());
			}

		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	public void onCloseDocument(PdfWriter writer, Document document) {

		//If Page # of # style is selected
		if(total!=null){

			ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
					new Phrase(String.valueOf(writer.getPageNumber()),new Font(Font.FontFamily.HELVETICA, 8)),
					2, 2, 0);
		}
	}


}
