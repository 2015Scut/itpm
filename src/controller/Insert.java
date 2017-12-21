package controller;
import java.io.FileInputStream;
import java.util.ArrayList;

import model.Classes;
import model.ClassesProcess;
import model.GradeProcess;
import model.MajorProcess;
import model.StudentProcess;
import model.TeacherProcess;


public class Insert {

	public Insert(){}
	
	public static String addGrade(Integer g) {
		GradeProcess gp=new GradeProcess();
		gp.insertGrade(g);
		MajorProcess mp=new MajorProcess();
		String id=String.valueOf(g);
		mp.insertmajor(g+"0", "文科", g);
		mp.insertmajor(g+"1", "理科", g);
		return null;
	}
	
	public static String addStudent(Integer g,String m,String c,String sid,String name,String sex,Integer age,FileInputStream in) {
		//插入学生 邢浩
		ClassesProcess cp=new ClassesProcess();
		ArrayList<Classes>cl=cp.getData(m, g);
		String cid="";
		for(Classes cla:cl) {
			if(cla.getClassName().equals(c)) {
				cid=cla.getClassId();
				break;
			}
		}
		StudentProcess sp=new StudentProcess();
		sp.insertStudent(sid, name, age, sex, in, cid,null, Integer.parseInt(sid)%100);
		
		return null;
	}
	
	public static String addClass(Integer g,String m,String cid,String cname,String teacherName) {
		//邢浩
		String mid=String.valueOf(g);
		if(m=="理科")mid+="1";
		else mid+="0";
		ClassesProcess cp=new ClassesProcess();
		cp.insertClasses(cid, cname,mid,teacherName);
		return null;
	}
	public static String addTeacher(Integer g,String tid,String name,String sex,Integer age) {
		//邢浩
		TeacherProcess tp=new TeacherProcess();
		tp.insertTeacher(Integer.parseInt(tid), name, age, sex, g);
		return null;
	}
}
