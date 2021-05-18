package com.qzing.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk代理
 */
class ProxyStar implements InvocationHandler {
        private Object target;
        public ProxyStar(Object target){
            this.target = target;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("我是经纪人唱歌之前收个钱");
            Object o = method.invoke(target,args);
            System.out.println("分钱分钱~");
            return o;
        }
    }