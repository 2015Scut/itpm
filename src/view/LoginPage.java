package view;

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
  
/** 
 * 登录页面
 * @author 钟恩俊
 * 
 */  
public class LoginPage extends Application {  
    
      
    private GridPane grid;  
    public static void main(String[] args) {  
       launch(args );  
    }  
      
    @Override  
    public void start(final Stage primaryStage) throws Exception {  
    	
        //���ڱ���  
        primaryStage.setTitle("登录");  
        //������弰����  
        grid = new GridPane();  
        grid.setAlignment(Pos.CENTER);  
        grid.setHgap(10);  
        grid.setVgap(10);  
        grid.setPadding(new Insets(25));  
        //�ı���  
        Text scenetitle = new Text("Welcome");  
        scenetitle.setId("welcome-text");  
        grid.add(scenetitle, 0, 0, 2, 1);  
        //��ǩ  
        Label userName = new Label("账号");  
        grid.add(userName, 0, 1);  
        //�ı������  
        final TextField userTextField = new TextField();  
        grid.add(userTextField, 1, 1);  
          
        Label passwd = new Label("密码");  
        grid.add(passwd, 0, 2);  
        //���������  
        final PasswordField passwdField = new PasswordField();  
        grid.add(passwdField, 1, 2);  
        //��ť����ť����  
        Button btn = new Button("登录");  
        HBox hbBtn = new HBox(10);  
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);  
        hbBtn.getChildren().add(btn);  
        grid.add(hbBtn, 1, 4);  
          
        
        final Text actiontarget = new Text();  
        actiontarget.setId("actiontarget");  
        grid.add(actiontarget, 1, 6);  
  

        btn.setOnAction(new EventHandler<ActionEvent>() {  
            @Override  
            public void handle(ActionEvent arg0) {  
                actiontarget.setText("��½�ɹ�");  
                grid.setVisible(false);  
                //������  
                StudentTable tp = new StudentTable();  
                //�ӵ�������  
                Scene tpScene = new Scene(tp,500,500);  
                //�л���̨����Ϊ������  
                primaryStage.setScene(tpScene);  
                  
            /*  //��ȡ�û����������ֵ 
                String userName = userTextField.getText(); 
                String passWord = passwdField.getText(); 
                //�������Ӳ�ѯ���ݿ� 
                conn = JdbcUtil.getConn(); 
                String sql = "select t.*, t.rowid from itcsys_user t where t.usercode='"+userName+"' and t.username='"+passWord+"'"; 
                try { 
                    ps = conn.prepareStatement(sql); 
                    rs = ps.executeQuery(); 
                    if(rs.next()){ 
                        actiontarget.setText("��½�ɹ�"); 
                        grid.setVisible(false); 
                        //������ 
                        TablePane tp = new TablePane(); 
                        //�ӵ������� 
                        Scene tpScene = new Scene(tp,500,500); 
                        //�л���̨����Ϊ������ 
                        primaryStage.setScene(tpScene); 
                    }else{ 
                        actiontarget.setText("��½ʧ��"); 
                    } 
                } catch (SQLException e) { 
                    e.printStackTrace(); 
                }finally{ 
                    JdbcUtil.close(rs,ps,conn); 
                }*/  
            }  
        });  
        Scene scene = new Scene(grid,300,275);  
        //Ϊ��������CSS��ʽ  
        primaryStage.setScene(scene);  
        primaryStage.show();  
    }  
      
}  
