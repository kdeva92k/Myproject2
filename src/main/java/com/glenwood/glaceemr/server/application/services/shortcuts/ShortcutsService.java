package com.glenwood.glaceemr.server.application.services.shortcuts;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.SoapElementDatalist;

/**
 * Service file for ShortcutsController
 * @author Chandrahas
 *
 */
public interface ShortcutsService {

	public List<SoapElementDatalist> addShortcut(Integer tabId, String elementId, String data);
	
	public List<SoapElementDatalist> fetchShortcuts(Integer shortcutId);

	public void deleteShortcut(List<SoapElementDatalist> newSoapElementDatalist);

	public List<SoapElementDatalist> fetchShortcuts(Integer tabId, String elementId);
	
}
