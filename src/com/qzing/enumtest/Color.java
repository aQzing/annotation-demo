package com.qzing.enumtest;

enum Color {
    /**
     * 在enum中，提供了一些基本方法：
     *
     * values()：返回 enum 实例的数组，而且该数组中的元素严格保持在 enum 中声明时的顺序。
     *
     * name()：返回实例名。
     *
     * ordinal()：返回实例声明时的次序，从0开始。
     *
     * getDeclaringClass()：返回实例所属的 enum 类型。
     *
     * equals() ：判断是否为同一个对象。
     *
     * 可以使用 == 来比较enum实例。
     *
     * 此外，java.lang.Enum实现了Comparable和 Serializable 接口，所以也提供 compareTo() 方法。
     */
    RED(), GREEN, BLUE;

    public static void main(String[] args) {
        System.out.println(Color.RED);//RED
        System.out.println(Color.RED.name());//RED
        System.out.println(Color.RED.ordinal());//0
        Color[] values = Color.values();
        for (Color c:values){
            System.out.println(c.name());
            //RED
            //GREEN
            //BLUE
        }

    }
}