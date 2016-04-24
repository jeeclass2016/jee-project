/**
 * 
 */
package com.vietsci.cms.frontend.etopup.service;

import java.util.List;

import org.primefaces.model.UploadedFile;

import com.vietsci.cms.frontend.etopup.dto.AgentAddressMgmtDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.model.AgentAddressManagement;
import com.vietsci.cms.frontend.etopup.model.TblMapDistrict;
import com.vietsci.cms.frontend.etopup.model.TblMapProvince;


/** 
 * Interface for communicating with eTopUp API to process with Agent Address Management.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
public interface AgentAddressManagementService {

  public List<AgentAddressManagement> findAgentAddressList(AgentAddressMgmtDTO agentAddressMgmtDTO);

  public int countTotalRecord(AgentAddressMgmtDTO agentAddressMgmtDTO);
  
  public int countAgentByMSISDN(String msisdn);
  
  public int countAddressData(String province, String district);
  
  public List<TblMapProvince> getAvailableProvinceList(String msisdn);
  
  public List<TblMapDistrict> getAvailableDistrictList(String msisdn);
  
  public List<Agent> getAgentByMSISDN(String msisdn);
  
  public Boolean createAgentAddress(AgentAddressManagement agentAddressManagement);
  
  public Boolean updateAgentAddress(AgentAddressManagement agentAddressManagement);

  List<TblMapProvince> getAvailableProvinceList(Long parentId);

  List<TblMapDistrict> getAvailableDistrictList();
  
  public Boolean createAgentAddressBatch(UploadedFile uploadedFile);

  List<TblMapDistrict> getAvailableDistrictList(List<String> provinceIdList);
}
