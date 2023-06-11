package com.kerrrusha.springcustomannotations.annotation.postprocessor;

import com.kerrrusha.springcustomannotations.annotation.InjectRandomJoke;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

import static java.util.Objects.isNull;

public class InjectRandomJokeAnnotationBeanPostProcessor implements BeanPostProcessor {

    private static final List<String> availableJokes = List.of(
            "Why don't scientists trust atoms?\n" +
                    "Because they make up everything!",
            "I'm reading a book about anti-gravity. It's impossible to put down!",
            "What's the difference between a snowman and a snowwoman?\n" +
                    "Snowballs!"
    );

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectRandomJoke annotation = field.getAnnotation(InjectRandomJoke.class);
            if (isNull(annotation)) {
                continue;
            }

            int jokeIndex = new Random().nextInt(availableJokes.size());
            String chosenJoke = availableJokes.get(jokeIndex);

            field.setAccessible(true);
            ReflectionUtils.setField(field, bean, chosenJoke);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
