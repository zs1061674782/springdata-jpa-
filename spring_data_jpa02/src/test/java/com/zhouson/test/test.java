package com.zhouson.test;

import com.zhouson.dao.UserDao;
import com.zhouson.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        User user = userDao.findOne(1003);
    }
}
