package view;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NumberOfClass extends VBox {
	private Label gradelb;
	private Label majorlb;
	private ComboBox<String> gradecb;
	private ComboBox<String> majorcb;
    
    public NumberOfClass() {
    	gradelb=new Label("年级: ");
    	majorlb=new Label("分科: ");
    	gradecb=new ComboBox<>();
    	majorcb=new ComboBox<>();
    	HBox hb=new HBox();
    	hb.getChildren().addAll(gradelb,gradecb,majorlb,majorcb);
    	hb.setSpacing(10);
    	
        final NumberAxis yAxis = new NumberAxis();
        final CategoryAxis xAxis = new CategoryAxis();
        final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
        bc.setTitle("班级人数");
        xAxis.setLabel("班级");
        yAxis.setLabel("人数");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("人数");
        series1.getData().add(new XYChart.Data("一班",48));
        series1.getData().add(new XYChart.Data("二班", 50));
        series1.getData().add(new XYChart.Data("三班", 45));
        series1.getData().add(new XYChart.Data("四班", 47));
        series1.getData().add(new XYChart.Data("五班", 50));
        series1.getData().add(new XYChart.Data("六班", 45));

        Scene scene = new Scene(bc, 1000, 700);
        bc.getData().addAll(series1);
        bc.prefHeightProperty().bind(this.heightProperty());
        
        this.getChildren().addAll(hb,bc);
    }

}