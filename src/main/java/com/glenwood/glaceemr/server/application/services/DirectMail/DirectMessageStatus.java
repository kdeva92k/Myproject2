package com.glenwood.glaceemr.server.application.services.DirectMail;

public class DirectMessageStatus {
	private int statusCode;
    private String statusLabel = new String();
    private String statusMessage = new String();
    private String messageId = new String();

    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getStatusLabel() {
        return statusLabel;
    }
    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
    }
    public String getStatusMessage() {
        return statusMessage;
    }
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    public String getMessageId() {
        return messageId;
    }
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }


}
