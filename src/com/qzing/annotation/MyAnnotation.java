package com.qzing.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
@Documented
//@MyAnnotation被限定只能使用在类、接口或方法上面
public @interface MyAnnotation {
    // 里边只能包含注解类型元素
    public String name();
    int age() default 18;
    //int[] array();
}
