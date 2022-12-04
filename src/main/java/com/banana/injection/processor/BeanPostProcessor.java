package com.banana.injection.processor;

import com.banana.injection.factory.ConfigProcessor;

import java.util.List;
import java.util.Map;

public interface BeanPostProcessor {

    <T> void configure(Map<Class, Object> cache, ConfigProcessor configProcessor);
}
