package com.demo.center.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * Created by LK on 2018/10/9.
 */

public class FeignLoggerConfiguration {
    //指定feign的日志级别
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
