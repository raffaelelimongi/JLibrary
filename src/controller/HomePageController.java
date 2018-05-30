package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {

    @FXML
    private Button bt2;

    public  void returnlogin(ActionEvent event) throws IOException {
        Stage logout;
        Parent login;

        logout =(Stage) bt2.getScene().getWindow();
        login = FXMLLoader.load(getClass().getResource("Login.fxml"));
        logout.setTitle("JLibrary");
        Scene homescene = new Scene(login);
        logout.setScene(homescene);
        logout.show();
    }
}
