package com.banana.injection.factory;

import com.banana.injection.annotation.SecondConstructor;
import com.banana.injection.annotation.Service;
import com.banana.injection.processor.BeanPostProcessor;
import lombok.Setter;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class BeanFactory {

    @Setter
    private String fileName = "application.properties";

    private final Reflections scanner;
    private final ConfigProcessor configProcessor;

    private final List<BeanPostProcessor> processors = new ArrayList<>();
    private final HashMap<Class, Object> cache = new HashMap<>();

    public BeanFactory() {
        this.scanner = new Reflections("com.banana.injection", new SubTypesScanner(false));
        this.configProcessor = new ConfigProcessor();
        configProcessor.init(fileName);
    }

    public void init() {
        Set<Class<?>> collect = new HashSet<>(scanner.getSubTypesOf(Object.class));
        collectProcessors(collect);

        List<Class<?>> annotated = collect.stream().filter(aClass -> aClass.isAnnotationPresent(Service.class)).collect(Collectors.toList());
        createInstances(annotated);
        processBeans();
        invokeSecondConstructor();
    }

    public <T> T getBean(Class<T> tClass) {
        return (T) cache.get(tClass);
    }

    private void collectProcessors(Set<Class<?>> collect) {
        collect.forEach(aClass -> {
            List<Class<?>> interfaces = Arrays.stream(aClass.getInterfaces()).collect(Collectors.toList());
            if (!interfaces.isEmpty()) {
                Class<?> intf = interfaces.get(0);
                if (intf == BeanPostProcessor.class) {
                    try {
                        processors.add((BeanPostProcessor)aClass.getDeclaredConstructor().newInstance());
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void createInstances(List<Class<?>> annotated) {
        annotated.forEach(aClass -> {
            Object instance;
            try {
                instance = aClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            List<Class<?>> interfaces = Arrays.stream(aClass.getInterfaces()).collect(Collectors.toList());

            if (interfaces.size() > 1) throw new RuntimeException("Class shouldn't have more then one interface");
            if (interfaces.isEmpty()) {
                cache.put(instance.getClass(), instance);
            } else {
                cache.put(interfaces.get(0), instance);
            }
        });
    }

    private void processBeans() {
        processors.forEach(processor -> processor.configure(cache, configProcessor));
    }

    private void invokeSecondConstructor() {
        cache.values().forEach(object -> {
            Arrays.stream(object.getClass().getMethods())
                    .filter(method -> method.isAnnotationPresent(SecondConstructor.class))
                    .forEach(method -> {
                        List<Class<?>> params = Arrays.stream(method.getParameterTypes()).collect(Collectors.toList());
                        if (!params.isEmpty()) throw new RuntimeException("Method should have 0 params");

                        String returnType = method.getReturnType().getName();
                        if (!returnType.equals("void")) throw new RuntimeException("Return type should be void");

                        try {
                            method.invoke(object);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    });
        });
    }
}
