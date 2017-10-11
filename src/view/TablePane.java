package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;  
import javafx.collections.ObservableList;  
import javafx.scene.control.TableColumn;  
import javafx.scene.control.TableView;  
import javafx.scene.control.cell.PropertyValueFactory;  
import javafx.scene.layout.AnchorPane;  
import model.*;
 
public class TablePane extends AnchorPane {  
	private TableView<Student> tableView;
    public TablePane() {  
        TableColumn xhColumn = new TableColumn();  
        xhColumn.setText("学号");  
        xhColumn.setMinWidth(146);
        xhColumn.setCellValueFactory(new PropertyValueFactory("studentId"));  
          
        TableColumn xmColumn = new TableColumn();  
        xmColumn.setText("姓名");  
        xmColumn.setMinWidth(100);
        xmColumn.setCellValueFactory(new PropertyValueFactory("name"));  
          
        TableColumn xbColumn = new TableColumn();  
        xbColumn.setText("性别");  
        xbColumn.setMinWidth(100);
        xbColumn.setCellValueFactory(new PropertyValueFactory("sex"));  
        
        TableColumn nlColumn = new TableColumn();  
        nlColumn.setText("年龄");  
        nlColumn.setMinWidth(100);
        nlColumn.setCellValueFactory(new PropertyValueFactory("age")); 
        
        TableColumn xyColumn = new TableColumn();  
        xyColumn.setText("学院");  
        xyColumn.setMinWidth(200);
        xyColumn.setCellValueFactory(new PropertyValueFactory("department")); 
        
        TableColumn zyColumn = new TableColumn();  
        zyColumn.setText("专业");  
        zyColumn.setMinWidth(150);
        zyColumn.setCellValueFactory(new PropertyValueFactory("major")); 
        
        TableColumn bjColumn = new TableColumn();  
        bjColumn.setText("班级");  
        bjColumn.setMinWidth(100);
        bjColumn.setCellValueFactory(new PropertyValueFactory("classes")); 
        
        TableColumn zwColumn = new TableColumn();  
        zwColumn.setText("职务");  
        zwColumn.setMinWidth(100);
        zwColumn.setCellValueFactory(new PropertyValueFactory("job")); 
          
        tableView = new TableView<>();  
        ArrayList<Student> sl=new ArrayList<>();
        for(int i=0;i<5;i++) {
        	sl.add(new Student("钟", 20, "男", "20150111001", null, 1, "20150111", null, "软件学院", "软件工程", "15软件工程一班"));
        }
        tableView.getItems().addAll(sl);
        tableView.getColumns().addAll(xhColumn,xmColumn,xbColumn,nlColumn,xyColumn,zyColumn,bjColumn,zwColumn);  
        tableView.prefHeightProperty().bind(this.heightProperty());
        tableView.prefWidthProperty().bind(this.widthProperty());
        
        
        this.getChildren().add(tableView);  
    }  
    
    public void setData(ArrayList<Student> s) {
    	tableView.getItems().clear();
    	tableView.getItems().addAll(s);
    }
}