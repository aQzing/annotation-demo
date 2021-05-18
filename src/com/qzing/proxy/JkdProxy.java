package com.qzing.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author qzing
 * @ClassName: JkdProxy
 * @projectName: annotation-demo
 * @description: 类A写死持有B，就是B的静态代理。如果A代理的对象是不确定的，就是动态代理。动态代理目前有两种常见的实现，jdk动态代理和cglib动态代理
 * @date 2021/5/18 10:53
 */
public class JkdProxy {


    public static void main(String[] args) {
        Star s = new Cxu();
        //需要增强对象的ClassLoader， 需要增强对象的Interfaces，代理对象（需要传入增强的实例）
        Star s1 = (Star) Proxy.newProxyInstance(s.getClass().getClassLoader(), s.getClass().getInterfaces(), new ProxyStar(s));
        s1.sing("rap");
    }
}

