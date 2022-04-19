package com.learn.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DruidUtils {

    private static final DruidDataSource dataSource = new DruidDataSource();

    static {
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("yuantu123");
    }

    public static Connection getConn() throws SQLException {
        return dataSource.getConnection();
    }

}
