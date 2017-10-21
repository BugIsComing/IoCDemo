package com.lc.test;
import com.lc.ioc.XmlConfig;

public class Main {
    public static void main(String[] args){
//        XMLApplicationContent test = new XMLApplicationContent("src/main/resources/applicationcontent.xml");
//        Dao dao = (Dao)test.getBean("dao");
//        Service service = (Service)test.getBean("service");
//        User user = (User)test.getBean("user");
//        service.save();
//        service.delete();
       XmlConfig.getApplicationContent("src/main/resources/applicationcontent.xml");
    }//存在的问题是如果具有一个类依赖某一个类，必须先注入依赖项对象
}
