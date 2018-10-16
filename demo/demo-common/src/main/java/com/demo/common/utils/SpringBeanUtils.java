package com.demo.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanUtils implements ApplicationContextAware {
	private static final Logger logger = LoggerFactory.getLogger(SpringBeanUtils.class);
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringBeanUtils.applicationContext == null) {
        	SpringBeanUtils.applicationContext = applicationContext;
        }
        logger.debug("---------------SpringBeanUtils------------------------------------------------------");
        logger.debug("========ApplicationContext配置成功,在普通类可以通过调用SpringBeanUtils.getAppContext()获取applicationContext对象,applicationContext="+SpringBeanUtils.applicationContext+"========");
        logger.debug("---------------------------------------------------------------------");
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

}