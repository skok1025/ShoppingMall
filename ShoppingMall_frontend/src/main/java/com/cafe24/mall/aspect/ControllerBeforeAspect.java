package com.cafe24.mall.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerBeforeAspect {

	@Before("execution(* *..controller.*.*(..))")
	public void beforeAdvice(JoinPoint joinpoint) {
		MethodSignature signature = (MethodSignature)joinpoint.getSignature();
		String methodName = signature.getMethod().getName();
		//String className = signature.getClass().getName();
		System.out.println("======================== before =============================");
		System.out.println("Controller Method Start: "+methodName);
		System.out.println("======================== before =============================");
		
		/*
		 * String className = pjp.getTarget().getClass().getName(); String methodName =
		 * pjp.getSignature().getName(); String taskName = className + "." + methodName;
		 * System.out.println("now:"+ taskName);
		 */
	}

	@After("execution(* *..controller.*.*(..))")
	public void afterAdvice(JoinPoint joinpoint) {
		MethodSignature signature = (MethodSignature)joinpoint.getSignature();
		String methodName = signature.getMethod().getName();
		//String className = signature.getClass().getName();
		System.out.println("=========================== after ==========================");
		System.out.println("Controller Method End: "+methodName);
		System.out.println("=========================== after ==========================");
		/*
		 * String className = pjp.getTarget().getClass().getName(); String methodName =
		 * pjp.getSignature().getName(); String taskName = className + "." + methodName;
		 * System.out.println("now:"+ taskName);
		 */
	}
	
}
