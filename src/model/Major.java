package model;


import java.util.ArrayList;
/**
 * 专业类
 * @author 钟恩俊
 *
 */
public class Major {
	/**专业id*/
	private String majorId; 
	/**专业名称*/
	private String majorName;
	/**所属年级id*/
	private int gradeId; 
	
	public Major() {}
	public Major(String mid,String mn,int did) {
		majorId=mid;
		majorName=mn;
		gradeId=did;
	}
	public String getMajorId() {
		return majorId;
	}
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	
	
}
