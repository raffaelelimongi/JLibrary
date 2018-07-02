package controller;

import dao.Interface.TrascrizioneQueryInterface;
import dao.TrascrizioneQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.UserModel;
import model.TrascrizioneDati;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class TrascrizioneController implements Initializable
{
    @FXML
    private TableView<TrascrizioneDati> tabletrascrizioni;
    @FXML
    private TableColumn<TrascrizioneDati,String> col_titolo2;
    @FXML
    private TableColumn<TrascrizioneDati,String> col_link;

    private ObservableList<TrascrizioneDati> oblist;

    TrascrizioneQueryInterface trascrQueryInterface = new TrascrizioneQuery();

    UserModel userModel =UserModel.getInstance();
    ArrayList<TrascrizioneDati> listtrasc= new ArrayList<>();

    public TrascrizioneController()
    {
    }

    public void gotohome(ActionEvent event)
    {
        new JavaFXController().sethome(event);
    }

    //in questo metodo carico il titolo dell'opera da trascrivere nella tabella per poterlo visualizzare
    public void getTitolo() throws SQLException
    {
        oblist.removeAll(oblist);
        if(userModel.getPrivilegio().equals("supervisor") || userModel.getPrivilegio().equals("admin"))
        {
                listtrasc = trascrQueryInterface.getTrascUserAbility(); //effettuo la query per selezionare tutte le trascrizioni aperte nel caso in cui l'utente è un supervisor.
                setTable(listtrasc);
            }
        else {

            listtrasc = trascrQueryInterface.getTrascrizioniList(userModel);
            setTable(listtrasc);
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
        }
    }
    //Metodo per settare la Tableview con i valori presi dal DB
    private void setTable(ArrayList<TrascrizioneDati>trasclist)
    {
        Iterator<TrascrizioneDati>itr=trasclist.iterator();
        while(itr.hasNext())
        {
            oblist.add(itr.next());
            tabletrascrizioni.setItems(oblist);
        }
    }
}
