package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.TrascrizioneDati;

import java.io.IOException;

public class JavaFXController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        primaryStage.setTitle("JLibrary");
        primaryStage.setScene(new Scene(root, 300, 285));
        primaryStage.show();
    }

    public static void sethome(ActionEvent event) {
        Parent root;
        try {
            //setto la nuova scena della home page e nascondo la precedente
            root = FXMLLoader.load(HomePageController.class.getResource("../view/home.fxml"));
            Stage stage = new Stage();
            stage.setTitle("HomePage");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setLogin(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(ViewOperaController.class.getResource("../view/Login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setSignUp(ActionEvent event)
    {
        Parent root;
        try {
            root = FXMLLoader.load(JavaFXController.class.getResource("../view/signup.fxml"));
            Stage stage = new Stage();
            stage.setTitle("SignUp");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setAdminPanel(ActionEvent event)
    {
        Parent root1;
        try {
            root1 = FXMLLoader.load(JavaFXController.class.getResource("../view/AdminPanel.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AdminPanel");
            Scene home = new Scene(root1);
            stage.setScene(home);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setProfile(ActionEvent event)
    {
        Parent root;
        try {
            root = FXMLLoader.load(JavaFXController.class.getResource("../view/profile.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Profile");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUpload()
    {
        Parent root;
        try {
            root = FXMLLoader.load(JavaFXController.class.getResource("../view/uploadView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Chose Image");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setManageUser(ActionEvent event)
    {
        Parent root;
        try {
            root = FXMLLoader.load(JavaFXController.class.getResource("../view/gestioneuser.fxml"));
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

    public static void setTrascrizione(ActionEvent event)
    {
        Parent root;
        try
        {
            root = FXMLLoader.load(JavaFXController.class.getResource("../view/trascrizione.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Trascrizione");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void setViewImage(String tit)
    {
        new ViewImage().setTitolo(tit);

        Parent root;
        try
        {
            root = FXMLLoader.load(JavaFXController.class.getResource("../view/imageview.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Image");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setAssegnaTrascrizione()
    {
        Parent root;
        try
        {
            root = FXMLLoader.load(JavaFXController.class.getResource("../view/assegnatrascrizione.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Trascrizione");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void setTeiEditor(String titolo) throws IOException
    {
        TrascrizioneDati trascrizioneDati= new TrascrizioneDati(titolo,null,null);
        new TeiEditor().setTitolo(trascrizioneDati);
        // Setto la scena per l'apertura della finestra del teiEditor di quella determinata opera
        Parent root;

        root = FXMLLoader.load(JavaFXController.class.getResource("../view/teieditor.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Trascrizione");
        Scene home = new Scene(root);
        stage.setScene(home);
        stage.show();
    }

    public static void setViewOpera(String tit)
    {
        new ViewOperaController().setTitolo(tit);

        Parent root;
        try
        {
            root = FXMLLoader.load(JavaFXController.class.getResource("../view/operaview.fxml"));
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

    public static void setManageImage(ActionEvent event)
    {
        Parent root;
        try {
            root = FXMLLoader.load(JavaFXController.class.getResource("../view/gestioneimage.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Manage Image");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
