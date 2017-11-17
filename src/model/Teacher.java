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
	private int gradeId;
	
	public Teacher() {}
	public Teacher(String n,int a,String s,String tid,int gid) {
		super(n,a,s);
		teacherId=tid;
		gradeId=gid;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public int getDepartId() {
		return gradeId;
	}
	public void setDepartId(int gradeId) {
		this.gradeId = gradeId;
	}
	
}
