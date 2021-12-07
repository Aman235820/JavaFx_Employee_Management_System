package sample;

import Connection.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {

    @FXML
    private Button log_in;

    @FXML
    private TextField user;

    @FXML
    private PasswordField pass;

    @FXML
    private Label err;

    @FXML
    public void loggedin(ActionEvent actionEvent) throws SQLException {


                 if(user.getText().isBlank()==false || pass.getText().isBlank()==false){
                     ConnectionClass connectionClass = new ConnectionClass();
                     Connection connection = connectionClass.getConnection();

                     String sql = "select count(1) from emp_data where email = '" + user.getText() + "' and password = '"+pass.getText()+"';";
                     try {
                         Statement statement = connection.createStatement();
                         ResultSet rs = statement.executeQuery(sql);
                         while(rs.next()){
                             if(rs.getInt(1)==1){

                                 //opening user profile
                                 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user.fxml"));
                                 Parent root2 = (Parent) fxmlLoader.load();
                                 UserController usc = fxmlLoader.getController();
                                 usc.setLabel(user.getText());
                                 Stage stage = new Stage();
                                 stage.setTitle("User");
                                 stage.setScene(new Scene(root2));
                                 stage.show();

                                 //closing the login page
                                 Node n = (Node) actionEvent.getSource();
                                 Stage st = (Stage) n.getScene().getWindow();
                                 st.close();

                             }
                             else{
                                 err.setText("Invalid Credentials !");
                             }
                         }
                     }catch(Exception e){
                         e.printStackTrace();
                     }
                 }
                 else {
                     err.setText("Please enter username and password !");
                 }



    }
}
