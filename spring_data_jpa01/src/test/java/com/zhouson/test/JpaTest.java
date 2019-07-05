package com.zhouson.test;

import com.zhouson.domain.User;
import com.zhouson.utils.JpaUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author zhouson
 * @create 2019-07-03 11:46
 */
public class JpaTest {
    /*
     * 测试jpa的保存
     * jpa的操作步骤
     *   1.加载配置文件创建工厂（实体管理类工厂）对象
     *   2.通过实体类管理类工厂获取实体管理类
     *   3.获取事务对象，开启事务
     *   4.完成crud操作
     *   5.提交事务（回滚事务）
     *   6.释放资源
     * */
    @Test
    public void testSave() {
       /* // 1.加载配置文件创建工厂（实体管理类工厂）
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2.通过实体类管理类工厂获取实体管理类
        EntityManager em = factory.createEntityManager();*/
       //通过工具类改进
        EntityManager em= JpaUtil.getEntityManager();
        // 3.获取事务对象，开启事务
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        //4.完成crud操作
        //保存
        /*User user = new User();
        user.setUsername("小鬼");
        user.setAge(14);
        em.persist(user);*/
       //根据id查询
        /**
         * find方法
         * 1.查询的对象就是当前客户对象本身
         * 2.在调用find方法的时候，就会发送sql语句查询数据库
         * 属于立即加载
         * getReference方法
         * 1.获取的对象是一个动态代理对象
         * 2.调用getReference方法不会立即发送sql语句查询数据库
         *   当调用查询结果对象的时候，才会发送查询的sql语句：什么时候用，什么时候发送sql语句查询
         *   属于延迟加载
         */
        /*User user = em.find(User.class, 1001);
        System.out.println(user);*/
 /*       User u = em.getReference(User.class, 1003);
        System.out.println(u);
        //更新

//        user.setId(1001);
        u.setUsername("小黑");
        em.merge(u);*/
       //删除
        User u = em.getReference(User.class, 1003);
        System.out.println(u);
        em.remove(u);
        // 5.提交事务（回滚事务）
        ts.commit();
        // 6.释放资源
        em.close();
    }
}
