package com.zhouson.test;

import com.zhouson.dao.UserDao;
import com.zhouson.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhouson
 * @create 2019-07-09 17:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class testComplicatedQuery {
    @Autowired
    private UserDao userDao;
    @Test
    public void testFindByName(){
        User user = userDao.findByName("小鬼");
        System.out.println(user);
    }
    @Test
    public void testFindByNameAndId(){
        User user = userDao.findByNameAndId("小鬼", 1001);
        System.out.println(user);
    }
    @Test
    @Transactional //添加事务的支持
    @Rollback(value = false)
    public void testMyUpdate(){
        userDao.MyUpdate(1001,"小小鬼");
    }
    //命名规则查询
    @Test
    public void testMyName(){
        User user = userDao.findByUsername("中鬼");
        System.out.println(user);
    }
    //命名规则模糊查询
    @Test
    public void testMyNameLike(){
        List lists = userDao.findByUsernameLike("%鬼");
        for (Object o : lists) {
            System.out.println(o);
        }
    }
    //命名规则之多条件查询
    @Test
    public void testMyNameLikeAndId() {
        User user = userDao.findByUsernameLikeAndId("%鬼", 1003);
        System.out.println(user);
    }
}
