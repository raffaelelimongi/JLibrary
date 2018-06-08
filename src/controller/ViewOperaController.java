package controller;

import dao.*;
import dao.Interface.SearchOperaInterface;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.UserModel;
import vo.*;
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
    @FXML
    private AnchorPane pane;

    private ObservableList<ImmagineDati> oblist;
    private Image image;
    static  String titolo;
    private List<Image> list = new ArrayList<>();

    SearchOperaInterface searchOperaInterface = new SearchOperaQuery();
    UserModel user = UserModel.getInstance();

    public ViewOperaController()
    {
    }

    public static void ViewOpera(String titolo1)
    {
        titolo=titolo1;

         Parent root;
            try
            {
                root = FXMLLoader.load(ViewOperaController.class.getResource("../view/operaview.fxml"));
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

    public void LoadOpera()throws SQLException, IOException
    {
        ResultSet resultSet = searchOperaInterface.LoadOpera(titolo); //faccio la query per caricarmi le info dell opera da visualizzare

        while (resultSet.next())
        {
            titolo=resultSet.getString("titolo");
            lbtitolo.setText(resultSet.getString("titolo"));
            lbautore.setText(resultSet.getString("autore"));
            lbdata.setText(resultSet.getString("data_pubb"));
            lbgenere.setText(resultSet.getString("c.nome"));

            if(resultSet.getBoolean("accept"))
            {
                text.setText(resultSet.getString("op.testo"));

            }
            else{
                text.setPromptText("Trascrizione non disponibile!!");
            }

            File file = new File(resultSet.getString("i.image"));
            image = new Image(file.toURI().toString());
            list.add(image);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(1000);
            imageView.setFitHeight(500);
            imageView.setPreserveRatio(true);
            setTable(titolo, imageView);
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

    public void setTable(String titolo, ImageView imageView2) throws IOException
    {
    oblist.add(new ImmagineDati("",imageView2,titolo,"",""));
    tableimage.setItems(oblist);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if(!user.isVip()) //controllo che l'utente sia un Vip per poter scaricare l'opera e quindi vedere il buttono per il download,altrimenti nascondo il button per il download
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
