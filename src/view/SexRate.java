package view;

import javafx.collections.*;
import javafx.scene.chart.*;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class SexRate extends VBox{
	private Label departlb;
	private Label majorlb;
	private Label classlb;
	private ComboBox<String> departcb;
	private ComboBox<String> majorcb;
	private ComboBox<String> classcb;
	private PieChart pc;
	
	public SexRate() {
		departlb=new Label("学院: ");
    	majorlb=new Label("专业: ");
    	classlb=new Label("班级: ");
    	departcb=new ComboBox<>();
    	majorcb=new ComboBox<>();
    	classcb=new ComboBox<>();
    	HBox hb=new HBox();
    	hb.getChildren().addAll(departlb,departcb,majorlb,majorcb,classlb,classcb);
    	hb.setSpacing(10);
    	
    	pc=new PieChart();
    	ObservableList<Data> d = FXCollections.observableArrayList();
        d.addAll(new PieChart.Data("女", 6),
                new PieChart.Data("男", 40));
        pc.setData(d);
        
        this.getChildren().addAll(hb,pc);
	}
	
	
}
