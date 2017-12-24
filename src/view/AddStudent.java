package view;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import controller.Insert;
import controller.Search;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	private Label gradelb;
	private Label majorlb;
	private Label classlb;
	private ComboBox<Integer>gradecb;
	private ComboBox<String>majorcb;
	private ComboBox<String>classcb;
	private Label agelb;
	private TextField agetf;
	private FileInputStream in;
	
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
		idtf.setEditable(false);
		idtf.setFocusTraversable(false);
		nametf=new TextField();
		ObservableList<String>options=FXCollections.observableArrayList("男","女");
		sex=new ComboBox<String>(options);
		gradelb=new Label("年级: ");
		majorlb=new Label("分科: ");
		classlb=new Label("班级: ");
		gradecb=new ComboBox<>();
		if(Search.getGrade()!=null)
			gradecb.getItems().addAll(Search.getGrade());
		majorcb=new ComboBox<>();
		classcb=new ComboBox<>();
		gradecb.valueProperty().addListener(new ChangeListener<Integer>() {
    		//当下拉框的值改变时，设置班级下拉框的items
			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				// TODO Auto-generated method stub
				majorcb.getItems().clear();
				majorcb.getItems().addAll("文科","理科");
				classcb.getItems().clear();
			}
    		
    	});
		majorcb.valueProperty().addListener(new ChangeListener<String>() {
    		//当下拉框的值改变时，设置学生id
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(Search.getClasses(gradecb.getValue(),newValue)!=null) {
					classcb.getItems().clear();
					classcb.getItems().addAll(Search.getClasses(gradecb.getValue(),newValue));
				}
			}
    		
    	});
		classcb.valueProperty().addListener(new ChangeListener<String>() {
    		//当下拉框的值改变时，设置学生id
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if(Search.getNextStudentId(gradecb.getValue(),majorcb.getValue(),newValue)!=null) {
					idtf.setText(Search.getNextStudentId(gradecb.getValue(),majorcb.getValue(),newValue));
				}
			}
    		
    	});
		BorderPane bp=new BorderPane();
		photo=new Button("选择照片");
		bp.setBottom(photo);
		photo.setOnAction(e->{
			photochooser=new FileChooser();
			photochooser.setTitle("打开");
			photochooser.getExtensionFilters().add(
					new FileChooser.ExtensionFilter("ALL Images(*.jpg;*.png;*.gif;*.bmp)", "*.jpg;*.png;*.gif;*.bmp"));
			File file=photochooser.showOpenDialog(stage);
			try {
				in=new FileInputStream(file);
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			//System.out.println(file.toPath());
			if(file!=null) {
				try {
					String url=file.toURI().toURL().toString();
					image=new Image(url);
					imageview=new ImageView(image);
					imageview.setFitWidth(150);
					imageview.setFitHeight(200);
					bp.setCenter(imageview);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		agelb=new Label("年龄: ");
		agetf=new TextField();
		HBox agehb=new HBox();
		agehb.getChildren().addAll(agelb,agetf);
		HBox idhb=new HBox();
		idhb.getChildren().addAll(idlb,idtf);
		HBox namehb=new HBox();
		namehb.getChildren().addAll(namelb,nametf);
		HBox sexhb=new HBox();
		sexhb.getChildren().addAll(sexlb,sex);
		HBox gradehb=new HBox();
		gradehb.getChildren().addAll(gradelb,gradecb);
		HBox majorhb=new HBox();
		majorhb.getChildren().addAll(majorlb,majorcb);
		HBox classhb=new HBox();
		classhb.getChildren().addAll(classlb,classcb);
		VBox vb=new VBox();
		vb.getChildren().addAll(gradehb,majorhb,classhb,idhb,namehb,sexhb,agehb,confirm);
		vb.setSpacing(20);
		
		HBox hb=new HBox();
		hb.getChildren().addAll(vb,bp);
		hb.setSpacing(20);
		hb.setPadding(new Insets(20));
		bp.setAlignment(hb, Pos.CENTER);
		stage.setScene(new Scene(hb,500,400));
		confirm.setOnAction(e->{
			//弹出确认窗口
			//录入数据
			Integer grade=gradecb.getValue();
			String major=majorcb.getValue();
			String classes=classcb.getValue();
			String sid=idtf.getText();
			String name=nametf.getText();
			String se=sex.getValue();
			String age=agetf.getText();
			String message=Insert.addStudent(grade,major,classes,sid,name,se,age,in);
			if(message==null)
				stage.close();
			else test.show(message);
		});
		
	}
	private String getSid() {
		return null;
	}
	
	
	
	public static void show() {
		af=new AddStudent();
		stage.show();
	}
}
