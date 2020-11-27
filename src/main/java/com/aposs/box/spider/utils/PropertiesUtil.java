package com.aposs.box.spider.utils;

import org.springframework.core.env.Environment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Aaron
 * @date 2020/11/27
 * 配置文件工具类
 */
public class PropertiesUtil {

    /**
     * 根据前缀匹配配置参数并转换为对象
     * @param env Environment对象
     * @param prefix 配置参数前缀
     * @param targetType 配置对象类型
     * @param <T>
     * @return
     */
    public static <T> T getProperties(Environment env, String prefix, Class<T> targetType) {
        try {
            T instance = targetType.newInstance();
            Field[] fields = targetType.getDeclaredFields();
            for (Field field : fields) {
                String propertiesName = getPropertiesName(prefix, field.getName());
                Object property = env.getProperty(propertiesName, field.getType());
                Method setMethod = targetType.getDeclaredMethod(getSetMethodName(field.getName()), field.getType());
                setMethod.invoke(instance, property);
            }
            return instance;
        } catch (Exception e) {
            return null;
        }
    }

    private static String getPropertiesName(String prefix, String fieldName) {
        return prefix + "." + fieldName;
    }

    private static String getSetMethodName(String fieldName) {
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
    }

}
