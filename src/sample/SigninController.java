package sample;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Connection.ConnectionClass;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SigninController {

    ObservableList<String> maritalStatusList = FXCollections.observableArrayList("Single","Married","Divorsee");

    @FXML
    private ChoiceBox maritalStatusBox;

    @FXML
    private Button signin;
    private Button res;

    @FXML
    private TextField nm;

    @FXML
    private TextField mail;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField phn;

    @FXML
    private RadioButton rbt1,rbt2,rbt3;

    @FXML
    private  DatePicker dob;

    @FXML
    private Label lbl;



    String gender="";

    @FXML
    public void getGen(ActionEvent actionEvent){

        if(rbt1.isSelected()){
            gender=rbt1.getText();
        }
        else if(rbt2.isSelected()){
            gender=rbt2.getText();
        }
        else if(rbt3.isSelected()){
            gender=rbt3.getText();
        }
    }

    @FXML
    private void initialize(){
        maritalStatusBox.setValue("Single");
        maritalStatusBox.setItems(maritalStatusList);

    }

    String dat = "";
    @FXML
    public void getDate(ActionEvent actionEvent){
        try {
            LocalDate mydate = dob.getValue();
            dat = mydate.toString();
        }catch(Exception e){

        }
    }







    @FXML
    public void submit(ActionEvent actionEvent) throws SQLException{

        if((!(nm.getText()).equals("")) && (!(mail.getText()).equals("")) && (!(pass.getText()).equals("")) && (!(phn.getText()).equals("")) && (!(gender).equals("")) &&  (!(dat).equals("")) ){
            try {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                String sql = "insert into emp_data (name,email,password,mobile,dob,gender,mt_status) values('" + nm.getText() + "' , '" + mail.getText() + "' , '" + pass.getText() + "' ,'" + phn.getText() + "' , '" + dat + "' ,'" + gender + "' , '" + maritalStatusBox.getValue() + "');";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                //System.out.println(sql);

                //opening user profile
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user.fxml"));
                Parent root2 = (Parent) fxmlLoader.load();
                UserController usc = fxmlLoader.getController();
                usc.setLabel(mail.getText());
                Stage stage = new Stage();
                stage.setTitle("User");
                stage.setScene(new Scene(root2));
                stage.show();

                //closing the login page
                Node n = (Node) actionEvent.getSource();
                Stage st = (Stage) n.getScene().getWindow();
                st.close();



            }catch(SQLException e){
                lbl.setText("Email address already taken :(");
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        else{
            lbl.setText("Please enter all the details !");
        }

    }

     @FXML
     public void reset(ActionEvent actionEvent){
          nm.setText("");
          mail.setText("");
          pass.setText("");
          phn.setText("");
          rbt1.setSelected(false);
          rbt2.setSelected(false);
          rbt3.setSelected(false);
          dob.setValue(null);
          maritalStatusBox.setValue("Single");
     }


}

