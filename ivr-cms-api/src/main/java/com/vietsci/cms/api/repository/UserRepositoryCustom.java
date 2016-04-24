package com.vietsci.cms.api.repository;

import java.util.List;

import com.vietsci.cms.api.dto.CmsUserDTO;
import com.vietsci.cms.api.model.CmsUser;

/**
 * This interface does complicated tasks related to CMS user. 
 * These tasks may be can not implement by UserRepository.java 
 * @author lam
 *
 */
public interface UserRepositoryCustom {

	/**
	 * Get list users
	 * 
	 * @param userName
	 * @return list of cms user which has userName like #userName
	 */
	List<CmsUser> getListUsersByUserName(CmsUserDTO cmsUserDTO);

	/**
	 * Get list users
	 * 
	 * @param email
	 * @return list of cms user which has email like #email
	 */
	List<CmsUser> getListUsersByEmail(CmsUserDTO cmsUserDTO);
}
