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

	/** 根据年级和分科搜索出班级和班主任 */
	private static final String gmSearchSQL = "select class_id,class_name,teacher_name,grade_id,major_id from teacher natural join teacher_class natural join class natural join major where (?='' or grade_id=?) and (?='' or major_name=?) ";

	/** 查询指定年级与专业的班级总数的sql语句 */
	private static final String numSQL = "select count(class_id) from class natural join major where grade_id=? and major_name=?";
	/** 查询专业名对应的专业id */
	private static final String rmidSQL = "select major_id from major where major_name=?";
	/** 查询同一个班级id的学生人数 */
	private static final String snumSQL = "select count(student_id) from student where class_id=?";
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
	 * 根据年级专业获取班级列表
	 * 
	 * @param major
	 *            专业
	 * @param grade
	 *            年级
	 * @throws SQLException
	 *             SQL异常
	 */

	public ArrayList<Classes> getData(String major, Integer grade) {
		ArrayList<Classes> classes = new ArrayList<Classes>();
		ct = ConnDB.getConn();
		if(major==null)major="";
		try {
			ps = ct.prepareStatement(gmSearchSQL);
			if(grade==null) {
				ps.setString(1, "");
				ps.setString(2, "");
			}else {
				ps.setInt(1, grade);
				ps.setInt(2, grade);
			}
			ps.setString(3, major);
			ps.setString(4, major);
			rs = ps.executeQuery();
			ResultSet rs1 = null;
			for (int i = 0;; i++) {
				Classes cl = new Classes();
				Teacher tea = new Teacher();
				if (rs.next()) {
					cl.setClassId(rs.getString(1));
					cl.setClassName(rs.getString(2));
					tea.setName(rs.getString(3));
					cl.setTeacher(tea);

					ps = ct.prepareStatement(snumSQL);
					ps.setString(1, cl.getClassId());
					rs1 = ps.executeQuery();
					int num = 0;
					if (rs1.next())
						num = rs1.getInt(1);
					cl.setNumber(num);
					cl.setGrade(rs.getInt(4));
					cl.setMajorId(rs.getString(5));
				}else
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
	 * 自动生成班级id函数
	 * 
	 * @param gra
	 *            年级
	 * @param maj
	 *            专业
	 * @throws SQLException
	 */
	public String retSid(int gra, String maj) {
		ct = ConnDB.getConn();
		String id="";
		try {
			ps = ct.prepareStatement(numSQL);
			ps.setInt(1, gra);
			ps.setString(2, maj);
			System.out.println(ps);
			rs = ps.executeQuery();
			if(rs.next()) {
				id+=String.valueOf(gra);
				int num=rs.getInt(1);
				num+=1;
				if(maj=="理科")id+="1";
				else id+="0";
				if(num>=10)id+=String.valueOf(num);
				else id=id+"0"+String.valueOf(num);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*String num = "";
		try {
			if (rs.next())
				num = String.valueOf(rs.getInt(1) + 1);

			ps = ct.prepareStatement(rmidSQL);
			ps.setString(1, maj);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String maj_id = "";
		try {
			if (rs.next())
				maj_id = rs.getString(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ct.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id;
		if (Integer.parseInt(num) <= 9) {
			id = gra + maj_id.substring(4, 5) + "0" + num;
		} else {
			id = gra + maj_id.substring(4, 5) + num;
		}*/
		return id;
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

	public static void main(String[] args) throws SQLException {// 测试

		ClassesProcess up = new ClassesProcess();
		//System.out.println(up.getData("00001", 0).size());
		
		String id=up.retSid(2015, "文科"); System.out.print(id);
		
		/*cla = up.getData("理科", 0);
		for(int i=0;i<cla.size();i++)
		System.out.println(cla.get(i).getClassName());
		/*
		 * StudentProcess up=new StudentProcess(); String id=up.retSid(0, "理科",
		 * "测试"); System.out.print(id);
		 */

	}
}
