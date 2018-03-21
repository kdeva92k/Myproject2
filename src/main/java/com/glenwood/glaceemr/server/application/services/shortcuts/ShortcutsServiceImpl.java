package com.glenwood.glaceemr.server.application.services.shortcuts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.SoapElementDatalist;
import com.glenwood.glaceemr.server.application.repositories.ShortcutsRepository;
import com.glenwood.glaceemr.server.application.specifications.ShortcutsSpecification;

/**
 * Service implementation file for ShortcutsController
 * @author Chandrahas
 */
@Service
public class ShortcutsServiceImpl implements ShortcutsService{

	@Autowired
	ShortcutsRepository shortcutsRepository;
	
	/**
	 * Method to add shortcut
	 * @param tabId
	 * @param elementId
	 * @param data
	 * @return list of shortcuts
	 */
	@Override
	public List<SoapElementDatalist> addShortcut(Integer tabId,
			String elementId, String data) {
		
		SoapElementDatalist newSoapElementDatalist = new SoapElementDatalist();
		newSoapElementDatalist.setSoapElementDatalistTabid(tabId);
		newSoapElementDatalist.setSoapElementDatalistElementid(elementId);
		newSoapElementDatalist.setSoapElementDatalistData(data);
		saveShortcut(newSoapElementDatalist);
		
		return fetchShortcuts(tabId, elementId.trim());
	}

	/**
	 * Method to fetch shortcut list
	 * @param tabId
	 * @param elementid
	 */
	@Override
	public List<SoapElementDatalist> fetchShortcuts(Integer tabId, String elementId) {
		return shortcutsRepository.findAll(ShortcutsSpecification.fetchShortcuts(tabId, elementId.trim()));
	}

	/**
	 * Method to save shortcut
	 * @param newSoapElementDatalist
	 */
	private void saveShortcut(SoapElementDatalist newSoapElementDatalist) {
		shortcutsRepository.save(newSoapElementDatalist);
	}

	/**
	 * Method to delete shortcut
	 * @param shortcutId 
	 */
	@Override
	public void deleteShortcut(List<SoapElementDatalist> newSoapElementDatalist) {
		shortcutsRepository.delete(newSoapElementDatalist);
	}

	/**
	 * Method to fetch shortcuts
	 * @param shortcutId
	 */
	@Override
	public List<SoapElementDatalist> fetchShortcuts(Integer shortcutId) {
		return shortcutsRepository.findAll(ShortcutsSpecification.fetchShortcuts(shortcutId));
	}
	
	
}
