package com.app.support.utils;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.springframework.cglib.beans.BeanMap;

public class MapUtil {
    public MapUtil() {
    }

    public static String toXml(Map<String, Object> map, String rootNode) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<").append(rootNode).append(">");
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();

        while(iterator.hasNext()) {
            String key = (String)iterator.next();
            sb.append("<" + key + ">");
            sb.append(map.get(key));
            sb.append("</" + key + ">");
        }

        sb.append("</").append(rootNode).append(">");
        return sb.toString();
    }

    public static <T> Map<String, String> beanToMap(T bean) {
        Map<String, String> map = new HashMap();
        if (bean == null) {
            return map;
        } else {
            BeanMap beanMap = BeanMap.create(bean);
            Iterator var3 = beanMap.keySet().iterator();

            while(var3.hasNext()) {
                Object key = var3.next();
                Object value = beanMap.get(key);
                if (value != null) {
                    if (value instanceof Date) {
                        Long time = ((Date)value).getTime();
                        map.put(key.toString(), time.toString());
                    } else {
                        map.put(key.toString(), value.toString());
                    }
                }
            }

            return map;
        }
    }

    /**
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<Object, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * @param bean
     * @return
     */
//    public static <T> Map<String, Object> beanToMap(T bean) {
//        Map<String, Object> map = Maps.newHashMap();
//        if (bean != null) {
//            BeanMap beanMap = BeanMap.create(bean);
//            for (Object key : beanMap.keySet()) {
//                map.put(key + "", beanMap.get(key));
//            }
//        }
//        return map;
//    }
}