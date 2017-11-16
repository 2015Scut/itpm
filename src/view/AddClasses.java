package view;


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
	private ComboBox<String> gradecb;
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
		majorlb=new Label("文理科: ");
		majorcb=new ComboBox<>();
		teacherlb=new Label("班主任");
		teachercb=new ComboBox<>();
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
		stage.setScene(new Scene(vb,500,300));
		confirm.setOnAction(e->{
			//弹出确认窗口
			//录入数据
			stage.close();
		});
	}
	
	public static void show() {
		ac=new AddClasses();
		stage.show();
	}
}
