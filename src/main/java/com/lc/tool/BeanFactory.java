package com.lc.tool;

/*
 *bean容器，存放所有的对象
 */
public interface BeanFactory {
    public Object getBean(String beanName);
}
