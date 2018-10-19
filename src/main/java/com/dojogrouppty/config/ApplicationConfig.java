package com.dojogrouppty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.dojogrouppty.Application;
import java.text.NumberFormat;
import java.util.Locale;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@PropertySource("file:${LOCATION_PROPERTIES}/persistence-mariadb.properties")
@PropertySource("file:${LOCATION_PROPERTIES}/application.properties")
@ComponentScan(basePackageClasses = Application.class)
@EnableAspectJAutoProxy (proxyTargetClass = true)   
class ApplicationConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean
    public static NumberFormat currencyFormatter(){
        Locale locale = new Locale("en", "US");      
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter;
    } 
}