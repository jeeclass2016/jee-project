package com.vietsci.cms.frontend.etopup.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.SearchCriterialUserDTO;
import com.vietsci.cms.frontend.etopup.dto.UserDTO;
import com.vietsci.cms.frontend.etopup.dto.UserManagementDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.model.User;
import com.vietsci.cms.frontend.etopup.model.UserAgentMap;
import com.vietsci.cms.frontend.etopup.service.UserManagementEtopupService;
import com.vietsci.cms.frontend.exception.CmsRestExceptionHandler;
import com.vietsci.cms.frontend.properties.EnvironmentProperties;
import com.vietsci.cms.frontend.resttemplate.CmsRestTemplate;

@Service
public class UserManagementEtopupServiceImpl implements UserManagementEtopupService {
  
  /* logger */
  private static final Log logger = LogFactory.getLog(UserManagementEtopupService.class);

  /* (non-Javadoc)
   * @see com.vietsci.cms.frontend.etopup.service.UserManagementEtopupService#findUserProfile(com.vietsci.cms.frontend.etopup.dto.UserManagementDTO)
   */
  public List<UserDTO> findUserProfile(SearchCriterialUserDTO searchCriterialUserDTO) throws RestClientException {
    /*RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    logger.debug("Gui du lieu den ETOPUP web service de tim kiem user");

    try {
      String data = objectMapper.writeValueAsString(searchCriterialUserDTO);
      String restUrl = String.format("%s/user/find-user/%s",
          EnvironmentProperties.getData("htc_platform_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return Arrays.asList(restTemplate.getForObject(restUrl, UserDTO[].class));

    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return new ArrayList<UserDTO>();

    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return new ArrayList<UserDTO>();
    }*/
    if (searchCriterialUserDTO != null && searchCriterialUserDTO.getUserName().equals("NguyenPV")) {
    
      List<UserDTO> userDTOList = new ArrayList<>();
      UserDTO userDTO = new UserDTO();
      userDTO.setUserId(10L);
      userDTO.setUserName("NguyenPV");
      userDTO.setAddress("1, Hoang Dieu, Hanoi");
      userDTO.setFullName("Vladimir Ivanov");
      userDTOList.add(userDTO);
      return userDTOList;
    } else {
      return new ArrayList<UserDTO>();
    }
  }
  
  public User findUserProfile(String userName) throws RestClientException {
    /*RestTemplate restTemplate = new CmsRestTemplate();
    logger.debug("Gui du lieu den HTC Platform web service de tim kiem user theo userName");

    String restUrl = String.format("%s/user/get-user-by-username/%s", EnvironmentProperties.getData("htc_platform_url"), userName);
    User user = restTemplate.getForObject(restUrl, User.class);*/
    if ("NguyenPV".equals(userName)) {
      
      User user = new User();
      user.setUserId(10L);
      user.setUserName("NguyenPV");
      user.setAddress("1, Hoang Dieu, Hanoi");
      user.setFullName("Vladimir Ivanov");
      return user;
    } else {
      return new User();
    }
  }
  
  public UserDTO findUserProfileByUserId(Long userId) throws RestClientException {
    /*RestTemplate restTemplate = new CmsRestTemplate();
    logger.debug("Gui du lieu den HTC Platform web service de tim kiem user theo userId");

    String restUrl = String.format("%s/user/get-user-by-id/%s", EnvironmentProperties.getData("htc_platform_url"), userId);
    UserDTO userDTO = restTemplate.getForObject(restUrl, UserDTO.class);*/
    
    UserDTO userDTO = new UserDTO();
    userDTO.setUserId(10L);
    userDTO.setUserName("NguyenPV");
    userDTO.setAddress("1, Hoang Dieu, Hanoi");
    userDTO.setFullName("Vladimir Ivanov");
    
    return userDTO;
  }
  
  /* (non-Javadoc)
   * @see com.vietsci.cms.frontend.etopup.service.UserManagementEtopupService#findAgentsMappedToUser(com.vietsci.cms.frontend.etopup.dto.UserManagementDTO)
   */
  public List<Agent> findAgentsMappedToUser(UserManagementDTO userManagementDTO) throws RestClientException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    logger.debug("Gui du lieu den ETOPUP web service de tim kiem cac agent duoc map toi user");

    try {
      String data = objectMapper.writeValueAsString(userManagementDTO);
      String restUrl = String.format("%s/user-agent-map/findAgents/%s",
          EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return Arrays.asList(restTemplate.getForObject(restUrl, Agent[].class));

    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return new ArrayList<Agent>();

    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return new ArrayList<Agent>();
    }
  }

  /* (non-Javadoc)
   * @see com.vietsci.cms.frontend.etopup.service.UserManagementEtopupService#doMapAgentWithUser()
   */
  public Boolean mapAgentWithUser(UserAgentMap userAgentMap) throws RestClientException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    // Call etopup service
    String restUrl = String.format("%s/user-agent-map/map/", EnvironmentProperties.getData("etopup_api_url"));
    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(userAgentMap);
      encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return Boolean.FALSE;
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return Boolean.FALSE;
    }
    
    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("data", encodedData);

    return restTemplate.postForObject(restUrl, formData, Boolean.class);
  }

  /* (non-Javadoc)
   * @see com.vietsci.cms.frontend.etopup.service.UserManagementEtopupService#deleteMappedAgent(com.vietsci.cms.frontend.etopup.model.UserAgentMap)
   */
  public Boolean deleteMappedAgent(Long agentId) throws RestClientException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/user-agent-map/delete-by-agentId/", EnvironmentProperties.getData("etopup_api_url"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(agentId);
      encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return Boolean.FALSE;
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return Boolean.FALSE;
    }
    
    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("data", encodedData);

    return restTemplate.postForObject(restUrl, formData, Boolean.class);
  }

  @Override
  public Boolean isAgentMappedWithAnyUser(Long agentId) throws RestClientException {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    String restUrl = String.format("%s/user-agent-map/isAgentMappedWithAnyUser/%s",
        EnvironmentProperties.getData("etopup_api_url"), agentId);
    return restTemplate.getForObject(restUrl, Boolean.class);
  }
}
