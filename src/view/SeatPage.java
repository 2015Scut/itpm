package view;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;  
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
	private ObservableList<row> data;
	private Button photo;
	private Button sbt;
	
	public SeatPage() {
		gradelb=new Label("年级: ");
    	majorlb=new Label("分科: ");
    	classlb=new Label("班级: ");
    	gradecb=new ComboBox<>();
    	majorcb=new ComboBox<>();
    	classcb=new ComboBox<>();
    	photo=new Button("显示照片");
    	sbt=new Button("搜索");
    	HBox hb=new HBox();
    	hb.getChildren().addAll(gradelb,gradecb,majorlb,majorcb,classlb,classcb,photo,sbt);
    	hb.setSpacing(10);
    	data=FXCollections.observableArrayList(new row("","","","讲","台","","",""));
    	
		tv=new TableView<>();
		for(int i=0;i<8;i++) {
			TableColumn tc=new TableColumn(i+1+"");
			
			tc.setMinWidth(124);
			tc.setCellValueFactory(new PropertyValueFactory<row,String>(String.valueOf((char)(i+97))));
			/*tc.setCellFactory(TextFieldTableCell.<row>forTableColumn());
			tc.setOnEditCommit(
					(CellEditEvent<row, String> t)->{
				((row)t.getTableView().getItems().get(
						t.getTablePosition().getRow())
						).set(i+1, t.getNewValue());
			});*/
			tv.getColumns().add(tc);
			
		}
		photo.setOnAction(e->{
    		System.out.println(tv.getFocusModel().getFocusedCell().getColumn());
    		
    	});
		tv.setId("seattable");
		
		this.getStylesheets().add(getClass().getResource("seattable.css").toExternalForm());
		tv.setEditable(true);
		tv.setItems(data);
		tv.setPrefHeight(592);
		tv.setMaxWidth(994);
		this.getChildren().addAll(hb,tv);
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
        
	}
}
