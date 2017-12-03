package controller;

import model.UsersProcess;

public class Register {
	public String register(String uid,String pw,String tid,String name,String passcom) {
		UsersProcess up=new UsersProcess();
		int state=up.insertUser(uid, pw, tid, name);
		if(!passcom.equals(pw))
			return"两次密码不一样";
		if(state==1) {
			return "身份验证错误";
		}else if(state==2) {
			return "没有注册权限";
		}else if(state==3) {
			return "已存在账号";
		}else
			return null;
	}

}
