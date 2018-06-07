package controller;

import dao.DeleteUserQuery;
import dao.InfoUserTableQuery;
import dao.Interface.DeleteUserInterface;
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
import vo.InfoUserTable;
import vo.OperaMetadati;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminPannelController implements Initializable {


    @FXML
    public TextField tfricerca;

    @FXML
    private Button butdelete;

    @FXML
    public Button butuser;

    @FXML
    private Button butsuperv;

    @FXML
    private Button srcbtn;

    @FXML
    public Button buthome;

    @FXML
    private CheckBox cbsuperv;

    @FXML
    private CheckBox cbtrascr;

    @FXML
    private ChoiceBox<String> cbou;

    @FXML
    private TableView<InfoUserTable> tableView;

    @FXML
    private TableView<OperaMetadati> tableView1;

    @FXML
    private TableColumn<InfoUserTable, String> col_username;

    @FXML
    private TableColumn<OperaMetadati, String> col_username1;

    @FXML
    private TableColumn<InfoUserTable, String> col_email;

    @FXML
    private TableColumn<OperaMetadati, String> col_email1;

    @FXML
    private TableColumn<InfoUserTable, String> col_nome;

    @FXML
    private TableColumn<OperaMetadati, String> col_nome1;

    @FXML
    private TableColumn<InfoUserTable, String> col_cognome;

    @FXML
    private TableColumn<OperaMetadati, String> col_cognome1;

    private ObservableList<InfoUserTable> oblist;

    private ObservableList<OperaMetadati> oblist1;

    public String autore;
    public String titolo;
    public String genere;
    public String username;
    public String email;
    public String nome;
    public String cognome;


    InfoUserTable infoUserTable = new InfoUserTable(username, email, nome, cognome);

    ObservableList<String> List = FXCollections.observableArrayList("User", "Supervisor", "Trascriber");

    SearchOperaInterface searchOperaInterface = new SearchOperaQuery();
    DeleteUserInterface deleteUserInterface= new DeleteUserQuery();

    public AdminPannelController() throws IOException {
    }


    public static void gotoAdmin(ActionEvent event) {
        Parent root1;
        try {
            root1 = FXMLLoader.load(AdminPannelController.class.getResource("../view/AdminPanel.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AdminPanel");
            Scene home = new Scene(root1);
            stage.setScene(home);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoProfile(ActionEvent event) throws Exception {
        Stage stage1;
        Parent home1;

        stage1 = (Stage) butuser.getScene().getWindow();
        home1 = FXMLLoader.load(getClass().getResource("../view/Profile.fxml"));

        Scene homescene1 = new Scene(home1);
        stage1.setScene(homescene1);
        stage1.setTitle("Profile");

        stage1.show();
    }

    public void gotoHome(ActionEvent event) throws Exception {
        HomePageController.setscene(event);
    }

    public void searchuser() throws SQLException, IOException {
        tableView1.setVisible(false);
        tableView.setVisible(true);
        InfoUserTable userTable = new InfoUserTable("", "", "", "");
        tfricerca.setPromptText("");
        oblist.removeAll(oblist);
        String keywords = tfricerca.getText();
        ResultSet resultSet = InfoUserTableQuery.InfoUserTableGeneral(keywords);
        while (resultSet.next()) {
            username = resultSet.getString("username");
            email = resultSet.getString("email");
            nome = resultSet.getString("nome");
            cognome = resultSet.getString("cognome");
            if (!userTable.getUsername().equals(username)
                    || !userTable.getEmail().equals(email)
                    || !userTable.getNome().equals(nome)
                    || !userTable.getCognome().equals(cognome)) {
                setTable(username, email, nome, cognome);
            }
            userTable.setUsername(username);
            userTable.setEmail(email);
            userTable.setNome(nome);
            userTable.setCognome(cognome);
        }
        tfricerca.clear();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbou.getItems().addAll("Utente", "Opera");
        cbou.setValue("Opera");
        butdelete.setVisible(false);
        cbsuperv.setVisible(false);
        cbtrascr.setVisible(false);

        col_username1.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        col_email1.setCellValueFactory(new PropertyValueFactory<>("Autore"));
        col_nome1.setCellValueFactory(new PropertyValueFactory<>("Genere"));
        col_cognome1.setCellValueFactory(new PropertyValueFactory<>("View"));

        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_cognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));


        oblist = FXCollections.observableArrayList();

    }

    private void setTable(String u, String e, String n, String c) throws IOException {
        oblist.add(new InfoUserTable(u, e, n, c));
        tableView.setItems(oblist);
    }

    private void checkBoxCheck() throws SQLException, IOException {
        InfoUserTable infoUserTable = tableView.getSelectionModel().getSelectedItem();
        ResultSet resultSet = InfoUserTableQuery.InfoUserTableSupervisor(infoUserTable.getUsername());
        while (resultSet.next()) {

        }
    }

    public void gotoSupervisor(ActionEvent event) throws Exception {
        Stage stage1;
        Parent home1;

        stage1 = (Stage) butsuperv.getScene().getWindow();
        home1 = FXMLLoader.load(getClass().getResource("../view/gestioneuser.fxml"));

        Scene homescene1 = new Scene(home1);
        stage1.setScene(homescene1);
        stage1.setTitle("Supervisor Panel");

        stage1.show();
    }

    private void searchOpera() throws SQLException, IOException
    {
        tableView.setVisible(false);
        tableView1.setVisible(true);
        String keywords2;
        col_username1.setCellValueFactory(new PropertyValueFactory<>("Titolo"));
        col_email1.setCellValueFactory(new PropertyValueFactory<>("Autore"));
        col_nome1.setCellValueFactory(new PropertyValueFactory<>("Genere"));
        col_cognome1.setCellValueFactory(new PropertyValueFactory<>("View"));

        oblist1 = FXCollections.observableArrayList();
        OperaMetadati operadati = new OperaMetadati("", "", "");
        tfricerca.setPromptText("");
        oblist1.removeAll(oblist1);
        keywords2 = tfricerca.getText();
            if (!keywords2.equals("")) {
                keywords2 = tfricerca.getText();
                ResultSet resultSet = searchOperaInterface.SearchOperaQueryKeyword(keywords2);
                while (resultSet.next()) {
                    //setto le variabili con le informazioni presenti nel DB e le passo al metodo setTable
                    autore = resultSet.getString("autore");
                    titolo = resultSet.getString("titolo");
                    genere = resultSet.getString("c.nome");

                    setTable1(titolo, autore, genere);

                    operadati.setTitolo(titolo);
                    operadati.setAutore(autore);
                    operadati.setGenere(genere);
                }

            } else {
                tfricerca.setPromptText("Inserire almeno una lettera!");
            }

            tfricerca.clear();
    }

    private void setTable1(String titolo, String autore, String genere) throws IOException
    {
        oblist1.add(new OperaMetadati(titolo, autore, genere));
        tableView1.setItems(oblist1);
    }

    public void search1() throws SQLException, IOException
    {
        if(cbou.getValue().equals("Utente"))
        {
            butdelete.setVisible(true);
            cbsuperv.setVisible(true);
            cbtrascr.setVisible(true);
            searchuser();
        }
        if(cbou.getValue().equals("Opera"))
        {
            butdelete.setVisible(false);
            cbsuperv.setVisible(false);
            cbtrascr.setVisible(false);
            searchOpera();
        }
    }

    public void deleteAccount(ActionEvent event) throws SQLException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You are going to delete this Account. Are you sure?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
        {
            tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
            InfoUserTable Deleter= tableView.getSelectionModel().getSelectedItem();
            deleteUserInterface.DeleteUser(Deleter.getUsername());
        }
    }
}
