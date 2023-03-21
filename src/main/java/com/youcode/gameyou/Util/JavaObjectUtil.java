package com.youcode.gameyou.Util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class JavaObjectUtil {
    // print all fields of an object
    public void printFields(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue = null;
            try {
                fieldValue = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(fieldName + " = " + fieldValue);
        }
    }
}
