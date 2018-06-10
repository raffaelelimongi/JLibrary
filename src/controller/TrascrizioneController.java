package controller;

import dao.Interface.TrascrizioneQueryInterface;
import dao.TrascrizioneQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.UserModel;
import model.TrascrizioneDati;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TrascrizioneController implements Initializable
{
    @FXML
    private TableView<TrascrizioneDati> tabletrascrizioni;
    @FXML
    private TableColumn<TrascrizioneDati,String> col_titolo2, col_link;

    private ObservableList<TrascrizioneDati> oblist;

    TrascrizioneQueryInterface trascrQueryInterface = new TrascrizioneQuery();
    TrascrizioneDati trascrizioneDati= new TrascrizioneDati(titolo,null);
    UserModel userModel =UserModel.getInstance();
    
    String titolo;

    public TrascrizioneController() throws IOException {

    }
    public void setScene(ActionEvent event)
    {
        Parent root;
        try
        {
            root = FXMLLoader.load(HomePageController.class.getResource("../view/trascrizione.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Trascrizione");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void gotohome(ActionEvent event)
    {
        HomePageController.setscene(event);
    }

    //in questo metodo carico il titolo dell'opera da trascrivere nella tabella per poterlo visualizzare
    public void getTitolo() throws SQLException, IOException
    {
        oblist.removeAll(oblist);

        ResultSet resultSet = trascrQueryInterface.getTrascrizioniList(userModel.getUsername(),userModel.getPrivilegio());

        while (resultSet.next())
        {
            //controllo se la trascrizione è stata accettata o meno, se non è stata accettata la carico nella tabella
            if (!resultSet.getBoolean("op.accept"))
            {
                    //setto le variabili con le informazioni presenti nel DB e le passo al metodo setTable
                    titolo = resultSet.getString("o.titolo");
                    setTable(titolo);
                    trascrizioneDati.setTitolo(titolo);
            }
        }

        if(userModel.getPrivilegio().equals("supervisor") || userModel.getPrivilegio().equals("admin"))
        {
            ResultSet result = trascrQueryInterface.getUserAbility(); //effettuo la query per selezionare tutte le trascrizioni aperte nel caso in cui l'utente è un supervisor.
            while (result.next())
            {
                    titolo = result.getString("o.titolo");
                    setTable(titolo);
                    trascrizioneDati.setTitolo(titolo);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        col_titolo2.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        col_link.setCellValueFactory(new PropertyValueFactory<>("link"));

        oblist=FXCollections.observableArrayList();

        try {
            getTitolo();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Metodo per settare la Tableview con i valori presi dal DB
    private void setTable(String titolo) throws IOException
    {
        oblist.add(new TrascrizioneDati(titolo,null));
        tabletrascrizioni.setItems(oblist);
    }
}
