package com.zhouson.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhouson
 * @create 2019-07-10 15:07
 */
@Entity
@Table(name = "cst_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private long id;
    @Column(name = "cust_name")
    private String name;
    @Column(name = "cust_source")
    private String source;
    @Column(name = "cust_industry")
    private String industry;
    @Column(name = "cust_level")
    private String level;
    @Column(name = "cust_address")
    private String address;
    @Column(name = "cust_phone")
    private String phone;

    //配置客户和联系人之间的关系(一对多关系)
    /**
     * 使用注解的形式配置夺标关系
     *   1.声明关系
     *   2.配置外键(中间表)
     */
//    @OneToMany(targetEntity = LinkMan.class)
    /**
     * 第一个参数：指定外键的名称
     * 第二个参数：指定外键引用的值
     * 在客户实体类上(主表)添加了外键的配置，所以对于客户而言，也具备了维护外键的作用
     */
//    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    //-----------------------------------------------------------------
    /**
     * 放弃外键维护权
     *      mappedBy：对方配置关系的属性名称\
     * cascade : 配置级联（可以配置到设置多表的映射关系的注解上）
     *      CascadeType.all         : 所有
     *                  MERGE       ：更新
     *                  PERSIST     ：保存
     *                  REMOVE      ：删除
     *
     * fetch : 配置关联对象的加载方式
     *          EAGER   ：立即加载(基本不会使用)
     *          LAZY    ：延迟加载

     */
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<LinkMan> linkManSet=new HashSet<LinkMan>();


    public Set<LinkMan> getLinkManSet() {
        return linkManSet;
    }

    public void setLinkManSet(Set<LinkMan> linkManSet) {
        this.linkManSet = linkManSet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", source='" + source + '\'' +
                ", industry='" + industry + '\'' +
                ", level='" + level + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
