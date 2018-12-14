package com.se.trans;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component 
@Aspect
public class CardCheck {
	//Advice
	@Around("execution(* com.se.trans.Transport.*())") //전 후
	public Object check(ProceedingJoinPoint join) {
		System.out.println("카드 찍기");
		System.out.println("학생입니다.");
		
		Object obj =null;
		try {
			obj = join.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("카드 찍기");
		System.out.println("삑--");
	
	return obj;
	}
	@Before("execution(* com.se.trans.Trip.*())")
	public void packing() {
		System.out.println("짐싸기");
		
	}
}
