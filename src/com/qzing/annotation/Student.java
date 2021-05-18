package com.qzing.annotation;
//@MyAnnotation(name="zhangsan") 注解不能在类上使用 因为注解定义的时候指定了只能在方法上使用@Target(value = {ElementType.METHOD})
public class Student{
    private String name;
    @MyAnnotation(name="zhangsan")
    public void study(Integer times){
        for(int i = 0; i < times; i++){
            System.out.println("Good Good Study, Day Day Up!");
        }
    }
}
