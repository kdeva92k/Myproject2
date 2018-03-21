package com.glenwood.glaceemr.server.application.services.fax;

public class FaxFolderBean {

	long totalInbox;
	long unreadInbox;
	long totalSaved;
	long unreadSaved;
	long totalTrash;
	long unreadTrash;
	long outboxFax;
	long sentFax;
	long MytotalInbox;
	long MyunreadInbox;
	long MytotalSaved;
	long MyunreadSaved;
	long MytotalTrash;
	long MyunreadTrash;
	long MyoutboxFax;
	long MysentFax;
	
	public FaxFolderBean(long totalInbox, long unreadInbox, long totalSaved, long unreadSaved, long totalTrash,
			long unreadTrash, long outboxFax, long sentFax, long mytotalInbox, long myunreadInbox, long mytotalSaved,
			long myunreadSaved, long mytotalTrash, long myunreadTrash, long myoutboxFax, long mysentFax) {
		super();
		this.totalInbox = totalInbox;
		this.unreadInbox = unreadInbox;
		this.totalSaved = totalSaved;
		this.unreadSaved = unreadSaved;
		this.totalTrash = totalTrash;
		this.unreadTrash = unreadTrash;
		this.outboxFax = outboxFax;
		this.sentFax = sentFax;
		MytotalInbox = mytotalInbox;
		MyunreadInbox = myunreadInbox;
		MytotalSaved = mytotalSaved;
		MyunreadSaved = myunreadSaved;
		MytotalTrash = mytotalTrash;
		MyunreadTrash = myunreadTrash;
		MyoutboxFax = myoutboxFax;
		MysentFax = mysentFax;
	}
	
	public FaxFolderBean() {
	}

	public long getTotalInbox() {
		return totalInbox;
	}

	public void setTotalInbox(long totalInbox) {
		this.totalInbox = totalInbox;
	}

	public long getUnreadInbox() {
		return unreadInbox;
	}

	public void setUnreadInbox(long unreadInbox) {
		this.unreadInbox = unreadInbox;
	}

	public long getTotalSaved() {
		return totalSaved;
	}

	public void setTotalSaved(long totalSaved) {
		this.totalSaved = totalSaved;
	}

	public long getUnreadSaved() {
		return unreadSaved;
	}

	public void setUnreadSaved(long unreadSaved) {
		this.unreadSaved = unreadSaved;
	}

	public long getTotalTrash() {
		return totalTrash;
	}

	public void setTotalTrash(long totalTrash) {
		this.totalTrash = totalTrash;
	}

	public long getUnreadTrash() {
		return unreadTrash;
	}

	public void setUnreadTrash(long unreadTrash) {
		this.unreadTrash = unreadTrash;
	}

	public long getOutboxFax() {
		return outboxFax;
	}

	public void setOutboxFax(long outboxFax) {
		this.outboxFax = outboxFax;
	}

	public long getSentFax() {
		return sentFax;
	}

	public void setSentFax(long sentFax) {
		this.sentFax = sentFax;
	}

	public long getMytotalInbox() {
		return MytotalInbox;
	}

	public void setMytotalInbox(long mytotalInbox) {
		MytotalInbox = mytotalInbox;
	}

	public long getMyunreadInbox() {
		return MyunreadInbox;
	}

	public void setMyunreadInbox(long myunreadInbox) {
		MyunreadInbox = myunreadInbox;
	}

	public long getMytotalSaved() {
		return MytotalSaved;
	}

	public void setMytotalSaved(long mytotalSaved) {
		MytotalSaved = mytotalSaved;
	}

	public long getMyunreadSaved() {
		return MyunreadSaved;
	}

	public void setMyunreadSaved(long myunreadSaved) {
		MyunreadSaved = myunreadSaved;
	}

	public long getMytotalTrash() {
		return MytotalTrash;
	}

	public void setMytotalTrash(long mytotalTrash) {
		MytotalTrash = mytotalTrash;
	}

	public long getMyunreadTrash() {
		return MyunreadTrash;
	}

	public void setMyunreadTrash(long myunreadTrash) {
		MyunreadTrash = myunreadTrash;
	}

	public long getMyoutboxFax() {
		return MyoutboxFax;
	}

	public void setMyoutboxFax(long myoutboxFax) {
		MyoutboxFax = myoutboxFax;
	}

	public long getMysentFax() {
		return MysentFax;
	}

	public void setMysentFax(long mysentFax) {
		MysentFax = mysentFax;
	}

}	