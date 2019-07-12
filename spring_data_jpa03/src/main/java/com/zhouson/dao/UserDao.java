package com.zhouson.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.zhouson.domain.User;



/**
 * @author zhouson
 * @create 2019-07-05 10:37
 */

/**
 *
 */
public interface UserDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

}
