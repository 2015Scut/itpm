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
	 */
	public int insertUser(String uid,String pw,String tid,String name){
		ct=ConnDB.getConn();//获取数据库连接
		try {
			ps=ct.prepareStatement(confirmSQL);
			ps.setString(1, tid);
			rs=ps.executeQuery();
			if(rs.next()) {
				if(!name.equals(rs.getString(2)))
				{
					System.out.println(1);
					return 1;//验证教师身份
					
				}
			}else
				return 2;

			ps=ct.prepareStatement(searchSQL);//验证用户名是否重复
			ps.setString(1, uid);
			rs=ps.executeQuery();
			
			if(rs.next())return 3;
			
			ps=ct.prepareStatement(insertSQL);//插入用户表
			ps.setString(1, uid);//向String中？的地方填入数据
			ps.setString(2, pw);
			ps.setString(3, tid);
			ps.executeUpdate();
			
			ps=ct.prepareStatement(relationSQL);//插入关系表
			ps.setString(1, tid);
			ps.setString(2, uid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ct=null;
			ps=null;
			rs=null;
		}
		return 0;
	}
	/**
	 * 获取用户信息
	 * @param uid 登录用户的id
	 * @return 用户
	 */
	public Users getData(String uid){
		ct=ConnDB.getConn();
		Users user=new Users();
		try {
			ps=ct.prepareStatement(searchSQL);
			ps.setString(1, uid);
			rs=ps.executeQuery();
			if(rs.next()) {
				user.setUserId(uid);
				user.setPassword(rs.getString(2));
				user.setTeacherId(rs.getString(3));
			}else
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ct=null;
			ps=null;
			rs=null;
		}
		return user;
	}
	/**
	 * 更改用户密码
	 * @param uid 用户名
	 * @param pw 新的密码
	 */
	public void updateUsers(String uid,String pw) {
		ct=ConnDB.getConn();
		try {
			ps=ct.prepareStatement(updateSQL);
			ps.setString(2, uid);
			ps.setString(1, pw);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ct=null;
			ps=null;
		}
		
	}
	/**
	 * 测试函数
	 * @param args 参数
	 */
	public static void main(String [] args) {//测试
		UsersProcess up=new UsersProcess();
		Users u=up.getData("admin");
		if(u!=null)
			System.out.println(u.getUserId()+" "+u.getPassword()+" "+u.getTeacherId());
		
		Users u1=up.getData("admin");
		//ConnDB.getConn();
	}
}
