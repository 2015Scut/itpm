package model;

import java.sql.Blob;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * 学生类
 * @author 钟恩俊
 *
 */
public class Student extends Person{
	/**学号*/
	private String studentId;
	/**职务*/
	private String job;
	/**座位号*/
	private int seatNumber;
	/**所在班级id*/
	private String classId;
	/**照片*/
	private Blob photo;
	/**年级*/
	private String grade;
	/**专业*/
	private String major;
	/**班级名称*/
	private String classes;
	
	public Student() {}
	public Student(String na,int a,String s,String sid,String j,int n,String cid,Blob p,String g,String m,String c) {
		super(na,a,s);
		studentId=sid;
		job=j;
		seatNumber=n;
		classId=cid;
		photo=p;
		grade=g;
		major=m;
		classes=c;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public Blob getPhoto() {
		return photo;
	}
	public void setPhoto(Blob photo) {
		this.photo = photo;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	
	
}
