package com.vietsci.cms.frontend.etopup.service;

import java.util.List;

import com.vietsci.cms.frontend.etopup.dto.AgentDataChangeDTO;
import com.vietsci.cms.frontend.etopup.model.AgentDataChange;
public interface AgentHistService {

  
  /**
   * Todo: get all agent changes history
   * Return type:List<AgentDataChange>
   * Created by: quanglh
   * Created date: May 7, 2014 6:57:55 PM
   */
  List<AgentDataChange> getAllAgentChanges(AgentDataChangeDTO agentDataChangeDTO);
  
  /**
   * Todo: count all agent changes
   * Return type:Long
   * Created by: quanglh
   * Created date: May 7, 2014 6:57:58 PM
   */
  Long countAllAgentChanges(AgentDataChangeDTO agentDataChangeDTO);
}
