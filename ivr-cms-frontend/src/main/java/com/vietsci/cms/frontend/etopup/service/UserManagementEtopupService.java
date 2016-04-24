package com.vietsci.cms.frontend.etopup.service;

import java.util.List;

import org.springframework.web.client.RestClientException;

import com.vietsci.cms.frontend.etopup.dto.SearchCriterialUserDTO;
import com.vietsci.cms.frontend.etopup.dto.UserDTO;
import com.vietsci.cms.frontend.etopup.dto.UserManagementDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.model.User;
import com.vietsci.cms.frontend.etopup.model.UserAgentMap;

/**
 * Service for User Management
 *
 * @author lam.lethanh
 *
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */
public interface UserManagementEtopupService {
  
  /**
   * Find user profile based on condition in user management DTO
   * @param userManagementDTO
   * @return
   */
  public List<UserDTO> findUserProfile(SearchCriterialUserDTO searchCriterialUserDTO) throws RestClientException;
  
  /**
   * @param userManagementDTO
   * @return
   */
  public List<Agent> findAgentsMappedToUser(UserManagementDTO userManagementDTO) throws RestClientException;
  
  /**
   * To check that agent is mapped with any user.
   * @return
   */
  public Boolean isAgentMappedWithAnyUser(Long agentId) throws RestClientException;
  
  /**
   * Map agent to user
   * @return
   */
  public Boolean mapAgentWithUser(UserAgentMap userAgentMap) throws RestClientException;
  
  /**
   * Delete mapped agent
   * @param userAgentMap
   * @return
   */
  public Boolean deleteMappedAgent(Long agentId) throws RestClientException;
  
  
  public User findUserProfile(String userName) throws RestClientException;
  
  public UserDTO findUserProfileByUserId(Long userId) throws RestClientException;
  
}
