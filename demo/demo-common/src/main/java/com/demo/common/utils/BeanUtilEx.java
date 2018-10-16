package com.demo.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by shuzy on 2018/6/1.
 */
public class BeanUtilEx extends BeanUtils {
    private BeanUtilEx() {


    }
    static {
        // 注册sql.date的转换器，即允许BeanUtils.copyProperties时的源目标的sql类型的值允许为空
        ConvertUtils.register(new org.apache.commons.beanutils.converters.SqlDateConverter(null), java.sql.Date.class);
        ConvertUtils.register(new org.apache.commons.beanutils.converters.SqlDateConverter(null), java.util.Date.class);
        ConvertUtils.register(new org.apache.commons.beanutils.converters.SqlTimestampConverter(null), java.sql.Timestamp.class);
    }

    public static void copyProperties(Object target, Object source)
            throws InvocationTargetException, IllegalAccessException {
        // 支持对日期copy
        BeanUtils.copyProperties(target, source);
    }
}

