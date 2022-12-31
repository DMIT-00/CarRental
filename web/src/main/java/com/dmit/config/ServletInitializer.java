package com.dmit.config;

import com.dmit.security.WebSecurityConfig;
import com.dmit.service.config.ServiceConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Initialize the Servlet container. This class is detected by the Servlet
 * container on startup.
 */
public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { DataConfig.class, ServiceConfig.class, WebSecurityConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

//    For PUT and other requests
//    https://stackoverflow.com/questions/34048617/spring-boot-how-to-use-hiddenhttpmethodfilter

//    @Override
//    protected Filter[] getServletFilters() {
//        return new Filter[] { new HiddenHttpMethodFilter() };
//    }

}
