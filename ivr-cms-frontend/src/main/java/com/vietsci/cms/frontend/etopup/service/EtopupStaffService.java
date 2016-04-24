package com.vietsci.cms.frontend.etopup.service;

import java.util.List;

import com.vietsci.cms.frontend.etopup.model.EposStaff;

public interface EtopupStaffService {
  /**
   * Todo: get active staff which its id is equals to staffId
   * Return type:Staff
   * Created by: quanglh
   * Created date: Apr 23, 2014 3:20:32 PM
   */
  EposStaff getActiveStaffByStaffId(Long staffId);
  /**
   * Todo: get all active staffs which their staff_code like staffCode
   * Return type:List<EposStaff>
   * Created by: quanglh
   * Created date: Apr 23, 2014 3:21:01 PM
   */
  List<EposStaff> getAllActiveStaffLikeStaffCode(String staffCode);
}
