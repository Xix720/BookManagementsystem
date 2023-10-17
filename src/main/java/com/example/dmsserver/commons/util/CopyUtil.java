package com.example.dmsserver.commons.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dmsserver.commons.vo.PageVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CopyUtil {

    /**
     * 单体复制
     */
    public static <T> T copy(Object source, Class<T> clazz){
        if (source == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BeanUtils.copyProperties(source, obj);

        try {
            Method sourceGetId = source.getClass().getMethod("getId");
            Object sourceId = sourceGetId.invoke(source);
            if(sourceId != null){
                Method targetGetId = obj.getClass().getMethod("getId");
                Object targetId = targetGetId.invoke(obj);
                if(sourceId!=targetId){
                    if(sourceId.getClass().getSimpleName().equals("Long")){
                        Method targetSetId = obj.getClass().getMethod("setId",String.class);
                        targetSetId.invoke(obj,sourceId.toString());
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static <T> T copy(Object source, Class<T> clazz,String... forceConvertFields){
        if (source == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BeanUtils.copyProperties(source, obj);

        try {
            for(String field : forceConvertFields){
                String methodName = "get"+ StringUtils.capitalize(field);
                Method sourceGetMethod = source.getClass().getMethod(methodName);
                Object sourceValue = sourceGetMethod.invoke(source);
                if(sourceValue != null){
                    Method targetGetMethod = obj.getClass().getMethod(methodName);
                    Object targetValue = targetGetMethod.invoke(obj);
                    if(! sourceValue.equals(targetValue)){
                        if(sourceValue.getClass().getSimpleName().equals("Long")){
                            String methodName2 = "set"+StringUtils.capitalize(field);
                            Method targetSetMethod = obj.getClass().getMethod(methodName2,String.class);
                            targetSetMethod.invoke(obj,sourceValue.toString());
                        }
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * 列表复制
     */
    public static <T> List<T> copyList(List source, Class<T> clazz) {
        List<T> target = new ArrayList<>();
        if (!CollectionUtils.isEmpty(source)){
            for (Object c: source) {
                T obj = copy(c, clazz);
                target.add(obj);
            }
        }
        return target;
    }

    public static <T> List<T> copyList(List source, Class<T> clazz,String ... forceConvertField) {
        List<T> target = new ArrayList<>();
        if (!CollectionUtils.isEmpty(source)){
            for (Object c: source) {
                T obj = copy(c, clazz,forceConvertField);
                target.add(obj);
            }
        }
        return target;
    }

    public static <T,E> PageVO<T> copyPage(IPage<E> page, Class<T> voClazz){
        PageVO<T> pageVO = new PageVO<>();
        pageVO.setCurrent(page.getCurrent());
        pageVO.setSize(page.getSize());
        pageVO.setTotal(page.getTotal());
        pageVO.setPages(page.getPages());

        List<T> records = copyList(page.getRecords(), voClazz);
        pageVO.setRecords(records);

        return pageVO;
    }

    public static <T,E> PageVO<T> copyPage(IPage<E> page, Class<T> voClazz,String ... forceConvertField){
        PageVO<T> pageVO = new PageVO<>();
        pageVO.setCurrent(page.getCurrent());
        pageVO.setSize(page.getSize());
        pageVO.setTotal(page.getTotal());
        pageVO.setPages(page.getPages());

        List<T> records = copyList(page.getRecords(), voClazz,forceConvertField);
        pageVO.setRecords(records);

        return pageVO;
    }
}