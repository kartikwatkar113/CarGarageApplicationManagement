package cg_app.config;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DBConfig {

	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	private static DBConfig db=null;
	private static Properties p=new Properties();;
	
	public DBConfig() {
		try {
			p.load(PathHelper.fin);
			String driverClassName=p.getProperty("driver.classname");
			String e_userName=p.getProperty("db.username");
			String e_password=p.getProperty("db.password");
			String url=p.getProperty("db.url");
			Class.forName(driverClassName);
			
			
			conn=DriverManager.getConnection(url,EncryptDecrypt.decrypt(e_userName),EncryptDecrypt.decrypt(e_password));
			
			
			if(conn!=null) {
				System.out.println("**********************");
			}
			else {
				System.out.println("Database Not Connected... :(");
			}
		}catch(Exception ex) {
			System.out.println("Error is:- "+ex);
		}
	}
	
	public static DBConfig getDBInstance() {
		if(db==null) {
			db=new DBConfig();
		}
		return db;
	}
	public static Connection getConnection() {
		return conn;
	}
	public static PreparedStatement getPreparedStatement() {
		return pstmt;
	}
	public static ResultSet getResultSet() {
		return rs;
	}
	public static Properties getProperties() {
		return p;
	}

}
