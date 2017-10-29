package com.lc.ioc.base;

/**
 * @Author:LC
 * @Date:Created in 9:31 2017/10/28
 * @Modifyed by:
 */
public class Node {
    private String name;
    private String ref;
    private String value;
    public Node(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
