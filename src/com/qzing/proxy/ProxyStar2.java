package com.qzing.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author qzing
 * @ClassName: ProxyStar2
 * @projectName: annotation-demo
 * @description: cglib代理
 * @date 2021/5/18 15:23
 */
public class ProxyStar2 implements MethodInterceptor {
    // 根据一个类型产生代理类，此方法不要求一定放在MethodInterceptor中
    public Object CreatProxyedObj(Class<?> clazz)
    {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置增强");
        Star s = (Star) methodProxy.invokeSuper(o,objects);//注意此处是invokeSuper而不是invoke否则会报StackOverflowError
        System.out.println("后置增强");
        return s;
    }
}
