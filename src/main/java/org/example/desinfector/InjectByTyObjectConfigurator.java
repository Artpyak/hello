package org.example.desinfector;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class InjectByTyObjectConfigurator implements ObjectConfigurator {

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
         if (field.isAnnotationPresent(InjectByTy.class)) {
             field.setAccessible(true);
             Object object = context.getObject(field.getType());
             field.set(t, object);
         }
        }
    }
}
