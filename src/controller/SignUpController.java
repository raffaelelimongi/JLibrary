package controller;

import dao.SignInQuery;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import model.UserModel;

public class SignUpController {
    @FXML
    private Label error;
    @FXML
    private TextField txtuser;
    @FXML
    private PasswordField txtpass;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtsurname;
    @FXML
    private TextField txtemail;
    @FXML
    private Button btsubmit;
    @FXML
    private Button btback;

    UserModel userModel = new UserModel("","","");
    public void Submit(ActionEvent event) throws Exception {

        userModel.setUsername(txtuser.getText());
        userModel.setPassword(txtpass.getText());
        userModel.setNome(txtname.getText());
        userModel.setCognome(txtsurname.getText());
        userModel.setEmail(txtemail.getText());

        try {

             int result = new SignInQuery().SignInQuery1(userModel.getUsername(),userModel.getPassword(),userModel.getNome(),userModel.getCognome(),userModel.getEmail());

             if(result == 1) {
                 Stage stage;
                 Parent home;

                 stage = (Stage) btsubmit.getScene().getWindow();
                 home = FXMLLoader.load(getClass().getResource("Login.fxml"));

                 Scene homescene = new Scene(home);
                 stage.setScene(homescene);
                 stage.setTitle("JLibrary");

                 stage.show();
             }else
             {
                 error.setText("Errore");
             }

        }catch (SQLException e) {
            e.printStackTrace();
        }




    }
    public  void gotologin(javafx.event.ActionEvent event) throws IOException {
        Stage back;
        Parent gologin;

        back =(Stage) btback.getScene().getWindow();
        gologin = FXMLLoader.load(getClass().getResource("Login.fxml"));
        back.setTitle("JLibrary");
        Scene homescene = new Scene(gologin);
        back.setScene(homescene);
        back.show();
    }



}
