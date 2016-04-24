package com.vietsci.cms.frontend.etopup.primefaces;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.AgentBatchUploadingDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.service.AgentManagementService;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;

/**
 * LazyDataModel for AgentBatchUploading (UploadDaiLyTheoLo)
 *
 * @author hong.nguyenmai
 *
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */

public class AgentBatchUploadingEtopupLazyDataModel extends LazyDataModel<Agent> {

  private List<Agent> datasource;
  private int pageSize;
  private int rowIndex;
  private int rowCount;

  private AgentManagementService agentManagementService;
  private AgentBatchUploadingDTO agentBatchUploadingDTO;

  public AgentBatchUploadingEtopupLazyDataModel(AgentManagementService agentManagementService, AgentBatchUploadingDTO agentBatchUploadingDTO) {
    this.agentManagementService = agentManagementService;
    this.agentBatchUploadingDTO = agentBatchUploadingDTO;
  }

  @Override
  public List<Agent> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
    int pageIndex = (first / pageSize) + 1;
    agentBatchUploadingDTO.setPageIndex(pageIndex);
    agentBatchUploadingDTO.setPageSize(pageSize);

    agentBatchUploadingDTO.setSortField(sortField);
    if (sortField != null && sortOrder.equals(SortOrder.ASCENDING)) {
      agentBatchUploadingDTO.setSortOrder(Constants.ASCENDING_SORT);
    } else if (sortField != null && sortOrder.equals(SortOrder.DESCENDING)) {
      agentBatchUploadingDTO.setSortOrder(Constants.DESCENDING_SORT);
    }

    datasource = agentManagementService.doFindAgents(agentBatchUploadingDTO);
    setRowCount(agentManagementService.countAgents(agentBatchUploadingDTO).intValue());

    for(Agent agent: datasource) {
      Integer status = agent.getStatus();
      switch (status) {
        case 1:
          agent.setStatusEnum(Agent.Status.ACTIVE);
          break;
        case 2:
          agent.setStatusEnum(Agent.Status.LOST_SIM);
          break;
        case 3:
          agent.setStatusEnum(Agent.Status.TEMPORAL_LOCKED);
          break;
        case 4:
          agent.setStatusEnum(Agent.Status.DELETED);
      }
    }
    return datasource;
  }

  @Override
  public boolean isRowAvailable() {
    if (datasource == null)
      return false;
    int index = rowIndex % pageSize;
    return index >= 0 && index < datasource.size();
  }

  @Override
  public Object getRowKey(Agent agent) {
    return agent.getAgentId();
  }

  @Override
  public Agent getRowData() {
    if (datasource == null)
      return null;
    int index = rowIndex % pageSize;
    if (index > datasource.size()) {
      return null;
    }
    return datasource.get(index);
  }

  @Override
  public Agent getRowData(String rowKey) {
    if (datasource == null)
      return null;
    for (Agent agent : datasource) {
      if (String.valueOf(agent.getAgentId()).equals(rowKey))
        return agent;
    }
    return null;
  }

  @Override
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  @Override
  public int getPageSize() {
    return pageSize;
  }

  @Override
  public int getRowIndex() {
    return this.rowIndex;
  }

  @Override
  public void setRowIndex(int rowIndex) {
    this.rowIndex = rowIndex;
  }

  @Override
  public void setRowCount(int rowCount) {
    this.rowCount = rowCount;
  }

  @Override
  public int getRowCount() {
    return this.rowCount;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setWrappedData(Object list) {
    this.datasource = (List<Agent>) list;
  }

  @Override
  public Object getWrappedData() {
    return datasource;
  }
}