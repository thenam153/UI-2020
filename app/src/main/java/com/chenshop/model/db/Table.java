package com.chenshop.model.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public final class Table<BeanType extends Bean<BeanType>> implements Serializable {
    private final List<BeanType> table;
    private final Map<String, Map<Object, BeanType>> uniqueMap;
    private final Set<String> notNullSet;

    private int idCounter;

    public Table(Class<BeanType> aClass, List<BeanType> table) {
        try {
            // ensure class are loaded before get map (and newInstance work, too)
            aClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new IllegalArgumentException(e);
        }

        this.table = new ArrayList<>();
        this.uniqueMap = new HashMap<>();
        this.notNullSet = new TreeSet<>();
        this.idCounter = 1;

        final Map<String, BeanHelper> fieldMap = Bean.getFieldMap(aClass);
        for (final Map.Entry<String, BeanHelper> entry : fieldMap.entrySet()) {
            final Bean.BeanField beanField = entry.getValue().getAnnotation();
            final String field = entry.getKey();
            if (beanField.isUnique()) uniqueMap.put(field, new HashMap<>());
            if (beanField.isNotNull()) notNullSet.add(field);
        }
        if (table != null && table.size() > 0) {
            for (final BeanType listBean : table) {
                if (add(listBean) == null) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    public Table(Class<BeanType> aClass) {
        this(aClass, null);
    }

    public synchronized int size() {
        return table.size();
    }

    private synchronized BeanType searchByIndex(BeanType searchBean, Set<String> searchFields) {
        for (final Map.Entry<String, Map<Object, BeanType>> entry : uniqueMap.entrySet()) {
            final String field = entry.getKey();
            if (searchFields.contains(field)) {
                final Map<Object, BeanType> map = entry.getValue();
                final Object searchValue = searchBean.getValue(field);
                if (searchValue != null) {
                    final BeanType indexBean = map.get(searchValue);
                    return match(indexBean, searchBean, searchFields) ? indexBean : null;
                }
            }
        }
        return null;
    }

    private synchronized boolean mergeCheck(BeanType listBean, BeanType inputBean, Set<String> inputFields) {
        for (final String field : inputFields) {
            final Object inputValue = inputBean.getValue(field);
            if (inputValue != null) {
                // unique check
                final Map<Object, BeanType> map = uniqueMap.get(field);
                if (map != null) {
                    final BeanType indexBean = map.get(inputValue);
                    if (indexBean != null && indexBean != listBean) return false;
                }
            } else if (notNullSet.contains(field)) {
                // null check
                return false;
            }
        }
        return true;
    }

    private synchronized void merge(BeanType listBean, BeanType inputBean, Set<String> inputFields) {
        // merge uncheck
        for (final String field : inputFields) {
            final Map<Object, BeanType> map = uniqueMap.get(field);
            final Object inputValue = inputBean.getValue(field);
            final Object oldValue = listBean.setValue(field, inputValue);
            if (map != null && map.get(oldValue) != null) {
                map.remove(oldValue);
                map.put(inputValue, listBean);
            }
        }
    }

    private synchronized boolean match(BeanType listBean, BeanType inputBean, Set<String> inputFields) {
        for (final String field : inputFields) {
            final Object value = inputBean.getValue(field);
            if (value != null && !value.equals(listBean.getValue(field))) return false;
        }
        return true;
    }

    private synchronized void delete(BeanType listBean) {
        for (final Map.Entry<String, Map<Object, BeanType>> entry : uniqueMap.entrySet()) {
            final Object value = listBean.getValue(entry.getKey());
            if (value != null) entry.getValue().remove(value);
        }
        table.remove(listBean);
    }

    public synchronized final List<BeanType> get(BeanType searchBean, Set<String> searchFields) {
        if (searchBean == null) return new ArrayList<>(this.table);
        final List<BeanType> list = new LinkedList<>();
        for (final BeanType listBean : this.table) {
            if (match(listBean, searchBean, searchFields)) {
                list.add(listBean.newCopy());
            }
        }
        return new ArrayList<>(list);
    }

    public synchronized List<BeanType> list() {
        return get(null, null);
    }

    public synchronized final BeanType add(BeanType inputBean) {
        // unique check
        for (final Map.Entry<String, Map<Object, BeanType>> entry : uniqueMap.entrySet()) {
            final String field = entry.getKey();
            final Map<Object, BeanType> map = entry.getValue();
            final Object value = inputBean.getValue(field);
            if (value != null) {
                if (map.get(value) != null) return null;
            } else {
                if (notNullSet.contains(field) && !field.equals("id")) return null;
            }
        }
        // copy, add id if needed
        final BeanType listBean = inputBean.newCopy();
        if (!listBean.hasNone("id")) {
            final Map<Object, BeanType> map = uniqueMap.get("id");
            assert map != null;
            while (map.containsKey(idCounter)) idCounter += 1;
            listBean.setId(idCounter);
        }
        // add
        table.add(listBean);
        // unique map
        for (final Map.Entry<String, Map<Object, BeanType>> entry : uniqueMap.entrySet()) {
            final String field = entry.getKey();
            final Map<Object, BeanType> map = entry.getValue();
            final Object value = inputBean.getValue(field);
            if (value != null) map.put(value, listBean);
        }
        // return copy
        return listBean.newCopy();
    }

    public synchronized final List<BeanType> edit(BeanType searchBean, Set<String> searchFields, BeanType inputBean, Set<String> inputFields) {
        // search by index
        {
            final BeanType listBean = searchByIndex(searchBean, searchFields);
            if (listBean != null) {
                if (!mergeCheck(listBean, inputBean, inputFields)) return null;
                merge(listBean, inputBean, inputFields);
                final List<BeanType> outputList = new ArrayList<>(1);
                outputList.add(listBean.newCopy());
                return outputList;
            }
        }
        // fallback to sequential search
        {
            // sequential search & check merge condition
            final List<BeanType> listBeans = new ArrayList<>();
            for (final BeanType listBean : this.table) {
                if (match(listBean, inputBean, inputFields)) {
                    if (!mergeCheck(listBean, inputBean, inputFields)) return null;
                    listBeans.add(listBean);
                }
            }
            // check input unique
            if (listBeans.size() > 1) {
                for (String inputField : inputFields) {
                    if (inputBean.has(inputField) && uniqueMap.containsKey(inputField)) return null;
                }
            }
            // merge and create output table
            final List<BeanType> outputList = new ArrayList<>(listBeans.size());
            for (BeanType listBean : listBeans) {
                merge(listBean, inputBean, inputFields);
                outputList.add(listBean.newCopy());
            }
            return outputList;
        }
    }

    public synchronized final List<BeanType> delete(BeanType searchBean, Set<String> searchFields) {
        // search by index
        {
            final BeanType listBean = searchByIndex(searchBean, searchFields);
            if (listBean != null) {
                delete(listBean);
                final List<BeanType> outputList = new ArrayList<>(1);
                outputList.add(listBean);
                return outputList;
            }
        }
        // fallback to sequential search
        {
            final List<BeanType> outputList = new ArrayList<>();
            for (final BeanType listBean : this.table) {
                if (match(listBean, searchBean, searchFields)) {
                    delete(listBean);
                    outputList.add(listBean);
                }
            }
            return outputList;
        }
    }
}
