package org.example.desinfector;

import lombok.SneakyThrows;


import javax.annotation.PostConstruct;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;


public class ObjectFactory {
    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    //    private Object t;
    private List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();


    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        Config config = context.getConfig();
        for (Class<? extends ObjectConfigurator> aClass : config.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
        for (Class<? extends ProxyConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ProxyConfigurator.class)) {
            proxyConfigurators.add(aClass.getDeclaredConstructor().newInstance());
        }
        /*for (Class<? extends ObjectConfigurator> aClass : this.context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            try {
                Constructor<? extends ObjectConfigurator> declaredConstructor = aClass.getDeclaredConstructor();
                configurators.add(declaredConstructor.newInstance());
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }*/

    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        T t = create(implClass);

        configure(t);

        invokeInit(implClass, t);

        t = wrapWithProxyIfNeeded(implClass, t);


        return t;
    }

    private <T> T wrapWithProxyIfNeeded(Class<T> implClass, T t) {
        for (ProxyConfigurator proxyConfigurator : proxyConfigurators) {
            t = (T) proxyConfigurator.replaceWithProxyIfNeeded(t, implClass);
        }
        return t;
    }

    private static <T> void invokeInit(Class<T> implClass, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }

        }
    }

    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
    }

    private static <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return implClass.getDeclaredConstructor().newInstance();
    }
}
