package io.codeleaf.oerm.object.mapping;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Mapping {
    String value() default "";

    Class<? extends ObjectFieldMapper> mapper() default DefaultObjectFieldMapper.class;
}
