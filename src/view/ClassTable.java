package view;


import java.util.ArrayList;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.Classes;
import model.Student;

public class ClassTable extends VBox{
	private TableView<Classes> tableView;
	private Button sbt;
	private Label gradelb;
	private Label majorlb;
	private ComboBox<String> gradecb;
	private ComboBox<String> majorcb;
	
	public ClassTable() {
		TableColumn bjColumn = new TableColumn();  
        bjColumn.setText("班级");  
        bjColumn.setMinWidth(200);
        bjColumn.setCellValueFactory(new PropertyValueFactory("className")); 
        
        TableColumn njColumn = new TableColumn();  
        njColumn.setText("年级");  
        njColumn.setMinWidth(200);
        njColumn.setCellValueFactory(new PropertyValueFactory("grade"));
        
        TableColumn rsColumn = new TableColumn();  
        rsColumn.setText("人数");  
        rsColumn.setMinWidth(200);
        rsColumn.setCellValueFactory(new PropertyValueFactory("grade"));
        
        TableColumn jsColumn = new TableColumn();  
        jsColumn.setText("班主任");  
        jsColumn.setMinWidth(200);
        jsColumn.setCellValueFactory(new PropertyValueFactory("grade"));
        
        tableView=new TableView<Classes>();
        tableView.getColumns().addAll(bjColumn,njColumn,rsColumn,jsColumn);
        HBox hb=new HBox();
        sbt=new Button("搜索");
        gradelb=new Label("年级: ");
        majorlb=new Label("分科: ");
        majorcb=new ComboBox<>();
    	gradecb=new ComboBox<>();
    	hb.getChildren().addAll(gradelb,gradecb,majorlb,majorcb,sbt);
    	hb.setSpacing(20);
    	this.getChildren().addAll(hb,tableView);
	}
}
