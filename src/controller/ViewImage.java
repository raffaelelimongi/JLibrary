package controller;

import dao.ImageQuery;
import dao.Interface.ImageQueryInterface;
import dao.Interface.TrascrizioneQueryInterface;
import dao.TrascrizioneQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ImmagineDati;
import model.OperaMetadati;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class ViewImage implements Initializable
{
    @FXML
    private TableView<ImmagineDati> tbwimage;
    @FXML
    private TableColumn<OperaMetadati,ImageView> col_image;
    @FXML
    private JFXButton btaccept, btdecline;

    private ObservableList<ImmagineDati> oblist;

    ImageQueryInterface imageQueryInterface = new ImageQuery();
    TrascrizioneQueryInterface createtrascr = new TrascrizioneQuery();

    public static String titolo;
    ArrayList<ImmagineDati> listimage = new ArrayList<>();

    public ViewImage()
    {
    }

    public void setTitolo(String tit)
    {
        titolo=tit;
    }

    public void LoadImage() throws SQLException
    {
        listimage = imageQueryInterface.LoadImage(titolo);
        setTable(listimage);
    }

    public void Accept() throws SQLException
    {
        createtrascr.Create(titolo);

        imageQueryInterface.Accept(listimage, titolo);

        Stage stage = (Stage) btaccept.getScene().getWindow();
        stage.close();
    }

    public void Decline() throws SQLException
    {
        imageQueryInterface.Decline(titolo);

        Stage stage = (Stage) btdecline.getScene().getWindow();
        stage.close();
    }

    private void setTable(ArrayList<ImmagineDati> listimage)
    {
        Iterator<ImmagineDati>itr=listimage.iterator();
        while (itr.hasNext())
        {
            oblist.add(itr.next());
            tbwimage.setItems(oblist);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        col_image.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        oblist=FXCollections.observableArrayList();

        try {
            LoadImage();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
