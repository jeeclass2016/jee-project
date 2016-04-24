package com.vietsci.cms.frontend.main.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class LoginDTO implements Serializable {

  private static final long serialVersionUID = -4339573571843722143L;

  public static final int ALLOWED_TO_ACCESS = 3;
  public static final int NOT_ALLOWED_TO_ACCESS_AT_THIS_TIME = 4;

  private int status;
  private List<UserModulePermissionDTO> lstUserModulePermissionDTO;

  /**
   * @return the status
   */
  public int getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(int status) {
    this.status = status;
  }

  /**
   * @return the lstUserModulePermissionDTO
   */
  public List<UserModulePermissionDTO> getLstUserModulePermissionDTO() {
    return lstUserModulePermissionDTO;
  }

  /**
   * @param lstUserModulePermissionDTO the lstUserModulePermissionDTO to set
   */
  public void setLstUserModulePermissionDTO(List<UserModulePermissionDTO> lstUserModulePermissionDTO) {
    this.lstUserModulePermissionDTO = lstUserModulePermissionDTO;
  }
}
