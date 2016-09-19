package com.cpower.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {
	private Logger logger = LogManager.getLogger(LoggingAspect.class);

	@Before("execution(public * *(..))")
	public void logBefore(JoinPoint joinPoint) {
		logger.info(joinPoint.getSignature().getDeclaringType().getCanonicalName() + " - " + joinPoint.getSignature().getName() + " method is started..");
	}

	@After("execution(public * *(..))")
	public void logAfter(JoinPoint joinPoint) {
		logger.info(joinPoint.getSignature().getDeclaringType().getCanonicalName() + " - " + joinPoint.getSignature().getName() + " method is finished..");
	}

}
