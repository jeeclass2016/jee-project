package com.vietsci.cms.frontend.etopup.controller;

import com.vietsci.cms.frontend.etopup.dto.AgentManagementDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.model.Centre;
import com.vietsci.cms.frontend.etopup.model.TblMapDistrict;
import com.vietsci.cms.frontend.etopup.model.TblMapProvince;
import com.vietsci.cms.frontend.etopup.service.AgentManagementService;
import com.vietsci.cms.frontend.etopup.service.CentreService;
import com.vietsci.cms.frontend.etopup.service.GeographicService;
import com.vietsci.cms.frontend.util.FacesUtil;

import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@Scope(value = "session")
public class AgentTraversalController extends EtopupBaseController implements Serializable {
  private static final long serialVersionUID = 4656153261121555136L;

  @Inject
  AgentManagementService agentManagementService;

  @Inject
  CentreService centreService;

  @Inject
  GeographicService geographicService;

  private AgentManagementDTO agentManagementDTO = new AgentManagementDTO();
  private List<Agent> agents;
  private Agent selectedAgent = new Agent();
  private List<SelectItem> centreSelectItems;
  private List<SelectItem> statusSelectItems;
  private List<SelectItem> agentStatusSelectItems;
  private List<SelectItem> agentStatusSelectItemsForSearching;
  private List<SelectItem> provinceSelectItems;
  private List<SelectItem> districtSelectItems;
  private List<SelectItem> agentTypeSelectItems;
  private boolean enableAccept = false;
  private boolean enableRenew = false;
  private boolean enableReject = false;

  public AgentManagementDTO getAgentManagementDTO() {
    return agentManagementDTO;
  }

  public void setAgentManagementDTO(AgentManagementDTO agentManagementDTO) {
    this.agentManagementDTO = agentManagementDTO;
  }

  public List<SelectItem> getAgentStatusSelectItems() {
    return agentStatusSelectItems;
  }

  public void setAgentStatusSelectItems(List<SelectItem> agentStatusSelectItems) {
    this.agentStatusSelectItems = agentStatusSelectItems;
  }

  public List<Agent> getAgents() {
    return agents;
  }

  public void setAgents(List<Agent> agents) {
    this.agents = agents;
  }

  public Agent getSelectedAgent() {
    return selectedAgent;
  }

  public void setSelectedAgent(Agent selectedAgent) {
    this.selectedAgent = selectedAgent;
  }

  public List<SelectItem> getCentreSelectItems() {
    return centreSelectItems;
  }

