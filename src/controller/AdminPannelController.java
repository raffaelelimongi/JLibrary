package controller;

import dao.Interface.SearchOperaInterface;
import dao.Interface.UserInfoInterface;
import dao.OperaInfoQuery;
import dao.SearchOperaQuery;
import dao.UserInfoQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.InfoUserTable;
import model.OperaMetadati;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminPannelController implements Initializable
{

    @FXML
    public TextField tfricerca;
    @FXML
    private Button butdelete,btdeleteopera;
    @FXML
    private CheckBox cbsuperv,cbadmin;
    @FXML
    private ChoiceBox<String> cbou;
    @FXML
    private TableView<InfoUserTable> tableView;
    @FXML
    private TableView<OperaMetadati> tableView1;
    @FXML
    private TableColumn<InfoUserTable, String> col_username;
    @FXML
    private TableColumn<OperaMetadati, String> col_nomeop;
    @FXML
    private TableColumn<InfoUserTable, String> col_email;
    @FXML
    private TableColumn<OperaMetadati, String> col_autoreop;
    @FXML
    private TableColumn<InfoUserTable, String> col_nome;
    @FXML
    private TableColumn<OperaMetadati, String> col_genereop;
    @FXML
    private TableColumn<InfoUserTable, String> col_cognome;
    @FXML
    private TableColumn<OperaMetadati, String> col_dataop;
    @FXML
    private TableColumn<OperaMetadati, String> col_viewop;

    private ObservableList<InfoUserTable> oblist;

    private ObservableList<OperaMetadati> oblist1;

    public String autore,titolo,genere,username,email,nome,cognome,privilegio;
    public int id;
    public Date data;

    UserInfoInterface userInfoInterface = new UserInfoQuery();
    OperaInfoQuery operainfoInterface = new OperaInfoQuery();
    SearchOperaInterface searchOperaInterface = new SearchOperaQuery();

    ArrayList<InfoUserTable> listuser= new ArrayList<>();

    public AdminPannelController() {
    }

    public void gotoProfile(ActionEvent event)  {
      JavaFXController.setProfile(event);
    }

    public void gotoHome(ActionEvent event) {
        JavaFXController.sethome(event);
    }

    private void searchuser() throws SQLException
    {
        tableView1.setVisible(false);
        tableView.setVisible(true);
        butdelete.setVisible(true);
        btdeleteopera.setVisible(false);

        oblist.removeAll(oblist);
        listuser = userInfoInterface.GetListUser(tfricerca.getText());

        setTable(listuser);

        tfricerca.clear();

    }

    public void gotoSupervisor(ActionEvent event) {
       JavaFXController.setManageUser(event);
    }

    private void searchOpera() throws SQLException, IOException
    {
        tableView.setVisible(false);
        tableView1.setVisible(true);
        btdeleteopera.setVisible(true);
        butdelete.setVisible(false);

        String keywords2;

        tfricerca.setPromptText("");
        oblist1.removeAll(oblist1);

            keywords2 = tfricerca.getText();
            ResultSet resultSet = searchOperaInterface.SearchOperaQueryAdmin(keywords2,"");
            while (resultSet.next()) {
                //setto le variabili con le informazioni presenti nel DB e le passo al metodo setTable
                autore = resultSet.getString("autore");
                titolo = resultSet.getString("titolo");
                genere = resultSet.getString("c.nome");
                data= resultSet.getDate("data_pubb");


                setTable1(titolo, autore, genere,data);

            }
        tfricerca.clear();
    }

    @FXML
    private void search1() throws SQLException, IOException
    {
        if(cbou.getValue().equals("Utente")) {
            searchuser();
        }else{
            searchOpera();
        }
    }

    @FXML
    private void DeleteAccount() throws SQLException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You are going to delete this Account. Are you sure?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
        {
            InfoUserTable Deleter= tableView.getSelectionModel().getSelectedItem();
            tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
            userInfoInterface.DeleteUser(Deleter.getUsername());
        }
    }

    @FXML
    private void DeleteOpera() throws SQLException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You are going to delete this Opera. Are you sure?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
        {
            OperaMetadati DeleteOP = tableView1.getSelectionModel().getSelectedItem();
            tableView1.getItems().removeAll(tableView1.getSelectionModel().getSelectedItem());
            operainfoInterface.DeleteOpera(DeleteOP.getTitolo(),DeleteOP.getAutore());
        }
    }

    @FXML
    private void cbSupV() throws SQLException
    {
        InfoUserTable superMan= tableView.getSelectionModel().getSelectedItem();
        if (cbsuperv.isSelected())
            userInfoInterface.setSupervisorQuery(superMan);
        else
        {
            if (cbadmin.isSelected())
                userInfoInterface.setAdminQuery(superMan);
            else
                userInfoInterface.setUserQuery(superMan);
        }
    }

    @FXML
    private void cbAdmin() throws SQLException
    {
        InfoUserTable superMan = tableView.getSelectionModel().getSelectedItem();
        if (cbadmin.isSelected()) {
            userInfoInterface.setAdminQuery(superMan);
        } else {
            if (cbsuperv.isSelected()) {
                userInfoInterface.setSupervisorQuery(superMan);
            } else {
                userInfoInterface.setUserQuery(superMan);
            }
        }
    }

    public void cbSet()
    {
        InfoUserTable setter= tableView.getSelectionModel().getSelectedItem();
        if (setter.getPrivilegio().equals("admin"))
        {
            cbadmin.setSelected(true);
            cbsuperv.setSelected(false);
        }
        else
        {
            if (setter.getPrivilegio().equals("supervisor"))
            {
                cbadmin.setSelected(false);
                cbsuperv.setSelected(true);
            }
            else
            {
                cbadmin.setSelected(false);
                cbsuperv.setSelected(false);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbou.getItems().addAll("Utente", "Opera");
        cbou.setValue("Opera");

        butdelete.setVisible(false);

        col_nomeop.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        col_autoreop.setCellValueFactory(new PropertyValueFactory<>("Autore"));
        col_genereop.setCellValueFactory(new PropertyValueFactory<>("Genere"));
        col_dataop.setCellValueFactory(new PropertyValueFactory<>("datapubb"));
        col_viewop.setCellValueFactory(new PropertyValueFactory<>("View"));

        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_cognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));

        oblist = FXCollections.observableArrayList();
        oblist1 = FXCollections.observableArrayList();
    }

    private void setTable(ArrayList<InfoUserTable>user)
    {
        for(int i=0;i<user.size();i++)
        {
            oblist.add(user.get(i));
            tableView.setItems(oblist);
        }
    }

    private void setTable1(String titolo, String autore, String genere,Date data) throws IOException
    {
        oblist1.add(new OperaMetadati(titolo, autore, genere, data));
        tableView1.setItems(oblist1);
    }
}
