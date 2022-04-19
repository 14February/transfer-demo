package com.learn.service;

import java.sql.SQLException;

public interface TransferService {
    void transfer(String fromCardNo, String toCardNo, int money) throws SQLException;
}
