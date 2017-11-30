package model;


import java.sql.*;
/** 
 * 处理教室类数据的类，可以获取，添加，更新教室数据 
 * @author 何广森
 * @version 1.0
 *  
 */
public class ClassesProcess implements Process {
	//private Classes classes;
	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	
	/**插入数据的sql语句*/
	private static final String insertSQL="insert into class values (?,?,?)";
	/**插入teacher_class关系表的sql语句*/
	private static final String relationSQL="insert into teacher_class values(?,?)";
	/**搜索用户id和密码的sql语句*/
	private static final String searchSQL="select * from class where class_id=?";
	/**更新班级的名字和课程的sql语句*/
	private static final String updateSQL="update class set class_name=?,major_id=? where class_id=?";
	/**根据三个值搜索出的学生集合sql语句*/
	private static final String msearchSQL="select student.student_id,student.student_name,major.major_id,major.major_name,class.class_id from student natural join class natural join major where student.class_id=? and major.major_name=? and major.grade_id=? ";
	/*
	 * 以上都是预先定义好的sql语句，？的地方为需要填入的变量
	 * 具体使用方法可以参考下面的insertUser函数
	 * 也可以自己百度学习一下有关JDBC的知识
	 * */
	
	public ClassesProcess() {}

	/**
	 * 向数据库插入用户
	 * @param cid 教室id
	 * @param class_name 教室名字
	 * @param mid 课程名字
	 * @throws SQLException SQL异常
	 */

	public boolean insertClasses(String cid,String class_name,String mid) throws SQLException {
		ct=ConnDB.getConn();//获取数据库连接
		ps=ct.prepareStatement(searchSQL);//验证教室id是否重复
		ps.setString(1, cid);
		rs=ps.executeQuery();
		if(rs.next())return false;
		
		ps=ct.prepareStatement(insertSQL);//插入教室表
		ps.setString(1, cid);//向String中？的地方填入数据
		ps.setString(2, class_name);
		ps.setString(3, mid);
		ps.executeUpdate();
		
		ct.close();//关闭数据库，记得每次用完都要关闭
		ct=null;
		ps=null;
		rs=null;
		return true;
	}
	
	/**
	 * 更改教室名字和课程id
	 * @param cid 学生id
	 * @param class_name 新的教室名字
	 * @param mid 新的课程id
	 * @throws SQLException SQL异常
	 */
	public void updateClasses(String cid,String class_name,String mid) throws SQLException {
		ct=ConnDB.getConn();
		ps=ct.prepareStatement(updateSQL);
		ps.setString(1, class_name);
		ps.setString(2, mid);
		ps.setString(3, cid);
		ps.executeUpdate();
		ct.close();
		ct=null;
		ps=null;
	}
	
	/**
	 * 查找学生信息
	 * @param cid 学生id
	 * @param major 专业
	 * @param grade 年级
	 * @throws SQLException SQL异常
	 */
	public ResultSet getData(String cid,String major,int grade) throws SQLException
	{
		ct=ConnDB.getConn();
		ps=ct.prepareStatement(msearchSQL);
		ps.setString(1, cid);
		ps.setString(2, major);
		ps.setInt(3, grade);
		
		ps.executeQuery();
		ct.close();
		ct=null;
		ps=null;
		return rs;
	}
	
	/**
	 * 测试函数
	 * @param args 参数
	 */
	public static void main(String[] args) {//测试
		ClassesProcess up=new ClassesProcess();
		try {
			up.insertClasses("000010101","三年2班","0000101");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*try {
			up.updateClasses("2222","zhoujile","2222");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
}
	
}
