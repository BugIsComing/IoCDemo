package com.lc.model;

/**
 * @Author:LC
 * @Date:Created in 18:30 2017/10/22
 * @Modifyed by:
 */
public class Test {
    private User user;
    private Dao dao;
    private Service service;
    public Test(){

    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString(){
        return "Test{" +
                "dao='" + dao + '\'' +
                ", user='" + user + '\'' +
                ", service='" + service + '\'' +
                '}';
    }
}
