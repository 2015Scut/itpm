package controller;

import model.UsersProcess;

public class Register {
	public boolean Zhuce(String uid,String pw,String tid,String name) {
		UsersProcess up=new UsersProcess();
		return up.insertUser(uid, pw, tid, name);
	}

}
