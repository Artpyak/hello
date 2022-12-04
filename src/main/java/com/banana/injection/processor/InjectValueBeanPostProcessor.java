package com.banana.injection.processor;

import com.banana.injection.annotation.InjectValue;
import com.banana.injection.factory.ConfigProcessor;

import java.util.Arrays;
import java.util.Map;

public class InjectValueBeanPostProcessor implements BeanPostProcessor {
    @Override
    public <T> void configure(Map<Class, Object> cache, ConfigProcessor configProcessor) {
        cache.values().forEach(bean -> {
            Arrays.stream(bean.getClass().getDeclaredFields()).forEach(field -> {
                if (field.isAnnotationPresent(InjectValue.class)) {
                    InjectValue annotation = field.getAnnotation(InjectValue.class);
                    String propertyName = annotation.propertyName();
                    String propertyValue = configProcessor.getPropertieMap().get(propertyName);

                    field.setAccessible(true);
                    try {
                        field.set(bean, "\"" + propertyValue + "\"");
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        });
    }
}
