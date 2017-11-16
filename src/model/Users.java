package model;
/**
 * 用户类
 * @author 钟恩俊
 *
 */

public class Users {
	/**用户名*/
	private String userId;
	/**密码*/
	private String password;
	/**教师id*/
	private String teacherId;
	
	public Users() {}
	public Users(String uid,String pw,String tid) {
		userId=uid;
		password=pw;
		teacherId=tid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
}
