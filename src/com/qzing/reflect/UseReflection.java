package com.qzing.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class UseReflection {
    public static void main(String args[]) {
        Object prey = new Prey();
        try {
            Field pf = prey.getClass().getDeclaredField("privateString");
            pf.setAccessible(true);
            pf.set(prey, "Aminur test");
            System.out.println(pf.get(prey));
        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }
 
    }
}
 
