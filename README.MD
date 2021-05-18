# 注解
## 什么是注解
1. 注解是一种元数据形式。即注解是属于java的一种数据类型，和类、接口、数组、枚举类似。
2. 注解用来修饰，类、方法、变量、参数、包。
3. 注解不会对所修饰的代码产生直接的影响。
## 自定义注解的步骤
- 第一步，定义注解——相当于定义标记；
- 第二步，配置注解——把标记打在需要用到的程序代码中；
- 第三步，解析注解——在编译期或运行时检测到标记，并进行特殊操作。
## 语法
> 格式 
```java
public @interface 注解名字 {
    // 里边只能包含注解类型元素
    public String name();
    int age() default 18;
    int[] array();
}

```
> 注解类元素
- 访问修饰符必须为public，不写默认为public；
- 该元素的类型只能是基本数据类型、String、Class、枚举类型、注解类型（体现了注解的嵌套效果）以及上述类型的一位数组；
- 该元素的名称一般定义为名词，如果注解中只有一个元素，请把名字起为value（后面使用会带来便利操作）；
- ()不是定义方法参数的地方，也不能在括号中定义任何参数，仅仅只是一个特殊的语法；
- default代表默认值，值必须和第2点定义的类型一致；
- 如果没有默认值，代表后续使用注解时必须给该类型元素赋值。

## 元注解：用来修饰注解的注解
> 常用元注解
###  @Target 
> 是专门用来限定某个自定义注解能够被应用在哪些Java元素上面的。它使用一个枚举类型定义如下java.lang.annotation.ElementType
```java
public enum ElementType {
    /** Class, interface (including annotation type), or enum declaration */
    TYPE,

    /** Field declaration (includes enum constants) */
    FIELD,

    /** Method declaration */
    METHOD,

    /** Formal parameter declaration */
    PARAMETER,

    /** Constructor declaration */
    CONSTRUCTOR,

    /** Local variable declaration */
    LOCAL_VARIABLE,

    /** Annotation type declaration */
    ANNOTATION_TYPE,

    /** Package declaration */
    PACKAGE,

    /**
     * Type parameter declaration
     *
     * @since 1.8
     */
    TYPE_PARAMETER,

    /**
     * Use of a type
     *
     * @since 1.8
     */
    TYPE_USE
}

```
### @Retention
> 翻译为持久力、保持力。即用来修饰自定义注解的生命力。
  注解的生命周期有三个阶段：1、Java源文件阶段；2、编译到class文件阶段；3、运行期阶段。同样使用了RetentionPolicy枚举类型定义了三个阶段：
```java
public enum RetentionPolicy {
    /**
     * Annotations are to be discarded by the compiler.
     */
//它将被限定在Java源文件中，那么这个注解即不会参与编译也不会在运行期起任何作用，这个注解就和一个注释是一样的效果，只能被阅读Java文件的人看到.默认情况
    SOURCE,

    /**
     * Annotations are to be recorded in the class file by the compiler
     * but need not be retained by the VM at run time.  This is the default
     * behavior.
     */
//它将被编译到Class文件中，那么编译器可以在编译时根据注解做一些处理动作，但是运行时JVM（Java虚拟机）会忽略它，我们在运行期也不能读取到
    CLASS,

    /**
     * Annotations are to be recorded in the class file by the compiler and
     * retained by the VM at run time, so they may be read reflectively.
     *
     * @see java.lang.reflect.AnnotatedElement
     */
//可以在运行期的加载阶段被加载到Class对象中。那么在程序运行阶段，我们可以通过反射得到这个注解，并通过判断是否有这个注解或这个注解中属性的值，从而执行不同的程序代码段。我们实际开发中的自定义注解几乎都是使用的RetentionPolicy.RUNTIME
    RUNTIME
}
```
### @Documented
> 来指定自定义注解是否能随着被定义的java文件生成到JavaDoc文档当中
### @Inherited
> 是指定某个自定义注解如果写在了父类的声明部分，那么子类的声明部分也能自动拥有该注解。@Inherited注解只对那些@Target被定义为ElementType.TYPE的自定义注解起作用
## 使用
自定义注解
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
@Documented
//@MyAnnotation被限定只能使用在类、接口或方法上面
public @interface MyAnnotation {
    // 里边只能包含注解类型元素
    public String name();
    int age() default 18;
    //int[] array();
}

```
被标注注解的类
```java
//@MyAnnotation(name="zhangsan") 注解不能在类上使用 因为注解定义的时候指定了只能在方法上使用@Target(value = {ElementType.METHOD})
public class Student{
    @MyAnnotation(name="zhangsan")
    public void study(int times){
        for(int i = 0; i < times; i++){
            System.out.println("Good Good Study, Day Day Up!");
        }
    }
}

