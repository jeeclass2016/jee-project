package com.vietsci.cms.api.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.vietsci.cms.api.dto.CmsUserDTO;
import com.vietsci.cms.api.model.CmsUser;
import com.vietsci.cms.api.repository.UserRepositoryCustom;
import com.vietsci.cms.api.util.CmsStringUtils;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

	/**
	 * logger to use.
	 */
	private static final Log logger = LogFactory.getLog(UserRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vietsci.cms.api.repository.UserRepositoryCustom#getListUsersByUserName
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CmsUser> getListUsersByUserName(CmsUserDTO cmsUserDTO) {
		String queryString = null;

		if (cmsUserDTO == null
				|| CmsStringUtils.isNullOrBlank(cmsUserDTO.getUserName())) {
			return new ArrayList<>();
		}
		logger.info("fetch users with name like: " + cmsUserDTO.getUserName());
		queryString = "SELECT * FROM CmsUser cu WHERE UPPER(cu.userName) LIKE UPPER(:userName)";

		Query query = entityManager.createQuery(queryString);
		query.setParameter("userName", cmsUserDTO.getUserName());

		int offset = ((cmsUserDTO.getPageIndex() - 1) * cmsUserDTO.getPageSize());

		query.setFirstResult(offset);
		query.setMaxResults(offset + cmsUserDTO.getPageSize());

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vietsci.cms.api.repository.UserRepositoryCustom#getListUsersByEmail
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CmsUser> getListUsersByEmail(CmsUserDTO cmsUserDTO) {
		String queryString = null;

		if (cmsUserDTO == null
				|| CmsStringUtils.isNullOrBlank(cmsUserDTO.getEmail())) {
			return new ArrayList<>();
		}

		logger.info("fetch users with email like: " + cmsUserDTO.getEmail());
		queryString = "SELECT * FROM CmsUser cu WHERE UPPER(cu.email) LIKE UPPER(:email)";

		Query query = entityManager.createQuery(queryString);
		query.setParameter("email", cmsUserDTO.getEmail());

		int offset = ((cmsUserDTO.getPageIndex() - 1) * cmsUserDTO.getPageSize());

		query.setFirstResult(offset);
		query.setMaxResults(offset + cmsUserDTO.getPageSize());

		return query.getResultList();
	}

}
