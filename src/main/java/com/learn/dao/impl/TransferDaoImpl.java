package com.learn.dao.impl;

import com.learn.dao.TransferDao;
import com.learn.pojo.AccountDO;
import com.learn.utils.ConnUtils;
import com.learn.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferDaoImpl implements TransferDao {

    private ConnUtils connUtils;

    public void setConnUtils(ConnUtils connUtils) {
        this.connUtils = connUtils;
    }

    @Override
    public AccountDO queryAccountByCardNo(String fromCardNo) {
        Connection conn = null;
        try {
//            conn = DruidUtils.getConn();
            conn = connUtils.getConn();
            String sql = "SELECT `name`, `card_no`, `balance` FROM `account` WHERE `card_no` = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fromCardNo);
            ResultSet rs = ps.executeQuery();
            AccountDO accountDO = new AccountDO();
            while (rs.next()) {
                accountDO.setCardNo(rs.getString("card_no"));
                accountDO.setName(rs.getString("name"));
                accountDO.setBalance(rs.getLong("balance"));
            }
            return accountDO;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public int updateAccountByCardNo(AccountDO accountDO) throws SQLException {
        Connection conn = null;
        try {
//            conn = DruidUtils.getConn();
            conn = connUtils.getConn();
            Long balance = accountDO.getBalance();
            String cardNo = accountDO.getCardNo();
            String sql = "UPDATE `account` SET `balance` = ? WHERE `card_no` = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, balance.intValue());
            ps.setString(2, cardNo);
            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throw throwables;
        }
    }
}
