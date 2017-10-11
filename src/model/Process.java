package model;

import java.sql.*;
/**
 * 数据处理接口
 * @author 钟恩俊
 *
 */
public interface Process {
	/**
	 * 搜索数据模型
	 * @param sql 搜索的sql语句
	 * @return 搜索结果集合
	 */
	ResultSet getData(String sql);
	/**
	 * 插入数据
	 * @param sql 插入数据的sql语句
	 */
	void insertData(String sql);
}
