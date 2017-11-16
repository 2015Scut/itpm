package controller;
import view.*;


public class Insert {

	public Insert(){}
	public void insertStudent(AddStudent af) {
		System.out.println(af.sex.getSelectionModel().getSelectedItem());
	}
}
