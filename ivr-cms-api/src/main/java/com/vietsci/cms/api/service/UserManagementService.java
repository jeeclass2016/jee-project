package com.vietsci.cms.api.service;

import com.vietsci.cms.api.dto.CmsUserDTO;
import com.vietsci.cms.api.exception.BusinessException;
import com.vietsci.cms.api.model.CmsUser;

public interface UserManagementService {
	/**
	 * @param username
	 * @return
	 */
	public CmsUser getUserByUserName(String username) throws BusinessException;

	/**
	 * Select user by username and password
	 * @param cmsUserDTO
	 * @return
	 */
	public CmsUser getUserByUserNameAndPassword(CmsUserDTO cmsUserDTO) throws BusinessException;
	
	/**
	 * Add new CMS user
	 * @param cmsUserDTO
	 * @return
	 * @throws BusinessException
	 */
	public Boolean addUser(CmsUser cmsUser) throws BusinessException;

	/**
	 * Delete existing user by username
	 * 
	 * @param userName
	 * @return
	 * @throws BusinessException
	 */
	public Boolean deleteUserByUsrName(String userName) throws BusinessException;

}
