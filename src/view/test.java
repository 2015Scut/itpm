package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class test {
	private static test t;
	
	private static Stage primaryStage;
	
	private test(String message) {
		Button t=new Button("确定");
		Label lb=new Label(message);
		VBox vb=new VBox();
		vb.getChildren().addAll(lb,t);
		vb.setSpacing(20);
		primaryStage = new Stage();
        primaryStage.setResizable(false);      
        primaryStage.setTitle("警告");
        primaryStage.setScene(new Scene(vb,300,150));
        primaryStage.setAlwaysOnTop(true);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        t.setOnAction(e->{
        	primaryStage.close();
        });
	}
	
	public static void show(String message) {
		t=new test(message);
		if(primaryStage != null)
            primaryStage.showAndWait(); 
	}
}
