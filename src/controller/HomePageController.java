package controller;

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
import model.UserModel;
import model.OperaMetadati;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class HomePageController implements Initializable
{
    @FXML
    private TextField txtsearch;
    @FXML
    private ChoiceBox<String> cbfilter;
    @FXML
    private Button btsearch,bttrascrizione,btlogout,btimage,btgestuser;
    @FXML
    private TableView<OperaMetadati> tablesearch;
    @FXML
    private TableColumn<OperaMetadati,String> col_titolo, col_autore, col_genere, col_data, col_link;

    private ObservableList<OperaMetadati> oblist;  //uso la Collection ObservableList per creare una lista di istanze di tipo OperaMetadati da inserire successivamente nella tableview

    public String autore;
    public static String titolo;
    public String genere;
    public Date data;

    UserModel user =UserModel.getInstance();
    OperaMetadati operadati = new OperaMetadati("","","",null);
    SearchOperaInterface searchOperaInterface =new SearchOperaQuery();

    public HomePageController() throws IOException
    {
    }

    public static void setscene(ActionEvent event)
    {
        Parent root;
        try
        {
            //setto la nuova scena della home page e nascondo la precedente
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

    @FXML
    private void search() throws SQLException, IOException
    {
        String keywords2, kind2;
        txtsearch.setPromptText("");

        oblist.removeAll(oblist); //ogni volta che ri-effetuo una ricerca rimuovo tutte le vecchie istanze create nella oblist

        if(cbfilter.getValue().equals(""))       //controllo che la choisebox sia vuota per cercare solo tramite keyword
        {
            keywords2 = txtsearch.getText();
            if (!keywords2.equals(""))
            {
                keywords2=txtsearch.getText();
                ResultSet resultSet = searchOperaInterface.SearchOperaQueryGeneral(keywords2,cbfilter.getValue());
                while (resultSet.next())
                {
                    //setto le variabili con le informazioni presenti nel DB e le passo al metodo setTable
                    autore = resultSet.getString("autore");
                    titolo = resultSet.getString("titolo");
                    genere = resultSet.getString("c.nome");
                    data=resultSet.getDate("data_pubb");

                        setTable(titolo, autore, genere,data);

                        operadati.setTitolo(titolo);
                        operadati.setAutore(autore);
                        operadati.setGenere(genere);
                        operadati.setDatapubb(data);
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
                data=resultSet.getDate("data_pubb");

                setTable(titolo, autore, genere,data);

                operadati.setTitolo(titolo);
                operadati.setAutore(autore);
                operadati.setGenere(genere);
                operadati.setDatapubb(data);
            }
        }
        txtsearch.clear();
    }

    public void returnlogin()
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
        if (user.getPrivilegio().equals("admin"))
            AdminPannelController.setsceneAdmin(event);
        else
            ViewProfileController.ViewProfile(event);
    }

    public void gotochose()
    {
        new UploadController().setscene();
    }

    public void gotomanageuser(ActionEvent event) throws SQLException
    {
        GestioneUserController.setscene(event);
    }
    public void gototrascrizione(ActionEvent event) throws IOException
    {
        new TrascrizioneController().setScene(event);
    }
    public void gotomanageimage(ActionEvent event)
    {
        new GestioneImmaginiController().setscene(event);
    }

    //metodo per inizializzare il ChoiseBox,tableview e l'observablelist
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        cbfilter.getItems().addAll("","horror","fantasy","historical", "crime", "action", "adventure", "drama");
        cbfilter.setValue("");

        col_titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        col_autore.setCellValueFactory(new PropertyValueFactory<>("autore"));
        col_genere.setCellValueFactory(new PropertyValueFactory<>("genere"));
        col_data.setCellValueFactory(new PropertyValueFactory<>("data"));
        col_link.setCellValueFactory(new PropertyValueFactory<>("view"));

        oblist=FXCollections.observableArrayList(); //dichiaro l'oblist nel metodo initialize della classe

        //controllo che entrambe le condizioni siano vere che l'utente non è un trascrittore e neanche un supervisor e nascondo il button per l'accesso alle trascrizioni
        if(!user.getTrascrittore() && !user.getPrivilegio().equals("supervisor") && !user.getPrivilegio().equals("admin"))
        {
            bttrascrizione.setVisible(false);
        }
        if(!user.getPrivilegio().equals("supervisor") && !user.getPrivilegio().equals("admin"))
        {
            btimage.setVisible(false);
            btgestuser.setVisible(false);
        }
    }

    //Metodo per settare la Tableview con i valori presi dal DB
    private void setTable(String titolo, String autore, String genere,Date data) throws IOException
    {
        oblist.add(new OperaMetadati(titolo, autore, genere,data));
        tablesearch.setItems(oblist);
    }

}
