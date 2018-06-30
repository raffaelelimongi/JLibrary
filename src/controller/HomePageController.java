package controller;

import dao.Interface.SearchOperaInterface;
import dao.SearchOperaQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.UserModel;
import model.OperaMetadati;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomePageController implements Initializable
{
    @FXML
    private TextField txtsearch;
    @FXML
    private ChoiceBox<String> cbfilter;
    @FXML
    private Button bttrascrizione,btimage,btgestuser;
    @FXML
    private TableView<OperaMetadati> tablesearch;
    @FXML
    private TableColumn<OperaMetadati,String> col_titolo, col_autore, col_genere, col_data, col_link;

    private ObservableList<OperaMetadati> oblist;  //uso la Collection ObservableList per creare una lista di istanze di tipo OperaMetadati da inserire successivamente nella tableview
    
    UserModel user =UserModel.getInstance();
    SearchOperaInterface searchOperaInterface =new SearchOperaQuery();

    ArrayList<OperaMetadati>listopere=new ArrayList<>();

    public HomePageController()
    {
    }

    @FXML
    private void search() throws SQLException
    {
        String keywords2, kind2;
        txtsearch.setPromptText("");

        oblist.removeAll(oblist); //ogni volta che ri-effetuo una ricerca rimuovo tutte le vecchie istanze create nella oblist

        if(cbfilter.getValue().equals(""))       //controllo che la choisebox sia vuota per cercare solo tramite keyword
        {
            keywords2 = txtsearch.getText();
            if (!keywords2.equals("")) {
                listopere = searchOperaInterface.SearchOperaQueryGeneral(txtsearch.getText(), cbfilter.getValue());
                setTable(listopere);
            } else {
                txtsearch.setPromptText("Inserire almeno una lettera!");
            }
        }
        else
        {
            keywords2 = txtsearch.getText();
            kind2 = cbfilter.getValue();

            listopere = searchOperaInterface.SearchOperaQueryGeneral(keywords2,kind2);
            setTable(listopere);
            }
        txtsearch.clear();
    }

    public void returnlogin(ActionEvent event)
    {
        JavaFXController.setLogin(event);
    }

    public void gotoprofile(ActionEvent event)
    {
        if (user.getPrivilegio().equals("admin"))
        JavaFXController.setAdminPanel(event);
        else
        JavaFXController.setProfile(event);
    }

    public void gotochose()
    {
        JavaFXController.setUpload();
    }
    public void gotomanageuser(ActionEvent event)
    {
      JavaFXController.setManageUser(event);
    }
    public void gototrascrizione(ActionEvent event)
    {
        JavaFXController.setTrascrizione(event);
    }
    public void gotomanageimage(ActionEvent event)
    {
        JavaFXController.setManageImage(event);
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
        col_data.setCellValueFactory(new PropertyValueFactory<>("datapubb"));
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
    private void setTable(ArrayList<OperaMetadati>listopere)
    {
        for(int i=0;i<listopere.size();i++)
        {
            oblist.add(listopere.get(i));
            tablesearch.setItems(oblist);
        }
    }

}
