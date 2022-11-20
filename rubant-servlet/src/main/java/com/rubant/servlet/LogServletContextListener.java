package com.rubant.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 应用启动及终止过程中，输出日志
 *
 * @author rubant
 * @date 2022/10/29 15:54
 */
public class LogServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        ServletContextLogger.log(context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
