package com.learn.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanFactory {

    public static final Map<String, Object> beanMap = new HashMap<>();

    // 解析xml并实例化bean放入map中
    static {
        InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document doc = saxReader.read(in);
            Element root = doc.getRootElement();
            List<Element> beanList = root.selectNodes("bean");
            if (beanList != null && !beanList.isEmpty()) {
                for (Element bean : beanList) {
                    String id = bean.attributeValue("id");
                    String className = bean.attributeValue("class");
                    Class<?> clazz = Class.forName(className);
                    Object obj = clazz.newInstance();
                    beanMap.put(id, obj);
                }
            }
            List<Element> propertyList = root.selectNodes("//property");
            if (propertyList != null && !propertyList.isEmpty()) {
                for (Element prop : propertyList) {
                    String name = prop.attributeValue("name");
                    String value = prop.attributeValue("value");
                    String ref = prop.attributeValue("ref");
                    Element parent = prop.getParent();
                    String id = parent.attributeValue("id");
                    Object obj = beanMap.get(id);
                    Method[] methods = obj.getClass().getMethods();
                    for (Method method : methods) {
                        if (method.getName().equalsIgnoreCase("set" + toUpperFirst(ref))) {
                            method.invoke(obj, beanMap.get(ref));
                        }
                    }
                    beanMap.put(id, obj);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static String toUpperFirst(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        char[] chars = str.toCharArray();
        chars[0] = (char) (chars[0] - 32);
        return String.valueOf(chars);
    }


    public static Object getBean(String name) {
        return beanMap.get(name);
    }

}
