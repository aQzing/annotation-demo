package com.qzing.reflect;

import java.security.Permission;

public class MySecurityManager extends SecurityManager {
    @Override
    public void checkPermission(Permission perm) {
        if (perm.getName().equals("suppressAccessChecks")) {
            // 一定要抛出异常不让反射还是会成功
            throw new SecurityException("Can not change the permission dude.!");
        }
    }
}