package com.kerrrusha.springcustomannotations;

import com.kerrrusha.springcustomannotations.joker.Joker;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static java.util.Objects.nonNull;

public class SpringCustomAnnotationsApplication {

	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("context.xml");

		System.out.print(SpringCustomAnnotationsApplication.class.getName() + " - ");
		logApplicationContextCreated();
		System.out.println();

		context.getBean(Joker.class).sayJoke();

		System.out.println("\n" + SpringCustomAnnotationsApplication.class.getName() + " - Closing context...");
		context.close();
	}

	public static void logApplicationContextCreated() {
		System.out.println("Application context created: " + nonNull(context));
	}

}
