package com.rubant.servlet.constant;

/**
 * servletConstant
 *
 * @author rubant
 * @date 2022/10/29 16:01
 */
public class ServletContextConstant {
    /**
     * 分号
     */
    public static final String SEMICOLON = " : ";
    /**
     * 服务端消息
     */
    public static final String SERVER_INFO = "serverInfo";

    public static final String CONTEXT_PATH = "contextPath";

    public static final String SERVLET_CONTEXT_NAME = "servletContextName";

    public static final String MAJOR_VERSION = "majorVersion";

    public static final String MINOR_VERSION = "minorVersion";

    public static final String VIRTUAL_SERVER_NAME = "virtualServerName";

    /**
     * context初始化参数
     */
    public static final String INIT_PARAMETER = "initParameterNames 系统初始化参数";

    /**
     * 属性名称
     */
    public static final String ATTRIBUTE_NAMES = "attributeNames 系统属性值";

    /**
     * 根目录
     */
    public static final String WORK_DIR = "dir";

    /**
     * 日志内容
     *
     * @param key
     * @param msg
     * @return
     */
    public static String getInfo(String key, String msg) {
        return key + SEMICOLON + msg;
    }
}
