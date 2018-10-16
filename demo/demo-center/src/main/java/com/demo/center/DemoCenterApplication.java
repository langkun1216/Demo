package com.demo.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

// 暂时禁用掉mongo,mysql的自动连接，以后项目有需要的时候再去掉exclude
// springboot启动时会自动注入数据源和配置jpa,在@SpringBootApplication中(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})可解决
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
@ServletComponentScan
@RefreshScope
public class DemoCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCenterApplication.class, args);
	}
}
