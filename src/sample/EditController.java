package sample;

import Connection.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class EditController {

    @FXML
    public Label mail;
    public Label err;

    @FXML
    public TextField editnm;
    public TextField editphn;
    public TextField editpass;

    @FXML
    public Button bt1;
    public Button bt2;
    public Button bt3;
    public Button finish;

    @FXML
    public void setLabel1(String id){
           mail.setText(id);
    }

    @FXML
    public void name(ActionEvent actionEvent) throws SQLException {
            if((editnm.getText()).equals("")){
                err.setText("Please enter name");
            }
            else{

                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();

                String sql = "update emp_data set name = '"+editnm.getText()+" 'where email = '" +mail.getText()+ "';";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                err.setText("Successfully updated name !");
            }
    }

    @FXML
    public void phn(ActionEvent actionEvent)throws SQLException{
        if((editphn.getText()).equals("")){
            err.setText("Please enter mobile no.");
        }
        else{

            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            String sql = "update emp_data set mobile = '"+editphn.getText()+" 'where email = '" +mail.getText()+ "';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            err.setText("Successfully updated Phone no. !");
        }
    }

    @FXML
    public void pass(ActionEvent actionEvent)throws SQLException{
        if((editpass.getText()).equals("")){
            err.setText("Please enter new Password");
        }
        else{

            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            String sql = "update emp_data set password = '"+editpass.getText()+" 'where email = '" +mail.getText()+ "';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            err.setText("Successfully updated password !");
        }
    }


    @FXML
    public void close(ActionEvent actionEvent){
        //closing the page
        Node n = (Node) actionEvent.getSource();
        Stage st = (Stage) n.getScene().getWindow();
        st.close();
    }

}
