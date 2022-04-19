package com.learn.dp.singleton;

/**
 * 懒汉式（静态内部类实现）
 * SingletonInner 是一个静态内部类，当外部类被加载的时候，并不会创建 SingletonInner 实例对象
 */
public class SingletonDemo4 {

    private SingletonDemo4() {}

    private static class SingletonInner {
        private static final SingletonDemo4 instance = new SingletonDemo4();
    }

    public static SingletonDemo4 getInstance() {
        return SingletonInner.instance;
    }

}
