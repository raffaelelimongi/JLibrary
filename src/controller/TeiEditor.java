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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.TrascrizioneDati;
import model.UserModel;
import model.ImmagineDati;
import model.OperaMetadati;
import javafx.scene.control.TableView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import  com.jfoenix.controls.JFXButton;

public class TeiEditor implements Initializable
{
    @FXML
    private com.jfoenix.controls.JFXTextArea texttrascrizione;
    @FXML
    private TableView<ImmagineDati> tbwimage;
    @FXML
    private TableColumn<OperaMetadati,ImageView> col_image;
    @FXML
    private JFXButton btclose,btaccept,btdecline;

    private ObservableList<ImmagineDati> oblist;

    ImageQueryInterface imagequery = new ImageQuery();
    TrascrizioneQueryInterface trascrQueryInterf = new TrascrizioneQuery();
    TrascrizioneQueryInterface trascmanage = new TrascrizioneQuery();
    static TrascrizioneDati trasc;
    ArrayList<ImmagineDati>imagedata=new ArrayList<>();

    public TeiEditor()
    {
    }

    public void setTitolo(TrascrizioneDati trascrizioneDati)
    {
        trasc=trascrizioneDati;
    }

    //metodo per caricare il testo dal DB al Text
    public void LoadText() throws SQLException
    {
        trasc = trascrQueryInterf.loadtext(trasc); //faccio la query per caricarmi le info delle opere da trascrivere
        texttrascrizione.setText(trasc.getTesto());

        imagedata= imagequery.LoadImageOpera(trasc.getTitolo());
        setTable(imagedata);

        }
    public void SaveText() throws SQLException
    {
        trasc.setTesto(texttrascrizione.getText());
        trascrQueryInterf.savetext(trasc);
    }

    //metodo per chiudere lo stage TeiEditor
    public void Close()
    {
        Stage stage = (Stage) btclose.getScene().getWindow();
        stage.close();
    }

    public void Decline() throws SQLException
    {
        trascmanage.Decline(trasc);
    }

    public void Accept() throws SQLException
    {
        trascmanage.Accept(trasc);
        Stage stage = (Stage) btaccept.getScene().getWindow();
        stage.close();
    }

    private void setTable(ArrayList<ImmagineDati>imagez)
    {
        Iterator<ImmagineDati>itr=imagez.iterator();

        while ((itr.hasNext()))
        {
            oblist.add(itr.next());
            tbwimage.setItems(oblist);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        UserModel user = UserModel.getInstance();
        col_image.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        oblist=FXCollections.observableArrayList();

        if(user.getPrivilegio().equals("utente base"))
        {
            btaccept.setVisible(false);
            btdecline.setVisible(false);
        }

        try {
            LoadText();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
