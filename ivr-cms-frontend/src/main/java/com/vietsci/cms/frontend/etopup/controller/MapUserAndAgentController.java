package com.vietsci.cms.frontend.etopup.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestClientException;

import com.vietsci.cms.frontend.etopup.dto.AgentManagementDTO;
import com.vietsci.cms.frontend.etopup.dto.SearchCriterialUserDTO;
import com.vietsci.cms.frontend.etopup.dto.UserDTO;
import com.vietsci.cms.frontend.etopup.dto.UserManagementDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.model.User;
import com.vietsci.cms.frontend.etopup.model.UserAgentMap;
import com.vietsci.cms.frontend.etopup.service.AgentManagementService;
import com.vietsci.cms.frontend.etopup.service.UserManagementEtopupService;
import com.vietsci.cms.frontend.util.FacesUtil;

@Named
@Scope(value = "session")
public class MapUserAndAgentController extends EtopupBaseController implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = 8100651247624187055L;

  /**
   * logger.
   */
  private static final Log logger = LogFactory.getLog(MapUserAndAgentController.class);

  @Inject
  private UserManagementEtopupService userManagementEtopupService;

  @Inject
  private AgentManagementService agentManagementService;

  private UserManagementDTO userManagementDTO;
  private AgentManagementDTO agentManagementDTO;
  private SearchCriterialUserDTO searchCriterialUserDTO;
  private UserDTO userDTO;
  private Agent newMappedAgent;
  private Agent agent;
  private List<Agent> listAgentsMapToUser;
  private boolean enableFindingAgent = false;
  private boolean enableCreatingMap = false;

  /* ============= Getter and setter methods ============= */
  public UserManagementDTO getUserManagementDTO() {
    return userManagementDTO;
  }

  public void setUserManagementDTO(UserManagementDTO userManagementDTO) {
    this.userManagementDTO = userManagementDTO;
  }

  public AgentManagementDTO getAgentManagementDTO() {
    return agentManagementDTO;
  }

  public void setAgentManagementDTO(AgentManagementDTO agentManagementDTO) {
    this.agentManagementDTO = agentManagementDTO;
  }

  public SearchCriterialUserDTO getSearchCriterialUserDTO() {
    return searchCriterialUserDTO;
  }

  public void setSearchCriterialUserDTO(SearchCriterialUserDTO searchCriterialUserDTO) {
    this.searchCriterialUserDTO = searchCriterialUserDTO;
  }

  public UserDTO getUserDTO() {
    return userDTO;
  }

  public void setUserDTO(UserDTO userDTO) {
    this.userDTO = userDTO;
  }

  public Agent getNewMappedAgent() {
    return newMappedAgent;
  }

  public void setNewMappedAgent(Agent newMappedAgent) {
    this.newMappedAgent = newMappedAgent;
  }

  public Agent getAgent() {
    return agent;
  }

  public void setAgent(Agent agent) {
    this.agent = agent;
  }

  public List<Agent> getListAgentsMapToUser() {
    return listAgentsMapToUser;
  }

  public void setListAgentsMapToUser(List<Agent> listAgentsMapToUser) {
    this.listAgentsMapToUser = listAgentsMapToUser;
  }

  public boolean isEnableFindingAgent() {
    return enableFindingAgent;
  }

  public void setEnableFindingAgent(boolean enableFindingAgent) {
    this.enableFindingAgent = enableFindingAgent;
  }

  public boolean isEnableCreatingMap() {
    return enableCreatingMap;
  }

  public void setEnableCreatingMap(boolean enableCreatingMap) {
    this.enableCreatingMap = enableCreatingMap;
  }

  /* ============= Business methods ============= */
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init MapUserAndAgentController members");
    userManagementDTO = new UserManagementDTO();
    agentManagementDTO = new AgentManagementDTO();
    newMappedAgent = new Agent();
    searchCriterialUserDTO = new SearchCriterialUserDTO();
    userDTO = new UserDTO();
    agent = new Agent();
    enableFindingAgent = false;
    enableCreatingMap = false;
    listAgentsMapToUser = new ArrayList<>();
    logger.debug("Init MapUserAndAgentController members successfully");
  }

  /**
   * Find user profile based on user name
   */
  public void doFindUserProfile() throws Exception {
    logger.debug("Find user profile  with user name: " + userManagementDTO.getUserName());
    enableFindingAgent = false;
    listAgentsMapToUser = new ArrayList<>();
    FacesContext context = FacesContext.getCurrentInstance();
    
    try {
      searchCriterialUserDTO.setUserName(userManagementDTO.getUserName());
      User user = userManagementEtopupService.findUserProfile(userManagementDTO.getUserName());
      if (user == null || user.getUserId() == null) {
        context.addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Không tìm thấy người sử dụng", ""));
        userDTO = new UserDTO();
        enableFindingAgent = false;
        return;
      } 
      userDTO = new UserDTO();
      userDTO.setUserId(user.getUserId());
      userDTO.setUserName(user.getUserName());
      userDTO.setFullName(user.getFullName());
      userDTO.setAddress(user.getAddress());

      Long userId = userDTO.getUserId() != null ? userDTO.getUserId() : 0L;
      userManagementDTO.setUserId(userId);

      doFindUserAgentMap();
      enableFindingAgent = true;

    } catch (Exception e) {
      handleExceptionMessage(e, MapUserAndAgentController.class);
    }
  }

  private void doFindUserAgentMap() throws RestClientException {
    listAgentsMapToUser = userManagementEtopupService.findAgentsMappedToUser(userManagementDTO);
  }

  /**
   * Find agent by msisdn
   */
  public void doFindAgent() {
    logger.debug("Find agent with agent MSISDN: " + agentManagementDTO.getMsisdn());
    enableCreatingMap = false;
    try {
      newMappedAgent = agentManagementService.getAgentByMSISDN(agentManagementDTO.getMsisdn());
      
      if (newMappedAgent == null || newMappedAgent.getAgentId() == null) {
        FacesUtil.addErrorMsg(null, "Không tìm thấy kết quả nào", "");
        return;
      }
      Integer status = newMappedAgent.getStatus();
      String agentStatus = newMappedAgent.getAgentStatus();
      String mpinStatus = newMappedAgent.getMpinStatus();
      
      if (status != null && status.intValue() == 4) {
        FacesUtil.addErrorMsg(null, "Đại lý đã bị hủy", "");
        return;
      }
      
      boolean isActiveAgent = ("2".equals(agentStatus) || "5".equals(agentStatus)) && "1".equals(mpinStatus); 
      
      if ( !isActiveAgent ) {
        FacesUtil.addErrorMsg(null, "Đại lý chưa được active", "");
        return;
      }
      
      if ( isAgentMappedWithUser() ) {
        FacesUtil.addErrorMsg(null, "Đại lý đã được map với người sử dụng", "");
        return;
      }
      
      enableCreatingMap = true;
    } catch (Exception e) {
      handleExceptionMessage(e, MapUserAndAgentController.class);
    }
  }
  
  private boolean isAgentMappedWithUser() {

    if (listAgentsMapToUser == null || listAgentsMapToUser.size() <= 0) {
      return false;
    }
    Long newMappedAgentId = newMappedAgent.getAgentId();
    for (Agent agent : listAgentsMapToUser) {
      if (newMappedAgentId.longValue() == agent.getAgentId().longValue()) {
        return true;
      }
    }
    boolean isAgentMappedWithOtherUser = userManagementEtopupService.isAgentMappedWithAnyUser(newMappedAgentId);
    return isAgentMappedWithOtherUser;
  }

  /**
   * Save mapping between agent and user to DB
   */
  public void doMapAgentWithUser() {
    logger.debug("Map user having user id: " + userDTO.getUserId()
      + " with agent having agent id: " + newMappedAgent.getAgentId());

    try {
      UserAgentMap userAgentMap = new UserAgentMap();
      int userId;
      userId = (userDTO.getUserId() != null) ? userDTO.getUserId().intValue() : 0;
      userAgentMap.setUserId(userId);
      userAgentMap.setAgentId(newMappedAgent.getAgentId());

      userManagementEtopupService.mapAgentWithUser(userAgentMap);

      FacesUtil.addSuccessMessage("Tạo Map Người sử dụng và Đại lý thành công", "");
      doFindUserAgentMap();
    } catch (Exception e) {
      handleExceptionMessage(e, MapUserAndAgentController.class);
    }
  }

  /**
   * Save mapping between agent and user to DB
   */
  public void deleteMappedAgent(Agent selectedAgent) {
    logger.debug("Delete mapped agent having agent id: " + agent.getAgentId());
    try {
      userManagementEtopupService.deleteMappedAgent(selectedAgent.getAgentId());
      FacesUtil.addSuccessMessage("Đã xóa map Người sử dụng và Đại lý thành công", "");
      doFindUserAgentMap();
      enableFindingAgent = true;
    } catch (Exception e) {
      handleExceptionMessage(e, MapUserAndAgentController.class);
    }
  }

  public void onSelectRowListener() {
    //Just want to make sure ajax was called
  }

}
