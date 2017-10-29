package com.lc.ioc;

import com.lc.ioc.base.Node;

public class Property extends Node{

    public Property(String name, String ref, String value) {
        setName(name);
        setRef(ref);
        setValue(value);
    }

    public Property() {
    }
}
