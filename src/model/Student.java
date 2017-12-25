package model;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
/**
 * 学生类
 * @author 钟恩俊
 *
 */
public class Student extends Person implements Comparable{
	/**学号*/
	private String studentId;
	/**职务*/
	private String job;
	/**座位号*/
	private int seatNumber;
	/**所在班级id*/
	private String classId;
	/**照片*/
	private InputStream photo;
	/**年级*/
	private int grade;
	/**专业*/
	private String major;
	/**班级名称*/
	private String classes;
	private Image im=null;
	
	public Student() {}
	public Student(String na,int a,String s,String sid,String j,int n,String cid,InputStream p,int g,String m,String c) {
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
	public InputStream getPhoto() {
		return photo;
	}
	public void setPhoto(InputStream photo) {
		this.photo = photo;
		
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
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
	@Override
	public int compareTo(Object o) {
		Student s=(Student) o;
		if(this.seatNumber>s.getSeatNumber())return 1;
		else if(this.seatNumber==s.getSeatNumber())return 0;
		else return -1;
	}
	public Image getImage() {
		if(im!=null)return im;
		if(photo==null)return null;
		im=new Image(photo);
		return im;
	}
	
	
}
