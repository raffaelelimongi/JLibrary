package controller;

import dao.*;
import dao.Interface.UserDaoInterface;
import dao.Interface.UserInfoInterface;
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



public class LoginController
{

    @FXML
    public TextField textusername;

    @FXML
    public TextField textpassword;

    @FXML
    private Button btlogin;

    @FXML
    private Label result;

    @FXML
    private Button btsignup;

    private static String username;

   // public  UserModel userModel = new UserModel("","",true,"",0,true,"","","");

    public LoginController()
    {
    }

    public void Login (ActionEvent event) throws Exception
    {
       //setUser(textusername.getText());
        try {
            UserDaoInterface userDaoInterface = new UserAuthenticationQuery();
            UserInfoInterface userInfoInterface = new UserInfoQuery();

            ResultSet resultSet = (ResultSet)  userDaoInterface.UserAuthenticationQuery(textusername.getText(),textpassword.getText());

            if(resultSet.next())
            {
                //userModel= new UserInfoQuery().UserInfoQuery(textusername.getText(),userModel);
                userInfoInterface.UserInfoQuery(textusername.getText());
                new HomePageController().setscene(event);
            }
            else {
                result.setText("Username or password invalid");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gotosignup(ActionEvent event) throws IOException
    {
        Stage stage;
        Parent home;

        stage = (Stage) btsignup.getScene().getWindow();
        home = FXMLLoader.load(getClass().getResource("../view/signup.fxml"));

        Scene homescene = new Scene(home);
        stage.setScene(homescene);
        stage.setTitle("SignUp");

        stage.show();
    }
}
