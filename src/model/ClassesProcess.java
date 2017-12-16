package model;

import java.sql.*;
import java.util.ArrayList;

/**
 * 处理教室类数据的类，可以获取，添加，更新教室数据
 * 
 * @author 何广森
 * @version 1.1
 * 
 */
public class ClassesProcess implements Process {
	// private Classes classes;
	private Connection ct = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	/** 插入数据的sql语句 */
	private static final String insertSQL = "insert into class values (?,?,?)";
	/** 插入teacher_class关系表的sql语句 */
	private static final String relationSQL = "insert into teacher_class values(?,?)";
	/** 搜索用户id和密码的sql语句 */
	private static final String searchSQL = "select * from class where class_id=?";
	/** 更新班级的名字和课程的sql语句 */
	private static final String updateSQL = "update class set class_name=?,major_id=? where class_id=?";
	/** 根据三个值搜索出的学生集合sql语句 */
	private static final String msearchSQL = "select student.student_id,student.student_name,major.major_id,major.major_name,class.class_id from student natural join class natural join major where student.class_id=? and major.major_name=? and major.grade_id=? ";

	/** 根据年级搜索出班级和班主任 */
	private static final String gradeSearchSQL = "select class.class_name,teacher.teacher_name from teacher natural join teacher_class natural join class where garde_id=?";
	/** 根据分科搜索出班级年级和班主任 */
	private static final String majorSearchSQL = "select class.class_name,major.grade_id,teacher.name from teacher natural join teacher_class natural join class natural join major where major_nmae=?";
	/** 根据年级和分科搜索出班级和班主任 */
	private static final String gmSearchSQL = "select class.class_name,grade_id,teacher.teacher_name from teacher natural join teacher_class natural join class natural join major where grade_id=? and major_name=? ";

	/*
	 * 以上都是预先定义好的sql语句，？的地方为需要填入的变量 具体使用方法可以参考下面的insertUser函数
	 * 也可以自己百度学习一下有关JDBC的知识
	 */

	public ClassesProcess() {
	}

	/**
	 * 向数据库插入用户
	 * 
	 * @param cid
	 *            教室id
	 * @param class_name
	 *            教室名字
	 * @param mid
	 *            课程名字
	 * @throws SQLException
	 *             SQL异常
	 */

	public int insertClasses(String cid, String class_name, String mid) {
		ct = ConnDB.getConn();// 获取数据库连接
		try {
			ps = ct.prepareStatement(searchSQL);// 验证教室id是否重复
			ps.setString(1, cid);
			rs = ps.executeQuery();
			System.out.println(searchSQL);
			if (rs.next())
				return 1;

			ps = ct.prepareStatement(insertSQL);// 插入教室表
			ps.setString(1, cid);// 向String中？的地方填入数据
			ps.setString(2, class_name);
			ps.setString(3, mid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ct.close();// 关闭数据库，记得每次用完都要关闭
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ct = null;
			ps = null;
			rs = null;
		}
		return 0;
	}

	/**
	 * 更改教室名字和课程id
	 * 
	 * @param cid
	 *            学生id
	 * @param class_name
	 *            新的教室名字
	 * @param mid
	 *            新的课程id
	 * @throws SQLException
	 *             SQL异常
	 */
	public void updateClasses(String cid, String class_name, String mid) {
		ct = ConnDB.getConn();
		try {
			ps = ct.prepareStatement(updateSQL);
			ps.setString(1, class_name);
			ps.setString(2, mid);
			ps.setString(3, cid);
			ps.executeUpdate();
		} catch (SQLException e) {
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
	}

	/**
	 * 查找学生信息
	 * 
	 * @param cid
	 *            学生id
	 * @param major
	 *            专业
	 * @param grade
	 *            年级
	 * @throws SQLException
	 *             SQL异常
	 */
	/*
	 * public ArrayList<Classes> getData(String cid,String major,int grade) {
	 * ct=ConnDB.getConn(); ArrayList<Classes> classes=new ArrayList<Classes>();
	 * try{ ps=ct.prepareStatement(msearchSQL); ps.setString(1, cid);
	 * rs=ps.executeQuery(); for(int i=0;;i++){ Classes cla=new Classes();
	 * 
	 * if(rs.next()) { cla.setClassId(cid); cla.setMajorId(rs.getString(2));
	 * cla.setGrade(rs.getInt(3)); }else break; classes.add(cla); } }catch
	 * (SQLException e){ // TODO Auto-generated catch block e.printStackTrace();
	 * }finally{ try{ ct.close(); }catch (SQLException e){ // TODO
	 * Auto-generated catch block e.printStackTrace(); } ct=null; ps=null; }
	 * return classes; }
	 * 
	 * public Classes getData(int grade) { ct=ConnDB.getConn(); Classes
	 * classes=new Classes(); try{ ps=ct.prepareStatement(gradeSearchSQL);
	 * rs=ps.executeQuery(); if(rs.next()) {
	 * 
	 * classes.setGrade(rs.getInt(1)); }else return null;
	 * 
	 * }catch (SQLException e){ // TODO Auto-generated catch block
	 * e.printStackTrace(); }finally{ try{ ct.close(); }catch (SQLException e){
	 * // TODO Auto-generated catch block e.printStackTrace(); } ct=null;
	 * ps=null; } return classes; }
	 * 
	 * public Classes getData(String major) { ct=ConnDB.getConn(); Classes
	 * classes=new Classes(); try{ ps=ct.prepareStatement(majorSearchSQL);
	 * rs=ps.executeQuery(); if(rs.next()) {
	 * classes.setMajorId(rs.getString(1)); }else return null;
	 * 
	 * }catch (SQLException e){ // TODO Auto-generated catch block
	 * e.printStackTrace(); }finally{ try{ ct.close(); }catch (SQLException e){
	 * // TODO Auto-generated catch block e.printStackTrace(); } ct=null;
	 * ps=null; } return classes; }
	 */
	public ArrayList<Classes> getData(String major, int grade) {
		ArrayList<Classes> classes = new ArrayList<Classes>();
		ct = ConnDB.getConn();

		Classes cla = new Classes();
		try {
			ps = ct.prepareStatement(gmSearchSQL);
			ps.setString(1, major);
			ps.setInt(2, grade);
			rs = ps.executeQuery();

			for (int i = 0;; i++) {
				Classes cl = new Classes();
				if (rs.next()) {
					cl.setClassName(rs.getString(1));
					cl.setGrade(rs.getInt(2));
					cl.setTeachername(rs.getString(3));
				} else
					break;
				classes.add(cl);
			}
		} catch (SQLException e) {
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
		return classes;
	}
	/**
	 * 测试函数
	 * 
	 * @param args
	 *            参数
	 */
	/*
	 * public static void main(String[] args) {//测试 ClassesProcess up=new
	 * ClassesProcess(); try { up.insertClasses("0000102","三年2班","00001"); }
	 * catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 */
	/*
	 * try { up.updateClasses("2222","zhoujile","2222"); } catch (SQLException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 */
}
