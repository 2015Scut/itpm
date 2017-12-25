package controller;
import java.io.FileInputStream;
import java.util.ArrayList;

import model.Classes;
import model.ClassesProcess;
import model.GradeProcess;
import model.MajorProcess;
import model.StudentProcess;
import model.TeacherProcess;
import java.util.regex.*;

public class Insert {

	public Insert(){}
	
	public static String addGrade(Integer g) {
		if(g==null)
			return "信息不能为空";
		GradeProcess gp=new GradeProcess();
		int state=gp.insertGrade(g);
		if(state==0)
			return "年级重复";
		MajorProcess mp=new MajorProcess();
		String id=String.valueOf(g);
		mp.insertmajor(g+"0", "文科", g);
		mp.insertmajor(g+"1", "理科", g);
		return null;
	}
	
	public static String addStudent(Integer g,String m,String c,String sid,String name,String sex,String age,FileInputStream in) {
		//插入学生 邢浩
		if(g==null||c==null||sid==null||name==null||sex==null||age==null)
			return "信息不能为空";
		Pattern pattern = Pattern.compile("[1-9][0-9]*"); 
		Matcher isNum = pattern.matcher(age);
		if(!isNum.matches()) {
			return "年龄信息错误";
		}
		Integer a=Integer.parseInt(age);
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
		int state=sp.insertStudent(sid, name, a, sex, in, cid,null, Integer.parseInt(sid)%100);
		if(state==1)
			return "学生id重复";
		return null;
	}
	
	public static String addClass(Integer g,String m,String cid,String cname,String teacherName) {
		//邢浩
		if(g==null||m==null||cid==null||cname==null||teacherName==null)
			return "信息不能为空";
		String mid=String.valueOf(g);
		if(m=="理科")mid+="1";
		else mid+="0";
		ClassesProcess cp=new ClassesProcess();
		cp.insertClasses(cid, cname,mid,teacherName);
		return null;
	}
	public static String addTeacher(Integer g,String tid,String name,String sex,String age) {
		//邢浩
		if(g==null||tid==null||name==null||sex==null||age==null)
			return "信息不能为空";
		Pattern pattern = Pattern.compile("[1-9][0-9]*"); 
		Matcher isNum = pattern.matcher(age);
		if(!isNum.matches()) {
			return "年龄信息错误";
		}
		Integer a=Integer.parseInt(age);
		TeacherProcess tp=new TeacherProcess();
		tp.insertTeacher(Integer.parseInt(tid), name, a, sex, g);
		return null;
	}
}
