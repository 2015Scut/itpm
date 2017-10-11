package model;

import java.sql.*;

public class ClassesProcess implements Process {
	private Classes classes;
	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	
	public ClassesProcess() {}

	@Override
	public ResultSet getData(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertData(String sql) {
		// TODO Auto-generated method stub
		
	}

}
