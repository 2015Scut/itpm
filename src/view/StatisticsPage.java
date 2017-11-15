package view;

import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
/**
 * 统计报表页面
 * @author 钟恩俊
 *
 */
public class StatisticsPage extends VBox{
	private StudentTable tb;
	private NumberOfClass noc;
	private TabPane tp;
	private Tab NumberOfClasst;
	private Tab Sext;
	private Tab aget;
	private SexRate sr;
	
	public StatisticsPage() {
		noc=new NumberOfClass();
		tp=new TabPane();
		NumberOfClasst=new Tab("班级人数");
		NumberOfClasst.setClosable(false);
		NumberOfClasst.setContent(noc);
		Sext=new Tab("性别比例");
		Sext.setClosable(false);
		sr=new SexRate();
		Sext.setContent(sr);
		aget=new Tab("平均年龄");
		aget.setClosable(false);
		tb=new StudentTable();
		aget.setContent(tb);
		tp.getTabs().addAll(NumberOfClasst,Sext,aget);
		tp.setSide(Side.BOTTOM);
		tp.prefHeightProperty().bind(this.heightProperty());
		this.getChildren().add(tp);
	}
}
