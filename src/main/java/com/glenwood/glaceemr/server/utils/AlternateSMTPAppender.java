package com.glenwood.glaceemr.server.utils;

import java.util.Date;

import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.net.SMTPAppender;
import org.apache.log4j.spi.LoggingEvent;
import org.springframework.beans.factory.annotation.Autowired;

public class AlternateSMTPAppender extends SMTPAppender{

	@Autowired
	SessionMap sessionMap;
	
	@Override
	 protected void sendBuffer() {

	  // Note: this code already owns the monitor for this
	  // appender. This frees us from needing to synchronize on 'cb'.
	  try {
	   MimeBodyPart part = new MimeBodyPart();

	   StringBuffer sbuf = new StringBuffer();
	   String t = layout.getHeader();
	   if (t != null)
	    sbuf.append(t);
	   int len = cb.length();
	   for (int i = 0; i < len; i++) {
	    // sbuf.append(MimeUtility.encodeText(layout.format(cb.get())));
	    LoggingEvent event = cb.get();

	    // setting the subject
	    if (i==0) {
	     Layout subjectLayout = new PatternLayout(getSubject());
	     StringBuffer acutalsubject=new StringBuffer();
	    final String Subject[]=MimeUtility.encodeText(subjectLayout.format(event), "UTF-8", null).split("]");
	     for(int z=0;z<Subject.length;z++)
	     {
	    	 if(z==0){
	    		 acutalsubject.append(Subject[0].substring(1,Subject[0].length())+": ");
	    	 }
	    	 if(z==1){
	    		 acutalsubject.append(Subject[1].substring(1, Subject[1].length())+"");
	    	 }
	    	 if(z==2){
	    		 acutalsubject.append(" on ");
	    		 acutalsubject.append(Subject[2].substring(1, Subject[2].length())+"");
	    	 }
	     }
	     msg.setSubject(acutalsubject+"");
	    }

	    sbuf.append(layout.format(event));
	    if (layout.ignoresThrowable()) {
	     String[] s = event.getThrowableStrRep();
	     if (s != null) {
	      for (int j = 0; j < s.length; j++) {
	       sbuf.append(s[j]);
	       sbuf.append(Layout.LINE_SEP);
	      }
	     }
	    }
	   }
	   t = layout.getFooter();
	   if (t != null)
	    sbuf.append(t);
	   part.setContent(sbuf.toString(), layout.getContentType());

	   Multipart mp = new MimeMultipart();
	   mp.addBodyPart(part);
	   msg.setContent(mp);

	   msg.setSentDate(new Date());
//	     msg.setSubject("Exception Raised For AccountId : 2222 Dated On :"+new Date());
//	   System.out.println("msg>>>"+msg.getSubject()+"<<<todays date>>>>"+msg.getSentDate()+"<<<with new date()>>>>"+new Date());
	   Transport.send(msg);
	   
	  } catch (Exception e) {
	   LogLog.error("Error occured while sending e-mail notification.", e);
	  }
	 }}
