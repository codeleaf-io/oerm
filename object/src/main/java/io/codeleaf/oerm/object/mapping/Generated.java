package io.codeleaf.oerm.object.mapping;

import io.codeleaf.oerm.object.Entity;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Map;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Generated {
    Class<? extends ObjectFieldGenerator> value() default NotSpecified.class;

    class NotSpecified implements ObjectFieldGenerator {
        @Override
        public boolean supportsType(Class<?> fieldType) {
            return false;
        }

        @Override
        public <T> T generate(Class<? extends Entity> entityType, Method method, Map<Method, Object> fields) {
            return null;
        }
    }
}
