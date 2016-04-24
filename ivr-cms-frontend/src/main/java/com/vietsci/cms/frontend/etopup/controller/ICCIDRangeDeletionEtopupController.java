package com.vietsci.cms.frontend.etopup.controller;

import com.vietsci.cms.frontend.etopup.dto.ICCIDRangeDeletionDTO;
import com.vietsci.cms.frontend.etopup.model.LoadedSim;
import com.vietsci.cms.frontend.etopup.primefaces.ICCIDRangeDeletionEtopupLazyDataModel;
import com.vietsci.cms.frontend.etopup.service.ICCIDManagentService;
import com.vietsci.cms.frontend.util.ErrorConstants;
import com.vietsci.cms.frontend.util.FacesUtil;
import com.vietsci.cms.frontend.util.MessageConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for ICCIDRangeDeletion
 *
 * @author hong.nguyenmai
 *
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */

@Named("iccidRangeDeletionEtopupController")
@Scope(value = "session")
public class ICCIDRangeDeletionEtopupController extends EtopupBaseController implements Serializable {

  /* logger */
  private static final Log logger = LogFactory.getLog(ICCIDRangeDeletionEtopupController.class);

  @Inject
  ICCIDManagentService iccidManagentService;

  private ICCIDRangeDeletionDTO iccidRangeDeletionDTO;
  private ICCIDRangeDeletionEtopupLazyDataModel iccidRangeDeletionLazyDataModel;
  private LoadedSim[] selectedICCIDs;

  /* ============= Business methods ============= */

  /**
   * Initialize instance variables
   *
   * @author hong.nguyenmai
   */
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init ICCIDRangeDeletionEtopupController members");

    iccidRangeDeletionDTO = new ICCIDRangeDeletionDTO();
    iccidRangeDeletionLazyDataModel = new ICCIDRangeDeletionEtopupLazyDataModel(iccidManagentService,
      iccidRangeDeletionDTO);

    logger.debug("Init ICCIDRangeDeletionEtopupController members successfully");
  }

  /**
   * Find ICCIDs with search criteria
   *
   * @author hong.nguyenmai
   */
  public void doFindICCIDs() {
    logger.debug("Start doFindICCIDs");

    String message = null;

    if(!validateSearchCriteria()) {
      message = getErrorMessage(ErrorConstants.ETOPUP_ICCID_MANAGEMENT_ICCID_START_GREATER_ICCID_END);
      FacesUtil.addErrorMsg(null, message, "");
      return;
    }

    try {
      iccidRangeDeletionLazyDataModel = new ICCIDRangeDeletionEtopupLazyDataModel(iccidManagentService,
        iccidRangeDeletionDTO);
    } catch(Exception e) {
      handleExceptionMessage(e, ICCIDRangeDeletionEtopupController.class);
    }

    logger.debug("Complete doFindICCIDs");
  }

  /**
   * Delete a list of ICCIDs
   *
   * @author hong.nguyenmai
   */
  public void doDeleteICCIDs() {
    logger.debug("Start doDeleteICCIDs");

    String message = null;

    if(selectedICCIDs.length == 0) {
      message = getMessage(MessageConstants.ETOPUP_ICCID_MANAGEMENT_DELETE_SELECT_ITEM);
      FacesUtil.addSuccessMessage(message);
      return;
    }

    List<Long> iccidIdList = new ArrayList<Long>();
    for(LoadedSim selectedICCID : selectedICCIDs) {
      iccidIdList.add(selectedICCID.getId());
    }

    try {
      boolean isSuccess = iccidManagentService.deleteICCIDs(iccidIdList);
      if(isSuccess) {
        message = getMessage(MessageConstants.ETOPUP_ICCID_MANAGEMENT_DELETE);
        FacesUtil.addSuccessMessage(message);
      } else {
        message = getMessage(ErrorConstants.ETOPUP_ICCID_MANAGEMENT_DELETE);
        FacesUtil.addErrorMsg(null, message, "");
      }

    }catch (Exception e) {
      handleExceptionMessage(e, ICCIDRangeDeletionEtopupController.class);
    }

    logger.debug("Complete doDeleteICCIDs");
  }

  private boolean validateSearchCriteria() {
    BigInteger start = new BigInteger(iccidRangeDeletionDTO.getIccidStart());
    BigInteger end = new BigInteger(iccidRangeDeletionDTO.getIccidEnd());

    if(start.compareTo(end) > 0) {
      return false;
    }

    return true;
  }

  /* ============= Getter and Setter methods ============= */

  public ICCIDRangeDeletionDTO getIccidRangeDeletionDTO() {
    return iccidRangeDeletionDTO;
  }

  public void setIccidRangeDeletionDTO(ICCIDRangeDeletionDTO iccidRangeDeletionDTO) {
    this.iccidRangeDeletionDTO = iccidRangeDeletionDTO;
  }

  public ICCIDRangeDeletionEtopupLazyDataModel getIccidRangeDeletionLazyDataModel() {
    return iccidRangeDeletionLazyDataModel;
  }

  public void setIccidRangeDeletionLazyDataModel(ICCIDRangeDeletionEtopupLazyDataModel iccidRangeDeletionLazyDataModel) {
    this.iccidRangeDeletionLazyDataModel = iccidRangeDeletionLazyDataModel;
  }

  public LoadedSim[] getSelectedICCIDs() {
    return selectedICCIDs;
  }

  public void setSelectedICCIDs(LoadedSim[] selectedICCIDs) {
    this.selectedICCIDs = selectedICCIDs;
  }
}
