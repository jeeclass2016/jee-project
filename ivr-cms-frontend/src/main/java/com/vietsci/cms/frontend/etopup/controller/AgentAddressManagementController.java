package com.vietsci.cms.frontend.etopup.controller;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.common.util.Region;
import com.vietsci.cms.frontend.etopup.dto.AgentAddressMgmtDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.model.AgentAddressManagement;
import com.vietsci.cms.frontend.etopup.model.TblMapDistrict;
import com.vietsci.cms.frontend.etopup.model.TblMapProvince;
import com.vietsci.cms.frontend.etopup.primefaces.AgentAddressDataModel;
import com.vietsci.cms.frontend.etopup.service.AgentAddressManagementService;
import com.vietsci.cms.frontend.util.FacesUtil;

/** 
 * Controller class for Agent Address management.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
@Named
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class AgentAddressManagementController extends EtopupBaseController implements Serializable {

  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = -6724426835354170001L;
  
  /**
  * logger.
  */
  private static final Log logger = LogFactory.getLog(AgentAddressManagementController.class);
 
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init AgentAddressManagementController members");
    agentAddressMgmtDTO = new AgentAddressMgmtDTO();
    newAgentAddressMgmtDTO = new AgentAddressMgmtDTO();
    editedAgentAddressMgmtDTO = new AgentAddressMgmtDTO();
    
    availableProvinceList = new ArrayList<TblMapProvince>();
    availableDistrictList = new ArrayList<TblMapDistrict>();
    
    statusMap = new HashMap<String, String>();
    statusMap.put(Constants.AgentAddressManagement.ACTIVE_STATUS_VALUE, Constants.AgentAddressManagement.ACTIVE_STATUS_LABEL);
    statusMap.put(Constants.AgentAddressManagement.INACTIVE_STATUS_VALUE, Constants.AgentAddressManagement.INACTIVE_STATUS_LABEL);
    initRegionMap();
    
    agentAddressDataModel = new AgentAddressDataModel(new ArrayList<AgentAddressManagement>());
    
    enableAddNewAgentAddressButton = false;
    logger.debug("Init AgentAddressManagementController members successfully");
  }
  

  public void findAgentAddressList() {
    doFindAgentAddressList();
    FacesContext context = FacesContext.getCurrentInstance();
    Long parentId = processingAgent.getParentId();
    if (parentId == null || parentId != Constants.VIETNAMOBILE_HEAD_NUMBER) {
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, "Đại lý ứng với số thuê bao trên không phải là NPP. Bạn không thể thêm mới địa bàn hoạt động cho đại lý này.", ""));
      agentAddressDataModel = new AgentAddressDataModel(new ArrayList<AgentAddressManagement>());
      enableAddNewAgentAddressButton = false;
      return;
    }
    availableProvinceList = agentAddressManagementService.getAvailableProvinceList(parentId);
    if (availableProvinceList == null || availableProvinceList.size() <= 0) {
      enableAddNewAgentAddressButton = false;
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, "Đại lý cấp trên chưa có địa bàn hoạt động nào. Bạn không thể thêm mới địa bàn hoạt động cho đại lý này.", ""));
      logger.debug("Finished finding Agent Address with msisds: " + agentAddressMgmtDTO.getMsisdn());
      return;
    }
    List<String> provinceIdList = new ArrayList<String>();
    for (TblMapProvince province : availableProvinceList) {
      provinceIdList.add(province.getProvinceId());
    }
    availableDistrictList = agentAddressManagementService.getAvailableDistrictList(provinceIdList);
    initProvinceSelectItems(availableProvinceList);
    
    initProvinceIdAndRegionMap(availableProvinceList);
    
    @SuppressWarnings("unchecked")
    List<AgentAddressManagement> agentAddressList = (List<AgentAddressManagement>) agentAddressDataModel.getWrappedData();
    if (agentAddressList.size() <= 0) {
      RequestContext.getCurrentInstance().execute("PF('dlg-confirm').show()");
    }
    
    /*context.addMessage(null, 
        new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.AgentAddressManagement.MSISDN_WITH_NO_ADDRESS_MESSAGE, null));*/
    logger.debug("Finished finding Agent Address with msisds: " + agentAddressMgmtDTO.getMsisdn());
  }
  
  private void doFindAgentAddressList() {
    String msisdn = agentAddressMgmtDTO.getMsisdn();
    logger.debug("Start finding Agent Address with msisdn: " + msisdn);
    FacesContext context = FacesContext.getCurrentInstance();
    
    if (StringUtils.isBlank(msisdn)) {
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bạn phải nhập thông tin số thuê bao", ""));
      agentAddressDataModel = new AgentAddressDataModel(new ArrayList<AgentAddressManagement>());
      enableAddNewAgentAddressButton = false;
      return;
    }
    
    List<Agent> agentList = agentAddressManagementService.getAgentByMSISDN(msisdn);

    if (agentList == null || agentList.size() <= 0) {
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.AgentAddressManagement.OPERATION_FAILED_MESSAGE, "Số thuê bao không tồn tại"));
      agentAddressDataModel = new AgentAddressDataModel(new ArrayList<AgentAddressManagement>());
      enableAddNewAgentAddressButton = false;
      return;
    }
    processingAgent = agentList.get(0);
    List<AgentAddressManagement> agentAddressList = new ArrayList<AgentAddressManagement>();
    Set<AgentAddressManagement> agentAddressSet = new HashSet<AgentAddressManagement>();
    for (Agent agent : agentList) {
      Set<AgentAddressManagement> agentAddressManagements = agent.getAgentAddressManagements();
      if (agentAddressManagements == null || agentAddressManagements.size() <= 0) {
        continue;
      }
      for (AgentAddressManagement agentAddress : agentAddressManagements) {
        agentAddress.setAgent(agent);
        agentAddressSet.add(agentAddress);
      }      
    }
    
    agentAddressList.addAll(agentAddressSet);
    //agentAddressLazyDataModel = new AgentAddressLazyDataModel(agentAddressManagementService, agentAddressMgmtDTO);
    agentAddressDataModel = new AgentAddressDataModel(agentAddressList);
    enableAddNewAgentAddressButton = true;
  }
  
  public String onCreateNewAgentAddress() {
    newAgentAddressManagement = new AgentAddressManagement();
    newAgentAddressManagement.setMsisdn(agentAddressMgmtDTO.getMsisdn());
    newAgentAddressManagement.setRegion("");
    //updateRegion(processingAgent.getProvince());
    return null;
  }
  
  @SuppressWarnings("unchecked")
  private boolean checkValidProvinceAndDistrictInfo(String province, String district) {
    if (agentAddressDataModel == null || agentAddressDataModel.getWrappedData() == null) {
      return true;
    }
    String region = this.provinceAndRegionMap.get(province);
    List<AgentAddressManagement> agentAddressList = (List<AgentAddressManagement>) agentAddressDataModel.getWrappedData();
    for (AgentAddressManagement addressManagement : agentAddressList) {
      String provinceInfo = addressManagement.getProvince();
      String districtInfo = addressManagement.getDistrict();
      if (province.equals(provinceInfo) && district.equals(districtInfo)) {
        return false;
      }
      String regionInfo = addressManagement.getRegion();
      if (!region.equals(regionInfo)) {
        return false;
      }
    }
    return true;
  }
  
  @SuppressWarnings("unchecked")
  private boolean checkValidProvinceAndDistrictInfo2(String province, String district) {
    if (agentAddressDataModel == null || agentAddressDataModel.getWrappedData() == null) {
      return true;
    }
    String region = this.provinceAndRegionMap.get(province);
    List<AgentAddressManagement> agentAddressList = (List<AgentAddressManagement>) agentAddressDataModel.getWrappedData();
    int currentAgentAddressListSize = agentAddressList.size();
    for (AgentAddressManagement addressManagement : agentAddressList) {
      String provinceInfo = addressManagement.getProvince();
      String districtInfo = addressManagement.getDistrict();
      if (province.equals(provinceInfo) && district.equals(districtInfo)) {
        return false;
      }
      if (currentAgentAddressListSize == 1) {
        return true;
      }
      String regionInfo = addressManagement.getRegion();
      if (!region.equals(regionInfo)) {
        return false;
      }
    }
    return true;
  }
  
  public String doCreateNewAgentAddress() {
    RequestContext requestContext = RequestContext.getCurrentInstance();
    Agent agent = new Agent();
    agent.setAgentId(processingAgent.getAgentId());
    agent.setAgentAddressManagements(null);

    newAgentAddressManagement.setAgent(agent);
    newAgentAddressManagement.setCreateDate(new Date());
    String regionId = String.valueOf(Region.getRegionIdFromName(newAgentAddressManagement.getRegion()));
    newAgentAddressManagement.setRegion(regionId);
    
    String provinceId = newAgentAddressManagement.getProvince();
    String provinceName = getProvinceByProvinceId(provinceId);
    newAgentAddressManagement.setProvince(provinceName);
    
    String districtId = newAgentAddressManagement.getDistrict();
    String districtName = getDistrictByDistrictId(districtId);
    newAgentAddressManagement.setDistrict(districtName);
    
    boolean validInput = true;
    if (StringUtils.isBlank(provinceName)) {
      FacesUtil.addErrorMessage("frm1:provinceTxtErrMsg", "Bạn phải nhập thông tin tỉnh/thành");
      validInput = false;
    }
    if (StringUtils.isBlank(districtName)) {
      FacesUtil.addErrorMessage("frm1:districtTxtErrMsg", "Bạn phải nhập thông tin quận/huyện");
      validInput = false;
    }
    
    if (!validInput) {
      return null;
    }
    validInput = checkValidProvinceAndDistrictInfo(provinceName, districtName);
    if (!validInput) {
      FacesUtil.addErrorMessage("Địa bàn hoạt động này đã tồn tại hoặc thuộc vùng khác.");
      requestContext.addCallbackParam("updateStatus", false);
      logger.error("Địa bàn hoạt động này đã tồn tại hoặc thuộc vùng khác.");
      String provinceName2 = newAgentAddressManagement.getProvince();
      String provinceId2 = getProvinceIdByProvinceName(provinceName2);
      newAgentAddressManagement.setProvince(provinceId2);
      
      String districtName2 = newAgentAddressManagement.getDistrict();
      String districtId2 = getDistrictIdByDistrictName(districtName2);
      newAgentAddressManagement.setDistrict(districtId2);
      
      updateRegion(provinceId2);
      return null;
    }
    
    Boolean rs = Boolean.FALSE;
    try {
      rs = agentAddressManagementService.createAgentAddress(newAgentAddressManagement);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    String provinceName2 = newAgentAddressManagement.getProvince();
    String provinceId2 = getProvinceIdByProvinceName(provinceName2);
    newAgentAddressManagement.setProvince(provinceId2);
    
    String districtName2 = newAgentAddressManagement.getDistrict();
    String districtId2 = getDistrictIdByDistrictName(districtName2);
    newAgentAddressManagement.setDistrict(districtId2);
    
    updateRegion(provinceId2);
    
    if (rs) {
      FacesUtil.addSuccessMessage("Thêm mới địa bàn hoạt động của đại lý thành công");
      requestContext.addCallbackParam("updateStatus", true);
      doFindAgentAddressList();
      return onCreateNewAgentAddress();
    } else {
      FacesUtil.addErrorMessage("Thêm mới địa bàn hoạt động của đại lý thất bại.");
      requestContext.addCallbackParam("updateStatus", false);
    }
    return null;
  }
  
  public String onEditCurrentAgentAddress(AgentAddressManagement agentAddressManagement) throws IllegalAccessException, InvocationTargetException {
    editedAgentAddressManagement = new AgentAddressManagement();
    BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
    BeanUtils.copyProperties(editedAgentAddressManagement, agentAddressManagement);
    String provinceName = editedAgentAddressManagement.getProvince();
    String provinceId = getProvinceIdByProvinceName(provinceName);
    editedAgentAddressManagement.setProvince(provinceId);

    onChangeProvinceFieldForUpdatingCurrAgentCase(null);
    
    String districtName = editedAgentAddressManagement.getDistrict();
    String districtId = getDistrictIdByDistrictName(districtName);
    editedAgentAddressManagement.setDistrict(districtId);
    
    return null;
  }
  
  public String doUpdateCurrentAgentAddress() {
    RequestContext requestContext = RequestContext.getCurrentInstance();
    Agent agent = new Agent();
    agent.setAgentId(processingAgent.getAgentId());
    agent.setAgentAddressManagements(null);
    String regionId = String.valueOf(Region.getRegionIdFromName(editedAgentAddressManagement.getRegion()));
    editedAgentAddressManagement.setRegion(regionId);
    editedAgentAddressManagement.setAgent(agent);
    
    String provinceId = editedAgentAddressManagement.getProvince();
    String provinceName = getProvinceByProvinceId(provinceId);
    editedAgentAddressManagement.setProvince(provinceName);
    
    String districtId = editedAgentAddressManagement.getDistrict();
    String districtName = getDistrictByDistrictId(districtId);
    editedAgentAddressManagement.setDistrict(districtName);
    boolean validInput = true;
    if (StringUtils.isBlank(provinceName)) {
      FacesUtil.addErrorMessage("frm2:provinceTxtErrMsg", "Bạn phải nhập thông tin tỉnh/thành");
      validInput = false;
    }
    if (StringUtils.isBlank(districtName)) {
      FacesUtil.addErrorMessage("frm2:districtTxtErrMsg", "Bạn phải nhập thông tin quận/huyện");
      validInput = false;
    }
    if (!validInput) {
      return null;
    }
    validInput = checkValidProvinceAndDistrictInfo2(provinceName, districtName);
    if (!validInput) {
      FacesUtil.addErrorMessage("Địa bàn hoạt động này đã tồn tại hoặc thuộc vùng khác.");
      requestContext.addCallbackParam("updateStatus", false);
      logger.error("Địa bàn hoạt động này đã tồn tại hoặc thuộc vùng khác.");
      String provinceName2 = editedAgentAddressManagement.getProvince();
      String provinceId2 = getProvinceIdByProvinceName(provinceName2);
      editedAgentAddressManagement.setProvince(provinceId2);
      
      String districtName2 = editedAgentAddressManagement.getDistrict();
      String districtId2 = getDistrictIdByDistrictName(districtName2);
      editedAgentAddressManagement.setDistrict(districtId2);
      
      updateRegion2(provinceId2);
      return null;
    }
    
    Boolean rs = Boolean.FALSE;
    try {
      rs = agentAddressManagementService.updateAgentAddress(editedAgentAddressManagement);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }  
    if (rs) {
      FacesUtil.addSuccessMessage("Cập nhật địa bàn hoạt động của đại lý thành công");
      requestContext.addCallbackParam("updateStatus", true);
      doFindAgentAddressList();
    } else {
      FacesUtil.addErrorMessage("Cập nhật địa bàn hoạt động của đại lý thất bại.");
      requestContext.addCallbackParam("updateStatus", false);
    }
    return null;
  }
  
  public void onChangeProvinceFieldForInsertingNewAgentCase(AjaxBehaviorEvent event) {
    if (event != null) {
      String submittedValue = ((SelectOneMenu) event.getSource()).getSubmittedValue().toString();
      newAgentAddressManagement.setProvince(submittedValue);
    }
    availableDistrictListPerProvince = new ArrayList<TblMapDistrict>();
    String province = newAgentAddressManagement.getProvince();
    updateRegion(province);
    if (availableDistrictList == null) {
      return;
    }
    for (TblMapDistrict district : availableDistrictList) {
      if (district.getProvinceId().equals(province)) {
        availableDistrictListPerProvince.add(district);
      }
    }
    initDistrictSelectItems(availableDistrictListPerProvince);
  }
  
  public void onChangeProvinceFieldForUpdatingCurrAgentCase(AjaxBehaviorEvent event) {
    if (event != null) {
      String submittedValue = ((SelectOneMenu) event.getSource()).getSubmittedValue().toString();
      editedAgentAddressManagement.setProvince(submittedValue);
    }
    availableDistrictListPerProvince = new ArrayList<TblMapDistrict>();
    String province = editedAgentAddressManagement.getProvince();
    updateRegion2(province);
    if (availableDistrictList == null) {
      return;
    }
    for (TblMapDistrict district : availableDistrictList) {
      if (district.getProvinceId().equals(province)) {
        availableDistrictListPerProvince.add(district);
      }
    }
    initDistrictSelectItems(availableDistrictListPerProvince);
  }
  
  public List<String> completeProvince(String query) {
    List<String> suggestions = new ArrayList<String>();
    for (TblMapProvince p : availableProvinceList) {
      if (p.getProvince().startsWith(query)) {
        suggestions.add(p.getProvince());
      }
    }
    return suggestions;
  }
  
  public List<String> completeDistrict(String query) {
    List<String> suggestions = new ArrayList<String>();
    if (availableDistrictListPerProvince == null) {
      return suggestions;
    }
    for (TblMapDistrict d : availableDistrictListPerProvince) {
      if (d.getDistrict().startsWith(query)) {
        suggestions.add(d.getDistrict());
      }
    }
    return suggestions;
  }
  
/*  private void handleExceptionMessage(FacesContext context, RequestContext requestContext, CmsRestException e) {
    String bodyMessage = e.getMessage();
    Map<String, String> bodyMessageMap = EtopupUtil.read(bodyMessage);
    if (bodyMessageMap == null || bodyMessageMap.size() <=0 ) {
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.AgentAddressManagement.OPERATION_FAILED_MESSAGE, null));
      if (requestContext != null) {
        requestContext.addCallbackParam("updateStatus", false);
      }
      return;
    }
    String messageCode = bodyMessageMap.get(Constants.MESSAGE_CODE);
    String message = bodyMessageMap.get(Constants.MESSAGE);
    logger.error("messageCode:" + messageCode);
    logger.error("message:" + message);
    context.addMessage(null, 
        new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.AgentAddressManagement.OPERATION_FAILED_MESSAGE, message));
    if (requestContext != null) {
      requestContext.addCallbackParam("updateStatus", false);
    }
  }*/
  
  private void updateRegion(String province) {
    String region = this.provinceIdAndRegionMap.get(province);
    if (region == null) {
      region = Region.NONE.getName();
    }
    if (region != null && !"".equals(region.trim())) {
      newAgentAddressManagement.setRegion(regionMap.get(Integer.parseInt(region)));
    } else {
      newAgentAddressManagement.setRegion("");
    }
  }
  
  private void updateRegion2(String province) {
    String region = this.provinceIdAndRegionMap.get(province);
    if (region == null) {
      region = Region.NONE.getName();
    }
    if (region != null && !"".equals(region.trim())) {
      editedAgentAddressManagement.setRegion(regionMap.get(Integer.parseInt(region)));
    } else {
      editedAgentAddressManagement.setRegion("");
    }
  }
  
  private void initProvinceIdAndRegionMap(List<TblMapProvince> provinces) {
    this.provinceIdAndRegionMap = new HashMap<>(provinces.size());
    this.provinceAndRegionMap = new HashMap<>(provinces.size());
    for (TblMapProvince province : provinces) {
      this.provinceIdAndRegionMap.put(province.getProvinceId(), province.getRegion());
      this.provinceAndRegionMap.put(province.getProvince(), province.getRegion());
    }
  }
  
  private void initRegionMap() {
    this.regionMap = new HashMap<>();
    this.regionMap.put(Region.NONE.getValue(), Region.NONE.getName());
    this.regionMap.put(Region.NORTHERN.getValue(), Region.NORTHERN.getName());
    this.regionMap.put(Region.SOUTHERN.getValue(), Region.SOUTHERN.getName());
    this.regionMap.put(Region.CENTRAL.getValue(), Region.CENTRAL.getName());
  }
  
  public String showStatusInfo(String status) {
    return statusMap.get(status);
  }
  
  public String showRegionInfo(String region) {
    if (region != null && !"".equals(region.trim())) {
      try {
        int regionId = Integer.parseInt(region.trim());
        return regionMap.get(regionId);
      } catch (NumberFormatException e) {
        logger.error("Region in DB is not a ID. Real value is " + region);
        return region;
      }
    }
    return "";
  }
  
  private void initProvinceSelectItems(List<TblMapProvince> provinces) {
    this.provinceSelectItems = new ArrayList<>();
    try {

      for (TblMapProvince province : provinces) {
        this.provinceSelectItems.add(new SelectItem(province.getProvinceId(), province.getProvince()));
      }
    } catch (Exception e) {
      handleExceptionMessage(e, AgentAddressManagementController.class);
    }
  }
  
  private void initDistrictSelectItems(List<TblMapDistrict> districts) {
    this.districtSelectItems = new ArrayList<>();
    try {
      for (TblMapDistrict district : districts) {
        this.districtSelectItems.add(new SelectItem(district.getDistrictId(), district.getDistrict()));
      }
    } catch (Exception e) {
      handleExceptionMessage(e, AgentAddressManagementController.class);
    }
  }
  
  private String getProvinceByProvinceId(String provinceId) {
    if (availableProvinceList == null || availableDistrictList .size() <= 0) {
      return null;
    }
    for (TblMapProvince p : availableProvinceList) {
      if (p.getProvinceId().equals(provinceId)) {
        return p.getProvince();
      }
    }
    return null;
  }
  
  private String getDistrictByDistrictId(String districtId) {
    if (availableDistrictListPerProvince == null || availableDistrictListPerProvince .size() <= 0) {
      return null;
    }
    for (TblMapDistrict d : availableDistrictListPerProvince) {
      if (d.getDistrictId().equals(districtId)) {
        return d.getDistrict();
      }
    }
    return null;
  }
  
  private String getProvinceIdByProvinceName(String provinceName) {
    if (availableProvinceList == null || availableDistrictList .size() <= 0) {
      return null;
    }
    for (TblMapProvince p : availableProvinceList) {
      if (p.getProvince().equals(provinceName)) {
        return p.getProvinceId();
      }
    }
    return null;
  }
  
  private String getDistrictIdByDistrictName(String districtName) {
    if (availableDistrictListPerProvince == null || availableDistrictListPerProvince .size() <= 0) {
      return null;
    }
    for (TblMapDistrict d : availableDistrictListPerProvince) {
      if (d.getDistrict().equals(districtName)) {
        return d.getDistrictId();
      }
    }
    return null;
  }
  
  /* **** GETTERS & SETTERS **** */
  @Inject
  private AgentAddressManagementService agentAddressManagementService;
  
  // so thue bao
  private String msisdnSearchCriteria;
  private AgentAddressMgmtDTO agentAddressMgmtDTO;
  private AgentAddressMgmtDTO newAgentAddressMgmtDTO;
  private AgentAddressMgmtDTO editedAgentAddressMgmtDTO;
  private AgentAddressManagement newAgentAddressManagement;
  private AgentAddressManagement editedAgentAddressManagement;
  private List<TblMapDistrict> availableDistrictList;
  private List<TblMapDistrict> availableDistrictListPerProvince;
  private List<TblMapProvince> availableProvinceList;
  private Agent processingAgent;
  private Map<String, String> statusMap;
  private List<SelectItem> provinceSelectItems;
  private List<SelectItem> districtSelectItems;
  
  private List<AgentAddressMgmtDTO> agentAddressList;
  //private AgentAddressLazyDataModel agentAddressLazyDataModel;
  private AgentAddressDataModel agentAddressDataModel;
  private boolean enableAddNewAgentAddressButton;

  private HashMap<String, String> provinceIdAndRegionMap = new HashMap<>();
  private HashMap<String, String> provinceAndRegionMap = new HashMap<>();
  private HashMap<Integer, String> regionMap;
  private boolean displayPopup = true;
  
  public String getMsisdnSearchCriteria() {
    return msisdnSearchCriteria;
  }


  public void setMsisdnSearchCriteria(String msisdnSearchCriteria) {
    this.msisdnSearchCriteria = msisdnSearchCriteria;
  }


  public AgentAddressMgmtDTO getAgentAddressMgmtDTO() {
    return agentAddressMgmtDTO;
  }


  public void setAgentAddressMgmtDTO(AgentAddressMgmtDTO agentAddressMgmtDTO) {
    this.agentAddressMgmtDTO = agentAddressMgmtDTO;
  }


  public AgentAddressMgmtDTO getNewAgentAddressMgmtDTO() {
    return newAgentAddressMgmtDTO;
  }


  public void setNewAgentAddressMgmtDTO(AgentAddressMgmtDTO newAgentAddressMgmtDTO) {
    this.newAgentAddressMgmtDTO = newAgentAddressMgmtDTO;
  }


  public AgentAddressMgmtDTO getEditedAgentAddressMgmtDTO() {
    return editedAgentAddressMgmtDTO;
  }


  public void setEditedAgentAddressMgmtDTO(AgentAddressMgmtDTO editedAgentAddressMgmtDTO) {
    this.editedAgentAddressMgmtDTO = editedAgentAddressMgmtDTO;
  }


  public List<TblMapDistrict> getAvailableDistrictList() {
    return availableDistrictList;
  }


  public void setAvailableDistrictList(List<TblMapDistrict> availableDistrictList) {
    this.availableDistrictList = availableDistrictList;
  }


  public List<TblMapProvince> getAvailableProvinceList() {
    return availableProvinceList;
  }


  public void setAvailableProvinceList(List<TblMapProvince> availableProvinceList) {
    this.availableProvinceList = availableProvinceList;
  }


  public List<AgentAddressMgmtDTO> getAgentAddressList() {
    return agentAddressList;
  }


  public void setAgentAddressList(List<AgentAddressMgmtDTO> agentAddressList) {
    this.agentAddressList = agentAddressList;
  }


 /* public AgentAddressLazyDataModel getAgentAddressLazyDataModel() {
    return agentAddressLazyDataModel;
  }


  public void setAgentAddressLazyDataModel(AgentAddressLazyDataModel agentAddressLazyDataModel) {
    this.agentAddressLazyDataModel = agentAddressLazyDataModel;
  }*/


  public boolean isEnableAddNewAgentAddressButton() {
    return enableAddNewAgentAddressButton;
  }


  public void setEnableAddNewAgentAddressButton(boolean enableAddNewAgentAddressButton) {
    this.enableAddNewAgentAddressButton = enableAddNewAgentAddressButton;
  }


  public Agent getProcessingAgent() {
    return processingAgent;
  }


  public void setProcessingAgent(Agent processingAgent) {
    this.processingAgent = processingAgent;
  }


  public Map<String, String> getStatusMap() {
    return statusMap;
  }


  public AgentAddressManagement getNewAgentAddressManagement() {
    return newAgentAddressManagement;
  }


  public void setNewAgentAddressManagement(AgentAddressManagement newAgentAddressManagement) {
    this.newAgentAddressManagement = newAgentAddressManagement;
  }


  public AgentAddressManagement getEditedAgentAddressManagement() {
    return editedAgentAddressManagement;
  }


  public void setEditedAgentAddressManagement(AgentAddressManagement editedAgentAddressManagement) {
    this.editedAgentAddressManagement = editedAgentAddressManagement;
  }


  public void setStatusMap(Map<String, String> statusMap) {
    this.statusMap = statusMap;
  }


  public boolean isDisplayPopup() {
    return displayPopup;
  }


  public void setDisplayPopup(boolean displayPopup) {
    this.displayPopup = displayPopup;
  }


  public AgentAddressDataModel getAgentAddressDataModel() {
    return agentAddressDataModel;
  }


  public void setAgentAddressDataModel(AgentAddressDataModel agentAddressDataModel) {
    this.agentAddressDataModel = agentAddressDataModel;
  }


  public List<SelectItem> getProvinceSelectItems() {
    return provinceSelectItems;
  }


  public void setProvinceSelectItems(List<SelectItem> provinceSelectItems) {
    this.provinceSelectItems = provinceSelectItems;
  }


  public List<SelectItem> getDistrictSelectItems() {
    return districtSelectItems;
  }


  public void setDistrictSelectItems(List<SelectItem> districtSelectItems) {
    this.districtSelectItems = districtSelectItems;
  }


}
