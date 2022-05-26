package com.app.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

public class AppContextUtil {
    private static ApplicationContext context = null;

    public AppContextUtil() {
    }

    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (context == null) {
            context = applicationContext;
        }

    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> Object getBean(String beanName) {
        try {
            return context.getBean(beanName);
        } catch (BeansException var2) {
            return null;
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        try {
            return context.getBean(clazz);
        } catch (BeansException var2) {
            return null;
        }
    }

    public static boolean containsBean(ServletContext servletContext, String beanName) {
        WebApplicationContext wct = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        return wct.containsBean(beanName);
    }

    public static boolean containsBean(String beanName) {
        if (context == null) {
            throw new RuntimeException("ApplicationContext对象未实例化.");
        } else {
            return context.containsBean(beanName);
        }
    }

    public static String getActiveProfiles() {
        return context == null ? "prod" : context.getEnvironment().getActiveProfiles()[0];
    }
}