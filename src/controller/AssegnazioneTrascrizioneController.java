package controller;

import dao.Interface.SearchOperaInterface;
import dao.Interface.TrascrizioneQueryInterface;
import dao.SearchOperaQuery;
import dao.TrascrizioneQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.OperaMetadati;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private TableColumn<OperaMetadati,String> col_titolo;
    @FXML
    private TableColumn<OperaMetadati,String> col_autore;

    private ObservableList<OperaMetadati> oblist;

    SearchOperaInterface assegnatrascrizione= new TrascrizioneQuery();
    TrascrizioneQueryInterface assegnatrascrizione2= new TrascrizioneQuery();

    public AssegnazioneTrascrizioneController()
    {}

    public static void setscene()
    {
        Parent root;
        try
        {
            root = FXMLLoader.load(HomePageController.class.getResource("../view/assegnatrascrizione.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Trascrizione");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void CaricaOpere() throws SQLException, IOException
    {
        oblist.removeAll(oblist);

        ResultSet resultSet = assegnatrascrizione.SearchOperaSoft();
        while (resultSet.next())
        {
            setTable(resultSet.getString("titolo"), resultSet.getString("autore"));
        }
    }

    public void Assegna() throws SQLException
    {
        if(textuser.getText().equals("") || textTitolo.getText().equals("") || textautore.getText().equals(""))
        {
            lbmex.setText("Errore nessun campo deve essere vuoto");
                } else {
                    int result = assegnatrascrizione2.AssegnaTrascrizione(textTitolo.getText(), textautore.getText(), textuser.getText());
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
    private void setTable(String titolo, String autore) throws IOException
    {
        oblist.add(new OperaMetadati(titolo, autore, "",null));
        table.setItems(oblist);
    }
}
