package view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PictureView {
	private static PictureView pv;
	
	private static Stage primaryStage;
	
	private PictureView(Image im) {
		VBox vb=new VBox();
		ImageView imageView = new ImageView(im);
		imageView.setFitWidth(300);
		imageView.setFitHeight(300);
		vb.getChildren().add(imageView);
		
		primaryStage = new Stage();
        primaryStage.setResizable(false);      
        primaryStage.setTitle("相册");
        primaryStage.setScene(new Scene(vb,300,300));
        primaryStage.setAlwaysOnTop(true);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
	}
	
	public static void show(Image im) {
		pv=new PictureView(im);
		if(primaryStage != null)
            primaryStage.showAndWait(); 
	}
}
