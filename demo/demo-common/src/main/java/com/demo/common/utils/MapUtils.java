package com.demo.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Description : Map工具类
 */
public class MapUtils {
    private static final Logger logger = LoggerFactory.getLogger(MapUtils.class);
    
    /**
     * @Description : 将map转成bean
     */
    public static void populate(Object bean, Map<String, ? extends Object> properties) {
        try {
            BeanUtilsBean.getInstance().populate(bean, properties);
       } catch (IllegalAccessException e) {
            logger.error(e.toString());
       } catch (InvocationTargetException e) {
            logger.error(e.toString());
        }
    }
    /**
     * @Description : 将bean 转化成map
     */
    public static Map<String, String> describe(Object bean) {
        Map<String,String> map = null;
        try {
             map = BeanUtilsBean.getInstance().describe(bean);
        } catch (IllegalAccessException e) {
           logger.error(e.toString());
        } catch (InvocationTargetException e) {
            logger.error(e.toString());
        } catch (NoSuchMethodException e) {
           logger.error(e.toString());
        }
        return map;
    }

    /**
     * Description: 对象相同的属性的值进行复制
     */
    public static void  copyProperties(Object dest, Object orig) {
        try {
            BeanUtils.copyProperties(dest,orig);
        } catch (IllegalAccessException e) {
          logger.info(e.toString());
        } catch (InvocationTargetException e) {
            logger.info(e.toString());
        }
    }
}
