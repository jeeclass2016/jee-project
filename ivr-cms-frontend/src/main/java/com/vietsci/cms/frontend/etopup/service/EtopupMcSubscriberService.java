package com.vietsci.cms.frontend.etopup.service;

import com.vietsci.cms.frontend.etopup.model.McSubscriber;

import java.util.List;
/**
 * @author quanglh
 *
 */
public interface EtopupMcSubscriberService {
  /**
   * Todo: find actvie MC Subscriber by MSISDN
   * Return type:McSubscriber
   * Created by: quanglh
   * Created date: May 9, 2014 9:57:26 AM
   */
  public McSubscriber findActiveMcSubscriberByMDN(String mdn);
  public List<McSubscriber> findListActiveMcSubscriberByListMDN(List<String> mdn);
}
