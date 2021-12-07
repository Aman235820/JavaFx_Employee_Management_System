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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {

    @FXML
    public Label email;
    public Label lb1;
    public Label lb4;
    public Label lb5;
    public Label lb6;

    @FXML
    public Button bt1;
    public Button bt2;
    public Button bt3;
    public Button edit;
    public Button del;

    

    @FXML
    public void setLabel(String mail) {
        email.setText(mail);

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        String sql = "select * from emp_data where email = '" +mail+ "';";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                lb1.setText(rs.getString(1));
                lb4.setText(rs.getString(4));
                lb5.setText(rs.getString(5));
                lb6.setText(rs.getString(6));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    @FXML
    public void home(ActionEvent actionEvent){

        //closing the page
        Node n = (Node) actionEvent.getSource();
        Stage st = (Stage) n.getScene().getWindow();
        st.close();
    }

    @FXML
    public void sign(ActionEvent actionEvent) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("SignIn");
        stage.setScene(new Scene(root1));
        stage.show();

        //closing the page
        Node n = (Node) actionEvent.getSource();
        Stage st = (Stage) n.getScene().getWindow();
        st.close();

    }

    @FXML
    public void logout(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Login Page");
        stage.setScene(new Scene(root2));
        stage.show();

        //closing the page
        Node n = (Node) actionEvent.getSource();
        Stage st = (Stage) n.getScene().getWindow();
        st.close();
    }


    @FXML
    public void update(ActionEvent actionEvent)throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit.fxml"));
        Parent root3 = (Parent) loader.load();
        EditController esc = loader.getController();
        esc.setLabel1(email.getText());
        Stage stage = new Stage();
        stage.setTitle("Edit Profile");
        stage.setScene(new Scene(root3));
        stage.show();

        //closing the profile
        Node n = (Node) actionEvent.getSource();
        Stage st = (Stage) n.getScene().getWindow();
        st.close();

    }

    @FXML
    public void delete(ActionEvent actionEvent) throws  SQLException{

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        String sql = "delete from emp_data where email = '"+email.getText()+"';";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        }catch(SQLException e){
            e.printStackTrace();
        }


        //closing the profile
        Node n = (Node) actionEvent.getSource();
        Stage st = (Stage) n.getScene().getWindow();
        st.close();

    }




}
