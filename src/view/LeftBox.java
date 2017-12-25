package view;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
/**
 * 左侧导航栏
 * @author 钟恩俊
 *
 */
public class LeftBox extends VBox{
	public Button getBt1() {
		return bt1;
	}
	public Button getBt2() {
		return bt2; 
	}
	public Button getBt3() {
		return bt3;
	}
	public Button getBt4() {
		return bt4;
	}
	private Button bt1=new Button("基本情况查询");
	private Button bt2=new Button("班级浏览");
	private Button bt3=new Button("座位表");
	private Button bt4=new Button("统计报表");
	public LeftBox() {
		this.setSpacing(40);
		this.setPadding(new Insets(15, 12, 15, 12));
		bt1.setMaxWidth(Double.MAX_VALUE);
		bt2.setMaxWidth(Double.MAX_VALUE);
		bt3.setMaxWidth(Double.MAX_VALUE);
		bt4.setMaxWidth(Double.MAX_VALUE);
		this.getChildren().addAll(bt1);
		this.getChildren().addAll(bt2);
		this.getChildren().addAll(bt3);
		this.getChildren().addAll(bt4);
		
	}
	
}
