package com.lc.model;

public class Dao {
    private Test test;
    public Dao(){
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public void save(){
        System.out.println("save a user");
    }
    public void delete(){
        System.out.println("delete a user");
    }
    @Override
    public String toString(){
        return "Dao{" +
                "test='" +"test对象" + '\'' +
                '}';
    }
}
