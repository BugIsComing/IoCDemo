package com.lc.ioc;

import java.util.ArrayList;
import java.util.List;

public class Bean {
    private String id;
    private String className;
    private List<Property> propertiesList = new ArrayList<Property>();
    private List<ConstructorArgument> constructorArgumentList = new ArrayList<ConstructorArgument>();

    public List<ConstructorArgument> getConstructorArgumentList() {
        return constructorArgumentList;
    }

    public void setConstructorArgumentList(List<ConstructorArgument> constructorArgumentList) {
        this.constructorArgumentList = constructorArgumentList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Property> getPropertiesList() {
        return propertiesList;
    }

    public void setPropertiesList(List<Property> propertiesList) {
        this.propertiesList = propertiesList;
    }


    public Bean(String id, String className, List<Property> propertiesList) {
        this.id = id;
        this.className = className;
        this.propertiesList = propertiesList;
    }

    public Bean() {
    }
}
