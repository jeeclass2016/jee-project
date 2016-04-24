package com.vietsci.cms.frontend.etopup.service;

import com.vietsci.cms.frontend.etopup.dto.AgentBatchTraversalDTO;
import com.vietsci.cms.frontend.etopup.dto.AgentBatchUploadingDTO;
import com.vietsci.cms.frontend.etopup.dto.AgentManagementDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;

import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import java.util.List;


/**
 * @author lehuyquang
 */
public interface AgentManagementService {
  /**
   * file Agency by its MSISDN
   * lehuyquang
   *
   * @param agentMSISDN
   * @return Agent
   *         Apr 4, 2014
   */
  Agent getAgentByMSISDN(String agentMSISDN) throws RestClientException, ResourceAccessException;

  /**
   * Add new an Agent object
   * lehuyquang
   *
   * @param newAgent
   * @return Agent
   *         Apr 4, 20
   */
  public Agent create(Agent newAgent) throws RestClientException, ResourceAccessException;
  
  /**
   * file Agency by its agentId
   * lehuyquang
   *
   * @param agentMSISDN
   * @return Agent
   *         Apr 4, 2014
   */
  Agent getAgentByAgentId(Long agentId) throws RestClientException, ResourceAccessException;

  /**
   * update an Agent object
   * lehuyquang
   *
   * @param newAgent
   * @return Agent
   *         Apr 4, 20
   */
  public Boolean update(Agent newAgent) throws RestClientException, ResourceAccessException;

  /**
   * to mark an Agent as deleted
   * lehuyquang
   *
   * @param agentId
   * @throws RestClientException, ResourceAccessException
   *                              void
   *                              Apr 4, 2014
   */
  public Boolean delete(long agentId) throws RestClientException, ResourceAccessException;

  /**
   * get subAgent by agentId
   * lehuyquang
   *
   * @param agentId
   * @return List<Agent>
   *         Apr 4, 2014
   */
  List<Agent> getSubAgentByAgentId(long agentId) throws RestClientException, ResourceAccessException;

  /**
   * get all active potential superior agent by agentId
   * lehuyquang
   *
   * @param agentId
   * @return List<Agent>
   *         Apr 4, 2014
   */
  List<String> getPotentialSuperiorAgentMsisdnByAgentId(AgentManagementDTO agentId) throws RestClientException, ResourceAccessException;

  /**
   * find number of records that matches the searching criteria
   * lehuyquang
   *
   * @param agentDTO void
   *                 Mar 26, 2014
   */
  Long countPotentialSuperiorAgent(AgentManagementDTO agentDTO) throws RestClientException, ResourceAccessException;

  /**
   * Search Agents that satisfy searching criteria
   * @param agentSearchCriteria
   * @return A list of Agents
   * @throws RestClientException
   * @throws ResourceAccessException
   * @author hong.nguyenmai
   */
  public List<Agent> doFindAgents(AgentBatchUploadingDTO agentSearchCriteria) throws RestClientException, ResourceAccessException;

  /**
   * Calculate the number of Agents that satisfy searching criteria
   * @param agentSearchCriteria
   * @return the number of Agents
   * @throws RestClientException
   * @throws ResourceAccessException
   * @author hong.nguyenmai
   */
  public Long countAgents(AgentBatchUploadingDTO agentSearchCriteria) throws RestClientException, ResourceAccessException;

  public Boolean addAgentBatch(List<String> dataList) throws Exception;
  
  /**
   * Change ICCID and MSISDN of an agent
   * @param agent
   * @return success or failed in updating
   */
  public Boolean changeIccidAndMsisdnOfAgent(Agent agent);

  /**
   * Search Agents that satisfy searching criteria
   *
   * @param agentManagementDTO
   * @return A list of Agents
   * @throws RestClientException
   * @throws ResourceAccessException
   * @author xuan.nguyenmai
   */
  public List<Agent> doFindAgentsByMsisdnAndAgentStatus(AgentManagementDTO agentManagementDTO) throws RestClientException, ResourceAccessException;

  /**
   * Update Agent Status
   *
   * @param agent
   * @throws RestClientException
   * @throws ResourceAccessException
   * @author xuan.nguyenmai
   */
  public void doUpdateAgentStatus(Agent agent) throws RestClientException, ResourceAccessException;

  /**
   * Reset password of agent
   *
   * @param agent
   * @throws RestClientException
   * @throws ResourceAccessException
   * @author xuan.nguyenmai
   */
  public void doResetAgentPassword(Agent agent, long userId, String userName) throws RestClientException, ResourceAccessException;

  public List<Agent> findAgentByMsisdnList(List<String> msisdns) throws RestClientException, ResourceAccessException;

  /**
   * Find Agents for agent batch traversal
   * @param msisdnList
   * @return a list of Agents
   * @throws RestClientException
   *
   * @author hong.nguyenmai
   */
  public List<AgentBatchTraversalDTO> findAgentsForBatchTraversal(List<String> msisdnList) throws RestClientException;

  /**
   * Traverse a batch of agents
   * @param msisdnList
   * @return TRUE if success, FALSE if fail
   * @throws RestClientException
   *
   * @author hong.nguyenmai
   */
  public boolean traverseAgents(List<String> msisdnList, long stockStaffId) throws RestClientException;

  public Agent findAgentByDealerCodeFromErpPosSaleOrder(String dealerCode) throws RestClientException, ResourceAccessException;;
}
