package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import view.test;
/**
 * 
 * @author 钟恩俊
 *
 */
public class ConnDB {
	/**Mysql JDBC 驱动*/
	private static final String driver = "com.mysql.jdbc.Driver";  
	/**数据库url*/
	private static final String url = "jdbc:mysql://110.64.88.82:3306/itpm?useUnicode=true&characterEncoding=utf-8&useSSL=false"; 
	 /**数据库用户名*/
	private static final String user = "root";
	 /**数据库密码*/
	private static final String password = "1234";
	private static Connection ct=null;
	
	private ConnDB() {}
	public static void close() {
		try {
			ct.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			test.show("数据库连接错误");
		}
		ct=null;
	}
	/**
	 * 获取数据库的连接
	 * @return 数据库连接
	 */
	public static Connection getConn() {
		try{
        	Class.forName("com.mysql.jdbc.Driver");
        	ct = DriverManager.getConnection(url, user, password);  
		}catch(Exception e){
			test.show("数据库连接错误");
		} 
		return ct;
	}
}
