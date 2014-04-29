package fr.treeptik.bibliotheque.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component("serviceOperationTracer")
public class ServiceOperationTracer
{
	//
	@Before("execution(* fr.treeptik.bibliotheque.service.*.*(..))")
	public void logBefore(JoinPoint joinPoint)
	{
		System.out.println("Before ServiceOperation : "+joinPoint.getSignature().toString());
	}
	@After("execution(* fr.treeptik.bibliotheque.service.*.*(..))")
	public void logAfter(JoinPoint joinPoint)
	{
		System.out.println("After ServiceOperation : "+joinPoint.getSignature().toString());
	}
}
