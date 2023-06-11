package com.kerrrusha.springcustomannotations;

import com.kerrrusha.springcustomannotations.joker.Joker;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCustomAnnotationsApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
		ctx.getBean(Joker.class).sayJoke();
	}

}
