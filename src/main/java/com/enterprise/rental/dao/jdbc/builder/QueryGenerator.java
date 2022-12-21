package com.enterprise.rental.dao.jdbc.builder;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Query generator is used to generate a query by giving set of rules.
 * Returns a String Builder of Field objects reflecting all the fields declared by the class.
 *
 * @author Pasha Pollack
 */
public class QueryGenerator {

    static String findAll(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            return "";
        }

        StringBuilder query = new StringBuilder("SELECT ");
        StringJoiner columns = new StringJoiner(", ");
        Arrays.stream(clazz.getDeclaredFields())
                .map(declaredField -> declaredField.getAnnotation(Column.class))
                .filter(Objects::nonNull)
                .map(Column::name)
                .forEach(columns::add);

        query.append(columns);
        query.append(" FROM ");

        Table annotation = clazz.getAnnotation(Table.class);
        String name = annotation.name().isEmpty() ? clazz.getName() : annotation.name();
        query.append(name);
//        query.append(";");

        return query.toString();
    }
}
