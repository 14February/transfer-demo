package com.learn.test;


import com.learn.factory.BeanFactory;
import com.learn.factory.ProxyFactory;
import com.learn.service.TransferService;
import com.learn.service.impl.TransferServiceImpl;
import org.junit.Test;

import java.sql.SQLException;

public class TransferTest {

    @Test
    public void testTransfer() throws SQLException {
        TransferServiceImpl transferService = (TransferServiceImpl) BeanFactory.getBean("transferService");
        ProxyFactory proxyFactory = (ProxyFactory) BeanFactory.getBean("proxyFactory");
        TransferService proxy = (TransferService) proxyFactory.getProxy(transferService);
        proxy.transfer("12345", "88888", 1000);
    }

}
