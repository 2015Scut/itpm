package view;


import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.Student;
import view.StudentTable.EditingCell;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;  
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell; 

/**
 * 座位表页面
 * @author 钟恩俊
 *
 */
public class SeatPage extends VBox{
	private TableView<row> tv;
	
	private Label gradelb;
	private Label majorlb;
	private Label classlb;
	private ComboBox<String> gradecb;
	private ComboBox<String> majorcb;
	private ComboBox<String> classcb;
	private Button photo;
	private Button save;
	private Button sbt;
	
	public SeatPage() {
		gradelb=new Label("年级: ");
    	majorlb=new Label("分科: ");
    	classlb=new Label("班级: ");
    	gradecb=new ComboBox<>();
    	majorcb=new ComboBox<>();
    	classcb=new ComboBox<>();
    	photo=new Button("显示照片");
    	save=new Button("保存");
    	sbt=new Button("搜索");
    	HBox hb=new HBox();
    	hb.getChildren().addAll(gradelb,gradecb,majorlb,majorcb,classlb,classcb,photo,save,sbt);
    	hb.setSpacing(10);
    	ArrayList<row> rows=new ArrayList<>();
    	rows.add(new row("","","","讲","台","","",""));
    	
    	Callback<TableColumn<row, String>, TableCell<row, String>> cellFactory = (
                TableColumn<row, String> p) -> new EditingCell();
		tv=new TableView<>();
		for(int i=0;i<8;i++) {
			int n=i;
			TableColumn<row,String> tc=new TableColumn(i+1+"");
			
			tc.setMinWidth(124);
			tc.setCellValueFactory(new PropertyValueFactory<row,String>(String.valueOf((char)(i+97))));
			tc.setCellFactory(TextFieldTableCell.<row>forTableColumn());
			/*tc.setOnEditCommit(
					(CellEditEvent<row, String> t)->{
				((row)t.getTableView().getItems().get(
						t.getTablePosition().getRow())
						).set(i+1, t.getNewValue());
			});*/
			tc.setOnEditCommit((CellEditEvent<row, String> t) ->{
				((row) t.getTableView().getItems().get(t.getTablePosition().getRow()))
	            .set(n,t.getNewValue());
	        	t.getNewValue();//获得修改的新值
	        	t.getOldValue();//获得修改前的值
			});
			tv.getColumns().add(tc);
			
		}
		tv.getItems().addAll(rows);
		gradecb.valueProperty().addListener(new ChangeListener<String>() {
    		//当下拉框的值改变时，设置专业下拉框的items
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				ArrayList<String> majors = getMajorList(newValue);
				majorcb.getItems().clear();
				majorcb.getItems().addAll(majors);
			}
    		
    	});
		sbt.setOnAction(e->{//搜索
			
		});
		photo.setOnAction(e->{
			int r=tv.getFocusModel().getFocusedCell().getRow();
			int c=tv.getFocusModel().getFocusedCell().getColumn();
			
    		System.out.println(tv.getFocusModel().getFocusedItem().get(c));
    		
    	});
		tv.setId("seattable");
		
		this.getStylesheets().add(getClass().getResource("seattable.css").toExternalForm());
		tv.setEditable(true);
		tv.setPrefHeight(592);
		tv.setMaxWidth(994);
		this.getChildren().addAll(hb,tv);
	}
	
	class EditingCell extends TableCell<row,String>{
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
	
	protected ArrayList<String> getMajorList(String newValue) {
		// TODO Auto-generated method stub
		return null;
	}
	private void addData(ArrayList<Student>studentList,row r) {
		
	}

	public static class row{
		private SimpleStringProperty a;
        private SimpleStringProperty b;
        private SimpleStringProperty c;
        private SimpleStringProperty d;
        private SimpleStringProperty e;
        private SimpleStringProperty f;
        private SimpleStringProperty g;
        private SimpleStringProperty h;
        
        public row(String a,String b,String c,String d,String e,String f,String g,String h) {
        	super();
        	this.a=new SimpleStringProperty(a);
        	this.b=new SimpleStringProperty(b);
        	this.c=new SimpleStringProperty(c);
        	this.d=new SimpleStringProperty(d);
        	this.e=new SimpleStringProperty(e);
        	this.f=new SimpleStringProperty(f);
        	this.g=new SimpleStringProperty(g);
        	this.h=new SimpleStringProperty(h);
        	
        }
        public void set(int n,String s) {
        	if(n==0)
        		setA(s);
        	else if(n==1)
        		setB(s);
        	else if(n==3)
        		setC(s);
        	else if(n==4)
        		setD(s);
        	else if(n==5)
        		setE(s);
        	else if(n==6)
        		setF(s);
        	else if(n==7)
        		setG(s);
        	else setH(s);
        }
        public void setA(String a) {
        	this.a.set(a);
        }
        public void setB(String b) {
        	this.b.set(b);
        }
        public void setC(String c) {
        	this.c.set(c);
        }
        public void setD(String d) {
        	this.d.set(d);
        }
        public void setE(String e) {
        	this.e.set(e);
        }
        public void setF(String f) {
        	this.f.set(f);
        }
        public void setG(String g) {
        	this.g.set(g);
        }
        public void setH(String h) {
        	this.h.set(h);
        }
        
        public String getA() {
        	return a.get();
        }
        public String getB() {
        	return b.get();
        }
        public String getC() {
        	return c.get();
        }
        public String getD() {
        	return d.get();
        }
        public String getE() {
        	return e.get();
        }
        public String getF() {
        	return f.get();
        }
        public String getG() {
        	return g.get();
        }
        public String getH() {
        	return h.get();
        }
        public String get(int index) {
        	if(index==0)return getA();
        	else if(index==1)return getB();
        	else if(index==2)return getC();
        	else if(index==3)return getD();
        	else if(index==4)return getE();
        	else if(index==5)return getF();
        	else if(index==6)return getG();
        	else if(index==7)return getH();
        	else return null;
        }
        
	}
}
