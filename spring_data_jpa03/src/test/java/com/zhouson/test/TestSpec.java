package com.zhouson.test;

import com.zhouson.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.zhouson.dao.UserDao;

import javax.persistence.criteria.*;

import java.util.List;

/**
 * @author zhouson
 * @create 2019-07-10 11:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestSpec {
    @Autowired
    private UserDao userDao;

    /**
     * 动态查询
     * 自定义查询条件
     * 1.实现Specification接口(提供泛型，查询的对象类型)
     * 2.实现toPredicate方法(构造查询条件)
     * 3.需要借助方法参数中的两个参数(
     * root：获取需要查询的对象属性
     * CriteriaBuilder：构造查询条件的，内部封装了很多的查询条件(模糊匹配，精准匹配等)
     * )
     */
    @Test
    public void testSpec() {
        Specification<User> spec = new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获取比较的属性
                Path<Object> username = root.get("username");
                //2.构造查询条件
                /**
                 * 第一个参数：需要比较的属性(path对象)
                 * 第二个参数：需要比较的属性的值
                 * equal：表示精准查询
                 */
                Predicate predicate = cb.equal(username, "中鬼");//进行精准匹配(比较的属性，比较的属性的取值)
                return predicate;
            }
        };
        User u = userDao.findOne(spec);
        System.out.println(u);
    }

    /**
     * 多条件查询
     */
    @Test
    public void testSpec1() {
        Specification<User> spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                //多条件查询需要获取多个path属性
                Path username = root.get("username");
                Path id = root.get("id");
                Predicate equal = cb.equal(username, "中鬼");
                Predicate equal1 = cb.equal(id, 1002);
                //多条件查询需要将多个查询条件组合到一起：组合(满足条件一并且满足条件二：与关系，满足条件一或满足条件二：或关系)
                Predicate and = cb.and(equal, equal1);//以与的形式拼接多个查询条件
//cb.or();//以或的形式拼接多个查询条件
                return and;
            }
        };
        //将spec封装的查询条件放进方法里
        User one = userDao.findOne(spec);
        System.out.println(one);
    }

    /**
     * 模糊查询
     * 注意：equal：得到直接的path对象(属性)，然后进行比较即可
     * gt,lt,ge,le,like：得到path对象，根据path指定比较的参数类型(比较可能是数字，可能是字符串，所以需要指定)，
     * 再去进行比较
     */
    @Test
    public void testSpec2() {
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> username = root.get("username");
                //需要指定数据类型
                Predicate like = cb.like(username.as(String.class), "%鬼");
                return like;
            }
        };
        List<User> all = userDao.findAll();
        for (User user : all) {
            System.out.println(user);
        }
        //统计条数
        long count = userDao.count(spec);
        System.out.println(count);
        long count1 = userDao.count();
        System.out.println(count1);
    }

    /**
     * 在查询的基础上进行排序
     */
    @Test
    public void testSpec3() {
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> username = root.get("username");
                //需要指定数据类型
                Predicate like = cb.like(username.as(String.class), "%鬼");
                return like;
            }
        };
        /*添加排序
          创建排序对象，需要调用构造方法实例化sort对象
          第一个参数，排序的顺序(倒序，正序)
             -正序：Sort.Direction.ASC
             -倒序：Sort.Direction.DESC
          第二个参数，排序的属性名称
          */
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        List<User> all = userDao.findAll(spec, sort);
        for (User user : all) {
            System.out.println(user);
        }
    }

    /**
     * 分页查询
     * findAll(Specification,pageable)
     * -带有条件的分页查询
     * Specification:查询条件
     * pageable:分页参数
     * -查询的页码，每页查询的条数
     * findAll(pageable)
     * -不带条件的分页查询
     * 返回：Page(springDataJpa为我们封装好的pageBean对象，数据列表，总条数)
     */
    @Test
    public void testSpec4() {
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                return null;
            }
        };
        /**
         * 第一个参数：当前查询的页数(从0开始)
         * 第二个参数：每页查询的数量
         */
        Pageable pageable=new PageRequest(0,2);
        Page<User> page = userDao.findAll(pageable);
        //总页数
        System.out.println(page.getTotalPages());
        //总条数
        System.out.println(page.getTotalElements());
        //得到集合列表
        System.out.println(page.getContent());
    }
}

