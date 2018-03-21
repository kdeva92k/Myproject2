package com.glenwood.glaceemr.server.application.Bean.mailer;

public class MailerResponse {

	private String sender;

	private String[] to;

	private String[] cc; 
	
	private String[] bcc;

	private String subject;

	private String htmlbody;

	private String plaintext;

	private int mailtype;
	
	private String accountId;
	
	private String mailpassword;
	

	public MailerResponse(int mailtype,String sender,
			String[] to,String[] cc,String[] bcc,String subject,
			String htmlbody,String plaintext,String accountId,String mailpassword)
	{
		this.mailtype=mailtype;

		this.sender=sender;	

		this.to = to;

		this.cc = cc;

		this.bcc = bcc;

		this.subject=subject;

		this.htmlbody=htmlbody;

		this.plaintext=plaintext;
	    
	    this.accountId=accountId;
	    
	    this.mailpassword=mailpassword;
	
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getHtmlbody() {
		return htmlbody;
	}

	public void setHtmlbody(String htmlbody) {
		this.htmlbody = htmlbody;
	}

	public String getPlaintext() {
		return plaintext;
	}

	public void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
	}

	public int getmailtype() {
		return mailtype;
	}

	public void setmailtype(int mailtype2) {
		this.mailtype = mailtype2;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getMailpassword() {
		return mailpassword;
	}

	public void setMailpassword(String mailpassword) {
		this.mailpassword = mailpassword;
	}
	
}