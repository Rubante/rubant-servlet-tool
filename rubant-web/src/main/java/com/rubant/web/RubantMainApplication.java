package com.rubant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动入口类
 *
 * @author rubant
 * @date 2022/11/13 17:02
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RubantMainApplication {

    private static final Logger logger = LoggerFactory.getLogger(RubantMainApplication.class);

    /**
     * bubant web 入口
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(RubantMainApplication.class, args);
        logger.info("rubant web start success!");
    }
}
