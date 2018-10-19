package com.dojogrouppty.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {ApplicationConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        DelegatingFilterProxy securityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");

        return new Filter[] {characterEncodingFilter, securityFilterChain};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("defaultHtmlEscape", "true");
        registration.setInitParameter("spring.profiles.active", "default");
        registration.setMultipartConfig(getMultipartConfigElement());
        
    }
    /**
     * MultipartConfigElement
     */
      private MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement( LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }
 
    private static final String LOCATION = System.getProperty("LOCATION_PHOTO")==null?System.getenv("LOCATION_PHOTO"):System.getProperty("LOCATION_PHOTO"); // Temporary location where files will be stored
 
    private static final long MAX_FILE_SIZE = System.getProperty("MAX_FILE_SIZE") == null? Long.parseLong(System.getenv("MAX_FILE_SIZE")):Long.parseLong(System.getProperty("MAX_FILE_SIZE"));// 5242880; // 5MB : Max file size.
                                                        // Beyond that size spring will throw exception.
    private static final long MAX_REQUEST_SIZE = System.getProperty("MAX_REQUEST_SIZE")==null? Long.parseLong(System.getenv("MAX_REQUEST_SIZE")):Long.parseLong(System.getProperty("MAX_REQUEST_SIZE"));//20971520; // 20MB : Total request size containing Multi part.
     
    private static final int FILE_SIZE_THRESHOLD =System.getProperty("FILE_SIZE_THRESHOLD")==null? Integer.parseInt(System.getenv("FILE_SIZE_THRESHOLD")):Integer.parseInt(System.getProperty("FILE_SIZE_THRESHOLD"));//0; // Size threshold after which files will be written to disk
}