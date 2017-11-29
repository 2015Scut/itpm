package model;

import java.sql.*;
/**
 * 处理用户类数据的类，可以将用户数据录入数据库，验证用户登录，更新用户密码
 * @author 钟恩俊
 * @version 1.0
 * 
 */
public class UsersProcess {
	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	/**插入数据的sql语句*/
	private static final String insertSQL="insert into users values (?,?,?)";
	/**插入teacher_users关系表的sql语句*/
	private static final String relationSQL="insert into teacher_users values(?,?)";
	/**搜索用户id和密码的sql语句*/
	private static final String searchSQL="select * from users where user_id=?";
	/**更新用户密码的sql语句*/
	private static final String updateSQL="update users set pw=? where user_id=?";
	/**搜索教师id的sql语句*/
	private static final String confirmSQL="select * from teacher where teacher_id=?";
	/*
	 * 以上都是预先定义好的sql语句，？的地方为需要填入的变量
	 * 具体使用方法可以参考下面的insertUser函数
	 * 也可以自己百度学习一下有关JDBC的知识
	 * */
	public UsersProcess() {}
	
	/**
	 * 向数据库插入用户
	 * @param uid 用户名
	 * @param pw 密码
	 * @param tid 教师id
	 * @param name 教师名字
	 * @return 是否成功
	 * @throws SQLException SQL异常
	 */
	public boolean insertUser(String uid,String pw,String tid,String name) throws SQLException {
		ct=ConnDB.getConn();//获取数据库连接
		ps=ct.prepareStatement(confirmSQL);
		ps.setString(1, tid);
		rs=ps.executeQuery();
		rs.next();
		if(!name.equals(rs.getString(2)))return false;//验证教师身份
		

		ps=ct.prepareStatement(searchSQL);//验证用户名是否重复
		ps.setString(1, uid);
		rs=ps.executeQuery();
		
		if(rs.next())return false;
		
		ps=ct.prepareStatement(insertSQL);//插入用户表
		ps.setString(1, uid);//向String中？的地方填入数据
		ps.setString(2, pw);
		ps.setString(3, tid);
		ps.executeUpdate();
		
		ps=ct.prepareStatement(relationSQL);//插入关系表
		ps.setString(1, tid);
		ps.setString(2, uid);
		ps.executeUpdate();
		ct.close();//关闭数据库，记得每次用完都要关闭
		ct=null;
		ps=null;
		rs=null;
		return true;
	}
	/**
	 * 验证输入的用户名和密码是否正确
	 * @param uid 登录用户的id
	 * @param pw 登录用户的密码
	 * @return 是否成功登录
	 * @throws SQLException SQL异常
	 */
	public boolean login(String uid,String pw) throws SQLException {
		ct=ConnDB.getConn();
		ps=ct.prepareStatement(searchSQL);
		ps.setString(1, uid);
		rs=ps.executeQuery();
		if(!rs.next())return false;
		String password=rs.getString(2);
		if(!password.equals(pw))return false;
		ct.close();
		ct=null;
		ps=null;
		rs=null;
		return true;
		
	}
	/**
	 * 更改用户密码
	 * @param uid 用户名
	 * @param pw 新的密码
	 * @throws SQLException SQL异常
	 */
	public void updateUsers(String uid,String pw) throws SQLException {
		ct=ConnDB.getConn();
		ps=ct.prepareStatement(updateSQL);
		ps.setString(2, uid);
		ps.setString(1, pw);
		ps.executeUpdate();
		ct.close();
		ct=null;
		ps=null;
	}
	/**
	 * 测试函数
	 * @param args 参数
	 */
	public static void main(String [] args) {//测试
		/*UsersProcess up=new UsersProcess();
		Users u=new Users();
		u.setUserId("admin");
		u.setPassword("itpm");
		u.setTeacherId("03001");
		try {
			up.insertUser("admin","itpm","1","admin");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		ConnDB.getConn();
	}
}
