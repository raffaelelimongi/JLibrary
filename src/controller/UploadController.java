package controller;

import dao.ImageQuery;
import dao.Interface.ImageQueryInterface;
import dao.Interface.OperaInfoInterface;
import dao.OperaInfoQuery;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ImmagineDati;
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
    private TextField txtTitolo,txtautore,txtgenere;
    @FXML
    private DatePicker date;
    @FXML
    private Label lblchose;

    private Image image;
    private ImageView imageView;
    String nome;
    List<File> f;
    int result;

    OperaInfoInterface operaInfoInterface = new OperaInfoQuery();
    ImageQueryInterface imageQueryInterface = new ImageQuery();

   ImmagineDati imagedate; //creo una istanza di immagineDati presente nel VO
    {
        try {
            imagedate = new ImmagineDati("",null,"","","",null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UploadController()
    {
    }


    //metodo per la scelta e il caricamento delle immagini nel DB
    public void choseImage()
    {
        FileChooser fc = new FileChooser(); //creo un nuovo filechoser per la scelta dei file
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",filext)); //setto l'estensione jpg di tali file
        //ciclo sui file scelti per leggere il nome e il file di tipo jpg e l'invio alla classe del dao per la memorizzazione nel DB
         f =  fc.showOpenMultipleDialog(null);

        for(File file:f)
        {
            nome = file.getName();
            lblchose.setText(lblchose.getText()+file.getAbsolutePath());
            imagedate.setNomeimg(nome);
        }

      /*  if(f!=null)
        {


            /* 2METODO MENO EFFICENTE E SICURO MA PIU SIMILE AD UNA SITUAZIONE REALE
            image = new Image(f.toURI().toString(),100,150,true,true);
            imageView=new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(true);

            imagedate.setNomeimg(nome);
        }*/
    }

    public void Upload() throws SQLException
    {
         //invio le informazioni relative all'opera da inserire nel DB
       result=  operaInfoInterface.OperaInfoQuery(txtTitolo.getText(), txtautore.getText(), date.getValue(), txtgenere.getText(),nome,lblchose.getText());

        for(File file:f)
        {
            //passo il nome dell'immagine e l'immagine alla query per inserirla nel DB
           result=result + imageQueryInterface.UploadImageQuery(file.getName(), file.getAbsolutePath(), txtTitolo.getText(),txtautore.getText());
        }
        if (result >= 2)
        {
            lblchose.setText("Info Opera e immagini Caricate correttamente");
        }
        else
        {
            lblchose.setText("Errore, opera gia presente!");
        }
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
