package com.zhouson.test;

import com.zhouson.dao.UserDao;
import com.zhouson.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhouson
 * @create 2019-07-05 10:48
 */
//声明spring提供的单元测试环境
@RunWith(SpringJUnit4ClassRunner.class)
//指定spring容器的配置信息
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class test {
    @Autowired
    private UserDao userDao;
    @Test
    public void test1(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testFindOne(){
        User user = userDao.findOne(1003);
        System.out.println(user);
    }
    /**
     * save : 保存或者更新
     *      根据传递的对象是否存在主键id，
     *      如果没有id主键属性：保存
     *      存在id主键属性，根据id查询数据，更新数据
     */
    @Test
    public void testUpdate(){
        User user=new User();
        user.setId(1003);
        user.setAge(17);
        user.setUsername("大鬼");
        userDao.save(user);
    }
    @Test
    public void testSave(){
        User user=new User();
        user.setUsername("超鬼");
        user.setAge(15);
        userDao.save(user);
    }
    @Test
    public void testDelete(){
        userDao.delete(1006);
    }
    @Test
    public void testCount(){
        long count = userDao.count();
        System.out.println(count);
    }
    @Test
    public void testExists(){
        boolean exists = userDao.exists(1004);
        System.out.println(exists);
    }

    /**
     * em:表示EntityManager
     * 根据id从数据库查询
     * @Transactional：保证getOne正常运行
     * findOne:
     *    em.find():立即加载
     * getOne:
     *    em.getReference():延迟加载
     */
    @Test
    @Transactional
    public void testGetOne(){
        User one = userDao.getOne(1001);
        System.out.println(one);
    }
}
