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
import vo.InfoUserTable;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GestioneUserController implements Initializable
{
    @FXML
    private Button btsearch,btacctrasc, bteliminaacc;
    @FXML
    private TextField searchutente;
    @FXML
    private ChoiceBox<String> cbfilter;
    @FXML
    private TableView<InfoUserTable> tablesearch;
    @FXML
    private TableColumn<InfoUserTable,String> col_username, col_nome, col_cognome, col_email;

    private ObservableList<InfoUserTable> oblist;

    public String Username,Nome,Cognome,Email;
    InfoUserTable datiuser;
    {
        try {
            datiuser = new InfoUserTable("","","","");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    UserInfoInterface user = new UserInfoQuery();

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

    public void search() throws SQLException, IOException
    {
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

            setTable(Username, Nome, Cognome, Email);

            datiuser.setUsername(Username);
            datiuser.setNome(Nome);
            datiuser.setCognome(Cognome);
            datiuser.setEmail(Email);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        cbfilter.getItems().addAll( "ric_trascrittore", "Trascrittori");
        cbfilter.setValue("ric_trascrittore");

        col_username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        col_cognome.setCellValueFactory(new PropertyValueFactory<>("Cognome"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("Email"));

        oblist = FXCollections.observableArrayList();
    }

    //Metodo per settare la Tableview con i valori presi dal DB
    private void setTable (String Username, String Nome, String Cognome, String Email) throws IOException
    {
        oblist.add(new InfoUserTable(Username, Nome, Cognome, Email));
        tablesearch.setItems(oblist);
    }


}
