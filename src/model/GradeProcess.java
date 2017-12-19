
package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * 处理年级类数据的类，可以插入，查询，删除年级信息
 * @author 李龙康
 * @version 1.0
 * 
 */

public class GradeProcess implements Process { 

	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	/**插入数据的sql语句*/
	private static final String insertSQL="insert into grade values (?)";
	/**删除年级数据的sql语句*/
	private static final String deleteSQL="delete from grade where grade_id=?";
	/**搜索年级数据的sql语句*/
	private static final String searchSQL="select * from grade where grade_id=?";
	/**搜索所有年级的sql语句*/
	private static final String msearchSQL="select * from grade";
	public GradeProcess() {}
	
	/**
	 * 向数据库插入年级
	 * @param gid 年级编号
	 * @return 是否成功
	 * @throws SQLException SQL异常
	 */
	public int insertGrade(int gid) {
		
		ct=ConnDB.getConn();//获取数据库连接
		try{
		ps=ct.prepareStatement(searchSQL);//验证年级id是否重复
		ps.setInt(1, gid);
		rs=ps.executeQuery();
		System.out.println(ps);
		if(rs.next())return 0;
		
		ps=ct.prepareStatement(insertSQL);//插入年级表
		ps.setInt(1, gid);
		ps.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ct = null;
			ps = null;
		}
		
		return 1;
	}
	public ArrayList<Grade> getData() {
		ArrayList<Grade> Grade_ = new ArrayList<Grade>();
		ct=ConnDB.getConn();
		try{
			ps=ct.prepareStatement(msearchSQL);
			ps.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ct = null;
			ps = null;
		}
		return Grade_;
	   
	}
	/**
	 * 测试函数
	 * @param args 参数
	 */
	public static void main(String [] args) {//测试
		GradeProcess up=new GradeProcess();
		up.insertGrade(2016);
	}
}