```
### 特殊语法
+ 如果注解本身没有注解类型元素，那么在使用注解的时候可以省略()，直接写为：@注解名，它和标准语法@注解名()等效！
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Documented
public @interface FirstAnnotation {
}

//等效于@FirstAnnotation()
@FirstAnnotation
public class JavaBean{
	//省略实现部分
}

```
+ 如果注解本本身只有一个注解类型元素，而且命名为value，那么在使用注解的时候可以直接使用：@注解名(注解值)，其等效于：@注解名(value = 注解值)
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Documented
public @interface SecondAnnotation {
	String value();
}
//等效于@ SecondAnnotation(value = "this is second annotation")
@SecondAnnotation("this is annotation")
public class JavaBean{
	//省略实现部分
}

```
+ 如果注解中的某个注解类型元素是一个数组类型，在使用时又出现只需要填入一个值的情况，那么在使用注解时可以直接写为：@注解名(类型名 = 类型值)，它和标准写法：@注解名(类型名 = {类型值})等效！
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Documented
public @interface ThirdAnnotation {
	String[] name();
}
//等效于@ ThirdAnnotation(name = {"this is third annotation"})
@ ThirdAnnotation(name = "this is third annotation")
public class JavaBean{
	//省略实现部分
}
```
+ 如果一个注解的@Target是定义为Element.PACKAGE，那么这个注解是配置在package-info.java中的，而不能直接在某个类的package代码上面配置

## 反射操作获取注解
```JAVA
public class TestAnnotation {
    public static void main(String[] args){
        try {
            //获取Student的Class对象
            Class stuClass = Class.forName("pojos.Student");

            //说明一下，这里形参不能写成Integer.class，应写为int.class
            Method stuMethod = stuClass.getMethod("study",int.class);

            if(stuMethod.isAnnotationPresent(MyAnnotation.class)){
                System.out.println("Student类上配置了CherryAnnotation注解！");
                //获取该元素上指定类型的注解
                MyAnnotation cherryAnnotation = stuMethod.getAnnotation(MyAnnotation.class);
                System.out.println("name: " + cherryAnnotation.name() + ", age: " + cherryAnnotation.age());
            }else{
                System.out.println("Student类上没有配置CherryAnnotation注解！");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
```
- 如果我们要获得的注解是配置在方法上的，那么我们要从Method对象上获取；如果是配置在属性上，就需要从该属性对应的Field对象上去获取，如果是配置在类型上，需要从Class对象上去获取。总之在谁身上，就从谁身上去获取！
- isAnnotationPresent(Class<? extends Annotation> annotationClass)方法是专门判断该元素上是否配置有某个指定的注解；
-  getAnnotation(Class<A> annotationClass)方法是获取该元素上指定的注解。之后再调用该注解的注解类型元素方法就可以获得配置时的值数据；
- 反射对象上还有一个方法getAnnotations()，该方法可以获得该对象身上配置的所有的注解。它会返回给我们一个注解数组，需要注意的是该数组的类型是Annotation类型，这个Annotation是一个来自于java.lang.annotation包的接口。
# 反射
> 参考 https://www.jianshu.com/p/9be58ee20dee
##定义
> JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意方法和属性；这种动态获取信息以及动态调用对象方法的功能称为java语言的反射机制
### 获取字节码class对象的三种方式
1. 静态类直接 .class
2. 该对象的.getClass()方法
3. Class.forName("类的全路径名")
### 属性
#### 获取私有属性
Field getDeclaredField(String name)
#### 改变私有属性的方法
public void setAccessible(boolean flag) throws SecurityException
pf.setAccessible(true);// pf为Field对象
#### 获取属性的值
public Object get(Object obj)
#### 设置属性的值（私有属性改为共有属性）
public void set(Object obj, Object value)
### 方法
#### 改变方法的可见性
public void setAccessible(boolean flag) throws SecurityException
#### 方法的获取
public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
#### 方法的调用
public Object invoke(Object obj, Object... args)
### 注解
#### 判断是否指定类型的注解
public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass)
### 反射的预防
```
public class BanUseReflection {
    static {

            // 方式一
            System.setSecurityManager(new MySecurityManager());
            //方式二  方法已过期 不推荐  且不能用try包含下面的代码，因为那样会报异常但是反射还是成功
            //SecurityManager sm = new SecurityManager();
            //如果是PUBLIC，反射可以执行，DECLARED反射运行时，会报错
            //sm.checkMemberAccess(Prey.class, Member.DECLARED);
            //方式三
            SecurityManager manager =new SecurityManager();
            if(manager!=null){
                manager.checkPermission(new ReflectPermission("suppressAccessChecks"));
            }
    }

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

public class MySecurityManager extends SecurityManager {
    @Override
    public void checkPermission(Permission perm) {
        if (perm.getName().equals("suppressAccessChecks")) {
            // 一定要抛出异常不让反射还是会成功
            throw new SecurityException("Can not change the permission dude.!");
        }
    }
}

class Prey {
    private String privateString = "privateValue";
}
```
### 防止反射的预防
看了oracle-jdk源码后有修改源码让反射可以进行但是并没有成功原因：http://www.blogjava.net/jiangshachina/archive/2015/11/01/428010.html
所以想修改可以从open-jdk源码着手        