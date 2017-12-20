package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 * 处理学生类数据的类，可以获取，添加，更新和删除学生数据
 * 
 * @author 王圣杰
 * @version 1.2
 * 
 *
 */
public class StudentProcess implements Process {

	private static Connection ct = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	/** 插入数据的sql语句 */
	private static final String insertSQL = "insert into student values (?,?,?,?,?,?,?,?)";
	/** 搜索用户id和密码的sql语句 */
	private static final String searchSQL = "select * from student where student_id=?";
	/** 更新学生的职业的sql语句 */
	private static final String updateSQL = "update student set job=?,seat=? where student_id=?";
	/** 删除学生的sql语句 */
	private static final String deleteSQL = "delete from student where student_id=?";
	/** 根学号，名字，年级，专业，班级查询学生集合的sql语句 */
	private static final String msearchSQL = "select student_id,student_name,student_sex,student_age,grade_id,major_name,class_name,job,seat,photo from student natural join class natural join major where (?='' or student_id=?) and (?='' or student_name=?) and (?='' or grade_id=?) and (?='' or major_name=?) and (?='' or class_name=?)";
	/** 根据学号，名字，年级，专业，班级，页数，查询 18条记录sql语句 */
	private static final String pageSQL = "select student_id,student_name,student_sex,student_age,grade_id,major_name,class_name,job,seat,class_id,photo from student natural join class natural join major where (?='' or student_id=?) and (?='' or student_name=?) and (?='' or grade_id=?) and (?='' or major_name=?) and (?='' or class_name=?) limit ?,?";
	/**根据学号，名字，年级，专业，班级，查询总页数*/
	private static final String countSQL = "select count(student_id) from student natural join class natural join major where (?='' or student_id=?) and (?='' or student_name=?) and (?='' or grade_id=?) and (?='' or major_name=?) and (?='' or class_name=?)";
	/** 查询指定年级专业及班级的学生总数的sql语句 */
	private static final String numSQL = "select count(student_id) from student natural join class natural join major where grade_id=? and major_name=? and class_name=?";
	/** 查询专业名对应的专业id */
	private static final String rmidSQL = "select major_id from major where major_name=?";
	/** 查询班级名对应的班级id */
	private static final String rcidSQL = "select class_id from class where class_name=?";

	public StudentProcess() {
	}

