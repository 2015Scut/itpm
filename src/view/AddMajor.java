package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddMajor {
	private static AddMajor am;
	private static Stage stage;
	private Label namelb;
	private Label idlb;
	private Label gradelb;
	private ComboBox<String> gradecb;
	private TextField idtf;
	private ComboBox<String> namecb;
	private Button confirm;
	
	private AddMajor() {
		stage=new Stage();
		stage.setResizable(false);
		stage.setTitle("新建");
		stage.initModality(Modality.APPLICATION_MODAL);
		namelb=new Label("文理科: ");
		namecb=new ComboBox<>();
		idlb=new Label("id: ");
		idtf=new TextField();
		idtf.setEditable(false);//id通过查询数据库获取自动分配
		gradelb=new Label("年级: ");
		gradecb=new ComboBox<>();
		confirm=new Button("确定");
		
		HBox idhb=new HBox();
		idhb.getChildren().addAll(idlb,idtf);
		HBox namehb=new HBox();
		namehb.getChildren().addAll(namelb,namecb);
		HBox departhb=new HBox();
		departhb.getChildren().addAll(gradelb,gradecb);
		VBox vb=new VBox();
		vb.getChildren().addAll(departhb,idhb,namehb,confirm);
		vb.setSpacing(20);
		stage.setScene(new Scene(vb,500,250));
		confirm.setOnAction(e->{
			//弹出确认窗口
			//录入数据
			stage.close();
		});
	}
	
	public static void show() {
		am=new AddMajor();
		stage.show();
	}
}
