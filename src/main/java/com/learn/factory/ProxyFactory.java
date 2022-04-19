package com.learn.factory;

import com.learn.service.impl.TransferServiceImpl;
import com.learn.utils.TransactionManagerUtils;
import org.omg.IOP.TransactionService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    private TransactionManagerUtils transactionManagerUtils;

    public void setTransactionManagerUtils(TransactionManagerUtils transactionManagerUtils) {
        this.transactionManagerUtils = transactionManagerUtils;
    }

    public Object getProxy(Object obj) {
        return Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    Object res;
                    try {
                        transactionManagerUtils.startTrans();
                        res = method.invoke(obj, args);
                        transactionManagerUtils.commitTrans();
                    } catch (Exception e) {
                        transactionManagerUtils.rollbackTrans();
                        throw new Exception(e.getMessage());
                    } finally {
                        transactionManagerUtils.release();
                    }
                    return res;
                });
    }

}
