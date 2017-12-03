package view;
	

import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Users;

/**
 * 目录页面
 * @author 钟恩俊
 *
 */
public class IndexPage extends Application {
	private StackPane border;
	private VBox vbox;
	private MenuBar mb;
	private LeftBox men;
	private Separator separator;
	private HBox hbox;
	private SearchPage search;
	private StatisticsPage form;
	private Group g;
	private Users user;
	@Override
	public void start(Stage primaryStage) {
		System.out.println("欢迎  "+user.getTeacherId());
		separator=new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		separator.setPrefHeight(650);
		separator.setValignment(VPos.CENTER);
		separator.setPadding(new Insets(0,10,0,50));
		
		hbox=new HBox();
		
		
		border = new StackPane();
		
		vbox=new VBox();
		
		mb=new MenuBar();
		Menu menuInsert = new Menu("录入");
        Menu menuEdit = new Menu("管理");
        Menu menuView = new Menu("View");
 
        mb.getMenus().addAll(menuInsert, menuEdit, menuView);
		men=new LeftBox();
		men.setPrefWidth(150);
		
		MenuItem studentItem=new MenuItem("录入学生信息");
		menuInsert.getItems().add(studentItem);
		studentItem.setOnAction(e->{
			AddStudent.show();
		});
		
		MenuItem departItem=new MenuItem("录入年级信息");
		menuInsert.getItems().add(departItem);
		departItem.setOnAction(e->{
			AddGrade.show();
		});
		MenuItem majorItem=new MenuItem("录入文理科信息");
		menuInsert.getItems().add(majorItem);
		majorItem.setOnAction(e->{
			AddMajor.show();
		});
		MenuItem classesItem=new MenuItem("录入班级信息");
		menuInsert.getItems().add(classesItem);
		classesItem.setOnAction(e->{
			AddClasses.show();
		});
		MenuItem teacherItem=new MenuItem("录入教师信息");
		menuInsert.getItems().add(teacherItem);
		teacherItem.setOnAction(e->{
			AddTeacher.show();
		});
		
		MenuItem userItem=new MenuItem("用户管理");
		//MenuItem test=new MenuItem("test", new ImageView(new Image("menusample/new.png")));给菜单加图标
		menuEdit.getItems().add(userItem);
		userItem.setOnAction(e->{
			
		});
		
		hbox.getChildren().addAll(men,border);
		hbox.getChildren().add(1,separator);
		
		vbox.getChildren().addAll(mb,hbox);
		
		
		Scene scene=new Scene(vbox,1200,700);
		mb.prefWidthProperty().bind(scene.widthProperty());
		border.setPrefWidth(1000);
		
		men.getBt1().setOnAction((ActionEvent e)->{
			search=new SearchPage();
			border.getChildren().clear();
			border.getChildren().add(search);
			primaryStage.setScene(scene);
		});
		men.getBt2().setOnAction((ActionEvent e)->{
			ClassTable ct = new ClassTable();
			border.getChildren().clear();
			border.getChildren().add(ct);
			primaryStage.setScene(scene);
		});
		men.getBt3().setOnAction((ActionEvent e)->{
			SeatPage st=new SeatPage();
			border.getChildren().clear();
			border.getChildren().add(st);
			primaryStage.setScene(scene);
		});
		men.getBt4().setOnAction((ActionEvent e)->{
			form=new StatisticsPage();
			border.getChildren().clear();
			border.getChildren().add(form);
			primaryStage.setScene(scene);
		});
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("学生信息查询系统");
		//primaryStage.getIcons().add(null);图标
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	public void setUser(Users user) {
		this.user=user;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
