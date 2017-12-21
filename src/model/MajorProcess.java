package model;


import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * 处理分科类数据的类，可以获取和插入分科信息
 * @author 邢浩
 * @version 1.1
 * 
 */

public class MajorProcess implements Process{

	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	/**插入数据的sql语句*/
	private static final String insertSQL="insert into major values (?,?,?)";
	/**根据一个值搜索出的分科集合sql语句*/
	private static final String searchSQL="select *from major where grade_id=?";
	public MajorProcess() {}
	/**
	 * 向数据库插入用户
	 * @param major_id 
	 *                   分科的id
	 * @param major_name 
	 *                   分科的名字
	 * @param grade_id 
	 *                   年级id
	 * @return 
	 *                   是否成功
	 * @throws SQLException
	 *                   SQL异常
	 *                   
	 */
	public int insertmajor(String major_id,String major_name,int grade_id) {
		ct=ConnDB.getConn();//获取数据库连接
		try{
			ps=ct.prepareStatement(searchSQL);//验证majorid是否重复
			ps.setString(1, major_id);
			rs=ps.executeQuery();
			if(rs.next())return 1;
			
			ps=ct.prepareStatement(insertSQL);//插入学生表
			ps.setString(1, major_id);//向String中？的地方填入数据
			ps.setString(2,major_name);
			ps.setInt(3, grade_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				ct.close();// 关闭数据库，记得每次用完都要关闭
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
	 * 根据年级获取分科列表
	 * 
	 * @param grade
	 *            年级
	 * @throws SQLException
	 *             SQL异常
	 */
	public ArrayList<Major> getData(int grade_id){
		ArrayList<Major> major=new ArrayList<Major>();
		ct=ConnDB.getConn();
		try{
			ps=ct.prepareStatement(searchSQL);
			ps.setInt(1,grade_id);
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
			ct=null;
			ps=null;
		}
		return major;
	}
	/**
	 * 测试函数
	 * @param args 参数
	 */
     public static void main(String [] args) throws SQLException{
    	MajorProcess up=new MajorProcess();
    	up.insertmajor("000001", "理科", 2017);
     }
}
 
