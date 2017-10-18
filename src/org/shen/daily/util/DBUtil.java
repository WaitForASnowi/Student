package org.shen.daily.util;



import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class DBUtil {
	private static final String USER="root";
	private static final String PASSWORD="1234";
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/daily";
	private static final int INITIAL_POOL_SIZE=5;
	private static final int MAX_POOL_SIZE=30;
	private static final int MIN_POOL_SIZE=10;
	private static final int MAX_STATEMENT_SIZE=100;
	private static final int TIME_OUT=8000;
	private static ComboPooledDataSource dataSource;
	
	static {
			try {
				dataSource=new ComboPooledDataSource();
				dataSource.setDriverClass(DRIVER);
				dataSource.setJdbcUrl(URL);
				dataSource.setUser(USER);
				dataSource.setPassword(PASSWORD);
				dataSource.setInitialPoolSize(INITIAL_POOL_SIZE);
				dataSource.setMinPoolSize(MIN_POOL_SIZE);
				dataSource.setMaxPoolSize(MAX_POOL_SIZE);
				dataSource.setMaxStatements(MAX_STATEMENT_SIZE);
				dataSource.setCheckoutTimeout(TIME_OUT);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
	}
	
	public static Connection getConnection() throws SQLException{
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw e;
		}
	}
}
