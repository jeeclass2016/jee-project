package com.vietsci.cms.frontend.etopup.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.WebApplicationContext;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.common.util.EtopupUtil;
import com.vietsci.cms.frontend.etopup.dto.CentreDTO;
import com.vietsci.cms.frontend.etopup.model.Centre;
import com.vietsci.cms.frontend.etopup.primefaces.CentreDataModel;
import com.vietsci.cms.frontend.etopup.primefaces.CentreLazyDataModel;
import com.vietsci.cms.frontend.etopup.service.CentreService;
import com.vietsci.cms.frontend.exception.CmsRestException;

/**
 * Handling and all request for processing operation related to ETopup Centre management
 * @author lehuyquang
 * 
 */

@Named
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class CentreListController extends EtopupBaseController implements Serializable{

  private static final long serialVersionUID = 5799146597682706286L;

  private CentreDataModel centreDataModel;
  
  /**
   * lehuyquang
   * Mar 26, 2014
   */
  private CentreDTO centreDTO;
  
  /**
   * lehuyquang
   * Mar 26, 2014
   */
  private CentreLazyDataModel centreLazyDataModel;
  /**
   * lehuyquang
   * Mar 21, 2014
   */
  private List<SelectItem> statusList;
  
  /**
   * This object is used for binding data in case of inserting/updating data
   * lehuyquang
   * Mar 21, 2014
   */
  private Centre centreDataForEditing;
  
  /**
   * This object is used for binding data in case of inserting/updating data
   * lehuyquang
   * Mar 21, 2014
   */
  private long centreIdToDelete;
  
  /**
   * for mapping status (number) to status description (string)
   * lehuyquang
   * Mar 21, 2014
   */
  private Map<String, String> statusMap;
  
  private String popupTitle;
  private boolean renderCheckbox;
  
  /**
   * for processing business task
   * lehuyquang
   * Mar 21, 2014
   */
  @Inject
  private CentreService centreService;
  
  final Logger logger = LoggerFactory.getLogger(getClass());
  
  /**
   * for initializing data
   * lehuyquang
   * void
   * Mar 21, 2014
   */
  @PostConstruct
  public void init() {
    // setup status menu items for selectOneMenu element
    statusMap = new HashMap<String, String>();
    statusMap.put(Constants.CentreManagement.ACTIVE_STATUS_VALUE, Constants.CentreManagement.ACTIVE_STATUS_LABEL);
    statusMap.put(Constants.CentreManagement.INACTIVE_STATUS_VALUE, Constants.CentreManagement.INACTIVE_STATUS_LABEL);
    
    statusList = new ArrayList<SelectItem>();
    Iterator<Entry<String, String>> it = statusMap.entrySet().iterator();
    while(it.hasNext()){
      Map.Entry<String, String> pairs = it.next();
      statusList.add(new SelectItem(pairs.getKey(), pairs.getValue()));
    }
    centreDataForEditing = new Centre();
    centreDataModel = new CentreDataModel(new ArrayList<Centre>());
    
    centreDTO = new CentreDTO();    
    centreDTO.setStatus(Integer.valueOf(Constants.CentreManagement.ACTIVE_STATUS_VALUE));
    centreLazyDataModel = new CentreLazyDataModel(centreService, centreDTO);
  }

  /**
   * get centreId from UI for deleting
   * lehuyquang
   * @param centreId
   * void
   * Mar 21, 2014
   */
  public void bindAndValidate(long centreId, String centreStatus){
    RequestContext rContext = RequestContext.getCurrentInstance();
    if(centreStatus.equals(Constants.CentreManagement.ACTIVE_STATUS_VALUE)){
      rContext.addCallbackParam("canDelete", false);
      FacesContext fContext = FacesContext.getCurrentInstance();
      fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
          Constants.CentreManagement.MESSAGE_ERROR_STATUS, 
          Constants.CentreManagement.MESSAGE_CANNOT_DELETE_ACTIVE_CENTRE));  
    } else {
      rContext.addCallbackParam("canDelete", true);
    }
    centreIdToDelete = centreId;
  }
  
  /**
   * prepare a new clean Centre when we need to insert new Centre
   * lehuyquang
   * void
   * Mar 21, 2014
   */
  public void openAddCentreDialog(){
    popupTitle = Constants.CentreManagement.POPUP_TITLE_ADD;
    renderCheckbox = true;
    centreDataForEditing = new Centre();
  }
  
  public String getStatusName(String status){
    return statusMap.get(status);
  }
  
  /**
   * find all centre by Code, or Name, or status
   * lehuyquang
   * void
   * Mar 21, 2014
   */
  public void doFindCentre() { 
      centreDTO.setCode(centreDTO.getCode().replaceAll(" ", ""));
      centreDTO.setName(centreDTO.getName().trim());
      centreDataModel = new CentreDataModel(new ArrayList<Centre>());
      centreLazyDataModel = new CentreLazyDataModel(centreService, centreDTO);
  }
  
  
  /**
   * to update an existing Centre or insert a new Centre
   * lehuyquang
   * void
   * Mar 21, 2014
   */
  public void doUpdateCentre(){
    Centre newCentre = new Centre();
    if (centreDataForEditing.getCentreId() != null)
      newCentre.setCentreId(centreDataForEditing.getCentreId());
    
    newCentre.setCode(centreDataForEditing.getCode().trim().replaceAll(" ", ""));
    newCentre.setName(centreDataForEditing.getName().trim());
    newCentre.setStatus(centreDataForEditing.getStatus());

    RequestContext rContext = RequestContext.getCurrentInstance();
    FacesContext fContext = FacesContext.getCurrentInstance();
    try {
      Centre updatedCentre = centreService.updateCentre(newCentre);
      if (centreDataForEditing.getCentreId() != null && updatedCentre.getCentreId() != null) {        
        rContext.addCallbackParam("updateStatus", true);
        fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
            Constants.CentreManagement.MESSAGE_SUCCESS_STATUS, 
            Constants.CentreManagement.MESSAGE_UPDATE_CENTRE_SUCCESS));  
      } else if(centreDataForEditing.getCentreId() == null && updatedCentre.getCentreId() != null){
        rContext.addCallbackParam("updateStatus", true);
        fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
            Constants.CentreManagement.MESSAGE_SUCCESS_STATUS, 
            Constants.CentreManagement.MESSAGE_ADD_NEW_CENTRE_SUCCESS));  
        // reset value
        centreDataForEditing = new Centre();
      } 
      this.doFindCentre();
    } catch (CmsRestException e){
      Map<String, String> eMessage = EtopupUtil.read(e.getMessage());
      logger.error("Saving centre data failed, centreCode=" + newCentre.getCode() + ", because of " 
                  + getErrorMessage(eMessage.get(Constants.MESSAGE_CODE)) + ", errorCode=" + eMessage.get(Constants.MESSAGE_CODE));
      rContext.addCallbackParam("updateStatus", false);
      fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.CentreManagement.MESSAGE_ERROR_STATUS, 
          getErrorMessage(eMessage.get(Constants.MESSAGE_CODE))));
    } catch (ResourceAccessException e){
      logger.error(null, e);
      rContext.addCallbackParam("updateStatus", false);
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.CentreManagement.MESSAGE_ERROR_STATUS, 
          Constants.CentreManagement.MESSAGE_CANNOT_ACCESS_SERVER); 
      FacesContext.getCurrentInstance().addMessage(null, message); 
    }

  }
  

  /**
   * get Centre data from UI for editing an existing Centre
   * lehuyquang
   * @param centreToEdit
   * void
   * Mar 21, 2014
   */
  public void doEditCentre(Centre centreToEdit){
    popupTitle = Constants.CentreManagement.POPUP_TITLE_EDIT;
    renderCheckbox = false;
    if(centreDataForEditing == null) 
      centreDataForEditing = new Centre();
    centreDataForEditing.setCentreId(centreToEdit.getCentreId());
    centreDataForEditing.setCode(centreToEdit.getCode());
    centreDataForEditing.setName(centreToEdit.getName());
    centreDataForEditing.setStatus(centreToEdit.getStatus());
  }
  
  /**
   * to delete a Centre record
   * lehuyquang
   * void
   * Mar 21, 2014
   */
  public void doDeleteCentre(){
    FacesContext fContext = FacesContext.getCurrentInstance();
    try {
      centreService.deleteCentre(centreIdToDelete);
      this.doFindCentre();
      fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
          Constants.CentreManagement.MESSAGE_SUCCESS_STATUS, 
          Constants.CentreManagement.MESSAGE_DELETE_CENTRE_SUCCESS));  
      
    } catch (CmsRestException e) {
      Map<String, String> eMessage = EtopupUtil.read(e.getMessage());
      logger.error("Delete centre failed, centreId=" + centreIdToDelete + " because of: " + eMessage.get(Constants.MESSAGE));
      fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
          eMessage.get(Constants.MESSAGE))); 
    } 
  }


  
  public CentreDTO getCentreDTO() {
    return centreDTO;
  }

  public void setCentreDTO(CentreDTO centreDTO) {
    this.centreDTO = centreDTO;
  }

  public List<SelectItem> getStatusList() {
    return statusList;
  }

  public void setStatusList(List<SelectItem> statusList) {
    this.statusList = statusList;
  }

  public Centre getCentreDataForEditing() {
    return centreDataForEditing;
  }

  public void setCentreDataForEditing(Centre centreDataForEditing) {
    this.centreDataForEditing = centreDataForEditing;
  }

  public CentreDataModel getCentreDataModel() {
    return centreDataModel;
  }

  public void setCentreDataModel(CentreDataModel centreDataModel) {
    this.centreDataModel = centreDataModel;
  }

  public CentreLazyDataModel getCentreLazyDataModel() {
    return centreLazyDataModel;
  }

  public void setCentreLazyDataModel(CentreLazyDataModel centreLazyDataModel) {
    this.centreLazyDataModel = centreLazyDataModel;
  }

  public String getPopupTitle() {
    return popupTitle;
  }

  public void setPopupTitle(String popupTitle) {
    this.popupTitle = popupTitle;
  }

  public boolean isRenderCheckbox() {
    return renderCheckbox;
  }

  public void setRenderCheckbox(boolean renderCheckbox) {
    this.renderCheckbox = renderCheckbox;
  }
  
  

}
