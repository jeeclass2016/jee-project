package com.vietsci.cms.frontend.etopup.controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import com.vietsci.cms.frontend.etopup.common.util.EtopupStringUtil;
import com.vietsci.cms.frontend.etopup.common.util.Region;
import com.vietsci.cms.frontend.etopup.dto.AgentDTO;
import com.vietsci.cms.frontend.etopup.dto.AgentManagementDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.model.Centre;
import com.vietsci.cms.frontend.etopup.model.TblMapDistrict;
import com.vietsci.cms.frontend.etopup.model.TblMapProvince;
import com.vietsci.cms.frontend.etopup.primefaces.AgentManagementDataModel;
import com.vietsci.cms.frontend.etopup.service.AgentManagementService;
import com.vietsci.cms.frontend.etopup.service.CentreService;
import com.vietsci.cms.frontend.etopup.service.GeographicService;
import com.vietsci.cms.frontend.util.FacesUtil;
import com.vietsci.cms.frontend.util.MessageConstants;

@Named
@Scope(value = "session")
public class AgentManagementController extends EtopupBaseController implements Serializable {

  private static final long serialVersionUID = 5954508771669485395L;
  private static final Log logger = LogFactory.getLog(AgentManagementController.class);

  @Inject
  AgentManagementService agentManagementService;

  @Inject
  CentreService centreService;

  @Inject
  GeographicService geographicService;

  private AgentDTO agentDTO = new AgentDTO();
  private AgentManagementDataModel agentManagementDataModel;
  private Agent agent = new Agent();
  private List<Agent> subAgents = new ArrayList<>();
  private Agent superiorAgent = new Agent();

  private boolean currentActive;
  private boolean enableEditing;
  private boolean enableDeleting;
  private boolean enableAdding = true;
  private boolean enableConfirmEditing = false;
  private List<SelectItem> centreSelectItems;
  private List<SelectItem> agentStatusSelectItems;
  private List<SelectItem> provinceSelectItems;
  private List<SelectItem> districtSelectItems;
  private List<SelectItem> regionSelectItems;
  private List<SelectItem> agentTypeSelectItems;
  private String region;
  private HashMap<String, String> provinceIdAndRegionMap = new HashMap<>();
  private Agent parentAgent = new Agent();

  public List<SelectItem> getAgentStatusSelectItems() {
    return agentStatusSelectItems;
  }

  public void setAgentStatusSelectItems(List<SelectItem> agentStatusSelectItems) {
    this.agentStatusSelectItems = agentStatusSelectItems;
  }

  public List<SelectItem> getCentreSelectItems() {
    return centreSelectItems;
  }

  public void setCentreSelectItems(List<SelectItem> centreSelectItems) {
    this.centreSelectItems = centreSelectItems;
  }

  public boolean isEnableEditing() {
    return enableEditing;
  }

  public void setEnableEditing(boolean enableEditing) {
    this.enableEditing = enableEditing;
  }

  public boolean isEnableDeleting() {
    return enableDeleting;
  }

  public void setEnableDeleting(boolean enableDeleting) {
    this.enableDeleting = enableDeleting;
  }

  public boolean isEnableAdding() {
    return enableAdding;
  }

  public void setEnableAdding(boolean enableAdding) {
    this.enableAdding = enableAdding;
  }

  public AgentDTO getAgentDTO() {
    return agentDTO;
  }

  public void setAgentDTO(AgentDTO agentDTO) {
    this.agentDTO = agentDTO;
  }

  public AgentManagementDataModel getAgentDataModel() {
    return agentManagementDataModel;
  }

  public void setAgentDataModel(AgentManagementDataModel agentDataModel) {
    this.agentManagementDataModel = agentDataModel;
  }

  public Agent getAgent() {
    return agent;
  }

