package com.qzing.enumtest;
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
public enum ErrorCode {
    OK(0) {
        public String getDescription() {
            return "成功";
        }
    },
    ERROR_A(100) {
        public String getDescription() {
            return "错误A";
        }
    },
    ERROR_B(200) {
        public String getDescription() {
            return "错误B";
        }
    };

    private int code;

    // 构造方法：enum的构造方法只能被声明为private权限或不声明权限
    private ErrorCode(int number) { // 构造方法
        this.code = number;
    }
    public int getCode() { // 普通方法
        return code;
    } // 普通方法
    public abstract String getDescription(); // 抽象方法
    public static void main(String args[]) { // 静态方法
        for (ErrorCode s : ErrorCode.values()) {
            System.out.println("code: " + s.getCode() + ", description: " + s.getDescription());
        }
    }
}