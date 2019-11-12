package com._520.webhomework.commodity.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

// 封装jdbc的连接，释放资源
public class JdbcUtil {

    private static DataSource ds = null;

    static{
        try {
            // 获取资源文件
            Properties properties = new Properties();
            // 加载
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
            // 加载注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 创建DataSource对象
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {

        // 从连接池返回连接对象
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 释放资源
    public static void close(Connection con, PreparedStatement ps, ResultSet rs){
        if (con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if (ps != null){
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally {
                        if (rs != null){
                            try {
                                rs.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}
