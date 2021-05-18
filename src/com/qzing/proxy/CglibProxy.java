package com.qzing.proxy;

/**
 * @author qzing
 * @ClassName: CglibProxy
 * @projectName: annotation-demo
 * @description: TODO
 * @date 2021/5/18 15:40
 */
public class CglibProxy {
    public static void main(String[] args) {
        ProxyStar2 p = new ProxyStar2();
        Star s = (Star) p.CreatProxyedObj(Cxu.class);
        s.sing("小太阳");
    }
}
