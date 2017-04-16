package com.hp.webedu.util;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JDBCUtils {
	
	@Autowired
	DataSource ds;
	/**
	 * 通过jdbc执行一些sql
	 * @param sql 执行的语句
	 * @return 是否执行成功
	 * @throws SQLException
	 */
	public boolean executeSql(String sql) throws SQLException{
		return ds.getConnection().prepareStatement(sql).execute();
	}
}
