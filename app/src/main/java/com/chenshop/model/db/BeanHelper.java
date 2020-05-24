package com.chenshop.model.db;


import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * DO NOT TOUCH - mrmathami
 */
final class BeanHelper implements Serializable {
    private final Method getter;
    private final Method setter;
    private final Bean.BeanField annotation;

    BeanHelper(Method getter, Method setter, Bean.BeanField annotation) {
        this.getter = getter;
        this.setter = setter;
        this.annotation = annotation;
    }

    Object getValue(Object object) throws InvocationTargetException, IllegalAccessException {
        return getter.invoke(object);
    }

    Object setValue(Object object, Object value) throws InvocationTargetException, IllegalAccessException {
        return setter.invoke(object, value);
    }

    Bean.BeanField getAnnotation() {
        return annotation;
    }
}