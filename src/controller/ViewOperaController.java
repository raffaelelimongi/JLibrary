package controller;

import dao.*;
import vo.*;
import model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewOperaController
{
    public ViewOperaController()
    {

    }

    public static void ViewOpera()
    {
         Parent root;
            try
            {
                root = FXMLLoader.load(ViewOperaController.class.getResource("../view/login.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Opera");
                Scene home = new Scene(root);
                stage.setScene(home);
                stage.show();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
    }
}