  public void setCentreSelectItems(List<SelectItem> centreSelectItems) {
    this.centreSelectItems = centreSelectItems;
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

  public boolean isEnableAccept() {
    return enableAccept;
  }

  public void setEnableAccept(boolean enableAccept) {
    this.enableAccept = enableAccept;
  }

  public boolean isEnableRenew() {
    return enableRenew;
  }

  public void setEnableRenew(boolean enableRenew) {
    this.enableRenew = enableRenew;
  }

  public boolean isEnableReject() {
    return enableReject;
  }

  public void setEnableReject(boolean enableReject) {
    this.enableReject = enableReject;
  }

  public List<SelectItem> getAgentStatusSelectItemsForSearching() {
    return agentStatusSelectItemsForSearching;
  }

  public void setAgentStatusSelectItemsForSearching(List<SelectItem> agentStatusSelectItemsForSearching) {
    this.agentStatusSelectItemsForSearching = agentStatusSelectItemsForSearching;
  }

  public List<SelectItem> getStatusSelectItems() {
    return statusSelectItems;
  }

  public void setStatusSelectItems(List<SelectItem> statusSelectItems) {
    this.statusSelectItems = statusSelectItems;
  }

  /**
   * For initializing data
   */
  @PostConstruct
  public void initForSession() {

    List<TblMapProvince> provinces = geographicService.getAllProvince();
    initProvinceSelectItems(provinces);

    initDistrictSelectItems();

    initStatusSelectItems();
    initAgentStatusSelectItems();
    initAgentStatusSelectItemsForSearching();
    initAgentTypeSelectItems();
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
    initDistrictSelectItemsByProvinceId(selectedAgent.getProvince());
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

  private void initStatusSelectItems() {
    this.statusSelectItems = new ArrayList<>();
    this.statusSelectItems.add(new SelectItem(Agent.Status.ACTIVE.getValue(), Agent.Status.ACTIVE.getName()));
    this.statusSelectItems.add(new SelectItem(Agent.Status.LOST_SIM.getValue(), Agent.Status.LOST_SIM.getName()));
    this.statusSelectItems.add(new SelectItem(Agent.Status.TEMPORAL_LOCKED.getValue(), Agent.Status.TEMPORAL_LOCKED.getName()));
    this.statusSelectItems.add(new SelectItem(Agent.Status.DELETED.getValue(), Agent.Status.DELETED.getName()));
  }

  private void initAgentStatusSelectItems() {
    this.agentStatusSelectItems = new ArrayList<>();
    this.agentStatusSelectItems.add(new SelectItem(Agent.AgentStatus.ACTIVE.getValue(), Agent.AgentStatus.ACTIVE.getName()));
    this.agentStatusSelectItems.add(new SelectItem(Agent.AgentStatus.IDLE.getValue(), Agent.AgentStatus.IDLE.getName()));
    this.agentStatusSelectItems.add(new SelectItem(Agent.AgentStatus.NOT_PASS_AUDIT.getValue(), Agent.AgentStatus.NOT_PASS_AUDIT.getName()));
    this.agentStatusSelectItems.add(new SelectItem(Agent.AgentStatus.PASS_AUDIT.getValue(), Agent.AgentStatus.PASS_AUDIT.getName()));
    this.agentStatusSelectItems.add(new SelectItem(Agent.AgentStatus.NEW.getValue(), Agent.AgentStatus.NEW.getName()));
  }

  private void initAgentStatusSelectItemsForSearching() {
    this.agentStatusSelectItemsForSearching = new ArrayList<>();
    this.agentStatusSelectItemsForSearching.add(new SelectItem(Agent.AgentStatus.ACTIVE.getValue(), Agent.AgentStatus.ACTIVE.getName()));
    this.agentStatusSelectItemsForSearching.add(new SelectItem(Agent.AgentStatus.IDLE.getValue(), Agent.AgentStatus.IDLE.getName()));
    this.agentStatusSelectItemsForSearching.add(new SelectItem(Agent.AgentStatus.NOT_PASS_AUDIT.getValue(), Agent.AgentStatus.NOT_PASS_AUDIT.getName()));
    this.agentStatusSelectItemsForSearching.add(new SelectItem(Agent.AgentStatus.PASS_AUDIT.getValue(), Agent.AgentStatus.PASS_AUDIT.getName()));
    this.agentStatusSelectItemsForSearching.add(new SelectItem(Agent.AgentStatus.NEW.getValue(), Agent.AgentStatus.NEW.getName()));
  }

  private void initAgentTypeSelectItems() {
    this.agentTypeSelectItems = new ArrayList<>();
    this.agentTypeSelectItems.add(new SelectItem("SHT", "Đại lý SHT"));
  }


  public void doFindAgents() {
    try {
      reformatMsisdnBeforeSearching();
      agents = agentManagementService.doFindAgentsByMsisdnAndAgentStatus(agentManagementDTO);
      this.enableAccept = false;
      this.enableReject = false;
      this.enableRenew = false;
      this.selectedAgent = new Agent();

    } catch (Exception e) {
      handleExceptionMessage(e, AgentManagementController.class);
    }
  }

  private void reformatMsisdnBeforeSearching() {
    String msisdn = agentManagementDTO.getMsisdn();
    if (msisdn != null) {
      agentManagementDTO.setMsisdn(msisdn.trim());
    }
  }

  public void doAccept() {

    String backupAgentStatus = selectedAgent.getAgentStatus();

    try {

      if (selectedAgent.getAgentStatus().equalsIgnoreCase(Agent.AgentStatus.ACTIVE.getValue())) {
        selectedAgent.setAgentStatus(Agent.AgentStatus.PASS_AUDIT.getValue());
        agentManagementService.doUpdateAgentStatus(selectedAgent);
        FacesUtil.addSuccessMessage("Đồng ý thành công", "");
        disableButtons();
      }

    } catch (Exception e) {
      handleExceptionMessage(e, AgentManagementController.class);
      //Revert Agent Status as before changing
      selectedAgent.setAgentStatus(backupAgentStatus);
    }
  }

  public void doRenew() {

    String backupAgentStatus = selectedAgent.getAgentStatus();

    try {

      if (selectedAgent.getAgentStatus().equalsIgnoreCase(Agent.AgentStatus.IDLE.getValue())) {
        selectedAgent.setAgentStatus(Agent.AgentStatus.ACTIVE.getValue());
        agentManagementService.doUpdateAgentStatus(selectedAgent);
        FacesUtil.addSuccessMessage("Renew thành công", "");
        updateButtonsForActiveAgentStatus();
      }

    } catch (Exception e) {
      handleExceptionMessage(e, AgentManagementController.class);
      //Revert Agent Status as before changing
      selectedAgent.setAgentStatus(backupAgentStatus);
    }
  }

  public void doReject() {

    String backupAgentStatus = selectedAgent.getAgentStatus();
    Integer backupStatus = selectedAgent.getStatus();

    try {
      if (selectedAgent.getAgentStatus().equalsIgnoreCase(Agent.AgentStatus.ACTIVE.getValue())) {
        selectedAgent.setAgentStatus(Agent.AgentStatus.NOT_PASS_AUDIT.getValue());
        selectedAgent.setStatus(Agent.Status.DELETED.getValue());
        agentManagementService.doUpdateAgentStatus(selectedAgent);
        FacesUtil.addSuccessMessage("Từ chối thành công", "");
        disableButtons();
      }

    } catch (Exception e) {
      handleExceptionMessage(e, AgentManagementController.class);
      //Revert Agent Status as before changing
      selectedAgent.setAgentStatus(backupAgentStatus);
      selectedAgent.setStatus(backupStatus);
    }
  }

  public void onSelectRowListener() {

    if (selectedAgent.getAgentStatus().equalsIgnoreCase(Agent.AgentStatus.ACTIVE.getValue())) {
      updateButtonsForActiveAgentStatus();
    } else if (selectedAgent.getAgentStatus().equalsIgnoreCase(Agent.AgentStatus.NEW.getValue())) {
      updateButtonsForNewAgentStatus();
    } else if (selectedAgent.getAgentStatus().equalsIgnoreCase(Agent.AgentStatus.IDLE.getValue())) {
      updateButtonsForIdleAgentStatus();
    } else if (selectedAgent.getAgentStatus().equalsIgnoreCase(Agent.AgentStatus.PASS_AUDIT.getValue())) {
      disableButtons();
    } else {
      disableButtons();
    }

    initCentreSelectItems();

  }

  private void updateButtonsForNewAgentStatus() {
    enableAccept = false;
    enableReject = false;
    enableRenew = false;
  }

  private void updateButtonsForActiveAgentStatus() {
    enableAccept = true;
    enableReject = true;
    enableRenew = false;
  }

  private void updateButtonsForIdleAgentStatus() {
    enableAccept = false;
    enableReject = false;
    enableRenew = true;
  }

  private void disableButtons() {
    enableAccept = false;
    enableReject = false;
    enableRenew = false;
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
}
