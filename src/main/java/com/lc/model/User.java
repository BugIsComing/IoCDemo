package com.lc.model;

public class User {
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

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }

    private String userName;
    private String userAddress;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    private Service service;

}
