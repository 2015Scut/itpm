package view;
import controller.*;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.image.*;
import javafx.collections.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddStudent {
	private static AddStudent af;
	private static Stage stage;
	private Label idlb;
	private Label namelb;
	private Label sexlb;
	private TextField idtf;
	private TextField nametf;
	public ComboBox<String>sex;
	private Button confirm;
	private FileChooser photochooser;
	private Button photo;
	private Image image;
	private ImageView imageview;
	
	private AddStudent() {
		stage=new Stage();
		//stage.setResizable(false);
		stage.setTitle("增加学生");
		stage.initModality(Modality.APPLICATION_MODAL);
		idlb=new Label("学号: ");
		namelb=new Label("姓名: ");
		sexlb=new Label("性别: ");
		confirm=new Button("确定");
		idtf=new TextField();
		nametf=new TextField();
		ObservableList<String>options=FXCollections.observableArrayList("男","女");
		sex=new ComboBox<String>(options);
		BorderPane bp=new BorderPane();
		photo=new Button("选择照片");
		bp.setBottom(photo);
		photo.setOnAction(e->{
			photochooser=new FileChooser();
			photochooser.setTitle("打开");
			photochooser.getExtensionFilters().add(
					new FileChooser.ExtensionFilter("ALL Images(*.jpg;*.png;*.gif;*.bmp)", "*.jpg;*.png;*.gif;*.bmp"));
			File file=photochooser.showOpenDialog(stage);
			//System.out.println(file.toPath());
			if(file!=null) {
				try {
					String url=file.toURI().toURL().toString();
					image=new Image(url);
					imageview=new ImageView(image);
					imageview.setFitWidth(100);
					imageview.setFitHeight(100);
					bp.setCenter(imageview);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		HBox idhb=new HBox();
		idhb.getChildren().addAll(idlb,idtf);
		HBox namehb=new HBox();
		namehb.getChildren().addAll(namelb,nametf);
		HBox sexhb=new HBox();
		sexhb.getChildren().addAll(sexlb,sex);
		VBox vb=new VBox();
		vb.getChildren().addAll(idhb,namehb,sexhb,confirm);
		vb.setSpacing(20);
		HBox hb=new HBox();
		hb.getChildren().addAll(vb,bp);
		hb.setSpacing(20);
		stage.setScene(new Scene(hb,500,200));
		confirm.setOnAction(e->{
			//弹出确认窗口
			//录入数据
			Insert is=new Insert();
			is.insertStudent(this);
			stage.close();
		});
		
	}
	
	public static void show() {
		af=new AddStudent();
		stage.show();
	}
}
