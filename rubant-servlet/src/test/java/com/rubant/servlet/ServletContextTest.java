package com.rubant.servlet;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.lang.reflect.Method;

/**
 * 测试context
 *
 * @author rubant
 * @date 2022/10/29 16:02
 */
public class ServletContextTest {
    private static final Logger log = LoggerFactory.getLogger(ServletContextTest.class);

    @Test
    public void testContext() {
        Method[] methods = ServletContext.class.getDeclaredMethods();
        for (Method method : methods) {
            log.info("public static final String a = \"" + method.getName() + "\";");
        }
    }
}
