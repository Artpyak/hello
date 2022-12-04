package com.banana.injection.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
    Scope scope() default Scope.SINGLETON;
}
