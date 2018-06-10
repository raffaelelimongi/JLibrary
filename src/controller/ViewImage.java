package controller;

import dao.ImageQuery;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ImmagineDati;
import model.OperaMetadati;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewImage implements Initializable {
    @FXML
    private TableView<ImmagineDati> tbwimage;
    @FXML
    private TableColumn<OperaMetadati, ImageView> col_image;
    @FXML
    private JFXButton btaccept, btdecline;

    private ObservableList<ImmagineDati> oblist;

    ImageQueryInterface imageQueryInterface = new ImageQuery();
    TrascrizioneQueryInterface createtrascr = new TrascrizioneQuery();

    public static String titolo;
    private Image image;
    private List<Image> list = new ArrayList<>();
    private String[] name = new String[20];
    public int i = 0;

    public ViewImage() {
    }

    public void setscene(String tit) {
        titolo = tit;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/imageview.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Manage Image");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoadImage() throws SQLException, IOException {
        ResultSet resultSet = imageQueryInterface.LoadImage(titolo);

        while (resultSet.next()) {
            File file = new File(resultSet.getString("i.image"));
            image = new Image(file.toURI().toString());
            list.add(image);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(1000);
            imageView.setFitHeight(500);
            imageView.setPreserveRatio(true);
            setTable(name[i] = resultSet.getString("i.nome"), imageView);
            i++;
        }
    }

    public void Accept() throws SQLException {
        createtrascr.Create();
        for (int i = 0; i < list.size(); i++)
        {
            imageQueryInterface.Accept(name[i], titolo);
        }
        Stage stage = (Stage) btaccept.getScene().getWindow();
        stage.close();
    }

    public void Decline() throws SQLException {
        for (int i = 0; i < list.size(); i++) {
            imageQueryInterface.Decline(name[i], titolo);
        }
        Stage stage = (Stage) btdecline.getScene().getWindow();
        stage.close();
    }

    private void setTable(String name, ImageView imageView2) throws IOException {
        oblist.add(new ImmagineDati(name, imageView2, "", "", "", null));
        tbwimage.setItems(oblist);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_image.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        oblist = FXCollections.observableArrayList();

        try {
            LoadImage();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
