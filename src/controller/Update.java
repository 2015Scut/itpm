package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import model.Student;
import model.StudentProcess;

public class Update {
	public static void updateJob(String sid,String jobs) {
		//圣杰
		StudentProcess sp=new StudentProcess();
		sp.updateStudent(sid, jobs,null);
	}
	public static String updateSeat(ArrayList<String> rows,ArrayList<Student>sl) {
		//更新座位表
		HashMap<String,Integer> map=new HashMap<>();
		HashSet<Student> set=new HashSet<>();
		for(int i=0;i<rows.size();i++) {
			if(!rows.get(i).equals(sl.get(i).getName())) {
				map.put(rows.get(i), i);
				set.add(sl.get(i));
			}
		}
		if(map.size()!=set.size())
			return "错误";
		for(Student s:set) {
			Integer seat=map.get(s.getName());
			if(seat==null)return "错误";
			updateStudentSeat(s,seat);
		}
		return null;
	}
	private static void updateStudentSeat(Student s,Integer seat) {
		StudentProcess sp=new StudentProcess();
		sp.updateStudent(s.getStudentId(), s.getJob(), seat);
	}
}
