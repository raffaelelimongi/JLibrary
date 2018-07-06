package controller;

import dao.*;
import dao.Interface.UserInfoInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.*;

public class LoginController
{
    @FXML
    public TextField textusername,textpassword;
    @FXML
    private Label result;

    public LoginController()
    {
    }

    
    public void Login (ActionEvent event)
    {
        try {
            UserAuthenticationQuery userDaoInterface = new UserAuthenticationQuery();
            UserInfoInterface userInfoInterface = new UserInfoQuery();

            ResultSet resultSet = userDaoInterface.UserAuthenticationQuery(textusername.getText(),textpassword.getText());

            if(resultSet.next())
            {
                userInfoInterface.UserInfoQuery(textusername.getText());
                new JavaFXController().sethome(event);
            }
            else {
                result.setText("Username or password invalid");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gotosignup(ActionEvent event)
    {
        JavaFXController.setSignUp(event);
    }
}
