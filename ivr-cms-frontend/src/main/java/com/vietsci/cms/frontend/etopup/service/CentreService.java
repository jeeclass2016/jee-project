package com.vietsci.cms.frontend.etopup.service;

import java.util.List;

import com.vietsci.cms.frontend.etopup.dto.CentreDTO;
import com.vietsci.cms.frontend.etopup.model.Centre;

public interface CentreService {

  /**
   * find all Centre by CentreCode, or CentreName, or CentreStatus
   * lehuyquang
   * @param searchCriteriaDTO
   * @return List<Centre> , a list of Centre
   * List<Centre>
   * Mar 21, 2014
   */
  public List<Centre> findCentre(CentreDTO searchCriteriaDTO);
  
  /**
   * TODO
   * lehuyquang
   * @param newCentre
   * @return Centre, the Centre has been updated/inserted
   * Centre
   * Mar 21, 2014
   */
  public Centre updateCentre(Centre newCentre);
  /**
   * 
   * delete centre matches the centreId param
   * lehuyquang
   * @param centreId
   * void
   * Mar 21, 2014
   */
  public void deleteCentre(Long centreId);

 
  /**
   * count total number of records returned from the searching operation.
   * lehuyquang
   * @param centreDTO
   * @return
   * int
   * Mar 26, 2014
   */
  public int countTotalRecord(CentreDTO centreDTO);
  
  /**
   * get all active Centre
   * lehuyquang
   * @return
   * List<Centre> list of active centre.
   * Apr 7, 2014
   */
  public List<Centre> findAllActiveCentre();
}
