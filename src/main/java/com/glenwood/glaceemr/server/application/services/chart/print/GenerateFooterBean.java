package com.glenwood.glaceemr.server.application.services.chart.print;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glenwood.glaceemr.server.application.models.print.GenericLetterFooter;
import com.glenwood.glaceemr.server.application.services.chart.print.genericfooter.GenericFooterService;

@Component
public class GenerateFooterBean {
	
	@Autowired
	GenericFooterService genericFooterService;
	
	public int getPageFormatForFooter(int footerId){
		GenericLetterFooter letterFooter=genericFooterService.getGenericFooter(footerId);
		int pageFormat=letterFooter.getGenericLetterFooterVariant();
		return pageFormat;
	}
	
	public String generateFooter(int footerId){
		GenericLetterFooter letterFooter=genericFooterService.getGenericFooter(footerId);
		StringBuilder footerHTML=new StringBuilder();
		StringBuilder style=new StringBuilder();
		String styleData=letterFooter.getGenericLetterFooterStyle();
		if(!styleData.equals("")){
			
			String[] styleDataArr=styleData.split("#~#");
			if(styleDataArr.length==6){
			
				if(styleDataArr[0].equals("1")){
					style.append("font-weight:bold;");
				}
				if(styleDataArr[1].equals("1")){
					style.append("font-style:italic;");
				}
				if(styleDataArr[2].equals("1")){
					style.append("text-decoration:underline;");
					
				}
				style.append("font-family:"+styleDataArr[3]+";");
				style.append("font-size:"+styleDataArr[4]+";");
				style.append("color:#"+styleDataArr[5]+";");
				
			}
		}
		footerHTML.append("<table width='100%'>");
		String customText=letterFooter.getGenericLetterFooterCustom().replace("\n", "<br/>");
		if(letterFooter.getGenericLetterFooterVariant()==1){
			footerHTML.append("<tr><td align='right' style='"+style+"'>"+customText+"</td></tr>");
		}else if(letterFooter.getGenericLetterFooterVariant()==3){
			footerHTML.append("<tr><td align='left' style='"+style+"'>"+customText+"</td></tr>");
		}else if(letterFooter.getGenericLetterFooterVariant()==2){
			footerHTML.append("<tr><td align='center'  style='"+style+"'>"+customText+"</td></tr>");
		}else if(letterFooter.getGenericLetterFooterVariant()==4){
			footerHTML.append("<tr><td align='right' style='"+style+"'>"+customText+"</td></tr>");
		}else if(letterFooter.getGenericLetterFooterVariant()==6){
			footerHTML.append("<tr><td align='left' style='"+style+"'>"+customText+"</td></tr>");
		}else if(letterFooter.getGenericLetterFooterVariant()==5){
			footerHTML.append("<tr><td align='center'  style='"+style+"'>"+customText+"</td></tr>");
		}else if(letterFooter.getGenericLetterFooterVariant()==7){
			footerHTML.append("<tr><td align='right' style='"+style+"'>"+customText+"</td></tr>");
		}else if(letterFooter.getGenericLetterFooterVariant()==9){
			footerHTML.append("<tr><td align='left' style='"+style+"'>"+customText+"</td></tr>");
		}else if(letterFooter.getGenericLetterFooterVariant()==8){
			footerHTML.append("<tr><td align='center'  style='"+style+"'>"+customText+"</td></tr>");
		}else{
			footerHTML.append("<tr><td style='"+style+"'>"+customText+"</td></tr>");
		}
		footerHTML.append("</table>");
		
		return footerHTML.toString();
	}
}
