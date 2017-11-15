package view;

import java.util.ArrayList;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;  
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewFocusModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;  
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.*;
 
public class StudentTable extends AnchorPane {  
	private TableView<Student> tableView;
    public StudentTable() {  
    	Callback<TableColumn<Student, String>, TableCell<Student, String>> cellFactory = (
                TableColumn<Student, String> p) -> new EditingCell();
                
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
        
        TableColumn njColumn = new TableColumn();  
        njColumn.setText("年级");  
        njColumn.setMinWidth(200);
        njColumn.setCellValueFactory(new PropertyValueFactory("grade")); 
        
        TableColumn fkColumn = new TableColumn();  
        fkColumn.setText("分科");  
        fkColumn.setMinWidth(150);
        fkColumn.setCellValueFactory(new PropertyValueFactory("major")); 
        
        TableColumn bjColumn = new TableColumn();  
        bjColumn.setText("班级");  
        bjColumn.setMinWidth(100);
        bjColumn.setCellValueFactory(new PropertyValueFactory("classes")); 
        
        TableColumn<Student,String> zwColumn = new TableColumn<>();  
        zwColumn.setText("职务");  
        zwColumn.setMinWidth(100);
        zwColumn.setCellFactory(cellFactory);
        zwColumn.setCellValueFactory(new PropertyValueFactory("job")); 
          
        tableView = new TableView<>();  

    	tableView.setEditable(true);
        ArrayList<Student> sl=new ArrayList<>();
        for(int i=0;i<5;i++) {
        	sl.add(new Student("钟", 20, "男", "20150111001", null, 1, "20150111", null, "2015", "理科", "15级理科1班"));
        }
        tableView.getItems().addAll(sl);
        tableView.getColumns().addAll(xhColumn,xmColumn,xbColumn,nlColumn,njColumn,fkColumn,bjColumn,zwColumn);  
        tableView.prefHeightProperty().bind(this.heightProperty());
        tableView.prefWidthProperty().bind(this.widthProperty());
        
        
                
        zwColumn.setOnEditCommit((CellEditEvent<Student, String> t) ->{
        	/*((Student) t.getTableView().getItems().get(t.getTablePosition().getRow()))
            .setJob(t.getNewValue());*/
        });
        
        this.getChildren().add(tableView);  
    }  
    
    public TableView<Student> getTable() {
    	return tableView;
    }
    
    public int row() {
    	TableViewFocusModel<Student> f=tableView.getFocusModel();
    	return f.getFocusedCell().getRow();
    }
    
    public int col() {
    	TableViewFocusModel<Student> f=tableView.getFocusModel();
    	return f.getFocusedCell().getColumn();
    }
    
    
    public void setData(ArrayList<Student> s) {
    	tableView.getItems().clear();
    	tableView.getItems().addAll(s);
    }
    
    class EditingCell extends TableCell<Student,String>{
    	private TextField textField;

        public EditingCell() {
        }

        @Override
        public void startEdit() {
          if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
          }
        }

        @Override
        public void cancelEdit() {
          super.cancelEdit();

          setText((String) getItem());
          setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
          super.updateItem(item, empty);

          if (empty) {
            setText(null);
            setGraphic(null);
          } else {
            if (isEditing()) {
              if (textField != null) {
                textField.setText(getString());
              }
              setText(null);
              setGraphic(textField);
            } else {
              setText(getString());
              setGraphic(null);
            }
          }
        }

        private void createTextField() {
          textField = new TextField(getString());
          textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
          textField.focusedProperty()
              .addListener(
                  (ObservableValue<? extends Boolean> arg0, Boolean arg1,
                      Boolean arg2) -> {
                    if (!arg2) {
                      commitEdit(textField.getText());
                    }
                  });
        }

        private String getString() {
          return getItem() == null ? "" : getItem().toString();
        }
    }
}