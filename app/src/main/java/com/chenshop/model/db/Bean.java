package com.chenshop.model.db;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Bean<BeanType extends Bean<BeanType>> implements Serializable, Comparable<Bean<BeanType>> {
    private static final Map<Class<?>, Map<String, BeanHelper>> CLASS_FIELD_MAP = new HashMap<>();
    private static final long serialVersionUID = 1L;

    @BeanField(isUnique = true, isNotNull = true)
    private Integer id;

    private static void createBeanHelper(Map<String, BeanHelper> fieldMap, Class<?> aClass, Field field) {
        final BeanField fieldAnnotation = field.getAnnotation(BeanField.class);
        if (fieldAnnotation != null) {
            final Class<?> fieldType = field.getType();
            final char[] fieldNameCharArray = field.getName().toCharArray();
            fieldNameCharArray[0] = Character.toUpperCase(fieldNameCharArray[0]);
            final String fieldNameUppercase = new String(fieldNameCharArray);

            final String getterName = fieldAnnotation.getter().isEmpty() ? ("get" + fieldNameUppercase) : fieldAnnotation.getter();
            final String setterName = fieldAnnotation.setter().isEmpty() ? ("set" + fieldNameUppercase) : fieldAnnotation.setter();
            try {
                final Method getter = aClass.getMethod(getterName);
                final Method setter = aClass.getMethod(setterName, fieldType);
                fieldMap.put(field.getName(), new BeanHelper(getter, setter, fieldAnnotation));
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    protected synchronized static <BeanType extends Bean<BeanType>> void beanInitialize(Class<BeanType> aClass) {
        final Map<String, BeanHelper> fieldMap = new HashMap<>();
        try {
            createBeanHelper(fieldMap, Bean.class, Bean.class.getDeclaredField("id"));
        } catch (NoSuchFieldException ignored) {
        }
        for (final Field field : aClass.getDeclaredFields()) {
            createBeanHelper(fieldMap, aClass, field);
        }
        CLASS_FIELD_MAP.put(aClass, fieldMap);
    }

    synchronized static Map<String, BeanHelper> getFieldMap(Class<?> aClass) {
        return CLASS_FIELD_MAP.get(aClass);
    }

    public final Integer getId() {
        return id;
    }

    public final void setId(Integer id) {
        this.id = id;
    }

    public final Object getValue(String field) {
        final Map<String, BeanHelper> fieldMap = getFieldMap(getClass());
        final BeanHelper methods = fieldMap.get(field);
        if (methods == null) throw new IllegalArgumentException("field = " + field);
        try {
            return methods.getValue(this);
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException();
        }
    }

    public final Object setValue(String field, Object value) {
        final Map<String, BeanHelper> fieldMap = getFieldMap(getClass());
        final BeanHelper methods = fieldMap.get(field);
        if (methods == null) throw new IllegalArgumentException();
        try {
            return methods.setValue(this, value);
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public final boolean has(String... fields) {
        for (final String field : fields) if (getValue(field) == null) return false;
        return true;
    }

    public final boolean hasAll(String... exceptFields) {
        final Map<String, BeanHelper> fieldMap = getFieldMap(getClass());
        final Set<String> fields = fieldMap.keySet();
        Arrays.sort(exceptFields);
        for (final String field : fields) {
            if (getValue(field) == null && Arrays.binarySearch(exceptFields, field) < 0) {
                return false;
            }
        }
        return true;
    }

    public final boolean hasNone(String... fields) {
        for (final String field : fields) if (getValue(field) != null) return false;
        return true;
    }

    public final boolean hasNothing(String... exceptFields) {
        final Map<String, BeanHelper> fieldMap = getFieldMap(getClass());
        final Set<String> fields = fieldMap.keySet();
        Arrays.sort(exceptFields);
        for (final String field : fields) {
            if (getValue(field) != null && Arrays.binarySearch(exceptFields, field) < 0) {
                return false;
            }
        }
        return true;
    }

    public final BeanType newCopy() {
        try {
            @SuppressWarnings("unchecked") final Class<BeanType> beanClass = (Class<BeanType>) getClass();
            final BeanType bean = beanClass.newInstance();
            final Map<String, BeanHelper> fieldMap = getFieldMap(beanClass);
            for (final BeanHelper fieldHelper : fieldMap.values()) {
                fieldHelper.setValue(bean, fieldHelper.getValue(this));
            }
            return bean;
        } catch (ReflectiveOperationException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public final String toString() {
        final Map<String, BeanHelper> fieldMap = getFieldMap(getClass());
        final StringBuilder builder = new StringBuilder("{id=").append(id);
        for (final String field : fieldMap.keySet()) {
            if (!field.equals("id")) {
                builder.append(',').append(field).append('=').append(getValue(field));
            }
        }
        return builder.append('}').toString();
    }

    @Override
    public int compareTo(Bean<BeanType> bean) {
        return Integer.compare(id, bean.id);
    }

    @Retention(RetentionPolicy.RUNTIME)
    protected @interface BeanField {
        String getter() default "";

        String setter() default "";

        boolean isUnique() default false;

        boolean isNotNull() default false;
    }
}
