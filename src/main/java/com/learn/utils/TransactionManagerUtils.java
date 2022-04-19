package com.learn.utils;

import java.sql.SQLException;

public class TransactionManagerUtils {

    private ConnUtils connUtils;

    public void setConnUtils(ConnUtils connUtils) {
        this.connUtils = connUtils;
    }

    // 开启事务
    public void startTrans() throws SQLException {
        connUtils.getConn().setAutoCommit(false);
    }

    // 提交事务
    public void commitTrans() throws SQLException {
        connUtils.getConn().commit();
    }

    // 回滚事务
    public void rollbackTrans() throws SQLException {
        connUtils.getConn().rollback();
    }

    // 释放连接
    public void release() throws SQLException {
        connUtils.getConn().close();
        connUtils.remove();
    }

}
