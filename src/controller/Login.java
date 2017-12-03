package controller;

import java.sql.SQLException;

import model.Users;
import model.UsersProcess;

public class Login {
	public String getUsers(String uid,String password) {
		UsersProcess up=new UsersProcess();
		Users user;
		user = up.getData(uid);
		if(user==null) {
			return "账号不存在";
		}else if(!user.getPassword().equals(password)) {
			return "密码错误";
		}else
			return null;
	}
	

}
