package com.zhouson.test;

import com.zhouson.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhouson
 * @create 2019-07-10 9:26
 */

/**
 * 复杂查询的第二种：sql查询
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestNativeQuery {
    @Autowired
    private UserDao userDao;
    @Test
    public void testMyFindAll(){
        List<Object[]> list = userDao.MyFindAll();
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

}
