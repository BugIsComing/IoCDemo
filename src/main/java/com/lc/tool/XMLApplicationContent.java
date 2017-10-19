package com.lc.tool;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLApplicationContent implements BeanFactory {
    private Map<String,Object> beans = new HashMap<String,Object>();
    public XMLApplicationContent(){
        parseXML("");
    }
    public XMLApplicationContent(String fileName){
        parseXML(fileName);
    }
    @Override
    public Object getBean(String beanName) {
        return beans.get(beanName);
    }

    private Object reflectObject(String className){
        Object obj = null;
        try {
            try {
                obj = Class.forName(className).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private boolean isJavaClass(Class<?> cls){
        return cls != null && cls.getClassLoader() == null;
    }

    //这个方法需要优化，如果property是java基本类型。。
    private void parseXML(String fileName) {
        if ("".equals(fileName)){
            System.out.println("The file name is empty!");
            return;
        }

        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(new File(fileName));
            Element root = doc.getRootElement();
            List<Element> eleList = root.elements();

            for(Element ele : eleList){
                String beanId = ele.attributeValue("id");
                System.out.println(beanId);
                String cls = ele.attributeValue("class");
                System.out.println(cls);
                //实例化

                try {
                    Object obj = reflectObject(cls);
                    beans.put(beanId,obj);
                    List<Element> eleChildrenList = ele.elements();
                    for (Element attr : eleChildrenList){
                        String name = attr.attributeValue("name");
                        System.out.println(name);
                        String ref = attr.attributeValue("ref");
                        System.out.println(ref);
                        Object beanObj = this.getBean(name);
                        String methodName = "set"+ref.substring(0,1).toUpperCase()+ref.substring(1);
                        System.out.println(methodName);
                        try {
                            Method mth = obj.getClass().getMethod(methodName,beanObj.getClass());
                            try {
                                mth.invoke(obj,beanObj);
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }

                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

            
        }catch (DocumentException e){
             e.printStackTrace();
        }

    }
}
