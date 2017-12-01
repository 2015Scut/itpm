package model;


import java.sql.*;

public class TeacherProcess  {

	private Teacher t;


	public TeacherProcess() {}
	public  TeacherProcess(Teacher t) {
		this.t=t;
	}
	
	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	
	/**插入教师数据的sql语句*/
	private static final String insertSQL="insert into teacher values (?,?,?,?,?)";
	/**根据id搜索教师的sql语句*/
	private static final String idQuerySQL="select * from teacher where teacher_id=?";
	/**搜索某年级所有教师的sql语句*/
	private static final String gradeQuerySQL="select * from teacher where grade_id=?";
	/**更新教师所属年级的sql语句*/
	private static final String updateSQL="update teacher set grade_id=? where grade_id=?";
	/**删除教师的sql语句*/
	private static final String deleteSQL="delete from teacher where teacher_id=?";
	/**计算教师人数的sql语句*/
	private static final String countSQL="select count(teacher_id) from teacher";
	
	
	/**
	 * 返回搜索结果
	 * @param g 教师所属年级
	 * @throws SQLException SQL异常
	 */
	public ResultSet getTeacherData(int g) throws SQLException{
		ct=ConnDB.getConn();//获取数据库连接
		ps=ct.prepareStatement(gradeQuerySQL);
		ps.setInt(1, g);
		rs=ps.executeQuery();
		ct.close();
		ct=null;
		ps=null;
		rs=null;
		return rs;
	}

	/**(待修改)
	 * 返回数据库中教师表项的人数
	 * @param g 教师所属年级
	 * @throws SQLException SQL异常
	 */
	/*public int getTeacherNum() throws SQLException{
		ct=ConnDB.getConn();//获取数据库连接
		ps=ct.prepareStatement(countSQL);
		rs=ps.executeQuery();
		ct.close();
		ct=null;
		ps=null;
		rs.next();
		return rs.getInt(1);
	}*/
	
	/**
	 * 向数据库插入教师数据
	 * @param tid 教师id
	 * @param n 教师名字
	 * @param sex 学生性别
	 * @param age 教师年龄
	 * @param g  教师所属年级
	 * @throws SQLException SQL异常
	 */
	public boolean insertTeacher(int tid, String n,int age, String sex,  int g) throws SQLException{
		ct=ConnDB.getConn();
		ps=ct.prepareStatement(idQuerySQL);//验证教师id是否重复
		ps.setInt(1, tid);
		rs=ps.executeQuery();
		System.out.println(ps);
		if(rs.next())return false;
	
		ps=ct.prepareStatement(insertSQL);
		ps.setInt(1, tid);
		ps.setString(2, n);
		ps.setInt(3, age);
		ps.setString(4, sex);
		ps.setInt(5, g);
		ps.executeUpdate();
		ct.close();
		ct=null;
		ps=null;
		rs=null;
		return true;
	}
	
	/**
	 * 更新教师数据（所属年级）
	 * @param og 更新前教师所属年级
	 * @param ng 更新后教师所属年级
	 * @throws SQLException SQL异常
	 */
	public void updateTeacher(int og,int ng) throws SQLException {
		ct=ConnDB.getConn();
		ps=ct.prepareStatement(updateSQL);
		ps.setInt(1, ng);
		ps.setInt(2, og);
		ps.executeUpdate();
		ct.close();
		ct=null;
		ps=null;
	}
	
	/**
	 * 从数据库中删除教师数据
	 * @param tid  教师id
	 * @throws SQLException SQL异常
	 */
	public void deleteTeacher(int tid) throws SQLException {
		ct=ConnDB.getConn();
		ps=ct.prepareStatement(deleteSQL);
		ps.setInt(1, tid);
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
		TeacherProcess tp=new TeacherProcess();
		/*try {
			tp.insertTeacher(1001,"八代",28,"男",10);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
		/*try {
		tp.getTeacherData(1988);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}*/
		
		/*try {
		System.out.println(tp.getTeacherNum());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}*/
	
		/*try {
			tp.updateTeacher(10, 2016);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
		/*try {
			tp.deleteTeacher(1001);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}



	


