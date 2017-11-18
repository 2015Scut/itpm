package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/** 
 * 处理学生类数据的类，可以获取，添加，更新和删除学生数据 
 * @author 王圣杰 
 * @version 1.0
 *  
 */
public class StudentProcess implements Process {
	
	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	/**插入数据的sql语句*/
	private static final String insertSQL="insert into student values (?,?,?,?,?,?)";
	
	public StudentProcess() {}

	
	
	

}
