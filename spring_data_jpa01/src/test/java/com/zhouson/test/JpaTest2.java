package com.zhouson.test;

import com.zhouson.utils.JpaUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * @author zhouson
 * @create 2019-07-04 10:55
 */
public class JpaTest2 {
    //jpql查询
    //查询所有
    @Test
    public void findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        String sql = "from com.zhouson.domain.User";
        Query query = em.createQuery(sql);
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        ts.commit();
        em.close();
    }

    @Test
    //排序查询
    public void testOrders() {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        String sql = "from User order by age";
        Query query = em.createQuery(sql);
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        ts.commit();
        em.close();
    }
    //统计查询
    @Test
    public void testCount() {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        String sql = "select count(age)from User";
        Query query = em.createQuery(sql);
        Object s = query.getSingleResult();
        System.out.println(s);
        ts.commit();
        em.close();
    }
    @Test
    //分页查询
    public void testLimit() {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        //查询全部
        String sql = "from User ";
        Query query = em.createQuery(sql);
        //对参数赋值
        query.setFirstResult(1);
        query.setMaxResults(2);
        //发送查询
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        ts.commit();
        em.close();
    }
    @Test
    //条件查询
    public void testCondition() {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        String sql = "from User where username like ?";
        Query query = em.createQuery(sql);
        //对参数赋值-第一个参数，占位符的索引位置（从1开始），第二个参数取值
        query.setParameter(1,"小%");
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        ts.commit();
        em.close();
    }
}
