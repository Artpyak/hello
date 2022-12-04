package org.example.desinfector;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> ifc);

    org.reflections.Reflections getScanner();
}
