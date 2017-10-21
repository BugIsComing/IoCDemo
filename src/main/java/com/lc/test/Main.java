package com.lc.test;
import com.lc.ioc.XMLApplicationContent;
import com.lc.model.Dao;
import com.lc.model.Service;
import com.lc.model.User;

public class Main {
    public static void main(String[] args){
       XMLApplicationContent test = new XMLApplicationContent("src/main/resources/applicationcontent.xml");
       User user = (User)test.getBean("user");
       Service service = (Service) test.getBean("service");
       Dao dao = (Dao)test.getBean("dao");
       System.out.println(user.toString());
       System.out.println(service.toString());
       System.out.println(dao.toString());
    }//存在的问题是如果具有一个类依赖某一个类，必须先注入依赖项对象
}
