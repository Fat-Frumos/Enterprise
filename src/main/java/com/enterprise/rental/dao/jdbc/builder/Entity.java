package com.enterprise.rental.dao.jdbc.builder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Reflecting Entity the fields declared by the class.
 *
 * @author Pasha Polyak
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
    String name() default "";
}