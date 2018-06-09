package controller;

import dao.Interface.OperaInfoInterface;
import dao.OperaInfoQuery;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ImmagineDati;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GestioneImmaginiController implements Initializable
{
    @FXML
    private TableView<ImmagineDati> tablemanage;
    @FXML
    private TableColumn<ImmagineDati,String> col_titolo;
    @FXML
    private TableColumn<ImmagineDati,String> col_image;

    private ObservableList<ImmagineDati> oblist;

    public String titolo;

    OperaInfoInterface operainfo = new OperaInfoQuery();

    public GestioneImmaginiController()
    {}

    public void setscene(ActionEvent event)
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/gestioneimage.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Manage Image");
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

    //metodo per caricare il testo dal DB al Text
    public void LoadInfoOpera() throws SQLException, IOException
    {
        ResultSet resultSet = operainfo.LoadOpera(titolo);  //faccio la query per caricarmi le immagini da revisionare

        while (resultSet.next())
        {
            titolo = resultSet.getString("titolo");
            setTable(titolo, null);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        col_titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("link"));

        oblist=FXCollections.observableArrayList();

        try {
            LoadInfoOpera();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Metodo per settare la Tableview con i valori presi dal DB

    private void setTable(String titolo, ImageView imageView2) throws IOException
    {
        oblist.add(new ImmagineDati("",imageView2,titolo,"","",null));
        tablemanage.setItems(oblist);
    }
}
