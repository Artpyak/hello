package com.banana.injection.factory;

import com.banana.injection.annotation.InjectByType;
import com.banana.injection.annotation.SecondConstructor;
import com.banana.injection.annotation.Service;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObjectFactory {

    private final Reflections scanner;

    private final HashMap<Class, Object> cache = new HashMap<>();

    public ObjectFactory() {
        this.scanner = new Reflections("com.banana.injection", new SubTypesScanner(false));
    }

    public void init() {
        Set<Class<?>> collect = new HashSet<>(scanner.getSubTypesOf(Object.class));
        List<Class<?>> annotated = collect.stream().filter(aClass -> aClass.isAnnotationPresent(Service.class)).collect(Collectors.toList());

        createInstances(annotated);

        inject();

        invokeSecondConstructor();
    }

    public <T> T getBean(Class<T> tClass) {
        return (T) cache.get(tClass);
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

    private void inject() {
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
