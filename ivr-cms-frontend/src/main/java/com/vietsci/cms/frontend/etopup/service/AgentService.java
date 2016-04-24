package com.vietsci.cms.frontend.etopup.service;

import java.util.List;

import com.vietsci.cms.frontend.etopup.dto.AgentDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;


/** 
 * Interface for communicating with eTopUp API to process with Agent.
 * 
 * @author lam.lethanh
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
public interface AgentService {
  
  /**
   * Tìm kiếm đại lý dựa trên search criterias.
   * @param agentDTO chứa các thông tin để tìm kiếm
   * @return List<Agent> danh sách các đại lý thỏa mãn điều kiện tìm kiếm
   */
  public List<Agent> doFindAgents(AgentDTO agentSearchCriteria);
  
  /**
   * Thêm đại lý dựa trên dữ liệu mới.
   * @param agent chứa thông tin của đại lý mới sẽ được thêm vào database
   */
  public boolean doAddAgent(Agent agent) throws Exception;
  
  /**
   * Xóa đại lý dựa trên dữ liệu ID của đại lý.
   * @param agentId ID của đại lý.
   */
  public void deleteAgent(long agentId) throws Exception;

}
