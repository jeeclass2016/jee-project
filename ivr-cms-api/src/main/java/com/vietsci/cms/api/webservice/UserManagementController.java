package com.vietsci.cms.api.webservice;

import java.net.URLDecoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.api.dto.CmsUserDTO;
import com.vietsci.cms.api.exception.BusinessException;
import com.vietsci.cms.api.model.CmsUser;
import com.vietsci.cms.api.service.UserManagementService;
import com.vietsci.cms.api.util.Constants;

/**
 * Controller class for User Management
 *
 */

@Controller
@RequestMapping("api/cms/user/")
public class UserManagementController extends BaseController {
  
  private static final Log logger = LogFactory.getLog(UserManagementController.class);
  
  @Autowired
  private UserManagementService userManagementService;
  
  /**
   * Find user by username
   * @param data criteria
   * @return an user profile
   * @throws BusinessException
   *
   */
  @RequestMapping(value = "findUserByUserName/{data}", method = RequestMethod.POST)
  @ResponseBody
  public CmsUser findUserByUserName(@PathVariable("data") String data) throws BusinessException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    try {
      CmsUserDTO cmsUserDTO = mapper.readValue(URLDecoder.decode(data, Constants.ENCODING_UTF8), CmsUserDTO.class);
      return userManagementService.getUserByUserName(cmsUserDTO.getUserName());
    } catch (BusinessException e) {
      throw e;
    }catch (Exception e) {
      logger.error(null, e);
      throw new BusinessException(Constants.SYSTEM_ERROR_CODE, Constants.SYSTEM_ERROR_CODE);
    }
  }
  
  /**
   * Find user by username and password
   * @param data criteria
   * @return an user profile
   * @throws BusinessException
   *
   */
  @RequestMapping(value = "findUserByUserNameAndPassword/{data}", method = RequestMethod.POST)
  @ResponseBody
  public CmsUser findUserByUsername(@PathVariable("data") String data) throws BusinessException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    try {
      CmsUserDTO cmsUserDTO = mapper.readValue(URLDecoder.decode(data, Constants.ENCODING_UTF8), CmsUserDTO.class);
      return userManagementService.getUserByUserNameAndPassword(cmsUserDTO);
    } catch (BusinessException e) {
      throw e;
    }catch (Exception e) {
      logger.error(null, e);
      throw new BusinessException(Constants.SYSTEM_ERROR_CODE, Constants.SYSTEM_ERROR_CODE);
    }
  }
  
  /**
   * Add user
   * @param data criteria
   * @return add user successfully or not
   * @throws BusinessException
   *
   */
  @RequestMapping(value = "addUser/{data}", method = RequestMethod.POST)
  @ResponseBody
  public Boolean addUser(@PathVariable("data") String data) throws BusinessException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    try {
      CmsUser cmsUser = mapper.readValue(URLDecoder.decode(data, Constants.ENCODING_UTF8), CmsUser.class);
      return userManagementService.addUser(cmsUser);
    } catch (BusinessException e) {
      throw e;
    }catch (Exception e) {
      logger.error(null, e);
      throw new BusinessException(Constants.SYSTEM_ERROR_CODE, Constants.SYSTEM_ERROR_CODE);
    }
  }
  
  /**
   * Delete user
   * @param data
   * @return success or fail
   * @throws BusinessException
   */
  @RequestMapping(value = "delete-by-username/", method = RequestMethod.POST)
  @ResponseBody
  public Boolean deleteMappedAgent(@RequestParam("data") String data) throws BusinessException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    
    try {
      String username = mapper.readValue(URLDecoder.decode(data, "UTF-8"), String.class);
      return userManagementService.deleteUserByUsrName(username);
    } catch (BusinessException e) {
      throw e;
    } catch (Exception e) {
      logger.error(null, e);
      throw new BusinessException(Constants.SYSTEM_ERROR_CODE, Constants.SYSTEM_ERROR_CODE);
    }
  }
}
