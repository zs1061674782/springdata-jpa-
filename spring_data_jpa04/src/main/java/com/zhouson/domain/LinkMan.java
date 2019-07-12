package com.zhouson.domain;

import javax.persistence.*;

/**
 * @author zhouson
 * @create 2019-07-10 15:07
 */
@Entity
@Table(name="cst_linkman")
public class LinkMan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lkm_id")
    private long id;
    @Column(name="lkm_name")
    private String name;
    @Column(name="lkm_gender")
    private char gender;
    @Column(name="lkm_phone")
    private String phone;
    @Column(name="lkm_mobile")
    private String mobile;
    @Column(name="lkm_email")
    private String email;
    @Column(name="lkm_position")
    private String position;
    @Column(name="lkm_memo")
    private String memo;//备注
    //配置一对多关系
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "LinkMan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
