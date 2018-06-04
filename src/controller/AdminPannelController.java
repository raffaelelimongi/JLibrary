package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;

public class AdminPannelController 
{


    @FXML
    public TextField tfricerca;

    @FXML
    private Button butdelete;

    @FXML
    public Button butuser;

    @FXML
    private Button butsuperv;

    @FXML
    public Button buthome;

    @FXML
    private CheckBox cbsuperv;

    @FXML
    private CheckBox cbtrascr;

    @FXML
    private Label label22;

    @FXML
    public ComboBox<String> cbricerca;
    ObservableList<String> List = FXCollections.observableArrayList("User", "Supervisor", "Trascriber");

    public void initialize() 
    {
        cbricerca.setItems(List);
    }

    public void changeCombo (ActionEvent event)
    {
        label22.setText ( cbricerca.getValue());
    }

    public void gotoHome(ActionEvent event) throws Exception 
    {
        Stage stage4;
        Parent home4;

        stage4 = (Stage) buthome.getScene().getWindow();
        home4 = FXMLLoader.load(getClass().getResource("../view/home.fxml"));

        Scene homescene = new Scene(home4);
        stage4.setScene(homescene);
        stage4.setTitle("Home");

        stage4.show();

    }
    public void gotoProfile(ActionEvent event) throws Exception 
    {
        Stage stage1;
        Parent home1;

        stage1 = (Stage) butuser.getScene().getWindow();
        home1 = FXMLLoader.load(getClass().getResource("../view/Profile.fxml"));

        Scene homescene1 = new Scene(home1);
        stage1.setScene(homescene1);
        stage1.setTitle("Profile");

        stage1.show();
    }


}
