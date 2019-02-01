package io.codeleaf.oerm.object.mapping;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Generated {

    Locality value();

    Class<? extends FieldValueGenerator> generator() default DefaultFieldValueGenerator.class;
}
