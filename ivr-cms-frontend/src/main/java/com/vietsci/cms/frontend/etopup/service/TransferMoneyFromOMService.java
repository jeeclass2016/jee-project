package com.vietsci.cms.frontend.etopup.service;

import com.vietsci.cms.frontend.etopup.dto.ErpPosSaleOrdersDTO;
import com.vietsci.cms.frontend.etopup.model.ErpPosSaleOrders;

import java.util.List;

public interface TransferMoneyFromOMService {

  public List<ErpPosSaleOrders> findOrdersByFilter(ErpPosSaleOrdersDTO ordersDTO);

  public List<ErpPosSaleOrders> getAllErpPosSaleOrders();

  public boolean transferMoney(ErpPosSaleOrders orders, Integer staffId);

  public boolean reject(ErpPosSaleOrders orders, Integer staffId);
}
