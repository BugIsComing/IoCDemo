package com.lc.ioc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * @Author:LC
 * @Date:Created in 14:00 2017/10/21
 * @Modifyed by:
 */
@SuppressWarnings("AliDeprecation")
public class BeanUtil {
    public BeanUtil(){

    }

    /**
     * @param obj
     * @param pro
     * @param flag:值注入时为true，否则为false
     * @return
     */
    public static Method getAndInvokeSetterMethod(Object obj,Property pro,boolean flag){
        Method method = null;
        //获取属性的类型
        Class<?> objClass = obj.getClass();
        Class<?> cls = null;
        try {
             cls = objClass.getDeclaredField(pro.getName()).getType();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        String nameTemp = pro.getName();
        nameTemp = "set" + nameTemp.substring(0,1).toUpperCase()+nameTemp.substring(1);
        try {
            method = objClass.getDeclaredMethod(nameTemp,cls);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if(method == null){
            throw new RuntimeException("没有这个Set方法");
        }
        if (flag){
            invokeSetterMethod(obj,method,cls,pro);
        }
        return method;
    }

    private static void invokeSetterMethod(Object obj,Method method,Class<?> cls,Property pro){
        String className = cls.toString();
        Object data = null;
        if(className.equals(ConstantVariable.INT_CLASS_CONS) ){
            data = (int)Integer.parseInt(pro.getValue());
        }else if(className.equals(ConstantVariable.INTEGER_CLASS_CONS)){
            data= new Integer(pro.getValue());
        }else if(className.equals(ConstantVariable.DOUBLE_CLASS_CONS)){
            data= new Double(pro.getValue());
        }else if(className.equals(ConstantVariable.LITTELE_DOUBLE_CLASS_CONS)){
            data = (double)Double.parseDouble(pro.getValue());
        }else if(className.equals(ConstantVariable.DATE_CLASS_CONS)){
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
            try {
                data = sdf.parse(pro.getValue());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            data = (String)pro.getValue();
        }
        try {
            method.invoke(obj,data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
