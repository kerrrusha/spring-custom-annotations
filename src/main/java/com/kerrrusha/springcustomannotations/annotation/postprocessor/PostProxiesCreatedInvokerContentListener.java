package com.kerrrusha.springcustomannotations.annotation.postprocessor;

import com.kerrrusha.springcustomannotations.annotation.PostProxiesCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;

public class PostProxiesCreatedInvokerContentListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfigurableListableBeanFactory factory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition definition = factory.getBeanDefinition(name);
            String originalClassName = definition.getBeanClassName();
            try {
                Class<?> originalClass = Class.forName(originalClassName);
                Method[] methods = originalClass.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(PostProxiesCreated.class)) {
                        Object proxiedBean = context.getBean(name);
                        Method targetMethod = proxiedBean.getClass().getMethod(method.getName(), method.getParameterTypes());
                        targetMethod.invoke(proxiedBean);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
