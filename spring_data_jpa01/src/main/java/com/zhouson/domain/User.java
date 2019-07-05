package com.zhouson.domain;

import javax.persistence.*;

/**
 * @author zhouson
 * @create 2019-07-03 11:31
 */
/*
* 客户的实体类
*  配置映射关系
*    -实体类和表的映射关系
*       @Entity：声明实体类
*       @Table：配置实体类和表的映射关系
*          name:对应数据库表的名称
*    -实体类中属性和表中字段的映射关系
*       @Id:声明主键的配置
        @GeneratedValue：(strategy=GenerationType.IDENTITY)配置主键的生成策略
        * -GenerationType.IDENTITY-自增
        * -GenerationType.SEQUENCE-序列
        * -GenerationType.TABLE：jpa提供的一种机制，通过一张数据库表的形式帮助我们完成主键自增
        * -GenerationType.AUTO：由程序自动的帮助我们选择主键生成策略
*       @Column:配置普通属性的值
* */
@Entity
@Table(name="user")
public class User {
    @Column(name = "username")
    private String username;
    @Column(name = "age")
    private Integer age;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
