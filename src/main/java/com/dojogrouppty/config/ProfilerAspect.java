/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author lrodriguezn
 */
@Aspect
@Component
public class ProfilerAspect {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * org.springframework.data.repository.Repository+.*(..))")
    public void monitor() {}

    @Around("monitor()")
    public Object profile(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();
        logger.debug("JVM memory in use = "+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        Object output = null;
        try {
            output = pjp.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        long elapsedTime = System.currentTimeMillis() - start;
        logger.debug(pjp.getTarget()+"."+pjp.getSignature()+": Execution time: " + elapsedTime + " ms. ("+ elapsedTime/60000 + " minutes)");

        return output;
    }
    @Before("within(com.dojogrouppty..*)")
	public void logBefore(JoinPoint point) {
		logger.info(point.getSignature().getName() + " called...");
	}

	
	// AfterReturning advice
	@AfterReturning("within(com.dojogrouppty..*)")
	public void logAfter(JoinPoint point) {
		logger.info(point.getSignature().getName() + " called...");
	}

	// AfterThrowing advice
	@AfterThrowing(pointcut = "within(com.dojogrouppty..*)",throwing= "error")
	public void accident(JoinPoint point, Throwable error) {
		logger.info(point.getSignature().getName() + " called...");
//                logger.info(error.getMessage());
                logger.error(error.getMessage());
	}
        
}