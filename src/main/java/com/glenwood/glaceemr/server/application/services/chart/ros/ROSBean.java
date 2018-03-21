package com.glenwood.glaceemr.server.application.services.chart.ros;

import java.util.LinkedHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="request",proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ROSBean{
	private LinkedHashMap<Integer, ROSSystemBean> ROSBean;
	
	ROSBean(){
		ROSBean = new LinkedHashMap<Integer, ROSSystemBean>();
	}
	
	public LinkedHashMap<Integer, ROSSystemBean> getROS(){
		return this.ROSBean;
	}
	
}