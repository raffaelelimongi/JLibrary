package controller;

import dao.Interface.ImageQueryInterface;
import dao.Interface.TrascrizioneQueryInterface;
import dao.TrascrizioneQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.UserModel;
import model.ImmagineDati;
import model.OperaMetadati;
import javafx.scene.control.TableView;
import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private JFXButton btclose,btaccept,btdecline,btsave;

    private ObservableList<ImmagineDati> oblist;
    private Image image;
    TrascrizioneQueryInterface trascrQueryInterf = new TrascrizioneQuery();
    ImageQueryInterface trascmanage = new TrascrizioneQuery();
    static  String titolo;

    public TeiEditor()
    {
    }

    public static void setscene(String titolo1) throws IOException
    {
        titolo=titolo1;
        // Setto la scena per l'apertura della finestra del teiEditor di quella determinata opera
        Parent root;

        root = FXMLLoader.load(TrascrizioneController.class.getResource("../view/teieditor.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Trascrizione");
        Scene home = new Scene(root);
        stage.setScene(home);
        stage.show();
    }

    //metodo per caricare il testo dal DB al Text
    public void LoadText() throws SQLException, IOException
    {

        ResultSet resultSet = trascrQueryInterf.loadtext(titolo); //faccio la query per caricarmi le info delle opere da trascrivere

        while (resultSet.next())
        {
                texttrascrizione.setText(resultSet.getString("op.testo"));
                titolo = resultSet.getString("o.titolo");
          /*   2 modo per visualizzare le immagini ma poco efficente e sicuro,anche se un po piu reale

            InputStream is = resultSet.getBinaryStream("i.image");
            OutputStream os = new FileOutputStream(new File("photo.jpg"));
            byte[] content = new byte[1024];
            int size ;
            while((size = is.read(content)) != -1)
            {
                os.write(content,0,size);
            }
            os.close();
            is.close();

            image = new Image("file:photo.jpg", 1000, 500, true, true);
          ImageView imageView = new ImageView(image);

          */
                File file = new File(resultSet.getString("i.image"));
                image = new Image(file.toURI().toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(1000);
                imageView.setFitHeight(500);
                imageView.setPreserveRatio(true);
                setTable(titolo, imageView);
            }
        }


    public void SaveText() throws SQLException
    {
        trascrQueryInterf.savetext(titolo,texttrascrizione.getText());
    }

    //metodo per chiudere lo stage TeiEditor
    public void Close()
    {
        Stage stage = (Stage) btclose.getScene().getWindow();
        stage.close();
    }

    public void Decline() throws SQLException
    {
        trascmanage.Decline("",titolo);
    }

    public void Accept() throws SQLException
    {
        trascmanage.Accept(null,texttrascrizione.getText());
        Stage stage = (Stage) btaccept.getScene().getWindow();
        stage.close();
    }

    private void setTable(String titolo, ImageView imageView2) throws IOException
    {
        oblist.add(new ImmagineDati(titolo,imageView2,titolo,"","",null));
        tbwimage.setItems(oblist);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
