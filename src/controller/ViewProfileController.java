package controller;

import dao.Interface.UserInfoInterface;
import dao.UserInfoQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.UserModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewProfileController implements Initializable {
    @FXML
    private TextField txtuser;
    @FXML
    private PasswordField txtpass;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtnome;
    @FXML
    private TextField txtcognome;
    @FXML
    private Label lbresult;
    @FXML
    private Label lblevel;
    @FXML
    private Label lbtrascrittore;
    @FXML
    private Label lbvip;
    @FXML
    private Button btvip;
    @FXML
    private Label lbtxtvip;
    @FXML
    private Label textlbtrasc;
    @FXML
    private Button bttrasc;
    @FXML
    private Label lbprivilegio;

    UserModel userModel = UserModel.getInstance();

    UserInfoInterface userInfoInterface = new UserInfoQuery();

    public ViewProfileController()
    {

    }

    //metodo che carica la view per la visualizzazione del profilo
    public static void ViewProfile(ActionEvent event) throws SQLException
    {
        Parent root;
        try {
            root = FXMLLoader.load(ViewProfileController.class.getResource("../view/profile.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Profile");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backhome(ActionEvent event)
    {
        HomePageController.setscene(event);
    }

    public void userInfo() throws SQLException
    {
        txtuser.setText(userModel.getUsername());
        txtpass.setText(userModel.getPassword());
        txtemail.setText(userModel.getEmail());
        txtnome.setText(userModel.getNome());
        txtcognome.setText(userModel.getCognome());
        lblevel.setText(String.valueOf(userModel.getLivello()));
        lbprivilegio.setText(userModel.getPrivilegio());

        if (userModel.getTrascrittore() == false)
        {
            lbtrascrittore.setText("NO");
        } else {
            lbtrascrittore.setText("SI");
            textlbtrasc.setVisible(false);      //nascondo la scritta del diventare un trascrittore
            bttrasc.setVisible(false);          //nascondo il button per richiedere di diventare un trascrittore
            }

            if (userModel.isVip() == false)
            {
            lbvip.setText("NO");
            } else {
            lbvip.setText("SI");
            lbtxtvip.setVisible(false);         //nascondo la scritta deldiventare un vip
            btvip.setVisible(false);            // nascondo il button per diventare un vip
            }
            txtuser.setDisable(true);           //Disabilito la possibilità di cambiare username
    }

    //Metodo per l'update dei dati nel profilo dell'utente
    public void updateinfo() throws SQLException
    {
        int resultSet = userInfoInterface.UpdateInfo(userModel.getUsername(), txtpass.getText(), txtemail.getText(), txtnome.getText(), txtcognome.getText());

        if (resultSet == 1)
        {
            lbresult.setText("Update riuscito!!");
        } else
            {
            lbresult.setText("Update NON riuscito!!");
             }
    }

    public void Vip()
    {
        lbvip.setText("SI");
        try {
            userInfoInterface.VipQuery(userModel.getUsername(),1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lbtxtvip.setVisible(false);
        btvip.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try {
            userInfo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}