package com.learn.dp.singleton;

/**
 * 懒汉式（双重检测）
 * 这种实现方式在 Java 1.4 及更早的版本中有些问题，就是指令重排序，可能会导致 SingletonDemo3 对象被 new 出来，并且赋值给 instance 之后，
 * 还没来得及初始化，就被另一个线程使用了,要解决这个问题，需要给 instance 成员变量加上 volatile 关键字，从而禁止指令重排序,
 * 而高版本的 Java 已在 JDK 内部解决了这个问题，所以高版本的 Java 不需要关注这个问题
 */
public class SingletonDemo3 {

    private volatile static SingletonDemo3 instance;

    private SingletonDemo3() {}

    public static SingletonDemo3 getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo3.class) {
                if (instance == null) {
                    instance = new SingletonDemo3();
                }
            }
        }
        return instance;
    }

}
