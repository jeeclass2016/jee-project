package com.vietsci.cms.frontend.etopup.controller;


import com.vietsci.cms.frontend.etopup.dto.AgentDataChangeDTO;
import com.vietsci.cms.frontend.etopup.dto.AgentDataChangeDTO.FieldName;
import com.vietsci.cms.frontend.etopup.primefaces.AgentChangeHistoryLazyDataModel;
import com.vietsci.cms.frontend.etopup.service.AgentHistService;
import com.vietsci.cms.frontend.etopup.service.AgentManagementService;
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
public class AgentHistoryLookupController extends EtopupBaseController implements Serializable {
  private static final long serialVersionUID = -3223155522154897981L;

  @Inject
  AgentHistService agentHistService;

  @Inject
  AgentManagementService agentManagementService;

  private AgentDataChangeDTO agentDataChangeDTO = new AgentDataChangeDTO();
  private AgentChangeHistoryLazyDataModel agentChangeHistoryLazyDataModel;
  private List<SelectItem> fieldSelectItems;


  public AgentDataChangeDTO getAgentDataChangeDTO() {
    return agentDataChangeDTO;
  }

  public void setAgentDataChangeDTO(AgentDataChangeDTO agentDataChangeDTO) {
    this.agentDataChangeDTO = agentDataChangeDTO;
  }

  public AgentChangeHistoryLazyDataModel getAgentChangeHistoryLazyDataModel() {
    return agentChangeHistoryLazyDataModel;
  }

  public void setAgentChangeHistoryLazyDataModel(AgentChangeHistoryLazyDataModel agentChangeHistoryLazyDataModel) {
    this.agentChangeHistoryLazyDataModel = agentChangeHistoryLazyDataModel;
  }

  public List<SelectItem> getFieldSelectItems() {
    return fieldSelectItems;
  }

  public void setFieldSelectItems(List<SelectItem> fieldSelectItems) {
    this.fieldSelectItems = fieldSelectItems;
  }

  @PostConstruct
  public void init() {
    initFieldSelectItems();
    agentDataChangeDTO.setFieldName(FieldName.ACTIVE_DATE.getName());
    agentChangeHistoryLazyDataModel = new AgentChangeHistoryLazyDataModel(agentHistService, agentDataChangeDTO);
  }

  private void initFieldSelectItems() {
    this.fieldSelectItems = new ArrayList<>();
    this.fieldSelectItems.add(new SelectItem(FieldName.ACTIVE_DATE.getValue(), FieldName.ACTIVE_DATE.getName()));
    this.fieldSelectItems.add(new SelectItem(FieldName.AGENT_STATUS.getValue(), FieldName.AGENT_STATUS.getName()));
    this.fieldSelectItems.add(new SelectItem(FieldName.BIRTH_DATE.getValue(), FieldName.BIRTH_DATE.getName()));
    this.fieldSelectItems.add(new SelectItem(FieldName.CONTACT_NO.getValue(), FieldName.CONTACT_NO.getName()));
    this.fieldSelectItems.add(new SelectItem(FieldName.CREATE_DATE.getValue(), FieldName.CREATE_DATE.getName()));
    this.fieldSelectItems.add(new SelectItem(FieldName.EMAIL.getValue(), FieldName.EMAIL.getName()));
    this.fieldSelectItems.add(new SelectItem(FieldName.ICCID.getValue(), FieldName.ICCID.getName()));
    this.fieldSelectItems.add(new SelectItem(FieldName.MSISDN.getValue(), FieldName.MSISDN.getName()));
    this.fieldSelectItems.add(new SelectItem(FieldName.OUTLET_ADDRESS.getValue(), FieldName.OUTLET_ADDRESS.getName()));
    this.fieldSelectItems.add(new SelectItem(FieldName.OWNER_NAME.getValue(), FieldName.OWNER_NAME.getName()));
    this.fieldSelectItems.add(new SelectItem(FieldName.SAP_CODE.getValue(), FieldName.SAP_CODE.getName()));
    this.fieldSelectItems.add(new SelectItem(FieldName.TRADE_NAME.getValue(), FieldName.TRADE_NAME.getName()));
  }

  public void doFindHist() {
    if (validateTimeRange()) {
      agentChangeHistoryLazyDataModel = new AgentChangeHistoryLazyDataModel(agentHistService, agentDataChangeDTO);
    } else {
      FacesUtil.addErrorMsg(null, "\"Từ ngày\" phải trước \"Đến ngày\"", "");
    }
  }

  private boolean validateTimeRange() {
    if (agentDataChangeDTO.getFromDate() != null && agentDataChangeDTO.getToDate() != null) {
      return !agentDataChangeDTO.getFromDate().after(agentDataChangeDTO.getToDate());
    } else {
      return true;
    }
  }
  
  public String showFieldName(String fieldValue) {
    return AgentDataChangeDTO.FieldName.getNameByValue(fieldValue);
  }
}
