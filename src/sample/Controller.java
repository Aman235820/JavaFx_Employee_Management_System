package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLOutput;

public class Controller {

       @FXML
       private Button signbtn;

        @FXML
        private Button logbtn;

       @FXML
       public void signin(ActionEvent actionEvent) throws IOException{

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();

                    Stage stage = new Stage();
                    stage.setTitle("SignIn");
                    stage.setScene(new Scene(root1));
                    stage.show();


       }

    @FXML
    public void login(ActionEvent actionEvent) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Login Page");
        stage.setScene(new Scene(root2));
        stage.show();


    }




}
