package com.learn.dp.singleton;

/**
 * 懒汉式
 */
public class SingletonDemo2 {

    private static SingletonDemo2 instance;

    private SingletonDemo2() {}

    // 懒加载（加锁实现，影响性能）
    public synchronized static SingletonDemo2 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo2();
        }
        return instance;
    }

}
