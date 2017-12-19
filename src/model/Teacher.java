package model;

/**
 * 教师类
 * @author 钟恩俊
 *
 */
public class Teacher extends Person{
	/**教师id*/
	private int teacherId;
	/**所属学院id*/
	private int gradeId;
	
	public Teacher() {}
	public Teacher(String n,int a,String s,int tid,int gid) {
		super(n,a,s);
		teacherId=tid;
		gradeId=gid;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	
}
