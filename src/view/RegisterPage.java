package view;


import controller.Register;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RegisterPage {
	private Label tidlb;
	private Label namelb;
	private Label passlb;
	private Label passcomfirmlb;
	private TextField tidtf;
	private TextField nametf;
	private PasswordField passtf;
	private PasswordField passcomtf;
	private Button btn;
	private Label teacherlb;
	private TextField teachertf;
	private static RegisterPage rp;
	private static Stage stage;
	
	private RegisterPage() {
		stage=new Stage();
		stage.setResizable(false);
		stage.setTitle("注册");
		stage.initModality(Modality.APPLICATION_MODAL);
		tidlb=new Label("教师id: ");
		namelb=new Label("用户名: ");
		passlb=new Label("密码: ");
		passcomfirmlb=new Label("确认密码: ");
		teacherlb=new Label("姓名: ");
		teachertf=new TextField();
		tidtf=new TextField();
		nametf=new TextField();
		passtf=new PasswordField();
		passcomtf=new PasswordField();
		btn=new Button("确定");
		
		btn.setOnAction(e->{
			Register register=new Register();
			String message=register.register(nametf.getText(), passtf.getText(), tidtf.getText(), teachertf.getText(),passcomtf.getText());
			if(message!=null){
				test.show(message);
			}else {
				stage.close();
				LoginPage lp=new LoginPage();
				try { 
					lp.start(new Stage());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		VBox vb=new VBox();
		HBox idhb=new HBox();
		HBox namehb=new HBox();
		HBox passhb=new HBox();
		HBox passcomhb=new HBox();
		HBox teacherhb=new HBox();
		idhb.getChildren().addAll(tidlb,tidtf);
		namehb.getChildren().addAll(namelb,nametf);
		passhb.getChildren().addAll(passlb,passtf);
		passcomhb.getChildren().addAll(passcomfirmlb,passcomtf);
		teacherhb.getChildren().addAll(teacherlb,teachertf);
		vb.getChildren().addAll(idhb,teacherhb,namehb,passhb,passcomhb,btn);
		vb.setSpacing(20);
		stage.setScene(new Scene(vb,500,300));
	}
	
	public static void show() {
		rp=new RegisterPage();
		stage.show();
	}
	
	public static boolean isclose() {
		return !stage.isShowing();
	}

}
