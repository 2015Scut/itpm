package view;

import controller.*;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Grade;
import model.Student;
/**
 * 搜索页面
 * @author 钟恩俊
 *
 */
public class SearchPage extends BorderPane{
	private StudentTable tb;
	private Button sbt;
	private Button modify;
	//private Button add;
	private Button delete;
	private TextField idtf;
	private TextField nametf;
	private Label idlb;
	private Label namelb;
	private Pagination pg;
	private Label gradelb;
	private Label majorlb;
	private Label classlb;
	private ComboBox<Integer> gradecb;
	private ComboBox<String> majorcb;
	private ComboBox<String> classcb;
	private String uid;
	
	public SearchPage(String u) {
		uid=u;
		tb=new StudentTable();
		gradelb=new Label("年级: ");
    	majorlb=new Label("分科: ");
    	classlb=new Label("班级: ");
    	
    	Search search=new Search();
    	
    	gradecb=new ComboBox<>();
    	if(Search.getGrade()!=null)
			gradecb.getItems().addAll(Search.getGrade());
    	majorcb=new ComboBox<>();
    	classcb=new ComboBox<>();
    	gradecb.valueProperty().addListener(new ChangeListener<Integer>() {
    		//当下拉框的值改变时，设置班级下拉框的items
			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				// TODO Auto-generated method stub
				majorcb.getItems().clear();
				majorcb.getItems().addAll("文科","理科");
				classcb.getItems().clear();
			}
    		
    	});
		majorcb.valueProperty().addListener(new ChangeListener<String>() {
    		//当下拉框的值改变时，设置学生id
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(Search.getClasses(gradecb.getValue(),newValue)!=null) {
					classcb.getItems().clear();
					classcb.getItems().addAll(Search.getClasses(gradecb.getValue(),newValue));
				}
			}
    		
    	});
		sbt=new Button("搜索");
		modify=new Button("修改");
		delete=new Button("删除");
		idtf=new TextField();
		nametf=new TextField();
		namelb=new Label("姓名: ");
		idlb=new Label("学号: ");
		pg=new Pagination(10,0);
		pg.setPageFactory((Integer pageIndex) -> {
				String stdid=idtf.getText();
				String stdname=nametf.getText();
				Integer grade=gradecb.getValue();
				String major=majorcb.getValue();
				String classes=classcb.getValue();
				ArrayList<Student> sl=Search.get18Student(stdid, stdname, grade, major, classes,pageIndex);
				tb.getTable().getItems().clear();
				if(sl!=null)
					tb.getTable().getItems().addAll(sl);
				pg.setPageCount(Search.getStudentPageCount(stdid, stdname, grade, major, classes));

			
            return createPage(pageIndex);
        });
		
		tb.getColumn().setOnEditCommit((CellEditEvent<Student, String> t)->{
			test.show("是否保存");
        	Student seletedStudent=((Student) t.getTableView().getItems().get(t.getTablePosition().getRow()));
        	
        	if(test.getRet()==0) {
        		//tableView.getItems().clear();
        		String stdid=idtf.getText();
				String stdname=nametf.getText();
				Integer grade=gradecb.getValue();
				String major=majorcb.getValue();
				String classes=classcb.getValue();
				ArrayList<Student> sl=Search.get18Student(stdid, stdname, grade, major, classes,pg.getCurrentPageIndex());
				tb.getTable().getItems().clear();
				if(sl!=null)
					tb.getTable().getItems().addAll(sl);
        		return;
        	}
        	
        	seletedStudent.setJob(t.getNewValue());
        	Update.updateJob(seletedStudent.getStudentId(), t.getNewValue(),seletedStudent.getSeatNumber());
        	
		});
		
		delete.setOnAction(e->{
			Student seletedStudent=tb.getTable().getSelectionModel().getSelectedItem();
			if(seletedStudent==null) {
				test.show("请选中学生");
			}else {
				String c=seletedStudent.getClassId();
				String sid=seletedStudent.getStudentId();
				if(Search.checkUserRight(c, uid)) {
					String message=Delete.deleteStudent(sid);
					if(message!=null)test.show(message);
					else {
						String stdid=idtf.getText();
						String stdname=nametf.getText();
						Integer grade=gradecb.getValue();
						String major=majorcb.getValue();
						String classes=classcb.getValue();
						ArrayList<Student> sl=Search.get18Student(stdid, stdname, grade, major, classes,pg.getCurrentPageIndex());
						tb.getTable().getItems().clear();
						if(sl!=null)
							tb.getTable().getItems().addAll(sl);
					}
				}else {
					test.show("没有权限");
				}
			}
		});
		
		sbt.setOnAction(e->{
			//搜索
			String stdid=idtf.getText();
			String stdname=nametf.getText();
			Integer grade=gradecb.getValue();
			String major=majorcb.getValue();
			String classes=classcb.getValue();
			ArrayList<Student> sl=Search.get18Student(stdid, stdname, grade, major, classes,pg.getCurrentPageIndex());
			tb.getTable().getItems().clear();
			if(sl!=null)
				tb.getTable().getItems().addAll(sl);
			pg.setPageCount(Search.getStudentPageCount(stdid, stdname, grade, major, classes));
		});
		
		modify.setOnAction(e->{
			String c=tb.getTable().getSelectionModel().getSelectedItem().getClasses();
			if(!checkTeacher(c))
				test.show("没有权限");
		});
		
		HBox hb=new HBox();
    	hb.getChildren().addAll(idlb,idtf,namelb,nametf,gradelb,gradecb,majorlb,majorcb,classlb,classcb,sbt);
		hb.setSpacing(10);
		this.setTop(hb);
		this.setCenter(tb);
		VBox vb=new VBox();
		HBox buttonBox=new HBox();
		buttonBox.getChildren().addAll(modify,delete);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(20);
		vb.getChildren().addAll(pg,buttonBox);
		this.setBottom(vb);
	}
	/**
	 * 根据学院获取专业列表
	 * @param gradeId 年级id
	 * @return 专业名的数组
	 */
	
	private VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        return box;
    }
	private boolean checkTeacher(String c) {
		return false;
	}
}
