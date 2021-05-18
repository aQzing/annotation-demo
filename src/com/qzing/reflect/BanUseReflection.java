package com.qzing.reflect;

import com.qzing.annotation.Student;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ReflectPermission;

/**
 * @author qzing
 * @ClassName: BanUseReflection
 * @projectName: annotation-demo
 * @description: 禁止使用反射
 * @date 2021/4/25 14:27
 */
public class BanUseReflection {
    static {

            // 方式一
//            System.setSecurityManager(new MySecurityManager());
            //方式二  方法已过期 不推荐  且不能用try包含下面的代码，因为那样会报异常但是反射还是成功
            //SecurityManager sm = new SecurityManager();
            //如果是PUBLIC，反射可以执行，DECLARED反射运行时，会报错
            //sm.checkMemberAccess(Prey.class, Member.DECLARED);
            //方式三
//            SecurityManager manager =new SecurityManager();
//            if(manager!=null){
//                manager.checkPermission(new ReflectPermission("suppressAccessChecks"));
//            }
    }

    public static void main(String args[]) {
        Object prey = new Prey();
        try {
            Class<?> aClass = prey.getClass();
            Field pf = aClass.getDeclaredField("privateString");
            pf.setAccessible(true);
            pf.set(prey, "Aminur test");
            System.out.println(pf.get(prey));
            System.out.println("------------");
            Method hello = aClass.getDeclaredMethod("hello", String.class);
            hello.setAccessible(true);
            hello.invoke(prey,"你好");
        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }

    }
}
