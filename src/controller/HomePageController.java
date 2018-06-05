package controller;

import com.jfoenix.controls.JFXButton;
import dao.Interface.SearchOperaInterface;
import dao.SearchOperaQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import vo.OperaMetadati;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomePageController implements Initializable
{
    @FXML
    private JFXButton btlogout;
    @FXML
    private TextField txtsearch;
    @FXML
    private ChoiceBox<String> cbfilter;
    @FXML
    private Button btsearch;
    @FXML
    private TableView<OperaMetadati> tablesearch;
    @FXML
    private TableColumn<OperaMetadati,String> col_titolo;
    @FXML
    private TableColumn<OperaMetadati,String> col_autore;
    @FXML
    private TableColumn<OperaMetadati,String> col_genere;
    @FXML
    private TableColumn<OperaMetadati,String> col_link;

    private ObservableList<OperaMetadati> oblist;

    public String autore;
    public String titolo;
    public String genere;

    OperaMetadati operaMetadati= new OperaMetadati(titolo,autore,genere);

    SearchOperaInterface searchOperaInterface =new SearchOperaQuery();

    public HomePageController() throws IOException
    {
    }

    public static void setscene(ActionEvent event)
    {
        Parent root;
        try
        {
            root = FXMLLoader.load(HomePageController.class.getResource("../view/home.fxml"));
            Stage stage = new Stage();
            stage.setTitle("HomePage");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void search() throws SQLException, IOException
    {
        String keywords2;
        String kind2;
        OperaMetadati operadati = new OperaMetadati("","","");
        txtsearch.setPromptText("");
        
        oblist.removeAll(oblist);
        if(cbfilter.getValue().equals(""))       //controllo che la choisebox sia vuota per cercare solo tramite keyword
        {
            keywords2 = txtsearch.getText();

            if (!keywords2.equals(""))
            {
                keywords2=txtsearch.getText();
                ResultSet resultSet = searchOperaInterface.SearchOperaQueryKeyword(keywords2);
                while (resultSet.next())
                {
                    //setto le variabili con le informazioni presenti nel DB e le passo al metodo setTable
                    autore = resultSet.getString("autore");
                    titolo = resultSet.getString("titolo");
                    genere = resultSet.getString("c.nome");

                    setTable(titolo, autore, genere);

                    operadati.setTitolo(titolo);
                    operadati.setAutore(autore);
                    operadati.setGenere(genere);
                }

            } else
                {
                txtsearch.setPromptText("Inserire almeno una lettera!");
                }
        }
        else
        {
            keywords2 = txtsearch.getText();
            kind2 = cbfilter.getValue();

            ResultSet resultSet = searchOperaInterface.SearchOperaQueryGeneral(keywords2,kind2);

            while (resultSet.next())
            {
                //setto le variabili con le informazioni presenti nel DB e le passo al metodo setTable
                autore = resultSet.getString("autore");
                titolo = resultSet.getString("titolo");
                genere = resultSet.getString("c.nome");

                setTable(titolo, autore, genere);

                operadati.setTitolo(titolo);
                operadati.setAutore(autore);
                operadati.setGenere(genere);
            }
        }
        txtsearch.clear();
    }

    public void returnlogin() throws IOException
    {
        try{
        Stage back;
        Parent gologin;

        back =(Stage) btlogout.getScene().getWindow();
        gologin = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        back.setTitle("JLibrary");
        Scene homescene = new Scene(gologin);
        back.setScene(homescene);
        back.show();

        } catch (IOException e)
        {
            e.printStackTrace();

        }
    }

    public void gotoprofile(ActionEvent event) throws SQLException
    {
        ViewProfileController.ViewProfile(event);
    }

    public void gotochose(ActionEvent event) throws  SQLException
    {
        new UploadController().UploadController();
    }

    public void gotomanageuser(ActionEvent event) throws SQLException
    {
        GestioneUserController.GestioneUserController(event);
    }
    public void gototrascrizione(ActionEvent event) throws SQLException, IOException
    {
        new TrascrizioneController().setScene(event);
    }


    //metodo per inizializzare il ChoiseBox e la Table view
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        cbfilter.getItems().addAll("","horror","fantasy","historical", "crime", "action", "adventure", "drama");
        cbfilter.setValue("");

        col_titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        col_autore.setCellValueFactory(new PropertyValueFactory<>("autore"));
        col_genere.setCellValueFactory(new PropertyValueFactory<>("genere"));
        col_link.setCellValueFactory(new PropertyValueFactory<>("view"));

        oblist=FXCollections.observableArrayList();

    }

    //Metodo per settare la Tableview con i valori presi dal DB
    private void setTable(String titolo, String autore, String genere) throws IOException
    {
        oblist.add(new OperaMetadati(titolo, autore, genere));
        tablesearch.setItems(oblist);
    }

}
