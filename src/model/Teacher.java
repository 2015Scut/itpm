package model;

/**
 * 教师类
 * @author 钟恩俊
 *
 */
public class Teacher extends Person{
	/**教师id*/
	private String teacherId;
	/**所属学院id*/
	private String departId;
	
	public Teacher() {}
	public Teacher(String n,int a,String s,String tid,String did) {
		super(n,a,s);
		teacherId=tid;
		departId=did;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	
}
