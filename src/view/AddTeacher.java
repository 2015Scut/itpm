package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddTeacher {
	private static AddTeacher at;
	private static Stage stage;
	private Label namelb;
	private Label idlb;
	private Label departlb;
	private ComboBox<String> departcb;
	private TextField idtf;
	private TextField nametf;
	private Label sexlb;
	private ComboBox<String>sex;
	private Button confirm;
	
	private AddTeacher() {
		stage=new Stage();
		stage.setResizable(false);
		stage.setTitle("新建老师");
		stage.initModality(Modality.APPLICATION_MODAL);
		namelb=new Label("姓名: ");
		nametf=new TextField();
		idlb=new Label("工号: ");
		idtf=new TextField();
		idtf.setEditable(false);//id通过查询数据库获取自动分配
		departlb=new Label("学院: ");
		departcb=new ComboBox<>();
		sexlb=new Label("性别: ");
		ObservableList<String>options=FXCollections.observableArrayList("男","女");
		sex=new ComboBox<>(options);
		confirm=new Button("确定");
		
		HBox idhb=new HBox();
		idhb.getChildren().addAll(idlb,idtf);
		HBox namehb=new HBox();
		namehb.getChildren().addAll(namelb,nametf);
		HBox sexhb=new HBox();
		sexhb.getChildren().addAll(sexlb,sex);
		HBox departhb=new HBox();
		departhb.getChildren().addAll(departlb,departcb);
		VBox vb=new VBox();
		vb.getChildren().addAll(departhb,idhb,namehb,sexhb,confirm);
		vb.setSpacing(20);
		stage.setScene(new Scene(vb,500,250));
		confirm.setOnAction(e->{
			//弹出确认窗口
			//录入数据
			stage.close();
		});
		
	}
	
	public static void show() {
		at=new AddTeacher();
		stage.show();
	}
}
