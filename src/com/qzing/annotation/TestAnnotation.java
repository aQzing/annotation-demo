package com.qzing.annotation;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class TestAnnotation {
    static {
        SecurityManager sm = new SecurityManager();
        //如果是PUBLIC，反射可以执行，DECLARED反射运行时，会报错
        sm.checkMemberAccess(Student.class, Member.PUBLIC);
    }
    public static void main(String[] args){

        try {
            //获取Student的Class对象
            Class stuClass = Class.forName("com.qzing.annotation.Student");

            Method stuMethod = stuClass.getMethod("study",Integer.class);
            if(stuMethod.isAnnotationPresent(MyAnnotation.class)){
                System.out.println("Student类上配置了CherryAnnotation注解！");
                //获取该元素上指定类型的注解
                MyAnnotation cherryAnnotation = stuMethod.getAnnotation(MyAnnotation.class);
                System.out.println("name: " + cherryAnnotation.name() + ", age: " + cherryAnnotation.age());
            }else{
                System.out.println("Student类上没有配置CherryAnnotation注解！");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
