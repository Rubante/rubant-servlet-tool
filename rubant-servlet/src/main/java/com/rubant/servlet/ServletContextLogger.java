package com.rubant.servlet;

import static com.rubant.servlet.constant.ServletContextConstant.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.util.Enumeration;

/**
 * servletContext日志输出
 *
 * @author rubant
 * @date 2022/10/29 17:57
 */
public class ServletContextLogger {

    private static final Logger log = LoggerFactory.getLogger(ServletContextLogger.class);

    /**
     * 打印servletContext内容
     *
     * @param context
     */
    public static void log(ServletContext context) {
        if (!log.isInfoEnabled()) {
            return;
        }
        try {
            log.info(getInfo(SERVER_INFO, context.getServerInfo()));
            log.info(getInfo(CONTEXT_PATH, context.getContextPath()));
            log.info(getInfo(SERVLET_CONTEXT_NAME, context.getServletContextName()));
            log.info(getInfo(VIRTUAL_SERVER_NAME, context.getVirtualServerName()));
            log.info(getInfo(MAJOR_VERSION, String.valueOf(context.getMajorVersion())));
            log.info(getInfo(MINOR_VERSION, String.valueOf(context.getMinorVersion())));

            String dir = context.getResource("/").getPath();
            log.info(getInfo(WORK_DIR, dir));

            Enumeration<String> parameterNames = context.getInitParameterNames();
            log.info(getInfo(INIT_PARAMETER, " start -----------"));
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                log.info(getInfo(name, context.getInitParameter(name)));
            }
            log.info(getInfo(INIT_PARAMETER, " end -----------"));

            Enumeration<String> attributeNames = context.getAttributeNames();
            log.info(getInfo(ATTRIBUTE_NAMES, " start -----------"));
            while (attributeNames.hasMoreElements()) {
                String name = attributeNames.nextElement();
                Object value = context.getAttribute(name);
                log.info(getInfo(name, value != null ? value.toString() : ""));
            }
            log.info(getInfo(ATTRIBUTE_NAMES, " end -----------"));
        } catch (Exception ex) {
            log.error("servlet context logger error!", ex);
        }
    }
}
