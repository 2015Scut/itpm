package model;

import java.util.ArrayList;
/**
 * 年级类
 * @author 钟恩俊
 *
 */
public class Grade {
	/**年级id*/
	private String gradeId;
	/**专业列表*/
	private ArrayList<Major>majorList;
	/**教师列表*/
	private ArrayList<Teacher>teacherList;
	
	public Grade() {}
	public Grade(String did,ArrayList<Major>ml,ArrayList<Teacher>tl) {
		gradeId=did;
		majorList=ml;
		teacherList=tl;
	}
	
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public ArrayList<Major> getMajorList() {
		return majorList;
	}
	public void setMajorList(ArrayList<Major> majorList) {
		this.majorList = majorList;
	}
	public ArrayList<Teacher> getTeacherList() {
		return teacherList;
	}
	public void setTeacherList(ArrayList<Teacher> teacherList) {
		this.teacherList = teacherList;
	}
	/**
	 * 增加教师
	 * @param t 教师类
	 */
	public void addTeacher(Teacher t) {
		teacherList.add(t);
	}
	/**
	 * 增加专业
	 * @param m 专业类
	 */
	public void addMajor(Major m) {
		majorList.add(m);
	}
	
}
