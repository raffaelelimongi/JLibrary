package controller;

import dao.Interface.UserInfoInterface;
import dao.UserInfoQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InfoUserTable;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GestioneUserController implements Initializable
{
    @FXML
    private Button btsearch,btacctrasc;
    @FXML
    private TextField searchutente;
    @FXML
    private Button bteliminaacc;
    @FXML
    private ChoiceBox<String> cbfilter;
    @FXML
    private TableView<InfoUserTable> tablesearch;
    @FXML
    private TableColumn<InfoUserTable,String> col_username, col_nome, col_cognome, col_email, col_privilegio;
    
    private ObservableList<InfoUserTable> oblist;

    public String Username,Nome,Cognome,Email,Privilegio;
    UserInfoInterface userInfoInterface = new UserInfoQuery();
    InfoUserTable datiuser;
    {
        try {
            datiuser = new InfoUserTable("","","","","");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    UserInfoInterface user = new UserInfoQuery();

    public GestioneUserController()
    {
    }

    public void search() throws SQLException, IOException
    {

        oblist.removeAll(oblist);

        if(!cbfilter.getValue().equals("ric_trascrittore"))
        {
            btacctrasc.setVisible(false);
        }
        else
        {
            btacctrasc.setVisible(true);
        }

        String kind= cbfilter.getValue();
        String keywords = searchutente.getText();

        ResultSet resultSet = user.SupervisorUserPanelQuery (keywords,kind);

        while (resultSet.next())
        {
            //setto le variabili con le informazioni presenti nel DB e le passo al metodo setTable
            Username = resultSet.getString("username");
            Nome = resultSet.getString("nome");
            Cognome = resultSet.getString("cognome");
            Email = resultSet.getString("email");
            Privilegio= resultSet.getString("r.privilegio");

            setTable(Username, Nome, Cognome, Email,Privilegio);
        }
    }

    public void PromuoviUser() throws SQLException
    {
        InfoUserTable Deleter= tablesearch.getSelectionModel().getSelectedItem();
        tablesearch.getItems().removeAll(tablesearch.getSelectionModel().getSelectedItem());
        userInfoInterface.PromoteUser(Deleter.getUsername());
    }

    public void RetrocediUser() throws SQLException
    {
        InfoUserTable Deleter= tablesearch.getSelectionModel().getSelectedItem();
        tablesearch.getItems().removeAll(tablesearch.getSelectionModel().getSelectedItem());
        userInfoInterface.RetrocediUser(Deleter.getUsername());
    }

    public void AcceptTrascrittore() throws SQLException
    {
        InfoUserTable Deleter= tablesearch.getSelectionModel().getSelectedItem();
        tablesearch.getItems().removeAll(tablesearch.getSelectionModel().getSelectedItem());
        userInfoInterface.AcceptTrascrittore(Deleter.getUsername());

    }

    public void gotohome(ActionEvent event)
    {
        JavaFXController.sethome(event);
    }

    public void AssegnaTrascrizione()
    {
        JavaFXController.setAssegnaTrascrizione();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btacctrasc.setVisible(false);

        cbfilter.getItems().addAll( "","ric_trascrittore", "Trascrittori");
        cbfilter.setValue("");

        col_username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        col_cognome.setCellValueFactory(new PropertyValueFactory<>("Cognome"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        col_privilegio.setCellValueFactory(new PropertyValueFactory<>("Privilegio"));

        oblist = FXCollections.observableArrayList();
    }

    //Metodo per settare la Tableview con i valori presi dal DB
    private void setTable (String Username, String Nome, String Cognome, String Email, String Privilegio) throws IOException
    {
        oblist.add(new InfoUserTable(Username, Privilegio, Nome, Cognome,Email));
        tablesearch.setItems(oblist);
    }
