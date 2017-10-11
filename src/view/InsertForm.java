package view;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InsertForm {
	private static InsertForm f;
	
	private static Stage primaryStage;
	
	private InsertForm() {
		VBox vb=new VBox();
		primaryStage = new Stage();
        primaryStage.setResizable(false);      
        primaryStage.setTitle("录入信息");
        primaryStage.setScene(new Scene(vb,300,275));
        primaryStage.setAlwaysOnTop(true);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
	}
	
	public static void show() {
		f=new InsertForm();
		if(primaryStage != null)
            primaryStage.showAndWait(); 
	}
}
