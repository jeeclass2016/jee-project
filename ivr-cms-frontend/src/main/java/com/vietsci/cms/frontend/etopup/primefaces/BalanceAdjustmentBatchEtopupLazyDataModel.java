package com.vietsci.cms.frontend.etopup.primefaces;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.BalanceAdjustmentBatchDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.model.AgentAccount;
import com.vietsci.cms.frontend.etopup.model.BalanceAdjustmentResultData;
import com.vietsci.cms.frontend.etopup.service.AgentManagementService;
import com.vietsci.cms.frontend.util.CommonUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BalanceAdjustmentBatchEtopupLazyDataModel extends LazyDataModel<BalanceAdjustmentResultData> {
  private static final Log logger = LogFactory.getLog(BalanceAdjustmentBatchEtopupLazyDataModel.class);

  private List<BalanceAdjustmentResultData> datasource;

  private int pageSize;
  private int rowIndex;
  private int rowCount;

  private BalanceAdjustmentBatchDTO batchDTO;
  private AgentManagementService agentService;


  public BalanceAdjustmentBatchEtopupLazyDataModel(AgentManagementService agentService, BalanceAdjustmentBatchDTO batchDTO) {
    this.batchDTO = batchDTO;
    this.agentService = agentService;
    datasource = new ArrayList<>();
  }

  @Override
  public List<BalanceAdjustmentResultData> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
    try {
      int pageIndex = (first / pageSize) + 1;
      batchDTO.setPageIndex(pageIndex);
      batchDTO.setPageSize(pageSize);

      batchDTO.setSortField(sortField);
      if (sortField != null && sortOrder.equals(SortOrder.ASCENDING)) {
        batchDTO.setSortOrder(Constants.ASCENDING_SORT);
      } else if (sortField != null && sortOrder.equals(SortOrder.DESCENDING)) {
        batchDTO.setSortOrder(Constants.DESCENDING_SORT);
      }

      List<String> msisdns = new ArrayList<>(batchDTO.getAgentBalance().keySet());

      List<Agent> agents = agentService.findAgentByMsisdnList(msisdns);

      setRowCount(agents.size());

      for (Agent agent : agents) {
        BalanceAdjustmentResultData data = new BalanceAdjustmentResultData();
        AgentAccount agentAccount = (AgentAccount)agent.getAgentAccounts().toArray()[0];

        data.setMsisdn(agent.getMsisdn());
        data.setBalanceNumber(CommonUtils.formatMoneyToString(batchDTO.getAgentBalance().get(agent.getMsisdn())));

        data.setStatus(agentAccount.getStatus() == AgentAccount.Status.ACTIVE.getValue() ?
                AgentAccount.Status.ACTIVE.getName() : AgentAccount.Status.TEMPORAL_LOCKED.getName());

        data.setBalanceResult(CommonUtils.formatMoneyToString(agentAccount.getCurrBalance()));

        datasource.add(data);

      }

    } catch (Exception e) {
      e.printStackTrace();
      logger.error("Khong the load du lieu len bang hien thi");
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
  public Object getRowKey(BalanceAdjustmentResultData resultData) {
    return null;
  }

  @Override
  public BalanceAdjustmentResultData getRowData() {
    if (datasource == null)
      return null;
    int index = rowIndex % pageSize;
    if (index > datasource.size()) {
      return null;
    }
    return datasource.get(index);
  }

  @Override
  public BalanceAdjustmentResultData getRowData(String rowKey) {
    if (datasource == null)
      return null;
    for (BalanceAdjustmentResultData resultData : datasource) {
      if (String.valueOf(resultData.getBalanceNumber()).equals(rowKey))
        return resultData;
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
    this.datasource = (List<BalanceAdjustmentResultData>) list;
  }

  @Override
  public Object getWrappedData() {
    return datasource;
  }
}
