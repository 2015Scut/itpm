package view;
	
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
	@Override
	public void start(Stage primaryStage) {
		
		separator=new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		separator.setPrefHeight(650);
		separator.setValignment(VPos.CENTER);
		separator.setPadding(new Insets(0,10,0,50));
		
		hbox=new HBox();
		
		
		border = new StackPane();
		
		vbox=new VBox();
		
		mb=new MenuBar();
		Menu menuFile = new Menu("菜单");
        Menu menuEdit = new Menu("Edit");
        Menu menuView = new Menu("View");
 
        mb.getMenus().addAll(menuFile, menuEdit, menuView);
		men=new LeftBox();
		men.setPrefWidth(150);
		
		MenuItem depart=new MenuItem("录入年级信息");
		menuFile.getItems().add(depart);
		depart.setOnAction(e->{
			AddGrade.show();
		});
		MenuItem major=new MenuItem("录入文理科信息");
		menuFile.getItems().add(major);
		major.setOnAction(e->{
			AddMajor.show();
		});
		MenuItem classes=new MenuItem("录入班级信息");
		menuFile.getItems().add(classes);
		classes.setOnAction(e->{
			AddClasses.show();
		});
		MenuItem teacher=new MenuItem("录入教师信息");
		menuFile.getItems().add(teacher);
		teacher.setOnAction(e->{
			AddTeacher.show();
		});
		
		hbox.getChildren().addAll(men,border);
		hbox.getChildren().add(1,separator);
		
		vbox.getChildren().addAll(mb,hbox);
		
		//g=new Group();
		//g.getChildren().add(vbox);
		
		Scene scene=new Scene(vbox,1200,700);
		mb.prefWidthProperty().bind(scene.widthProperty());
		border.setPrefWidth(1000);
		//hbox.prefHeightProperty().bind(scene.heightProperty());
		//hbox.prefWidthProperty().bind(scene.widthProperty());
		
		men.getBt1().setOnAction((ActionEvent e)->{
			System.out.println("button1 click");
			search=new SearchPage();
			border.getChildren().clear();
			border.getChildren().add(search);
			primaryStage.setScene(scene);
		});
		men.getBt2().setOnAction((ActionEvent e)->{
			System.out.println("button2 click");
			TablePane tp = new TablePane();
			border.getChildren().clear();
			border.getChildren().add(tp);
			primaryStage.setScene(scene);
		});
		men.getBt3().setOnAction((ActionEvent e)->{
			System.out.println("button3 click");
			SeatPage st=new SeatPage();
			border.getChildren().clear();
			border.getChildren().add(st);
			primaryStage.setScene(scene);
		});
		men.getBt4().setOnAction((ActionEvent e)->{
			System.out.println("button4 click");
			form=new StatisticsPage();
			border.getChildren().clear();
			border.getChildren().add(form);
			primaryStage.setScene(scene);
		});
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("学生信息查询系统");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
