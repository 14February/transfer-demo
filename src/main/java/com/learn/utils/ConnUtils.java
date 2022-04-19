package com.learn.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnUtils {

//    private static ConnUtils connUtils = new ConnUtils();
//
//    public static ConnUtils getInstance() {
//        return connUtils;
//    }

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public Connection getConn() {
        Connection conn = threadLocal.get();
        if (conn == null) {
            try {
                conn = DruidUtils.getConn();
                threadLocal.set(conn);
            } catch (SQLException throwables) {

            }
        }
        return conn;
    }

    public void remove() {
        threadLocal.remove();
    }

}
