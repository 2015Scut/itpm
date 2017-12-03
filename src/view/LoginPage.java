package view;


import controller.Login;
import javafx.application.Application;  
import javafx.event.ActionEvent;  
import javafx.event.EventHandler;  
import javafx.geometry.Insets;  
import javafx.geometry.Pos;  
import javafx.scene.Scene;  
import javafx.scene.control.Button;  
import javafx.scene.control.Label;  
import javafx.scene.control.PasswordField;  
import javafx.scene.control.TextField;  
import javafx.scene.layout.GridPane;  
import javafx.scene.layout.HBox;  
import javafx.scene.text.Text;  
import javafx.stage.Stage;
import model.Users;  
  
/** 
 * 登录界面
 * @author 钟恩俊
 * 
 */  
public class LoginPage extends Application {  
    
      
    private GridPane grid;  
    private static Stage stage;
    public static void main(String[] args) {  
       launch(args );  
    }  
      
    @Override  
    public void start(Stage primaryStage) throws Exception {  
    	
        primaryStage.setTitle("登录");  
        grid = new GridPane();  
        grid.setAlignment(Pos.CENTER);  
        grid.setHgap(10);  
        grid.setVgap(10);  
        grid.setPadding(new Insets(25));  
        Text scenetitle = new Text("Welcome");  
        scenetitle.setId("welcome-text");  
        grid.add(scenetitle, 0, 0, 2, 1);  
        Label userName = new Label("账号");  
        grid.add(userName, 0, 1);  
        final TextField userTextField = new TextField();  
        grid.add(userTextField, 1, 1);  
          
        Label passwd = new Label("密码");  
        grid.add(passwd, 0, 2);  
        final PasswordField passwdField = new PasswordField();  
        grid.add(passwdField, 1, 2);  
        Button btn = new Button("登录");  
        Button registerbtn=new Button("注册");
        HBox hbBtn = new HBox(10);  
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);  
        hbBtn.getChildren().addAll(registerbtn,btn);  
        grid.add(hbBtn, 1, 4);  
          
        
        final Text actiontarget = new Text();  
        actiontarget.setId("actiontarget");  
        grid.add(actiontarget, 1, 6);  
  

        registerbtn.setOnAction(e->{
            primaryStage.close();
            RegisterPage.show();
        });
        
        btn.setOnAction(e->{
        	String username=userTextField.getText();
        	String password=passwdField.getText();
        	Login login=new Login();
        	Users user=login.getUsers(username);
        	if(user==null) {
        		test.show("账号不存在");
        	}else if(!user.getPassword().equals(password)) {
        		test.show("密码错误");
        	}else {
        		primaryStage.close();
        		IndexPage index=new IndexPage();
        		index.setUser(user);
        		index.start(new Stage());
        	}
        });  
        Scene scene = new Scene(grid,300,275);  
        primaryStage.setScene(scene);  
        primaryStage.show();  
    }  
      
}  
