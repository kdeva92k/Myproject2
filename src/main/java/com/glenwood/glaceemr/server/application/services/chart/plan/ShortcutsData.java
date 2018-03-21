package com.glenwood.glaceemr.server.application.services.chart.plan;

import java.util.List;

public class ShortcutsData {

	List<Data> data;
	Pages pages;
	int offset;

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}