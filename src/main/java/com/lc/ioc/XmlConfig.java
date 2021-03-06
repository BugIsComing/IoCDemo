package com.lc.ioc;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlConfig {
    /**
     * 默认为setter
     */
    public  static String iocType = "setter";
    public static Map<String,Bean> getApplicationContent(String fileName){
        Map<String,Bean> config = new HashMap<String,Bean>();
        if ("".equals(fileName)){
            System.out.println("The file name is empty!");
            return null;
        }

        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(new File(fileName));
            String nodeName = "//bean";
            List<Element> typeList = doc.selectNodes("//type");
            iocType = typeList.get(0).attributeValue("value");
            List<Element> eleList = doc.selectNodes(nodeName);

            for(Element ele : eleList) {
                Bean bean = new Bean();
                String beanId = ele.attributeValue("id");
                String cls = ele.attributeValue("class");
                bean.setId(beanId);
                bean.setClassName(cls);
                List<Element> properties;
                if ("constructor".equals(iocType)){
                    properties = ele.elements("constructor-arg");
                }else{
                    properties = ele.elements("property");
                }

                for(Element proEle:properties){

                    Map<String,String> map = getProperty(proEle);

                    if ("constructor".equals(iocType)){
                        ConstructorArgument conArg = new ConstructorArgument();
                        conArg.setName(map.get("name"));
                        conArg.setRef(map.get("ref"));
                        conArg.setValue(map.get("value"));
                        bean.getConstructorArgumentList().add(conArg);
                    }else{
                        Property pro = new Property();
                        pro.setName(map.get("name"));
                        pro.setRef(map.get("ref"));
                        pro.setValue(map.get("value"));
                        bean.getPropertiesList().add(pro);
                    }
                }

                if(config.containsKey(beanId)){
                    throw new RuntimeException("bean节点ID重复：" + beanId);
                }
                config.put(beanId,bean);
            }

        }catch (DocumentException e){
            e.printStackTrace();
            return null;
        }
        return config;
    }

    public String getIocType() {
        return iocType;
    }

    public void setIocType(String iocType) {
        this.iocType = iocType;
    }

    public static Map<String,String> getProperty(Element ele){
        Map result = new HashMap<>();
        result.put("name",ele.attributeValue("name"));
        result.put("ref",ele.attributeValue("ref"));
        result.put("value",ele.attributeValue("value"));
        return result;
    }
}
