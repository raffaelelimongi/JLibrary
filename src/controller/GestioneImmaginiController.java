package controller;

import dao.ImageQuery;
import dao.Interface.ImageQueryInterface;
import dao.Interface.OperaInfoInterface;
import dao.OperaInfoQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ImmagineDati;
import model.OperaMetadati;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestioneImmaginiController implements Initializable
{
    @FXML
    private TableView<ImmagineDati> tablemanage;
    @FXML
    private TableColumn<ImmagineDati,String> col_titolo, col_image;

    private ObservableList<ImmagineDati> oblist;

    OperaInfoInterface operainfo = new OperaInfoQuery();
    ImageQueryInterface listimage = new ImageQuery();

    ArrayList<OperaMetadati> listopere;
    ArrayList<ImmagineDati> imagelist;

    public GestioneImmaginiController()
    {}
    public void gotohome(ActionEvent event)
    {
        JavaFXController.sethome(event);
    }

    //metodo per caricare il testo dal DB al Text
    public void LoadInfoOpera() throws SQLException
    {
        listopere = operainfo.LoadOpera();  //faccio le query per caricarmi le info delle opere relative alle immagini da revisionare
        setTable(listopere);
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
        }
    }
    //Metodo per settare la Tableview con i valori presi dal DB

    private void setTable(ArrayList<OperaMetadati> listop) throws SQLException
    {
        for(int i=0;i<listop.size();i++)
        {
            imagelist= listimage.LoadImage(listop.get(i).getTitolo());
            oblist.add(new ImmagineDati("",null, imagelist.get(i).getImageView(), listop.get(i).getTitolo(), "", "", null));
            tablemanage.setItems(oblist);
        }
    }
}
