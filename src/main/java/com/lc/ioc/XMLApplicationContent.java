package com.lc.ioc;

import javax.swing.text.html.HTMLDocument;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
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
        beanMap = new HashMap<>();
        config = XmlConfig.getApplicationContent(filePath);

        if ("constructor".equals(XmlConfig.iocType)){
            for (Map.Entry<String,Bean> bean:config.entrySet()){
                Object obj = createBeanByConstructor(bean);
                if(obj == null){
                    throw new RuntimeException("Create Bean Error");
                }
                beanMap.put(bean.getKey(),obj);
            }
        }else{
            initBeanMap();
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
    }

    /**
     * Create Bean by Setter
     */
    public Object createBeanBySetter(Map.Entry<String,Bean> bean){
        Object obj = beanMap.get(bean.getValue().getId());

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

    public Object createBeanByConstructor(Map.Entry<String,Bean> bean){
        List<ConstructorArgument> consList = bean.getValue().getConstructorArgumentList();
        //构造函数参数列表
        List<Class<?>> argsList = new ArrayList<>();
        //Class[] argsList;
        for (ConstructorArgument cons : consList){
            try {
                Class<?> temp = Class.forName(config.get(cons.getName()).getClassName());
                argsList.add(temp);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        int index = -1;
        Object obj = null;
        try {
            Class<?> beanCls = Class.forName(bean.getValue().getClassName());
            //没有构造参数，直接调用无参构造方法
            if(argsList.isEmpty()){
                try {
                    return beanCls.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            Constructor<?> []beanCons = beanCls.getConstructors();
            index = getMatchedConstructor(beanCons,argsList);
            if(-1 == index){
                new RuntimeException("没有匹配的构造函数");
            }

            try {
                obj = beanCons[index].newInstance(getArgumentObjects(consList));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     *
     */
    private void initBeanMap(){
        if (config!=null && !config.isEmpty()){
            for (Map.Entry<String,Bean> bean:config.entrySet()){
                intBeanByConstructor(bean);
            }
        }
    }

    private void intBeanByConstructor(Map.Entry<String,Bean> bean){
        Object obj = null;
        Class<?> cls = null;
        try {
            cls = Class.forName(bean.getValue().getClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            /* 默认构造函数 */
            obj = cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        beanMap.put(bean.getValue().getId(),obj);
    }
    public int getMatchedConstructor(Constructor<?> []beanCons, List<Class<?>> argsList){
        int index = 0;

        for(;index < beanCons.length ; index++){
            Class<?>[] paramsType = beanCons[index].getParameterTypes();
            List<Class<?>> temp = Arrays.asList(paramsType);
            boolean flag = (argsList.containsAll(temp))&(temp.containsAll(argsList));
            if (flag){
                return index;
            }
        }
        return -1;
    }
    public Object[] getArgumentObjects(List<ConstructorArgument> consList){
        Object[] paramsObject = new Object[consList.size()];
        for(int i = 0; i < consList.size(); i++){
            paramsObject[i] = beanMap.get(consList.get(i).getName());
        }
        return paramsObject;
    }
}
