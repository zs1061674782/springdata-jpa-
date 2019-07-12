package com.zhouson.dao;

import com.zhouson.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zhouson
 * @create 2019-07-05 10:37
 */

/**
 * 复合SpringDataJpa的dao层规范
 * JpaRepository<操作的实体类类型，实体类中主键类型>
 *     封装了基本CRUD操作
 * JpaSpecificationExecutor<操作的实体类类型>
 *     封装了复杂查询（分页）
 */
public interface UserDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    /**
     * 案例：根据客户名称查询客户
     *      使用jpql的形式查询
     *  jpql：from User where username=?
     *  配置jpql语句，使用@Query注解
     *  里面的数字表示占位符的位置（从1开始），默认是与给定参数的顺序一致(可以不写数字)，
     *  当参数与jpql语句默认占位符顺序不一致时，则应该手动表明顺序
     */
    @Query(value ="from User where username=?1")
    public User findByName(String username);
    /**
     * 多占位符的使用:根据id和客户名称进行查询
     */
    @Query(value = "from User where username=?1 and id=?2")
    public User findByNameAndId(String username,Integer id);
    /**
     * 根据id更新用户的名称(SpringData jpa封装的更新方法会将全部字段更新，
     * 不给值就为空)
     *  @Modifying:表示当前执行的是一个更新操作
     */
    @Query(value ="update User set username=?2 where id=?1")
    @Modifying
    public void MyUpdate(Integer id,String username);

    /**
     * 使用sql查询
     * nativeQuery：
     *  -true:表示使用sql查询
     *  -false:表示使用jpql查询
     *  记住sql查询返回值都是List<Object []>
     * @return
     */
    @Query(value = "select * from user",nativeQuery = true)
    public List<Object[]> MyFindAll();
    /**
     * 方法名称规则查询
     *   -是对jpql查询，更加深入的一层封装，我们只需要按照SpringData Jpa提供的方法名称规则
     *   定义方法，不需要再去配置jpql语句，完成查询
     *
     * 方法名的约定：
     *   findBy:查询
     *       对象中的属性名(首字母大写)：查询的条件
     *       比如：用户名（Username）
     *   再springData Jpa的运行阶段会根据方法名称进行解析
     *       findBy ：from xxx(实体类) where 属性名 =
     *
     */
    public User findByUsername(String username);
    //命名规则之模糊查询
    public List<User> findByUsernameLike(String str);
    //命名规则之多条件查询-findBy+属性名+"查询方式"+多条件连接符(and,or)+属性名+查询方式
    public User findByUsernameLikeAndId(String str,Integer id);
}
