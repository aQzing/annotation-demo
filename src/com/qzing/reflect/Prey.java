package com.qzing.reflect;

class Prey {
    private String privateString = "privateValue";
    private String hello(String str){
        System.out.println("hello:"+str);
        return "hi hello";
    }
}