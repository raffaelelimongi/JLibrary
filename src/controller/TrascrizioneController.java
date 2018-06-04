package controller;

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
import vo.OperaMetadati;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TrascrizioneController implements Initializable
{
    @FXML
    private TableView<OperaMetadati> tabletrascrizioni;
    @FXML
    private TableColumn<OperaMetadati,String> col_titolo;
    @FXML
    private TableColumn<OperaMetadati,String> col_link;

    private ObservableList<OperaMetadati> oblist;

    public TrascrizioneController()
    {

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

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        col_titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        col_link.setCellValueFactory(new PropertyValueFactory<>("view"));

        oblist=FXCollections.observableArrayList();
    }

    //Metodo per settare la Tableview con i valori presi dal DB
    private void setTable(String titolo) throws IOException
    {
        oblist.add(new OperaMetadati(titolo, "", ""));
        tabletrascrizioni.setItems(oblist);
    }
}
