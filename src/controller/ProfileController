package controller;

import dao.ProfileQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.UserModel;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class ProfileController
{
    @FXML
    private SplitPane tutto;

    @FXML
    private AnchorPane sinistra;

    @FXML
    private AnchorPane destra;

    @FXML
    private Label error;

    @FXML
    private Label jlibrary;

    @FXML
    private Label livelloaccount;

    @FXML
    private ProgressBar barralivello;

    @FXML
    private Label nomeprofilo;

    @FXML
    private Label username;

    @FXML
    private TextField usernamenuovo;

    @FXML
    private Label password;

    @FXML
    private TextField passwordnuova;

    @FXML
    private Label email;

    @FXML
    private TextField emailnuova;

    @FXML
    private Label nome;

    @FXML
    private TextField nomenuovo;

    @FXML
    private Label cognome;

    @FXML
    private TextField cognomenuovo;

    @FXML
    private Button caricaimmagine;

    @FXML
    private Button submit;

    UserModel userModel = new UserModel("", "", "");

    public void modifica(ActionEvent event) throws Exception
    {
        if (usernamenuovo != null)
            userModel.setUsername(usernamenuovo.getText());
        if (passwordnuova != null)
            userModel.setUsername(passwordnuova.getText());
        if (emailnuova != null)
            userModel.setUsername(emailnuova.getText());
        if (nomenuovo != null)
            userModel.setUsername(nomenuovo.getText());
        if (cognomenuovo != null)
            userModel.setUsername(cognomenuovo.getText());

        try {
            int result = new ProfileQuery().ProfileQuery1(userModel.getUsername(), userModel.getPassword(), userModel.getNome(), userModel.getCognome(), userModel.getEmail());

            if (result == 1) {
                Stage stage;
                Parent home;

                stage = (Stage) submit.getScene().getWindow();
                home = FXMLLoader.load(getClass().getResource("Profile.fxml"));

                Scene homescene = new Scene(home);
                stage.setScene(homescene);
                stage.setTitle("JLibrary");

                stage.show();
            } else {
                error.setText("Errore");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
