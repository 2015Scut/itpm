package view;


import controller.Insert;
import controller.Search;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddClasses {
	private static AddClasses ac;
	private static Stage stage;
	private Label namelb;
	private Label idlb;
	private Label gradelb;
	private ComboBox<Integer> gradecb;
	private TextField idtf;
	private TextField nametf;
	private Label majorlb;
	private ComboBox<String> majorcb;
	private Label teacherlb;
	private ComboBox<String> teachercb;
	private Button confirm;
	
	private AddClasses() {
		stage=new Stage();
		stage.setResizable(false);
		stage.setTitle("新建班级");
		stage.initModality(Modality.APPLICATION_MODAL);
		namelb=new Label("名称: ");
		nametf=new TextField();
		idlb=new Label("id: ");
		idtf=new TextField();
		idtf.setEditable(false);//id通过查询数据库获取自动分配
		gradelb=new Label("年级: ");
		gradecb=new ComboBox<>();
		if(Search.getGrade()!=null)
			gradecb.getItems().addAll(Search.getGrade());
		majorlb=new Label("文理科: ");
		majorcb=new ComboBox<>();
		gradecb.valueProperty().addListener(new ChangeListener<Integer>() {
    		//当下拉框的值改变时，设置班级下拉框的items
			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				// TODO Auto-generated method stub
				majorcb.getItems().clear();
				majorcb.getItems().addAll("文科","理科");
			}
    		
    	});
		teacherlb=new Label("班主任: ");
		teachercb=new ComboBox<>();
		if(Search.getTeacher(gradecb.getValue())!=null)
			teachercb.getItems().addAll(Search.getTeacher(gradecb.getValue()));
		confirm=new Button("确定");
		
		HBox idhb=new HBox();
		idhb.getChildren().addAll(idlb,idtf);
		HBox namehb=new HBox();
		namehb.getChildren().addAll(namelb,nametf);
		HBox departhb=new HBox();
		departhb.getChildren().addAll(gradelb,gradecb);
		HBox majorhb=new HBox();
		majorhb.getChildren().addAll(majorlb,majorcb);
		HBox teacherhb=new HBox();
		teacherhb.getChildren().addAll(teacherlb,teachercb);
		VBox vb=new VBox();
		vb.getChildren().addAll(departhb,majorhb,idhb,namehb,teacherhb,confirm);
		vb.setSpacing(20);
		vb.setPadding(new Insets(20));
		stage.setScene(new Scene(vb,500,300));
		confirm.setOnAction(e->{
			//弹出确认窗口
			//录入数据
			Integer g=gradecb.getValue();
			String m=majorcb.getValue();
			String cid=idtf.getText();
			String name=nametf.getText();
			String t=teachercb.getValue();
			String message=Insert.addClass(g, m, cid, name, t);
			if(message==null)
				stage.close();
			else test.show(message);
		});
	}
	
	public static void show() {
		ac=new AddClasses();
		stage.show();
	}
}
