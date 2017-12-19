package model;

import java.sql.*;
import java.util.ArrayList;

/**
 * 处理教师数据的类，可以将教师数据录入数据库，按id或年级查询教师，删除教师数据
 * @author 吕睿
 * @version 1.0
 * 
 */
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
	 * 返回某年级所有教师资料的搜索结果
	 * @param g 教师所属年级
	 * @throws SQLException SQL异常
	 */
	public ArrayList<Teacher> getTeacherData(Integer g) {
		ct=ConnDB.getConn();//获取数据库连接
		ArrayList<Teacher> tList = new ArrayList<Teacher>();
		try {
			ps=ct.prepareStatement(gradeQuerySQL);
			if (g == null) ps.setString(1, "grade_id");
			else ps.setInt(1, g);
			rs=ps.executeQuery();
			if(rs.next()) {
				rs.last();
				int n = rs.getRow();
				rs.first();
				for(int i = 1; i<n; i++){
					t.setTeacherId(rs.getInt("teacher_id"));
					t.setName(rs.getString("teacher_name"));
					t.setAge(rs.getInt("teacher_age"));
					t.setSex(rs.getString("teacher_sex"));
					t.setGradeId(rs.getInt("grade_id"));
					tList.add(t);
					rs.next();
				}
			}
			else return null;
		}catch (SQLException e) {
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
		return tList;
	}

	/**
	 * 返回某年级所有教师的名字的搜索结果
	 * @param g 教师所属年级
	 * @throws SQLException SQL异常
	 */
	public ArrayList<String> getTeacherName(int g) {
		
		ArrayList<Teacher> tList =  getTeacherData(g);
		if(tList != null){
			ArrayList<String> nList =  new ArrayList<String>();
			for(int i = 0; i < tList.size(); i++){
				String t = tList.get(i).getName();
				nList.add(t);
			}

			return nList;
		}
		else return null;
	}
	
	/**
	 * 返回可以选用以录入的下一个教师ID,必须是数据库中没有使用到的。(实现中返回的是数据库中最大Id+1)
	 * @param g 教师所属年级
	 * @throws SQLException SQL异常
	 */
public String nextId() {
		
		ArrayList<Teacher> tList =  getTeacherData(null);
		if(tList != null){
			int nId = 0;
			for(int i = 0; i < tList.size(); i++){
				int t = tList.get(i).getTeacherId();
				if (t > nId) nId = t;
			}

			nId++;
			return "" + nId;
		}
		else return "1";
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
	public int insertTeacher(int tid, String n,int age, String sex,  int g) throws SQLException{
		
		ct=ConnDB.getConn();
		try{
			ps=ct.prepareStatement(idQuerySQL);//验证教师id是否重复
			ps.setInt(1, tid);
			rs=ps.executeQuery();
			if(rs.next())return 1;
		
			ps=ct.prepareStatement(insertSQL);
			ps.setInt(1, tid);
			ps.setString(2, n);
			ps.setInt(3, age);
			ps.setString(4, sex);
			ps.setInt(5, g);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try{
				ct.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			ct=null;
			ps=null;
			rs=null;
		}
		return 0;
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



	


