package com.enterprise.rental.dao.jdbc.factory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String name() default "";
    int length() default 255;
}
