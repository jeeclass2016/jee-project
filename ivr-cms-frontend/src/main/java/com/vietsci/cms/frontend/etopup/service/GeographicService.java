package com.vietsci.cms.frontend.etopup.service;

import java.util.List;

import com.vietsci.cms.frontend.etopup.model.TblMapDistrict;
import com.vietsci.cms.frontend.etopup.model.TblMapProvince;

public interface GeographicService {

  /**
   * get all Province in Vietnam.
   * lehuyquang
   * @return List of TBlMapProvinces
   * List<TblMapProvince>
   * Apr 10, 2014
   */
  List<TblMapProvince> getAllProvince();
  
  /**
   * get all District of a Province
   * lehuyquang
   * @param provinceId province ID
   * @return List of Districts
   * List<TblMapDistrict>
   * Apr 10, 2014
   */
  List<TblMapDistrict> getAllDistrictByProvince(String provinceId);
}
