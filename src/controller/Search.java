package controller;

import java.util.ArrayList;

import model.Classes;
import model.Student;

public class Search {
	public static ArrayList<Integer> getGrade(){
		//查询数据库中年级列表 龙康
		return null;
	}
	public static ArrayList<String> getClasses(Integer g,String m){
		//根据年级和专业查询班级列表 龙康
		return null;
	}
	public static ArrayList<String> getTeacher(Integer g){
		//根据年级查询教师列表 吕睿
		return null;
	}
	public static String getNextTeacherId() {
		//自动获得教师id 吕睿
		return null;
	}
	public static String getNextStudentId(Integer g,String majorName,String className) {
		//根据年级，专业，班级自动获取学生id  圣杰
		return null;
	}
	public static String getNextClassId(Integer g,String m) {
		//根据年级，专业自动获取班级id  广森
		return null;
	}
	public static ArrayList<Student> get18Student(String sid,String name,Integer g,String majorName,String className,int pageIndex){
		//根据学号，名字，年级，专业，班级，页数，查询 18条记录  圣杰
		return null;
	}
	public static int getStudentPageCount(String sid,String name,Integer g,String majorName,String className) {
		//根据学号，名字，年级，专业，班级，查询总页数  圣杰
		return 1;
	}
	public static boolean checkUserRight(String cid,String uid) {
		//根据班级id和用户id 检查用户权限  恩俊
		return true;
	}
	public static ArrayList<Classes> getClassList(Integer g,String m){
		//根据年级，专业获得班级列表  广森
		return null;
	}
	public static ArrayList<Student> getStudentList(Integer g,String majorName,String className){
		//根据年级，专业名，班级名，获得学生列表  吕睿
		return null;
	}
}
