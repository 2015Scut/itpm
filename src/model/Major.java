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
	/**所属学院id*/
	private String departId;
	/**班级列表*/
	private ArrayList<Classes>classList;
	
	public Major() {}
	public Major(String mid,String mn,String did,ArrayList<Classes>cl) {
		majorId=mid;
		majorName=mn;
		departId=did;
		classList=cl;
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
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public ArrayList<Classes> getClassList() {
		return classList;
	}
	public void setClassList(ArrayList<Classes> classList) {
		this.classList = classList;
	}
	/**
	 * 增加班级
	 * @param c 班级类
	 */
	public void addClass(Classes c) {
		classList.add(c);
	}
	
}
