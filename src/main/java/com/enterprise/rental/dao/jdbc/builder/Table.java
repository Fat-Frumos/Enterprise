package com.enterprise.rental.dao.jdbc.builder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Reflecting Table the fields declared by the class.
 *
 * @author Pasha Pollack
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String name() default "";
}