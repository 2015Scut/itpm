package view;

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
	private ComboBox<String> gradecb;
	private Button confirm;
	
	private AddGrade() {
		stage=new Stage();
		stage.setResizable(false);
		stage.setTitle("新建年级");
		stage.initModality(Modality.APPLICATION_MODAL);
		gradelb=new Label("年级: ");
		gradecb=new ComboBox<>();
		confirm=new Button("确定");
		
		HBox namehb=new HBox();
		namehb.getChildren().addAll(gradelb,gradecb);
		VBox vb=new VBox();
		vb.getChildren().addAll(namehb,confirm);
		vb.setSpacing(20);
		stage.setScene(new Scene(vb,500,200));
		confirm.setOnAction(e->{
			//弹出确认窗口
			//录入数据
			insert();
			stage.close();
		});
	}
	
	public static void show() {
		ad=new AddGrade();
		stage.show();
	}
	
	private boolean insert() {
		return true;
	}
}
