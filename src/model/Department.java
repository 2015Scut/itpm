package model;

import java.util.ArrayList;
/**
 * 学院类
 * @author 钟恩俊
 *
 */
public class Department {
	/**学院id*/
	private String departId;
	/**学院名称*/
	private String departName;
	/**专业列表*/
	private ArrayList<Major>majorList;
	/**教师列表*/
	private ArrayList<Teacher>teacherList;
	
	public Department() {}
	public Department(String did,String dn,ArrayList<Major>ml,ArrayList<Teacher>tl) {
		departId=did;
		departName=dn;
		majorList=ml;
		teacherList=tl;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
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
