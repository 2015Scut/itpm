package view;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class test {
	private static test t;
	
	private static Stage primaryStage;
	
	private static int ret; 
	
	private test(String message) {
		ret=0;
		Button t1=new Button("确定");
		Button t2=new Button("取消");
		Label lb=new Label(message);
		HBox hb=new HBox();
		hb.getChildren().addAll(t1,t2);
		hb.setSpacing(20);
		hb.setAlignment(Pos.CENTER);
		VBox vb=new VBox();
		vb.getChildren().addAll(lb,hb);
		vb.setSpacing(20);
		vb.setAlignment(Pos.CENTER);
		primaryStage = new Stage();
        primaryStage.setResizable(false);      
        primaryStage.setTitle("警告");
        primaryStage.setScene(new Scene(vb,300,150));
        primaryStage.setAlwaysOnTop(true);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        t1.setOnAction(e->{
        	ret=1;
        	primaryStage.close();
        });
        t2.setOnAction(e->{
        	primaryStage.close();
        });
	}
	
	public static void show(String message) {
		t=new test(message);
		if(primaryStage != null)
            primaryStage.showAndWait(); 
	}
	public static int getRet() {
		return ret;
	}
}
