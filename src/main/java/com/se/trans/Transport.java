package com.se.trans;

import org.springframework.stereotype.Component;

//target
@Component
public class Transport {
	//join point
	
	//pointcut
	public void bus() {
		System.out.println("게임하기");
		System.out.println("잠자기");
	}
	
	public void subway() {
		System.out.println("노래듣기");
		System.out.println("옆사람 카톡 보기");
	}
	
}
