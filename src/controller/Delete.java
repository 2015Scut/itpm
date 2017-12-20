package controller;

import model.StudentProcess;

public class Delete {
	public static String deleteStudent(String sid) {
		StudentProcess sp=new StudentProcess();
		sp.deleteStudent(sid);
		return null;
	}
}
