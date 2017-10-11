package view;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Department;
/**
 * 搜索页面
 * @author 钟恩俊
 *
 */
public class SearchPage extends BorderPane{
	private TablePane tb;
	private Button sbt;
	private Button modify;
	private Button add;
	private Button delete;
	private TextField idtf;
	private TextField nametf;
	private Label idlb;
	private Label namelb;
	private Pagination pg;
	private Label departlb;
	private Label majorlb;
	private Label classlb;
	private ComboBox<String> departcb;
	private ComboBox<String> majorcb;
	private ComboBox<String> classcb;
	
	public SearchPage() {
		
		tb=new TablePane();
		departlb=new Label("学院: ");
    	majorlb=new Label("专业: ");
    	classlb=new Label("班级: ");
    	
    	//ObservableList<String> departments = FXCollections.observableArrayList(getDepartmentList());
    	departcb=new ComboBox<>();
    	String[] departments=getDepartmentList();
    	departcb.getItems().addAll(departments);
    	majorcb=new ComboBox<>();
    	classcb=new ComboBox<>();
    	departcb.valueProperty().addListener(new ChangeListener<String>() {
    		//当学院下拉框的值改变时，设置专业下拉框的items
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				String[] majors = getMajorList(newValue);
				majorcb.getItems().clear();
				majorcb.getItems().addAll(majors);
			}
    		
    	});
    	majorcb.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				String[] classes = getClassList(newValue);
				classcb.getItems().clear();
				classcb.getItems().addAll(classes);
			}
    		
    	});
		sbt=new Button("搜索");
		modify=new Button("修改");
		add=new Button("增加");
		delete=new Button("删除");
		idtf=new TextField();
		nametf=new TextField();
		namelb=new Label("姓名: ");
		idlb=new Label("学号: ");
		pg=new Pagination(10,0);
		pg.setPageFactory((Integer pageIndex) -> {
            if (pageIndex >= 11) {
                return null;
            } else {
                return createPage(pageIndex);
            }
        });
		
		sbt.setOnAction(e->{
			//搜索
		});
		
		modify.setOnAction(e->{
			test.show("没有权限");
		});
		add.setOnAction(e->{
			AddForm.show();
		});
		
		HBox hb=new HBox();
    	hb.getChildren().addAll(idlb,idtf,namelb,nametf,departlb,departcb,majorlb,majorcb,classlb,classcb,sbt);
		hb.setSpacing(10);
		this.setTop(hb);
		this.setCenter(tb);
		VBox vb=new VBox();
		HBox buttonBox=new HBox();
		buttonBox.getChildren().addAll(modify,add,delete);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(20);
		vb.getChildren().addAll(pg,buttonBox);
		this.setBottom(vb);
	}
	/**
	 * 根据学院获取专业列表
	 * @param departName 学院名
	 * @return 专业名的数组
	 */
	private String[] getMajorList(String departName){
		return null;
	}
	private String[] getClassList(String majorName){
		return null;
	}
	private String[] getDepartmentList(){
		String[] ml=new String[1];
		ml[0]="软件学院";
		return ml;
	}
	private VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        return box;
    }
}
