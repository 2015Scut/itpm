package view;


import controller.Insert;
import controller.Search;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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
	private Label gradelb;
	private ComboBox<Integer> gradecb;
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
		idtf=new TextField(Search.getNextTeacherId());
		idtf.setEditable(false);//id通过查询数据库获取自动分配
		gradelb=new Label("年级: ");
		gradecb=new ComboBox<>();
		if(Search.getGrade()!=null)
			gradecb.getItems().addAll(Search.getGrade());
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
		HBox gradehb=new HBox();
		gradehb.getChildren().addAll(gradelb,gradecb);
		VBox vb=new VBox();
		vb.getChildren().addAll(gradehb,idhb,namehb,sexhb,confirm);
		vb.setSpacing(20);
		vb.setPadding(new Insets(20));
		stage.setScene(new Scene(vb,500,250));
		confirm.setOnAction(e->{
			//弹出确认窗口
			//录入数据
			Integer g=gradecb.getValue();
			String tid=idtf.getText();
			String name=nametf.getText();
			String s=sex.getValue();
			String message=Insert.addTeacher(g, tid, name, s);
			if(message==null)
				stage.close();
			else test.show(message);
		});
		
	}
	
	public static void show() {
		at=new AddTeacher();
		stage.show();
	}
}
