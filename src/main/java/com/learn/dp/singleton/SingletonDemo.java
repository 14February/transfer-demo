package com.learn.dp.singleton;

/**
 * 饿汉式
 */
public class SingletonDemo {

    // final修饰的静态成员变量必须在声明的地方或者静态代码块赋初值一次
    // final修饰的成员变量必须在声明的地方或构造方法中赋初值一次
    // final修饰的局部变量在任何地方赋初值一次
    private static final SingletonDemo instance = new SingletonDemo();

    // 构造函数私有
    private SingletonDemo() {}

    public static SingletonDemo getInstance() {
        return instance;
    }

}
