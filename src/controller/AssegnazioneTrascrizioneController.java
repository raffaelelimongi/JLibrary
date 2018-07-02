package controller;

import dao.Interface.TrascrizioneQueryInterface;
import dao.TrascrizioneQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.InfoUserTable;
import model.OperaMetadati;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class AssegnazioneTrascrizioneController implements Initializable
{
    @FXML
    private TextField textuser,textTitolo,textautore;
    @FXML
    private Label lbmex;
    @FXML
    private TableView<OperaMetadati> table;
    @FXML
    private TableColumn<OperaMetadati,String> col_titolo, col_autore;

    private ObservableList<OperaMetadati> oblist;

    TrascrizioneQueryInterface searchopera= new TrascrizioneQuery();
    TrascrizioneQueryInterface assegnatrascrizione2= new TrascrizioneQuery();

    ArrayList<OperaMetadati> listoperatrascr = new ArrayList<>();

    public AssegnazioneTrascrizioneController()
    {}

    public void CaricaOpere() throws SQLException
    {
        oblist.removeAll(oblist);

        listoperatrascr = searchopera.SearchOperaSoft();

        setTable(listoperatrascr);
    }

    public void Assegna() throws SQLException, IOException
    {
        if(textuser.getText().equals("") || textTitolo.getText().equals("") || textautore.getText().equals(""))
        {
            lbmex.setText("Errore nessun campo deve essere vuoto");
                } else {
                    int result = assegnatrascrizione2.AssegnaTrascrizione(new OperaMetadati(textTitolo.getText(),textautore.getText(),null,null),new InfoUserTable( textuser.getText(),null,null,null,null));
                    if (result == 1) {
                        lbmex.setText("Assegnazione riuscita!");
                    } else {
                        lbmex.setText("ERRORE,RIPROVA!");
                    }
        }

    }

    //metodo per inizializzare il ChoiseBox,tableview e l'observablelist
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        col_titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        col_autore.setCellValueFactory(new PropertyValueFactory<>("autore"));

        oblist=FXCollections.observableArrayList(); //dichiaro l'oblist nel metodo initialize della classe

    }

    //Metodo per settare la Tableview con i valori presi dal DB
    private void setTable(ArrayList<OperaMetadati> listopere)
    {
        Iterator<OperaMetadati>itr=listopere.iterator();

        while(itr.hasNext())
        {
            oblist.add(itr.next());
            table.setItems(oblist);
        }
    }
}
