package io.codeleaf.oerm.object.mapping;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Mapping {
    String value() default "";

    Class<? extends EntityFieldMapper> mapper() default DefaultEntityFieldMapper.class;
}
