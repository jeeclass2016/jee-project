package com.vietsci.cms.api.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vietsci.cms.api.dto.CmsUserDTO;
import com.vietsci.cms.api.exception.BusinessException;
import com.vietsci.cms.api.model.CmsUser;
import com.vietsci.cms.api.repository.UserRepository;
import com.vietsci.cms.api.repository.impl.UserRepositoryImpl;
import com.vietsci.cms.api.service.UserManagementService;

@Service("userManagementService")
public class UserManagementServiceImpl implements UserManagementService {

	private static final Log logger = LogFactory.getLog(UserManagementService.class);

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRepositoryImpl userRepositoryImpl;

	/* (non-Javadoc)
	 * @see com.vietsci.cms.api.service.UserManagementService#getUserByUserName(java.lang.String)
	 */
	@Override
	public CmsUser getUserByUserName(String userName) throws BusinessException {
		return userRepository.getUserByUserName(userName);
	}

	/* (non-Javadoc)
	 * @see com.vietsci.cms.api.service.UserManagementService#getUserByUserNameAndPassword(com.vietsci.cms.api.dto.CmsUserDTO)
	 */
	@Override
	public CmsUser getUserByUserNameAndPassword(CmsUserDTO cmsUserDTO) throws BusinessException {
		if (cmsUserDTO == null) {
			logger.info("cmsUserDTO is null");
			return null;
		}
		return userRepository.getUserByUserNameAndPassword(cmsUserDTO.getUserName(), 
								cmsUserDTO.getPassword());
	}
	

	/* (non-Javadoc)
	 * @see com.vietsci.cms.api.service.UserManagementService#addUser(com.vietsci.cms.api.model.CmsUser)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Throwable.class)
	public Boolean addUser(CmsUser cmsUser) throws BusinessException {
		userRepository.save(cmsUser);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.vietsci.cms.api.service.UserManagementService#deleteUserByUsrName(java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Throwable.class)
	public Boolean deleteUserByUsrName(String userName) throws BusinessException {
		userRepository.deleteUserByUserName(userName);
		return true;
	}
}
