package controller;

import vo.*;
import dao.*;
import model.*;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.concurrent.TimeUnit;

public class LoginController {



    @FXML
    public TextField textusername;

    @FXML
    public TextField textpassword;

    @FXML
    private Button bt1;

    @FXML
    private Label result;

    @FXML
    private Button signup;


    UserModel userModel = new UserModel("","","");

    public void Login (ActionEvent event) throws Exception {

        userModel.setUsername(textusername.getText());
        userModel.setPassword(textpassword.getText());
        try {


            /*String user=""+textusername.getText()+"";
            String pass=""+textpassword.getText()+"";*/

            ResultSet resultSet = (ResultSet)  new UserAuthenticationQuery().UserAuthenticationQuery1(userModel.getUsername(), userModel.getPassword());

            if(resultSet.next()){

                Stage stage;
                Parent home;

                stage =(Stage) bt1.getScene().getWindow();
                home = FXMLLoader.load(getClass().getResource("home.fxml"));

                Scene homescene = new Scene(home);
                stage.setScene(homescene);
                stage.setTitle("HomePage");

                stage.show();

            }
            else {
                result.setText("autenticazione fallita");
            }


        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void gotosignup(ActionEvent event) throws IOException {

        Stage stage;
        Parent home;

        stage =(Stage) signup.getScene().getWindow();
        home = FXMLLoader.load(getClass().getResource("signup.fxml"));

        Scene homescene = new Scene(home);
        stage.setScene(homescene);
        stage.setTitle("SignUp");

        stage.show();

    }
}
