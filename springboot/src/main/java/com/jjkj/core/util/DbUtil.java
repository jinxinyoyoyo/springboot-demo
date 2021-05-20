package com.jjkj.core.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * xxx
 *
 * @author fengchuang
 */
public class DbUtil {


    public static Connection getConnection() {

        try {

            InputStream is = DbUtil.class.getClassLoader().getResourceAsStream("application.properties");

            Properties prop = new Properties();
            //读取属性文件a.properties
            InputStream in = new BufferedInputStream(is);
            prop.load(in);     // 加载属性列表

            String dbDriver = prop.getProperty("spring.datasource.driver-class-name");
            String dbUrl = prop.getProperty("spring.datasource.url");
            String dbUser = prop.getProperty("spring.datasource.username");
            String dbPass = prop.getProperty("spring.datasource.password");

            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            return conn;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        getConnection();
    }



}
