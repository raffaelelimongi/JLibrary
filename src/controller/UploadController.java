package controller;

import dao.OperaInfoQuery;
import dao.UploadImageQuery;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vo.ImmagineDati;
import vo.OperaMetadati;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UploadController implements Initializable
{
    List <String> filext;
    @FXML
    private Button btupload;
    @FXML
    private TextField txtTitolo;
    @FXML
    private TextField txtautore;
    @FXML
    private TextField txtgenere;
    @FXML
    private DatePicker date;

   ImmagineDati imagedate = new ImmagineDati("");

    public UploadController()
    {
    }

    public void UploadController()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/uploadView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Chose Image");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //metodo per la scelta e il caricamento delle immagini nel DB
    public void choseImage() throws FileNotFoundException, SQLException
    {
        FileChooser fc = new FileChooser(); //creo un nuovo filechoser per la scelta dei file
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",filext)); //setto l'estensione jpg di tali file
        //ciclo sui file scelti per leggere il nome e il file di tipo jpg e l'invio alla classe del dao per la memorizzazione nel DB
        File f = fc.showOpenDialog(null);

        if(f!=null)
        {
            String nome = f.getName();
            InputStream immagine = new FileInputStream(new File(f.getPath()));
            imagedate.setNomeimg(nome);
            new UploadImageQuery(nome, immagine);
        }
    }

    public void Upload() throws SQLException
    {
           String nome= imagedate.getNomeimg();
           new OperaInfoQuery().OperaInfoQuery(txtTitolo.getText(), txtautore.getText(), date.getValue(), txtgenere.getText(),nome);
    }

    //metodo per inizializzare il ChoiseBox e la Table view
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        filext= new ArrayList<>();
        filext.add("*.jpg");
        filext.add("*.png");

    }
}