  public void setAgent(Agent agent) {
    this.agent = agent;
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

  public List<SelectItem> getAgentTypeSelectItems() {
    return agentTypeSelectItems;
  }

  public void setAgentTypeSelectItems(List<SelectItem> agentTypeSelectItems) {
    this.agentTypeSelectItems = agentTypeSelectItems;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public List<Agent> getSubAgents() {
    return subAgents;
  }

  public void setSubAgents(List<Agent> subAgents) {
    this.subAgents = subAgents;
  }

  public Agent getSuperiorAgent() {
    return superiorAgent;
  }

  public void setSuperiorAgent(Agent superiorAgent) {
    this.superiorAgent = superiorAgent;
  }

  public Agent getParentAgent() {
    return parentAgent;
  }

  public void setParentAgent(Agent parentAgent) {
    this.parentAgent = parentAgent;
  }

  public List<SelectItem> getRegionSelectItems() {
    return regionSelectItems;
  }

  public void setRegionSelectItems(List<SelectItem> regionSelectItems) {
    this.regionSelectItems = regionSelectItems;
  }

  public boolean isCurrentActive() {
    return currentActive;
  }

  public void setCurrentActive(boolean currentActive) {
    this.currentActive = currentActive;
  }

  public boolean isEnableConfirmEditing() {
    return enableConfirmEditing;
  }

  public void setEnableConfirmEditing(boolean enableConfirmEditing) {
    this.enableConfirmEditing = enableConfirmEditing;
  }

  /**
   * For initializing data
   */
  @PostConstruct
  public void initForSession() {

    List<TblMapProvince> provinces = geographicService.getAllProvince();
    initProvinceSelectItems(provinces);
    initProvinceIdAndRegionMap(provinces);

    initDistrictSelectItems();
    initRegionSelectItems();

    initAgentStatusSelectItems();
    initAgentTypeSelectItems();
  }

  public void initForOpeningPage() {
    logger.debug("Start initForOpeningPage method");
    initCentreSelectItems();
    logger.debug("End initForOpeningPage method");
  }

  private void initRegionSelectItems() {
    this.regionSelectItems = new ArrayList<>();
    this.regionSelectItems.add(new SelectItem(Region.NONE.getValue(), Region.NONE.getName()));
    this.regionSelectItems.add(new SelectItem(Region.NORTHERN.getValue(), Region.NORTHERN.getName()));
    this.regionSelectItems.add(new SelectItem(Region.SOUTHERN.getValue(), Region.SOUTHERN.getName()));
    this.regionSelectItems.add(new SelectItem(Region.CENTRAL.getValue(), Region.CENTRAL.getName()));
  }

  private void initProvinceIdAndRegionMap(List<TblMapProvince> provinces) {
    this.provinceIdAndRegionMap = new HashMap<>(provinces.size());
    for (TblMapProvince province : provinces) {
      this.provinceIdAndRegionMap.put(province.getProvinceId(), province.getRegion());
    }
  }

  private void initAgentTypeSelectItems() {
    this.agentTypeSelectItems = new ArrayList<>();
    this.agentTypeSelectItems.add(new SelectItem("SHT", "Đại lý SHT"));
  }

  private void initProvinceSelectItems(List<TblMapProvince> provinces) {
    this.provinceSelectItems = new ArrayList<>();
    try {

      for (TblMapProvince province : provinces) {
        this.provinceSelectItems.add(new SelectItem(province.getProvinceId(), province.getProvince()));
      }
    } catch (Exception e) {
      handleExceptionMessage(e, AgentManagementController.class);
    }
  }

  private void initDistrictSelectItems() {
    initDistrictSelectItemsByProvinceId(this.agent.getProvince());
  }

  private void initDistrictSelectItemsByProvinceId(String provinceId) {
    this.districtSelectItems = new ArrayList<>();
    try {
      List<TblMapDistrict> districts = geographicService.getAllDistrictByProvince(provinceId);
      for (TblMapDistrict district : districts) {
        this.districtSelectItems.add(new SelectItem(district.getDistrictId(), district.getDistrict()));
      }
    } catch (Exception e) {
      handleExceptionMessage(e, AgentManagementController.class);
    }
  }

  private void initCentreSelectItems() {
    this.centreSelectItems = new ArrayList<>();

    try {
      List<Centre> centreObjs = centreService.findAllActiveCentre();
      for (Centre centreObj : centreObjs) {
        this.centreSelectItems.add(new SelectItem(centreObj.getCentreId(), centreObj.getName()));
      }
    } catch (Exception e) {
      handleExceptionMessage(e, AgentManagementController.class);
    }

  }

  private void initAgentStatusSelectItems() {
    this.agentStatusSelectItems = new ArrayList<>();
    this.agentStatusSelectItems.add(new SelectItem(Agent.Status.ACTIVE.getValue(), Agent.Status.ACTIVE.getName()));
    this.agentStatusSelectItems.add(new SelectItem(Agent.Status.LOST_SIM.getValue(), Agent.Status.LOST_SIM.getName()));
    this.agentStatusSelectItems.add(new SelectItem(Agent.Status.TEMPORAL_LOCKED.getValue(), Agent.Status.TEMPORAL_LOCKED.getName()));
    this.agentStatusSelectItems.add(new SelectItem(Agent.Status.DELETED.getValue(), Agent.Status.DELETED.getName()));
  }

  /**
   * Listeners
   */
  public void doFindAgent() {

    try {
      agent = agentManagementService.getAgentByMSISDN(agentDTO.getMsisdn());
      parentAgent = new Agent();
      if (agent == null || agent.getAgentId() == null) {
        FacesUtil.addSuccessMessage("Không tìm thấy đại lý", "");
        this.enableDeleting = false;
        this.enableEditing = false;
        this.enableAdding = true;
        agent = new Agent();
        subAgents = new ArrayList<>();
      } else {
        setEnableDeleting(agent.getStatus() == Agent.Status.TEMPORAL_LOCKED.getValue());
        this.enableEditing = true;
        this.enableAdding = false;
        setCurrentActive(agent.getStatus() == Agent.Status.ACTIVE.getValue());
        subAgents = agentManagementService.getSubAgentByAgentId(agent.getAgentId());
      }

      findParentAgentFromCurrentAgent();

      updateRegion();
      enableConfirmEditing = false;

    } catch (Exception e) {
      handleExceptionMessage(e, AgentManagementController.class);
    }
  }

  private void findParentAgentFromCurrentAgent() {
    try {
      parentAgent = agentManagementService.getAgentByAgentId(agent.getParentId());
    } catch (Exception e) {
      handleExceptionMessage(e, AgentManagementController.class);
    }
  }

  public void doAddAgent() {
    Long backupAgentId = agent.getAgentId();
    Long emptyAgentId = 0L;
    try {
      if (validateChoosingParentAgent()) {
        agent.setAgentId(emptyAgentId);
        agent = agentManagementService.create(agent);
        FacesUtil.addSuccessMessage(getMessage(MessageConstants.ETOPUP_AGENT_MANAGEMENT_CREATE), "");
        //agentDTO.setMsisdn(agent.getMsisdn());
        //doFindAgent();
        agent = new Agent();
        this.enableEditing = false;
        this.enableDeleting = false;
        this.enableAdding = true;
      }
    } catch (Exception e) {
      agent.setAgentId(backupAgentId);
      handleExceptionMessage(e, AgentManagementController.class);
    }
  }

  public void doChangeAgent() {

    try {
      if (validateChoosingParentAgent()) {
        boolean success = agentManagementService.update(agent);
        if (success) {
          doFindAgent();
          FacesUtil.addSuccessMessage(getMessage(MessageConstants.ETOPUP_AGENT_MANAGEMENT_UPDATE), "");

          setEnableDeleting(agent.getStatus() == Agent.Status.TEMPORAL_LOCKED.getValue());
        }
      }
    } catch (Exception e) {
      handleExceptionMessage(e, AgentManagementController.class);
    }
  }


  private boolean validateChoosingParentAgent() {

/*
    if (parentAgent.getMsisdn() != null && !parentAgent.getMsisdn().trim().equals("")) {
      Agent parent = potentialParentAgentsMap.get(this.parentAgent.getMsisdn());
      if (parent == null) {
        FacesUtil.addErrorMsg(null, "Không tìm thấy đại lý cấp trên đã chọn", "");
        return false;
      }

      this.parentAgent = parent;
      agent.setParentId(parent.getAgentId());
    } else {
      agent.setParentId(null);
    }
*/

    return true;
  }

  public void doDeleteAgent() {
    try {
      boolean success = agentManagementService.delete(agent.getAgentId());
      if (success) {
        doFindAgent();
        FacesUtil.addSuccessMessage(getMessage(MessageConstants.ETOPUP_AGENT_MANAGEMENT_DELETE), "");
        this.enableAdding = false;
        this.enableDeleting = false;
        this.enableEditing = true;
      }
    } catch (Exception e) {
      handleExceptionMessage(e, AgentManagementController.class);
    }
  }

  public void onProvinceChange(final AjaxBehaviorEvent event) {
    initDistrictSelectItemsByProvinceId(this.agent.getProvince());
    updateRegion();
  }

  private void updateRegion() {
    this.region = this.provinceIdAndRegionMap.get(this.agent.getProvince());
    if (this.region == null) {
      this.region = Region.NONE.getName();
    }
  }

  public List<String> completeParentMSISDN(String query) {
    List<String> results = new ArrayList<>();
    String preparedQuery = EtopupStringUtil.preprocessQueryToSearchLikeInUI(query);

    List<String> potentialParentMsisdns = getPotentialParentMsisdns();

    for (String potentialParentMsisdn : potentialParentMsisdns) {
      if (potentialParentMsisdn.matches(preparedQuery)) {
        results.add(potentialParentMsisdn);
      }
    }

    return results;
  }

  private List<String> getPotentialParentMsisdns() {
    AgentManagementDTO dto = new AgentManagementDTO();
    List<String> potentialParentAgents = new ArrayList<>();
    try {

      if (agent != null) {
        dto.setAgentId(agent.getAgentId());
      }
      potentialParentAgents = agentManagementService.getPotentialSuperiorAgentMsisdnByAgentId(dto);
    } catch (Exception e) {
      handleExceptionMessage(e, AgentManagementController.class);
    }

    return potentialParentAgents;
  }

  public void handleSelectParentAgentMsisdn() {

    String parentMsisdn = this.parentAgent.getMsisdn();

    try {
      parentAgent = agentManagementService.getAgentByMSISDN(parentMsisdn);
    } catch (Exception e) {
      handleExceptionMessage(e, AgentManagementController.class);
    }

  }

  public void onChangeStatus() {
    enableConfirmEditing = this.currentActive && !this.agent.getStatus().equals(Agent.Status.ACTIVE.getValue());
  }
}
