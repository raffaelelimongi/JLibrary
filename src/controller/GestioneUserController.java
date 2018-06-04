package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class GestioneUserController
{

    public static void GestioneUserController(ActionEvent event) throws SQLException
    {
        Parent root;
        try {
            root = FXMLLoader.load(ViewProfileController.class.getResource("../view/gestioneuser.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Manage User");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
