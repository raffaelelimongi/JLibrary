package controller;

import dao.*;
import dao.Interface.ImageQueryInterface;
import dao.Interface.SearchOperaInterface;
import dao.Interface.TrascrizioneQueryInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.ImmagineDati;
import model.OperaMetadati;
import model.UserModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class ViewOperaController implements Initializable
{
    @FXML
    private Label lbtitolo,lbautore,lbdata,lbgenere;
    @FXML
    private TextArea text;
    @FXML
    private TableView<ImmagineDati> tableimage;
    @FXML
    private TableColumn<OperaMetadati,ImageView> col_image;
    @FXML
    private Button btdownload;

    private ObservableList<ImmagineDati> oblist;
    static  String titolo;
    private List<Image> list = new ArrayList<>();
    private ArrayList<OperaMetadati> opere = new ArrayList<>();
    private ArrayList<ImmagineDati> immagine = new ArrayList<>();

    SearchOperaInterface searchOperaInterface = new SearchOperaQuery();
    TrascrizioneQueryInterface trascrizioneQueryInterface = new TrascrizioneQuery();
    ImageQueryInterface imageQueryInterface = new ImageQuery();
    UserModel user = UserModel.getInstance();

    public ViewOperaController()
    {

    }

    public void setTitolo(String tit)
    {
        titolo=tit;
    }


    public void LoadOpera()throws SQLException
    {

        opere = searchOperaInterface.LoadOpera(titolo); //faccio la query per caricarmi le info dell opera da visualizzare
        Iterator<OperaMetadati>itr=opere.iterator();
        while(itr.hasNext())
        {
            OperaMetadati op=itr.next();
            titolo = op.getTitolo();
            lbtitolo.setText(op.getTitolo());
            lbautore.setText(op.getAutore());
            lbdata.setText(String.valueOf(op.getDatapubb()));
            lbgenere.setText(op.getGenere());
        }

        immagine = imageQueryInterface.LoadImageOpera(titolo);

        setTable(immagine);

       ResultSet resultSet=trascrizioneQueryInterface.LoadTrascrizione(titolo);

        while (resultSet.next())
        {
            if(resultSet.getBoolean("op.accept"))    {
                text.setText(resultSet.getString("op.testo"));
                          }
            else{
                text.setPromptText("Trascrizione non disponibile!!");
            }
        }
    }

    //Classe per effettuare il download delle opere
    public void Download()
    {
        Stage secondStage = new Stage();
        //dichiro l'azione che faccio una volta premuto il button per il download
        btdownload.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                for (int i=0;i <list.size();i++)                       //ciclo per salvare tutte le immagini delle opere presenti
                {
                    //creo e setto un nuovo Filechoser di tipo SAVE
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Image");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image", "*.jpg"));

                File file = fileChooser.showSaveDialog(secondStage);

                    //controllo che il file esista e lo prendo dalla lista di Image,in base all'indice corrente, e lo scrivo dandogli il formato .jpgdove lo voglio salvare(in questo caso sul pc)
                    if (file != null) {
                        try {
                            ImageIO.write(SwingFXUtils.fromFXImage(list.get(i), null), "jpg", file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

    }

    public void setTable(ArrayList<ImmagineDati> image)
    {
        Iterator<ImmagineDati>itr=image.iterator();
        while(itr.hasNext())
        {
            oblist.add(itr.next());
            tableimage.setItems(oblist);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if(!user.isVip()) //controllo che l'utente sia un Vip per poter scaricare l'opera e quindi vedere il button per il download,altrimenti nascondo il button per il download
        {
         btdownload.setVisible(false);
        }

        text.setEditable(false); //disabilito la TextArea per l'inserimento, only read

        col_image.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        oblist=FXCollections.observableArrayList();

        try {
            LoadOpera();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
