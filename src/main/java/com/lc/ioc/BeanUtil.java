package com.lc.ioc;

import java.lang.reflect.Method;

/**
 * @Author:LC
 * @Date:Created in 14:00 2017/10/21
 * @Modifyed by:
 */
public class BeanUtil {

    public static Method getSetterMethod(Object obj,String name){
       //构造set方法名
        String nameTemp = "set" + name.substring(0,1).toUpperCase()+name.substring(1);

        try {
            Method[] methods = obj.getClass().getMethods();
            for (int i=0;i<methods.length;i++){
                if(methods[i].getName().equals(nameTemp)){
                    return methods[i];
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
