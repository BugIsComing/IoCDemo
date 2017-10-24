package com.lc.model;

import java.util.Date;

/**
 * @author LC
 */
public class User {
    private String userName;
    private String userAddress;
    private int age;
    private Service service;
    private Dao dao;
    private Date birth;
    private Double height;
    private Long heavy;

    public User(){
    }

    public User(Service service,Dao dao){
        this.service = service;
        this.dao = dao;
        this.age = 25;
        this.userAddress = "LC";
        this.userAddress = "ChengDu";
        this.birth = new Date();
        this.height = 177.7;
        this.heavy = new Long(123);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Long getHeavy() {
        return heavy;
    }

    public void setHeavy(Long heavy) {
        this.heavy = heavy;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", age='" + age + '\'' +
                ", birth='" + birth + '\'' +
                ", height='" + height + '\'' +
                ", heavy='" + heavy + '\'' +
                '}';
    }
}
