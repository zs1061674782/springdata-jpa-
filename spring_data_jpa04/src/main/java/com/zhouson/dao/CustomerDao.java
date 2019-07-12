package com.zhouson.dao;

import com.zhouson.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



/**
 * @author zhouson
 * @create 2019-07-05 10:37
 */

/**
 *
 */
public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

}
