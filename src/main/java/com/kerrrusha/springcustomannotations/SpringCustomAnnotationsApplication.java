package com.kerrrusha.springcustomannotations;

import com.kerrrusha.springcustomannotations.joker.Joker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static java.util.Objects.nonNull;

public class SpringCustomAnnotationsApplication {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("context.xml");

		System.out.print(SpringCustomAnnotationsApplication.class.getName() + " - ");
		logApplicationContextCreated();
		System.out.println();

		context.getBean(Joker.class).sayJoke();
	}

	public static void logApplicationContextCreated() {
		System.out.println("Application context created: " + nonNull(context));
	}

}
