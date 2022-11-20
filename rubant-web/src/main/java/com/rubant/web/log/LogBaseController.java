package com.rubant.web.log;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

/**
 * 日志基础工具类
 *
 * @author rubant
 * @date 2022/11/13 17:47
 */
public abstract class LogBaseController {

    @Value("${baseLogDir:/export/Logs}")
    private String baseLogDir;

    public static String BASE_LOG_DIR;

    @PostConstruct
    public void init() {
        BASE_LOG_DIR = baseLogDir;
    }

    public String getBaseLogDir() {
        return baseLogDir;
    }
}
