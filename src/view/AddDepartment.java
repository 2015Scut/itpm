package view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddDepartment {
	private static AddDepartment ad;
	private static Stage stage;
	private Label namelb;
	private TextField nametf;
	private Button confirm;
	
	private AddDepartment() {
		stage=new Stage();
		stage.setResizable(false);
		stage.setTitle("新建学院");
		stage.initModality(Modality.APPLICATION_MODAL);
		namelb=new Label("名称: ");
		nametf=new TextField();
		confirm=new Button("确定");
		
		HBox namehb=new HBox();
		namehb.getChildren().addAll(namelb,nametf);
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
		ad=new AddDepartment();
		stage.show();
	}
	
	private boolean insert() {
		return true;
	}
}
