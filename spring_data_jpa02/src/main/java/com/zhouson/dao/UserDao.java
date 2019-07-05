package com.zhouson.dao;

import com.zhouson.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zhouson
 * @create 2019-07-05 10:37
 */

/**
 * 复合SpringDataJpa的dao层规范
 * JpaRepository<操作的实体类类型，实体类中主键类型>
 *     封装了基本CRUD操作
 * JpaSpecificationExecutor<操作的实体类类型>
 *     封装了复杂查询（分页）
 */
public interface UserDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
}