	/**
	 * 向数据库插入用户
	 * 
	 * @param sid
	 *            学生id
	 * @param name
	 *            学生名字
	 * @param age
	 *            学生年龄
	 * @param sex
	 *            学生性别
	 * @param photo
	 *            照片
	 * @param cid
	 *            班级id
	 * @param job
	 *            学生职业
	 * @param seat
	 *            座位号
	 * @throws SQLException
	 *             SQL异常
	 */
	public int insertStudent(String sid, String name, int age, String sex, InputStream photo, String cid, String job,
			int seat) {
		ct = ConnDB.getConn();// 获取数据库连接
		try {
			ps = ct.prepareStatement(searchSQL);// 验证学生id是否重复
			ps.setString(1, sid);
			rs = ps.executeQuery();
			if (rs.next())
				return 1;// 学生id重复

			ps = ct.prepareStatement(insertSQL);// 插入学生表
			ps.setString(1, sid);// 向String中？的地方填入数据
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setString(4, sex);
			ps.setBlob(5, photo);
			ps.setString(6, cid);
			ps.setString(7, job);
			ps.setInt(8, seat);
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
		return 2;
	}

	/**
	 * 更改学生职位和座位表
	 * 
	 * @param sid
	 *            学生id
	 * @param seat
	 *            新的座位号
	 * @throws SQLException
	 *             SQL异常
	 */
	public void updateStudent(String sid, String job,Integer seat) {
		ct = ConnDB.getConn();
		try {
			ps = ct.prepareStatement(updateSQL);
			ps.setString(1, job);
			ps.setInt(2, seat);
			ps.setString(3, sid);
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

	public void deleteStudent(String sid) {
		ct = ConnDB.getConn();
		try {
			ps = ct.prepareStatement(deleteSQL);
			ps.setString(1, sid);
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

	public ArrayList<Student> getData(String sid, String name, Integer grade, String major, String classn) {
		ArrayList<Student> stu = new ArrayList<Student>();
		ct = ConnDB.getConn();
		if(sid==null)sid="";
		if(name==null)name="";
		if(major==null)major="";
		if(classn==null)classn="";
		try {
			ps = ct.prepareStatement(msearchSQL);
			ps.setString(1, sid);
			ps.setString(2, sid);
			ps.setString(3, name);
			ps.setString(4, name);
			if(grade==null) {
				ps.setString(5, "");
				ps.setString(6, "");
			}else {
				ps.setInt(5, grade);
				ps.setInt(6, grade);
			}
			ps.setString(7, major);
			ps.setString(8, major);
			ps.setString(9, classn);
			ps.setString(10, classn);
			rs = ps.executeQuery();
			for (int i = 0;; i++) {
				Student st = new Student();
				if (rs.next()) {
					st.setStudentId(rs.getString(1));
					st.setName(rs.getString(2));
					st.setSex(rs.getString(3));
					st.setAge(rs.getInt(4));
					st.setGrade(rs.getInt(5));
					st.setMajor(rs.getString(6));
					st.setClasses(rs.getString(7));
					st.setJob(rs.getString(8));
					st.setSeatNumber(rs.getInt(9));
					if(rs.getBlob(10)!=null) {
						st.setPhoto(rs.getBlob(10).getBinaryStream());
						
					}
				} else
					break;
				stu.add(st);
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
		return stu;
	}

	/**
	 * 根据页面数值返回对应的数据库信息
	 * 
	 * @param page_num
	 *            页面值
	 * @throws SQLException
	 */
	public ArrayList<Student> getPageData(String sid,String name,Integer g,String majorName,String className,int pageIndex) {
		ArrayList<Student> stu = new ArrayList<Student>();
		if(sid==null)sid="";
		if(name==null)name="";
		if(majorName==null)majorName="";
		if(className==null)className="";
		ct = ConnDB.getConn();
		try {
			ps = ct.prepareStatement(pageSQL);
			int temp1 = (pageIndex) * 18;
			int temp2 = 18 ;
			ps.setString(1, sid);
			ps.setString(2, sid);
			ps.setString(3, name);
			ps.setString(4, name);
			if(g==null) {
				ps.setString(5, "");
				ps.setString(6, "");
			}else {
				ps.setInt(5, g);
				ps.setInt(6, g);
			}
			ps.setString(7, majorName);
			ps.setString(8, majorName);
			ps.setString(9, className);
			ps.setString(10, className);
			ps.setInt(11, temp1);
			ps.setInt(12, temp2);
			rs = ps.executeQuery();
			for (int i = 0;; i++) {
				Student st = new Student();
				if (rs.next()) {
					st.setStudentId(rs.getString(1));
					st.setName(rs.getString(2));
					st.setSex(rs.getString(3));
					st.setAge(rs.getInt(4));
					st.setGrade(rs.getInt(5));
					st.setMajor(rs.getString(6));
					st.setClasses(rs.getString(7));
					st.setJob(rs.getString(8));
					st.setSeatNumber(rs.getInt(9));
					st.setClassId(rs.getString(10));
					st.setPhoto(rs.getBlob(11).getBinaryStream());
				} else
					break;
				stu.add(st);
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
		return stu;
	}
	/**
	 * //根据学号，名字，年级，专业，班级，查询总页数的函数
	 */
	public int num_Page(String sid,String name,Integer g,String majorName,String className){
		ct = ConnDB.getConn();
		if(sid==null)sid="";
		if(name==null)name="";
		if(majorName==null)majorName="";
		if(className==null)className="";
		try {
			ps = ct.prepareStatement(countSQL);
				
		ps.setString(1, sid);
		ps.setString(2, sid);
		ps.setString(3, name);
		ps.setString(4, name);
		if(g==null) {
			ps.setString(5, "");
			ps.setString(6, "");
		}else {
			ps.setInt(5, g);
			ps.setInt(6, g);
		}
		ps.setString(7, majorName);
		ps.setString(8, majorName);
		ps.setString(9, className);
		ps.setString(10, className);
		rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int page_num = 0;
		try {
			if(rs.next())
				page_num=(int) Math.ceil(rs.getInt(1)/18.0);
			ct.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ct = null;
		ps = null;
		
		return page_num;
	}

	/**
	 * 自动生成学号函数
	 * 
	 * @param gra
	 *            年级
	 * @param maj
	 *            专业
	 * @param cla
	 *            班级
	 * @throws SQLException
	 */
	public String retSid(int gra, String maj, String cla) {
		ct = ConnDB.getConn();
		try {
			ps = ct.prepareStatement(numSQL);
			ps.setInt(1, gra);
			ps.setString(2, maj);
			ps.setString(3, cla);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String num = "";
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

			ps = ct.prepareStatement(rcidSQL);
			ps.setString(1, cla);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String cla_id = "";
		try {
			if (rs.next())
				cla_id = rs.getString(1);
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
		String id = gra + maj_id + cla_id.substring(5, 6) + num;
		return id;
	}

	/**
	 * 测试函数
	 * 
	 * @param args
	 *            参数
	 * @throws SQLException
	 * @throws IOException 
	 */
	public static void main(String[] args) throws SQLException, IOException  {// 测试
		
		File file=new File("D:\\test.png");
		try {
			String p="select photo from student where student_name='照片'";
			String up="update student set photo=? where student_name='照片'";
			FileInputStream insert=new FileInputStream(file);
			ct=ConnDB.getConn();
			ps=ct.prepareStatement(p);
			//ps.setBlob(1, insert);
			//ps.executeUpdate();
			rs=ps.executeQuery();
			if(rs.next()) {
				byte[] b=new byte[1024];
				FileOutputStream in=new FileOutputStream(new File("D:\\student.png"));
				//Image im=new Image(in);
				InputStream i=rs.getBlob(1).getBinaryStream();
				while(i.read(b)!=-1)
					in.write(b);
				in.close();
				i.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ct.close();
			ps.close();
		}
	}
	

}
