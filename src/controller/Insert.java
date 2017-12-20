package controller;
import model.ClassesProcess;
import model.MajorProcess;
import model.StudentProcess;
import model.TeacherProcess;
import view.*;


public class Insert {

	public Insert(){}
	
	public static String addGrade(Integer g) {
		return null;
	}
	
	public static String addStudent(Integer g,String m,String c,String sid,String name,String sex) {
		//插入学生 邢浩
		StudentProcess sp=new StudentProcess();
		sp.insertStudent(sid, name, g, sex, null, c, m, g);
		return null;
	}
	
	public static String addClass(Integer g,String m,String cid,String cname,String teacherName) {
		//邢浩
		ClassesProcess cp=new ClassesProcess();
		cp.insertClasses(cid, cname, teacherName);
		return null;
	}
	public static String addTeacher(Integer g,String tid,String name,String sex) {
		//邢浩
		TeacherProcess tp=new TeacherProcess();
		tp.insertTeacher(tid, name, g, sex, g);
		return null;
	}
}
