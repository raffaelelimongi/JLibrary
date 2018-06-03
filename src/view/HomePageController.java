package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {

    @FXML
    private Button bt2;

    @FXML
    private Button lol;

    @FXML
    public ComboBox Genere;
    public void gotosignup1(ActionEvent event) throws IOException
    {

        Stage stage3;
        Parent home3;

        stage3 = (Stage) lol.getScene().getWindow();
        home3 = FXMLLoader.load(getClass().getResource("../view/signup.fxml"));

        Scene homescene = new Scene(home3);
        stage3.setScene(homescene);
        stage3.setTitle("SignUp");

        stage3.show();

    }

    public  void returnlogin(ActionEvent event) throws IOException
    {
        Stage logout;
        Parent login;

        logout =(Stage) bt2.getScene().getWindow();
        login = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        logout.setTitle("JLibrary");
        Scene homescene = new Scene(login);
        logout.setScene(homescene);
        logout.show();
    }
}
