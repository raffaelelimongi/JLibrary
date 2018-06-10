package controller;

import dao.*;
import dao.Interface.UserInfoInterface;
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

public class LoginController extends Application
{
    @FXML
    public TextField textusername,textpassword;
    @FXML
    private Button btlogin,btsignup;
    @FXML
    private Label result;

    private static String username;

    public LoginController()
    {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        primaryStage.setTitle("JLibrary");
        primaryStage.setScene(new Scene(root, 300, 285));
        primaryStage.show();
    }
    
    public void Login (ActionEvent event) throws Exception
    {
        try {
            UserAuthenticationQuery userDaoInterface = new UserAuthenticationQuery();
            UserInfoInterface userInfoInterface = new UserInfoQuery();

            ResultSet resultSet = userDaoInterface.UserAuthenticationQuery(textusername.getText(),textpassword.getText());

            if(resultSet.next())
            {
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
