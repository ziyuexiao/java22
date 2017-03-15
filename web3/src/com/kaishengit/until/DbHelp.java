package com.kaishengit.until;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.catalina.valves.CometConnectionManagerValve;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.kaishengit.exception.DataAccessException;

public class DbHelp {

	/*
	 * 获取数据库连接
	 */
	private static /*final*/ String driver /*= "com.mysql.jdbc.Driver"*/;
	private static /*final*/ String url /*= "jdbc:mysql://localhost:3306/lib_22"*/;
	private static /*final*/ String username /*= "root"*/;
	private static /*final*/ String password /*= "root"*/;
	private static BasicDataSource dataSource = new BasicDataSource();
	
	
	static{
		//加载并读取config.properties文件
		Properties prop = new Properties();
		try {
			prop.load(DbHelp.class.getClassLoader().getResourceAsStream("config.properties"));
			driver = prop.getProperty("jdbc.driver");
			url = prop.getProperty("jdbc.url");
			username = prop.getProperty("jdbc.username");
			password = prop.getProperty("jdbc.password");
		} catch (IOException e) {
			throw new DataAccessException("读取config.properties文件异常",e);
		}
		
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		dataSource.setInitialSize(5);//初始容量
		dataSource.setMinIdle(5);//高峰过后容量
		dataSource.setMaxIdle(10);
		dataSource.setMaxWaitMillis(5000);
	}
	
	/**
	 * 获取数据库连接池
	 * @return
	 */
	public static DataSource getDataSource(){
		return dataSource;
		
	}
	
	public static Connection getConnection(){
			
			
			Connection connection = null;
			try {
				/*Class.forName(driver);
				connection = DriverManager.getConnection(url,username,password);*/
				
				connection = dataSource.getConnection();
			} catch (Exception e) {
				
				//e.printStackTrace();
				throw new DataAccessException("获取数据库连接异常",e);
			}
			return connection;	
	}
		
	/*
	 * 执行insert，update，delete
	 */
	
	public static void update(String sql,Object...params) throws DataAccessException{
		/*Connection connection = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		
		try {
			queryRunner.update(connection, sql, params);
			System.out.println("SQL:"+sql);
		} catch (SQLException e) {
			
			//e.printStackTrace();
			throw new DataAccessException("执行"+ sql + "异常",e);
		} finally{
			try {
				DbUtils.close(connection);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}*/
			
		
		try {
			QueryRunner queryRunner = new QueryRunner(getDataSource());
			queryRunner.update( sql, params);
		} catch (SQLException e) {
			throw new DataAccessException("执行"+ sql + "异常",e);
		}
		
	}
	
	
	/*
	 * 执行select
	 */
	
	public static<T> T query(String sql,ResultSetHandler<T> handler,Object... params) throws DataAccessException{
		/*Connection connection = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		
		try {
			return queryRunner.query(connection, sql, handler, params);
			//System.out.println("SQL:"+sql);
		} catch (SQLException e) {
		
			//e.printStackTrace();
			throw new DataAccessException("执行"+ sql + "异常",e);//将强制捕获型异常转换成运行时异常
		}
		*/
		
		QueryRunner queryRunner = new QueryRunner(getDataSource());
		try {
			T t = queryRunner.query(sql, handler, params);
			return t;
		} catch (SQLException e) {
			throw new DataAccessException("执行"+ sql + "异常",e);
		}
	}
	
	
	private static void close(Connection connection) {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DataAccessException("关闭Connection异常",e);
			}
		}
		
	}
	
	
	
}
