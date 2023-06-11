package com.kerrrusha.springcustomannotations.annotation.postprocessor;

import com.kerrrusha.springcustomannotations.annotation.InjectRandomInt;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

import static java.util.Objects.isNull;

public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
            if (isNull(annotation)) {
                continue;
            }
            int from = annotation.from();
            int to = annotation.to();

            int randomInt = from + new Random().nextInt(to - from);

            field.setAccessible(true);
            ReflectionUtils.setField(field, bean, randomInt);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
