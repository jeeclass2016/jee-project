package com.vietsci.cms.frontend.etopup.controller;

import com.vietsci.cms.frontend.etopup.dto.TransactionManagementDTO;
import com.vietsci.cms.frontend.etopup.dto.UserDTO;
import com.vietsci.cms.frontend.etopup.model.*;
import com.vietsci.cms.frontend.etopup.primefaces.BonusStatusBrowserEtopupDataModel;
import com.vietsci.cms.frontend.etopup.service.AgentManagementService;
import com.vietsci.cms.frontend.etopup.service.EtopupMcSubscriberService;
import com.vietsci.cms.frontend.etopup.service.TransactionManagementService;
import com.vietsci.cms.frontend.etopup.service.UserManagementEtopupService;
import com.vietsci.cms.frontend.util.FacesUtil;

import org.springframework.context.annotation.Scope;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.*;

@Named
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class BonusStatusBrowserController extends EtopupBaseController implements Serializable {

  private static final long serialVersionUID = 5799146597682706286L;

  private BonusStatusBrowserEtopupDataModel model;

  private TransactionManagementDTO managementDTO;

  private Trans transaction;

  private Map<Long, Long> transLogs;
  private List<McSubscriber> mcSubscribers;
  private List<Agent> agents;
  
  @Inject
  private TransactionManagementService transactionManagementService;

  @Inject
  private AgentManagementService agentManagementService;
  
  @Inject
  private UserManagementEtopupService userManagementEtopupService;

  @Inject
  private EtopupMcSubscriberService etopupMcSubscriberService;

  @PostConstruct
  public void init() {
    managementDTO = new TransactionManagementDTO();
    transaction = new Trans();
    transLogs = new HashMap<>();
    mcSubscribers = new ArrayList<>();
    agents= new ArrayList<>();
  }

  public void findBonusStatus() {
    transaction = new Trans();
    List<String> mdn = new ArrayList<>();
    if (validate()) {
      try {
        if(model == null) {
          model = new BonusStatusBrowserEtopupDataModel(transactionManagementService, managementDTO);
        }
        for (Trans trans : (List<Trans>)model.getWrappedData()) {
          if (trans.getTransLogs().iterator().hasNext()) {
            transLogs.put(trans.getTransId(), trans.getTransLogs().iterator().next().getLogId());
          }
          mdn.add(trans.getTargetMsisdn());
        }

        mcSubscribers.addAll(etopupMcSubscriberService.findListActiveMcSubscriberByListMDN(mdn));
        agents.addAll(agentManagementService.findAgentByMsisdnList(mdn));

        onSelectRowListener();
      } catch (ResourceAccessException e) {
        FacesUtil.addErrorMessage(":bonusDetailForm:bonusMessage", e.getMessage());
      }
    }
  }

  private boolean validate() {
    if (managementDTO.getStartDate() == null || managementDTO.getEndDate() == null) {
      FacesUtil.addErrorMessage(":bonusDetailForm:bonusMessage", "Ngày bắt đầu và ngày kết thúc phải được chọn");
      return false;
    }
    return true;
  }

  public Long getTransLog(Long transId) {
    return transLogs.get(transId);
  }

  public String getShtCode(String msisdn) {
    for(Agent agent : agents) {
        if(agent.getMsisdn().equals(msisdn)) {
            return agent.getSapCode();
        }
    }

    return "";
  }

  public String getCosCode(String msisdn) {
    for(McSubscriber subscriber : mcSubscribers) {
      if(subscriber.getMdn().equals(msisdn)) {
        return subscriber.getCosCode();
      }
    }
    return "";
  }

  public void onSelectRowListener() {
    if(transaction != null) {
      getActiveDate();
      getActioner();
    }
  }

  public Date getActiveDate() {
    for(McSubscriber subscriber : mcSubscribers) {
      if(subscriber.getMdn().equals(transaction.getTargetMsisdn())) {
        return subscriber.getDateCreated();
      }
    }
    return null;
  }

  public String getActioner() {
    if (transaction == null) {
      return "";
    }
    if (!transaction.getTransLogs().iterator().hasNext()) {
      return "";
    }
    TransLog transLog = transaction.getTransLogs().iterator().next();
    if (transLog == null) {
      return "";
    }
    Long userId = transLog.getUserId();
    if (userId == null || userId == 0L) {
      return "";
    }
    UserDTO userDTO = userManagementEtopupService.findUserProfileByUserId(userId);
    return userDTO.getFullName();
  }

  public String getChannel() {
    for(ApParam.ChannelType type : ApParam.ChannelType.values()) {
      if(type.getValue() == transaction.getChannel()) {
        return type.getName();
      }
    }
    return "";
  }


  /*GETTER AND SETTER*/
  public BonusStatusBrowserEtopupDataModel getModel() {
    return model;
  }

  public void setModel(BonusStatusBrowserEtopupDataModel model) {
    this.model = model;
  }

  public TransactionManagementDTO getManagementDTO() {
    return managementDTO;
  }

  public void setManagementDTO(TransactionManagementDTO managementDTO) {
    this.managementDTO = managementDTO;
  }

  public Trans getTransaction() {
    return transaction;
  }

  public void setTransaction(Trans transaction) {
    this.transaction = transaction;
  }
}
