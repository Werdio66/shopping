package com._520.webhomework.commodity.util;

import com._520.webhomework.commodity.dao.RowMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// CRUD模板类
public class JdbcTemplete {


    /**
     *      DML
     * @param sql       sql
     * @param obj       添加  修改  删除 的内容
     */
    public static void update(String sql, Object ... obj){
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = JdbcUtil.getConnection();
            ps = con.prepareStatement(sql);
            // 设置参数
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1,obj[i]);
            }
            // 执行sql
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 释放资源
            JdbcUtil.close(con,ps,null);
        }
    }

    /**
     *      DQL
     * @param sql   sql
     * @param obj   查询的id
     * @return      封装Commodity对象的集合
     */

    public static <T>T query(String sql, RowMapper<T> rowMapper, Object ... obj){
        // 获取连接对象
        Connection con = JdbcUtil.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取语句对象
            ps = con.prepareStatement(sql);
            // 设置参数
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            // 执行
            rs = ps.executeQuery();
            return rowMapper.handle(rs);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 释放资源
            JdbcUtil.close(con,ps,rs);
        }
        return null;
    }
}
