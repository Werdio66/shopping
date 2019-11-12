package com._520.webhomework.commodity.dao;


import java.sql.ResultSet;

public interface RowMapper<T> {

    T handle(ResultSet rs) throws Exception;
}
