package controller;

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
import vo.OperaMetadati;
import vo.UserInfo;
import vo.UserInfoTable;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GestioneUserController implements Initializable
{
    @FXML
    private Button btsearch;
    @FXML
    private TextField searchutente;
    @FXML
    private Button btacctrasc;
    @FXML
    private Button bteliminaacc;
    @FXML
    private ChoiceBox<String> cbfilter;
    @FXML
    private TableView<UserInfoTable> tablesearch;
    @FXML
    private TableColumn<UserInfoTable,String> col_username;
    @FXML
    private TableColumn<UserInfoTable,String> col_nome;
    @FXML
    private TableColumn<UserInfoTable,String> col_cognome;
    @FXML
    private TableColumn<UserInfoTable,String> col_email;

    private ObservableList<UserInfoTable> oblist;

    public String Username;
    public String Nome;
    public String Cognome;
    public String Email;

    UserInfoTable userInfoTable = new UserInfoTable(Username, Nome, Cognome, Email);




    public static void GestioneUserController(ActionEvent event) throws SQLException
    {
        Parent root;
        try {
            root = FXMLLoader.load(ViewProfileController.class.getResource("../view/gestioneuser.fxml"));
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

    public void gotohome(ActionEvent event)
    {
        HomePageController.setscene(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        cbfilter.getItems().addAll("", "RichiesteTrascrittori", "vip", "Trascrittori");
        cbfilter.setValue("");

        col_username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        col_cognome.setCellValueFactory(new PropertyValueFactory<>("Cognome"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("Email"));

        oblist = FXCollections.observableArrayList();
    }

        //Metodo per settare la Tableview con i valori presi dal DB
        private void setTable (String Username, String Nome, String Cognome, String Email) throws IOException
        {
            oblist.add(new UserInfoTable(Username, Nome, Cognome, Email));
            tablesearch.setItems(oblist);
        }

    public void search() throws SQLException, IOException
    {
        UserInfoTable datiuser = new UserInfoTable("","","","");
        searchutente.setPromptText("");

        String kind= cbfilter.getValue();
        String keywords = searchutente.getText();

        if (!keywords.equals(""))
        {
            ResultSet resultSet = UserInfoQuery.SupervisorUserPanelQuery (keywords,kind);
            while (resultSet.next())
            {

                //setto le variabili con le informazioni presenti nel DB e le passo al metodo setTable
                Username = resultSet.getString("username");
                Nome = resultSet.getString("nome");
                Cognome = resultSet.getString("cognome");
                Email = resultSet.getString("email");
                if(!datiuser.getUsername().equals("a") )
                {
                    setTable(Username, Nome, Cognome, Email);
                }
                datiuser.setUsername(Username);
                datiuser.setNome(Nome);
                datiuser.setCognome(Cognome);
                datiuser.setEmail(Email);
            }

        }
    }

}
