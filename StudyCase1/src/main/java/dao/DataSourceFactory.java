package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceFactory {

	private final DataSource datasource;
	
	DataSourceFactory()
	{
		MysqlDataSource datasource=new MysqlDataSource();
		datasource.setDatabaseName("latihan");
		datasource.setServerName("127.0.0.1");
		datasource.setPort(3306);
		datasource.setUser("root");
		datasource.setPassword("root");
		this.datasource=datasource;
	}
	
	public static Connection getConnection() throws SQLException
	{
		return SingletonHelper.INSTANCE.datasource.getConnection();
	}
	
	
	private static class SingletonHelper
	{
		private static final DataSourceFactory INSTANCE = new DataSourceFactory();
	}
	
	
}
