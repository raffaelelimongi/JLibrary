package controller;

import dao.Interface.OperaInfoInterface;
import dao.Interface.UploadImageInterface;
import dao.Interface.UserInfoInterface;
import dao.OperaInfoQuery;
import dao.UploadImageQuery;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vo.ImmagineDati;

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

   ImmagineDati imagedate = new ImmagineDati(""); //creo una istanza di immagineDati presente nel VO

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
            InputStream immagine = new FileInputStream(new File(f.getPath()));  //prendo il path dell'immagine(prendo l'immagine vera e propria) e lo metto in un inputstream
            imagedate.setNomeimg(nome);

            UploadImageInterface uploadImageInterface= new UploadImageQuery();  //creo una nuova istanza dell'interfaccia Uploadimage
            uploadImageInterface.UploadImageQuery(nome, immagine);              //passo il nome dell'immagine e l'immagine alla query per inserirla nel DB
        }
    }

    public void Upload() throws SQLException
    {
           String nome= imagedate.getNomeimg();

        OperaInfoInterface operaInfoInterface = new OperaInfoQuery();
        operaInfoInterface.OperaInfoQuery(txtTitolo.getText(), txtautore.getText(), date.getValue(), txtgenere.getText(),nome); //invio le informazioni relative all'opera da inserire nel DB
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //inizializzo una Arraylist per i tipi di immagine supportati
        filext= new ArrayList<>();
        filext.add("*.jpg");
        filext.add("*.png");

    }
}
