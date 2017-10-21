package com.lc.ioc;

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

        return obj;
    }


}
