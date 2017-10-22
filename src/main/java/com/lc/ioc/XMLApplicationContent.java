package com.lc.ioc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class XMLApplicationContent implements BeanFactory {
    private Map<String,Object> beanMap;
    private Map<String,Bean> config ;
    @Override
    public Object getBean(String beanName) {
        Object obj = beanMap.get(beanName);
        if(obj==null){
            System.out.println("No Bean");
            return null;
        }
        return obj;
    }

    /**
     * Constructor
     */
    public XMLApplicationContent(String filePath){
        beanMap = new HashMap<String,Object>();
        config = XmlConfig.getApplicationContent(filePath);
        if (config!=null && !config.isEmpty()){
            for (Map.Entry<String,Bean> bean:config.entrySet()){
                Object obj = createBeanBySetter(bean);
                if(obj == null){
                    throw new RuntimeException("Create Bean Error");
                }
                beanMap.put(bean.getKey(),obj);
            }
        }
    }

    /**
     * Create Bean by Setter
     */
    public Object createBeanBySetter(Map.Entry<String,Bean> bean){
        Object obj=null;
        String className = bean.getValue().getClassName();
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            obj = cls.newInstance();//调用类的无参构造方法
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        List<Property> temp = bean.getValue().getPropertiesList();
        if(temp != null && temp.size() != 0){
            for(Property pro:temp){
                if (pro.getValue() != null){
                   BeanUtil.getAndInvokeSetterMethod(obj,pro,true);
                }else if(pro.getRef() != null){
                    Object member = beanMap.get(pro.getName());
                    Method method = BeanUtil.getAndInvokeSetterMethod(obj,pro,false);
                    try {
                        method.invoke(obj,member);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }else{
                    throw new RuntimeException("配置文件中属性定义错误");
                }
            }
        }
        return obj;
    }
}
