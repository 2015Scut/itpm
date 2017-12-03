package controller;

import java.sql.SQLException;

import model.Users;
import model.UsersProcess;

public class Login {
	public Users getUsers(String uid) {
		UsersProcess up=new UsersProcess();
		Users user;
		user = up.getData(uid);
		return user;
	}
	

}
