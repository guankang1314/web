package com.bookstore.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
/**
 * 获取连接和释放连接的工具类
 * @author Chunsheng Zhang
 *
 */
public class JDBCUtils {
	private static DruidDataSource dataSource;
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

	static {
		try {
			//1、读取druip.properties文件
			Properties properties = new Properties();
			InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");

			properties.load(inputStream);
			//2、连接连接池
			dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
			System.out.println(dataSource.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	//获取连接
	public static Connection getConnection() {
		Connection connection = threadLocal.get();
		try {
			if (connection==null) {
				connection = dataSource.getConnection();
				threadLocal.set(connection);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	//释放连接
	public static void close() {
		Connection connection = threadLocal.get();
		if(connection != null) {
			try {
				connection.close();
				threadLocal.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
