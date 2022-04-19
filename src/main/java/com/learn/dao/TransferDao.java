package com.learn.dao;

import com.learn.pojo.AccountDO;

import java.sql.SQLException;

public interface TransferDao {
    AccountDO queryAccountByCardNo(String fromCardNo);

    int updateAccountByCardNo(AccountDO accountDO) throws SQLException;
}
