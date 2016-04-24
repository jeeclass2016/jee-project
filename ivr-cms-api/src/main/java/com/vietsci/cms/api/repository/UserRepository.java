package com.vietsci.cms.api.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vietsci.cms.api.model.CmsUser;

/**
 * This interface is to do basic tasks related to CMS user. 
 * @author lam
 *
 */
public interface UserRepository extends CrudRepository<CmsUser, BigDecimal>, UserRepositoryCustom {

	@Query("SELECT u FROM CmsUser u WHERE u.userName = :userName")
	CmsUser getUserByUserName(@Param("userName") String userName);
	
	@Query("SELECT u FROM CmsUser u WHERE u.userName = :userName AND u.password = :password")
	CmsUser getUserByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

	@Modifying
	@Query("DELETE FROM CmsUser u WHERE u.userName = :userName")
	void deleteUserByUserName(@Param("userName") String userName);
}
