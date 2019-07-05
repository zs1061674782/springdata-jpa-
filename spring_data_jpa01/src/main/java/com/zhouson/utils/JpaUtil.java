package com.zhouson.utils;

/**
 * @author zhouson
 * @create 2019-07-03 17:00
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 解决创建实体管理器工厂耗时、浪费资源的问题
 */
public class JpaUtil {
    private static EntityManagerFactory factory;
    static{
         factory = Persistence.createEntityManagerFactory("myJpa");
    }
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
