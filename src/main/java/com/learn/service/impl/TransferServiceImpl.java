package com.learn.service.impl;

import com.learn.dao.TransferDao;
import com.learn.dao.impl.TransferDaoImpl;
import com.learn.factory.BeanFactory;
import com.learn.pojo.AccountDO;
import com.learn.service.TransferService;
import com.learn.utils.TransactionManagerUtils;

import java.sql.SQLException;

public class TransferServiceImpl implements TransferService {

//    private TransferDao transferDao = new TransferDaoImpl();

    private TransferDao transferDao;

    public void setTransferDao(TransferDao transferDao) {this.transferDao = transferDao;}

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws SQLException {
//        TransactionManagerUtils transactionManagerUtils = (TransactionManagerUtils) BeanFactory.getBean("transactionManagerUtils");
//        try {
//            transactionManagerUtils.startTrans();
//            AccountDO fromAccount = transferDao.queryAccountByCardNo(fromCardNo);
//            AccountDO toAccount = transferDao.queryAccountByCardNo(toCardNo);
//            fromAccount.setBalance(fromAccount.getBalance() - Long.valueOf(money));
//            toAccount.setBalance(toAccount.getBalance() + Long.valueOf(money));
//            int count = transferDao.updateAccountByCardNo(fromAccount);
//            int num = 10 / 0;
//            int i = transferDao.updateAccountByCardNo(toAccount);
//            transactionManagerUtils.commitTrans();
//        } catch (Exception e) {
//            try {
//                transactionManagerUtils.rollbackTrans();
//            } catch (SQLException se) {
//                e.printStackTrace();
//            }
//        }
        AccountDO fromAccount = transferDao.queryAccountByCardNo(fromCardNo);
        AccountDO toAccount = transferDao.queryAccountByCardNo(toCardNo);
        fromAccount.setBalance(fromAccount.getBalance() - Long.valueOf(money));
        toAccount.setBalance(toAccount.getBalance() + Long.valueOf(money));
        int count = transferDao.updateAccountByCardNo(fromAccount);
//        int num = 10 / 0;
        int i = transferDao.updateAccountByCardNo(toAccount);
    }
}
