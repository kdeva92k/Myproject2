package com.glenwood.glaceemr.server.application.services.fax;

import java.util.List;
import com.glenwood.glaceemr.server.application.models.FaxBox;
import com.glenwood.glaceemr.server.application.models.FaxInbox;
import com.glenwood.glaceemr.server.application.models.FaxOutbox;
/**
 * Service Interface for Fax service
 * @author Anil Kumar
 *
 */
public interface FaxService {

	
	public List<FaxOutbox> getOutboxDetails(Integer fax_outbox_folderid, Integer userId,Integer pageNo,Integer pageSize);	

	public List<FaxInboxBean> getInboxDetails(Integer fax_inbox_folderid, Integer fax_inbox_statusid,Integer fax_inbox_forwardeduserid, Integer fax_inbox_location,int pageNo,int pageSize);
	
	public List<FaxFolderBean> getFaxFolderCount(Integer fax_location,Integer userId);

	public void deleteSelectFax(String faxId,String fileNames,Integer TRASH, Integer userId);

	public void deleteSelectOutboxFax(String faxId, Integer folderId,Integer userId) ;

	public List<FaxUsersBean> getFaxUserList();

	public List<FaxInbox> forwardFax(String faxId, String fileNames, Integer forwardTo,Integer folderId, Integer userId);

	public  List<FaxInbox> getInFaxDetails(Integer faxId, Integer userId, Integer faxTab, Integer faxFolder);

	public List<FaxOutbox> getOutFaxDetails(Integer userId,Integer faxId, Integer faxTab, Integer faxFolder);

	public List<FaxSignListBean> getSignatureDetails(Integer empId);

	public  String lastFaxReceivedTime(Integer userId);
	
	public List<FaxBox> getFaxLocation();

	public List<FaxInboxBean> markReadAlert(Integer faxId,Integer userId);
	
	public List<FaxInboxBean> markUnReadAlert(Integer faxId,Integer userId);

	public List<FaxInboxBean> searchFax(String nameString, String faxFolder,
			String faxTab, String faxLocation,String ForwardUserId);
	
	public List<FaxOutbox> outBoxSearch(String nameString, String faxFolder,
			String faxTab, String faxLocation,String ForwardUserId);
}