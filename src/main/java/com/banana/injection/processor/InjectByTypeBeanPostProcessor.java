package com.banana.injection.processor;

import com.banana.injection.annotation.InjectByType;
import com.banana.injection.factory.ConfigProcessor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InjectByTypeBeanPostProcessor implements BeanPostProcessor {
    @Override
    public <T> void configure(Map<Class, Object> cache, ConfigProcessor configProcessor) {
        cache.values().forEach(instance -> {
            Arrays.stream(instance.getClass().getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(InjectByType.class))
                    .forEach(field -> {
                        List<Class<?>> interfaces = Arrays.stream(field.getType().getInterfaces()).collect(Collectors.toList());

                        Object object;
                        if (interfaces.isEmpty()) {
                            Class<?> type = field.getType();
                            object = cache.get(type);
                        } else {
                            object = cache.get(interfaces.get(0));
                        }
                        field.setAccessible(true);
                        try {
                            field.set(instance, object);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    });
        });
    }
}
