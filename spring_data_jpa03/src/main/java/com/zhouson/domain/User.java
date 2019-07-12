package com.zhouson.domain;


import javax.persistence.*;

/**
 * @author zhouson
 * @create 2019-07-05 10:35
 */
@Entity
@Table(name="user")
public class User {
    @Column(name="username")
    private String username;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="age")
    private Integer age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }
}
