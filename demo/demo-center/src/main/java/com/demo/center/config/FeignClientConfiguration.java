package com.demo.center.config;

import org.springframework.context.annotation.Bean;

/**
 * Created by LK on 2018/10/9.
 */

public class FeignClientConfiguration {
    @Bean
    feign.Logger.Level feignLoggerLevel() {
        return feign.Logger.Level.FULL;
    }
}
