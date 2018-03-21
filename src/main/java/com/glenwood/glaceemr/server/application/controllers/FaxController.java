
package com.glenwood.glaceemr.server.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.FaxOutbox;
import com.glenwood.glaceemr.server.application.services.fax.FaxInboxBean;
import com.glenwood.glaceemr.server.application.services.fax.FaxService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;

/**
 * Controller for fax module. 
 * @author Anil Kumar
 *
 */
@RestController
@Transactional
@RequestMapping(value="/user/Fax")
public class FaxController {

	@Autowired
	FaxService faxService;
	/**
	 * API to get Outboxdetails of fax
	 * @param fax_outbox_folderid
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getOutboxDetails",method=RequestMethod.GET)
	@ResponseBody
	public  EMRResponseBean getOutboxDetails(@RequestParam(value="folderId",required=true,defaultValue="2")Integer fax_outbox_folderid,
			@RequestParam(value="userId",required=true,defaultValue="-1")Integer userId,
			@RequestParam(value="pageNo",required=true,defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="1")Integer pageSize) throws Exception{
		    EMRResponseBean emrResponse = new EMRResponseBean();
			emrResponse.setData(faxService.getOutboxDetails(fax_outbox_folderid,userId,pageNo,pageSize));
		return emrResponse;
	}
	

	/**
	 * API to get Inboxdetails of fax
	 * @param fax_inbox_folderid
	 * @param fax_inbox_statusid
	 * @param fax_inbox_forwardeduserid
	 * @param fax_inbox_location
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getInboxDetails",method=RequestMethod.GET)
	@ResponseBody
	public  EMRResponseBean getInboxDetails(
			@RequestParam(value="folderId",required=true,defaultValue="1")Integer fax_inbox_folderid,
			@RequestParam(value="faxStatusId",required=false,defaultValue="-1")Integer fax_inbox_statusid,
			@RequestParam(value="forwaredUserId",required=true,defaultValue="0")Integer fax_inbox_forwardeduserid,
			@RequestParam(value="faxLocation",required=false,defaultValue="1")Integer fax_inbox_location,
			@RequestParam(value="pageNo",required=true,defaultValue="1")int pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="1")int pageSize)throws Exception{
		    EMRResponseBean  emrResponse = new EMRResponseBean();
			emrResponse.setData(faxService.getInboxDetails(fax_inbox_folderid,fax_inbox_statusid,fax_inbox_forwardeduserid,fax_inbox_location,pageNo,pageSize));

		    return emrResponse;
	}

	/**
	 * API to get Faxfolder count
	 * @param fax_location
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getFaxFolderCount",method=RequestMethod.GET)
	@ResponseBody
	public  EMRResponseBean getFaxFolderCount(
			@RequestParam(value="faxLocation",required=false,defaultValue="1")Integer fax_location,
			@RequestParam(value="userId",required=true,defaultValue="-1")Integer userId) throws Exception{
		    EMRResponseBean emrResponse=new EMRResponseBean();
		    emrResponse.setData(faxService.getFaxFolderCount(fax_location,userId));
		return emrResponse;
	}

	/**
	 * API to Deleteselected fax
	 * @param faxId
	 * @param folderID
	 * @param userId
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteSelectFax",method=RequestMethod.GET)
	@ResponseBody
	public void deleteSelectFax(
			@RequestParam(value="faxId" ,required=true,defaultValue="-1")String  faxId,
			@RequestParam(value="fileNames" ,required=false,defaultValue="")String fileNames,
			@RequestParam(value="folderId" ,required=false,defaultValue="3")Integer  folderID,
			@RequestParam(value="userId" ,required=true,defaultValue="-1")Integer  userId) throws Exception{
		faxService.deleteSelectFax(faxId,fileNames,folderID,userId);
	}

	/**
	 * API to deleteselscted fax from outbox
	 * @param faxId
	 * @param folderID
	 * @param userId
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteSelectOutboxFax",method=RequestMethod.GET)
	@ResponseBody
	public void deleteSelectOutboxFax (
			@RequestParam(value="faxId" ,required=true,defaultValue="-1")String faxId,
			@RequestParam(value="folderId" ,required=false,defaultValue="3")Integer folderId,
			@RequestParam(value="userId" ,required=true,defaultValue="-1")Integer userId) throws Exception{

		faxService.deleteSelectOutboxFax(faxId,folderId,userId);
	}

	/**
	 * API to get Fax forwarduserlist
	 * @param empProfileGroupid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getForwardUserList",method=RequestMethod.GET)
	@ResponseBody
	public  EMRResponseBean getForwardUserList(
			@RequestParam(value="emp_profile_groupid",required=false,defaultValue="-1")Integer empProfileGroupid)throws Exception{
		    EMRResponseBean emrResponse = new EMRResponseBean();
		    emrResponse.setData(faxService.getFaxUserList());
		return emrResponse;		
	}

	/**
	 * API to Forward a fax
	 * @param faxId
	 * @param forwardTO
	 * @param folderID
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/forwardFax",method=RequestMethod.GET)
	@ResponseBody
	public  EMRResponseBean forwardFax(
			@RequestParam(value="faxId",required=true,defaultValue="-1")String  faxId,
			@RequestParam(value="fileNames",required=false,defaultValue="")String  fileNames,
			@RequestParam(value="fowardTo",required=true,defaultValue="-1")Integer forwardTo,
			@RequestParam(value="folderId",required=true,defaultValue="-3")Integer folderId,
			@RequestParam(value="userId",required=true,defaultValue="-1")Integer userId)throws Exception{
		    EMRResponseBean emrResponse = new EMRResponseBean();
		    emrResponse.setData(faxService.forwardFax(faxId,fileNames,forwardTo,folderId,userId));
		return emrResponse;
	}
	
	/**
	 * API to get Inbound fax details
	 * @param userId
	 * @param faxId
	 * @param faxTab
	 * @param faxFolder
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getInFaxDetails",method=RequestMethod.GET)
	@ResponseBody
	public  EMRResponseBean getInFaxDetails(
			@RequestParam(value="userId",required=false,defaultValue="-1")Integer userId,
			@RequestParam(value="faxId",required=true,defaultValue="-1")Integer faxId,
			@RequestParam(value="faxTab",required=true,defaultValue="0")Integer faxTab,
			@RequestParam(value="faxFolder",required=true,defaultValue="1")Integer faxFolder)throws Exception{
		    EMRResponseBean emrResponse = new EMRResponseBean();
		    emrResponse.setData(faxService.getInFaxDetails(faxId, userId, faxTab, faxFolder));
		return emrResponse ;
	}

	/**
	 * API to get outbound fax details
	 * @param faxId
	 * @param faxTab
	 * @param faxFolder
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getOutFaxDetails",method=RequestMethod.GET)
	@ResponseBody
	public  EMRResponseBean getOutFaxDetails(
			@RequestParam(value="userId",required=false,defaultValue="-1")Integer userId,
			@RequestParam(value="faxId",required=true,defaultValue="-1")Integer faxId,
			@RequestParam(value="faxTab",required=true,defaultValue="0")Integer faxTab,
			@RequestParam(value="faxFolder",required=true,defaultValue="2")Integer faxFolder)throws Exception{
		    EMRResponseBean emrResponse = new EMRResponseBean();
		    emrResponse.setData(faxService.getOutFaxDetails(userId,faxId, faxTab, faxFolder));
		return emrResponse;
	}

	/**
	 * API to get signature details
	 * @param empProfileLoginid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getSignatureDetails",method=RequestMethod.GET)
	@ResponseBody
	public  EMRResponseBean getSignatureDetails(
			@RequestParam(value="empId",required=true,defaultValue="-1")Integer empId)throws Exception{
	       EMRResponseBean emrResponse = new EMRResponseBean();
	       emrResponse.setData(faxService.getSignatureDetails(empId));
		return  emrResponse;
	}
	
	/**
	 * API to get last fax received time
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lastFaxReceivedTime",method=RequestMethod.GET)
	@ResponseBody
	public  EMRResponseBean lastFaxReceivedTime(
			@RequestParam(value="userId",required=false,defaultValue="-1")Integer userId)throws Exception{
		    EMRResponseBean emrResponse = new EMRResponseBean();
		    emrResponse.setData(faxService.lastFaxReceivedTime(userId));
		return emrResponse ;
	}
	
	/**
	 * API to get the Fax Location
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getFaxLocation",method=RequestMethod.GET)
	@ResponseBody
	public  EMRResponseBean getFaxLocation(
			@RequestParam(value="userId",required=true,defaultValue="-1")Integer userId) throws Exception{
		EMRResponseBean emrResponse=new EMRResponseBean();
		emrResponse.setData(faxService.getFaxLocation());
		return emrResponse;
	}

	/**
	 * API to read the Fax
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/markReadFax",method=RequestMethod.GET)
	@ResponseBody
	public  EMRResponseBean markReadFax(
			@RequestParam(value="faxId",required=true,defaultValue="-1")Integer faxId,
			@RequestParam(value="userId",required=true,defaultValue="-1")Integer userId) throws Exception{
		List<FaxInboxBean> readFax=faxService.markReadAlert(faxId,userId);
		EMRResponseBean readFaxBean=new EMRResponseBean();
		readFaxBean.setData(readFax);
		return readFaxBean;
	}

	/**
	 * API to unread the Fax
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/markUnReadFax",method=RequestMethod.GET)
	@ResponseBody
	public  EMRResponseBean markUnReadFax(
			@RequestParam(value="faxId",required=true,defaultValue="-1")Integer faxId,
			@RequestParam(value="userId",required=true,defaultValue="-1")Integer userId) throws Exception{
		List<FaxInboxBean> unReadFax=faxService.markUnReadAlert(faxId,userId);
		EMRResponseBean unReadFaxBean=new EMRResponseBean();
		unReadFaxBean.setData(unReadFax);
		return unReadFaxBean;
	}

	
	/**
	 * API to search the Fax
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/search",method=RequestMethod.GET)
	@ResponseBody
	public  EMRResponseBean search(
			@RequestParam(value="nameString",required=true,defaultValue="-1")String nameString,
			@RequestParam(value="faxFolder",required=true,defaultValue="2")String faxFolder,
			@RequestParam(value="faxTab",required=true,defaultValue="-1")String faxTab,
			@RequestParam(value="faxLocation",required=true,defaultValue="-1")String faxLocation,
			@RequestParam(value="forwaredUserId",required=true,defaultValue="0")String forwaredUserId) throws Exception{
		List<FaxInboxBean> searchFax=faxService.searchFax(nameString,faxFolder,faxTab,faxLocation,forwaredUserId);
		EMRResponseBean searchFaxBean=new EMRResponseBean();
		searchFaxBean.setData(searchFax);
		return searchFaxBean;
	}
	
	@RequestMapping(value="/outBoxSearch",method=RequestMethod.GET)
	@ResponseBody
	public  EMRResponseBean outBoxSearch(
			@RequestParam(value="nameString",required=true,defaultValue="-1")String nameString,
			@RequestParam(value="faxFolder",required=true,defaultValue="2")String faxFolder,
			@RequestParam(value="faxTab",required=true,defaultValue="-1")String faxTab,
			@RequestParam(value="faxLocation",required=true,defaultValue="-1")String faxLocation,
			@RequestParam(value="forwaredUserId",required=true,defaultValue="0")String forwaredUserId) throws Exception{
		List<FaxOutbox> searchFax=faxService.outBoxSearch(nameString,faxFolder,faxTab,faxLocation,forwaredUserId);
		EMRResponseBean searchFaxBean=new EMRResponseBean();
		searchFaxBean.setData(searchFax);
		return searchFaxBean;
	}


	
}