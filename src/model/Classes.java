package model;


import java.util.ArrayList;
/**
 * 班级类
 * @author 钟恩俊
 *
 */
public class Classes {
	/**班级id*/
	private String classId;
	/**班级名称*/
	private String className;
	/**年级*/
	private int grade;
	/**所属专业id*/
	private String majorId;
	/**班主任*/
	private Teacher teacher;
	/**班级人数*/
	private int number;
	
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Classes() {}
	public Classes(String cid,String cn,int g,String mid) {
		classId=cid;
		className=cn;
		grade=g;
		majorId=mid;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getMajorId() {
		return majorId;
	}
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	
	
}
