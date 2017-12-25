package view;


import java.util.ArrayList;

import controller.Insert;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality; 
import javafx.stage.Stage;

public class AddGrade {
	private static AddGrade ad;
	private static Stage stage;
	private Label gradelb;
	private ComboBox<Integer> gradecb;//下拉框
	private Button confirm;
	
	private AddGrade() {
		stage=new Stage();
		stage.setResizable(false);
		stage.setTitle("新建年级");
		stage.initModality(Modality.APPLICATION_MODAL);
		gradelb=new Label("年级: ");
		gradecb=new ComboBox<>();
		confirm=new Button("确定");
		ArrayList<Integer> grade=new ArrayList();
		for(int i=0;i<6;i++)
			grade.add(2016+i);
		gradecb.getItems().addAll(grade);
		
		HBox namehb=new HBox();
		namehb.getChildren().addAll(gradelb,gradecb);
		VBox vb=new VBox();
		vb.getChildren().addAll(namehb,confirm);
		vb.setSpacing(20);
		vb.setPadding(new Insets(25));
		namehb.setAlignment(Pos.CENTER);
		confirm.setAlignment(Pos.CENTER_RIGHT);
		vb.setAlignment(Pos.CENTER);
		stage.setScene(new Scene(vb,500,200));
		confirm.setOnAction(e->{
			//弹出确认窗口
			//录入数据
			Integer g=gradecb.getValue();
			if(g==null)test.show("信息不能为空");
			else {
				String message=Insert.addGrade(g);
				if(message==null)
					stage.close();
				else
					test.show(message);
			}
		});
	}
	
	public static void show() {
		ad=new AddGrade();
		stage.show();
	}
	
	
}
