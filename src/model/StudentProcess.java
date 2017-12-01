package model;


import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/** 
 * 处理学生类数据的类，可以获取，添加，更新和删除学生数据 
 * @author 王圣杰 
 * @version 1.1
 */
public class StudentProcess implements Process {
	
	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	/**插入数据的sql语句*/
	private static final String insertSQL="insert into student values (?,?,?,?,?,?,?,?)";
	/**搜索用户id和密码的sql语句*/
	private static final String searchSQL="select * from student where student_id=?";
	/**更新学生的职业和座位号的sql语句*/
	private static final String updateSQL="update student set job=?,seat=? where student_id=?";
	/**删除学生的sql语句*/
	private static final String deleteSQL="delete from student where student_id=?";
	/**根据五个值搜索出的学生集合sql语句*/
	private static final String msearchSQL="select student.student_id,student.student_name,major.major_id,major.major_name,class.class_id from student natural join class natural join major where student.student_id=? and student.student_name=? and major.major_id=? and major.major_name=? and class.class_id=?";
	/**查询指定行数区间的数据的sql语句*/
	private static final String pageSQL="select student_id,student_name,student_sex,student_age,grade_id,major_id,class_id,job from student natural join class natural join major limit ?,?";
	/**查询学生总数的sql语句*/
	private static final String numSQL="select count(student_id) from student";
	public StudentProcess() {}

	/**
	 * 向数据库插入用户
	 * @param sid 学生id
	 * @param name 学生名字
	 * @param age 学生年龄
	 * @param sex 学生性别
	 * @param photo 照片
	 * @param cid 班级id
	 * @param job 学生职业
	 * @param seat 座位号
	 * @throws SQLException SQL异常
	 */
	public boolean insertStudent(String sid,String name,int age,String sex,Blob photo,String cid,String job,int seat) throws SQLException {
		ct=ConnDB.getConn();//获取数据库连接
		ps=ct.prepareStatement(searchSQL);//验证学生id是否重复
		ps.setString(1, sid);
		rs=ps.executeQuery();
		System.out.println(ps);
		if(rs.next())return false;
		
		ps=ct.prepareStatement(insertSQL);//插入学生表
		ps.setString(1, sid);//向String中？的地方填入数据
		ps.setString(2,name);
		ps.setInt(3, age);
		ps.setString(4,sex);
		ps.setBlob(5, photo);
		ps.setString(6, cid);
		ps.setString(7, job);
		ps.setInt(8, seat);
		System.out.println(ps);
		ps.executeUpdate();
		
		ct.close();//关闭数据库，记得每次用完都要关闭
		ct=null;
		ps=null;
		rs=null;
		return true;
	}
	/**
	 * 更改学生职位和座位表
	 * @param sid 学生id
	 * @param job 新的职位
	 * @param seat 新的座位号
	 * @throws SQLException SQL异常
	 */
	public void updateStudent(String sid,String job,int seat) throws SQLException {
		ct=ConnDB.getConn();
		ps=ct.prepareStatement(updateSQL);
		ps.setString(1, job);
		ps.setInt(2, seat);
		ps.setString(3, sid);
		ps.executeUpdate();
		ct.close();
		ct=null;
		ps=null;
	}
	public void deleteStudent(String sid) throws SQLException {
		ct=ConnDB.getConn();
		ps=ct.prepareStatement(deleteSQL);
		ps.setString(1, sid);
		ps.executeUpdate();
		ct.close();
		ct=null;
		ps=null;
	}
	
	public ResultSet getData(String sid,String name,String grade,String major,String classn) throws SQLException
	{
		ct=ConnDB.getConn();
		ps=ct.prepareStatement(msearchSQL);
		ps.setString(1, sid);
		ps.setString(2, name);
		ps.setString(3, grade);
		ps.setString(4, major);
		ps.setString(5, classn);
		rs=ps.executeQuery();
		ct.close();
		ct=null;
		ps=null;
		return rs;
	}
	/**
	 * 根据页面数值返回对应的数据库信息
	 * @param page_num 页面值
	 * @throws SQLException 
	 */
	public ResultSet getPageData(int page_num) throws SQLException
	{
		ct=ConnDB.getConn();
		ps=ct.prepareStatement(pageSQL);
		int temp1=(page_num-1)*20+1;
		int temp2=20*page_num;
		ps.setInt(1, temp1);
		ps.setInt(2, temp2);
		rs=ps.executeQuery();
		ct.close();
		ct=null;
		ps=null;
		return rs;
	}
	/**
	 *自动生成学号函数 
	 * @param gra 年级
	 * @param maj 专业
	 * @param cla 班级
	 * @throws SQLException 
	 */
	public String retSid(String gra,String maj,String cla) throws SQLException
	{
		ct=ConnDB.getConn();
		ps=ct.prepareStatement(numSQL);
		rs=ps.executeQuery();
		String num="";
		if(rs.next())
			num=String.valueOf(rs.getInt(1)+1);
		String id=gra+maj+cla+num;
		return id;
	}
	/**
	 * 测试函数
	 * @param args 参数
	 * @throws SQLException 
	 */
	public static void main(String [] args) throws SQLException {//测试
		/*StudentProcess up=new StudentProcess();
		String id=up.retSid("2015", "01", "01");
		System.out.print(id);*/
		/*try {
			up.insertStudent("000010101","王",20,"男",null,"0000101","打酱油",666);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*try {
			up.updateStudent("0001","打麻油",888);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*try {
			up.deleteStudent("000010101");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
