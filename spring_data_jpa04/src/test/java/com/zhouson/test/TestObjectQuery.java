package com.zhouson.test;

import com.zhouson.dao.CustomerDao;
import com.zhouson.dao.LinkManDao;
import com.zhouson.domain.Customer;
import com.zhouson.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author zhouson
 * @create 2019-07-10 17:29
 * 对象导航查询
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestObjectQuery {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;
    //测试对象导航查询(查询一个对象的时候，通过此对象查询所有的关联对象)
    @Test
    @Transactional//解决在java代码中的nosession问题
    public void  testQuery(){
        //查询id为1的客户
        Customer customer=customerDao.findOne(1l);
        //对象导航查询
        Set<LinkMan> linkManSet = customer.getLinkManSet();
        for (LinkMan linkMan : linkManSet) {
            System.out.println(linkMan);
        }
    }

    /**
     * 对象导航查询默认使用的是延迟加载的形式查询
     *    调用getLinkManSet方法的时候并不会立即发送查询，而是在使用关联对象
     *  的时候才会发送
     *  修改配置，将延迟加载改为立即加载
     *     fetch，需要配置到多表映射的注解上面
     */
    @Test
    @Transactional//解决在java代码中的nosession问题
    public void  testQuery2(){
        //查询id为1的客户
        Customer customer=customerDao.getOne(1l);
        //对象导航查询
        Set<LinkMan> linkManSet = customer.getLinkManSet();
        for (LinkMan linkMan : linkManSet) {
            System.out.println(linkMan);
        }
    }

    /**
     * 从联系人对象导航查询他的所属客户
     *      * 默认 ： 立即加载
     *  延迟加载：
     *
     */
    @Test
    @Transactional // 解决在java代码中的no session问题
    public void  testQuery3() {
        LinkMan linkMan = linkManDao.findOne(2l);
        //对象导航查询所属的客户
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);
    }
}
