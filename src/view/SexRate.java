package view;


import javafx.collections.*;
import javafx.scene.chart.*;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class SexRate extends VBox{
	private Label gradelb;
	private Label majorlb;
	private Label classlb;
	private ComboBox<String> gradecb;
	private ComboBox<String> majorcb;
	private ComboBox<String> classcb;
	private PieChart pc;
	
	public SexRate() {
		gradelb=new Label("年级: ");
    	majorlb=new Label("分科: ");
    	classlb=new Label("班级: ");
    	gradecb=new ComboBox<>();
    	majorcb=new ComboBox<>();
    	classcb=new ComboBox<>();
    	HBox hb=new HBox();
    	hb.getChildren().addAll(gradelb,gradecb,majorlb,majorcb,classlb,classcb);
    	hb.setSpacing(10);
    	
    	pc=new PieChart();
    	ObservableList<Data> d = FXCollections.observableArrayList();
        d.addAll(new PieChart.Data("女", 6),
                new PieChart.Data("男", 40));
        pc.setData(d);
        
        this.getChildren().addAll(hb,pc);
	}
	
	
}
