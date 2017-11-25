package model;


import java.util.ArrayList;
/**
 * 年级类
 * @author 钟恩俊
 *
 */
public class Grade {
	/**年级id*/
	private int gradeId;
	
	public Grade() {}
	public Grade(int did) {
		gradeId=did;
	}
	
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	
	
}
