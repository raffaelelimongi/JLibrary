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
    private TextField txtuser,txtemail,txtnome,txtcognome;
    @FXML
    private PasswordField txtpass;
    @FXML
    private Label lbresult,lblevel,lbtrascrittore,lbvip,lbtxtvip,textlbtrasc,lbprivilegio;
    @FXML
    private Button btvip,bttrasc;

    UserModel userModel = UserModel.getInstance();

    UserInfoInterface userInfoInterface = new UserInfoQuery();

    public ViewProfileController()
    {

    }

    public void backhome(ActionEvent event)
    {
        JavaFXController.sethome(event);
    }

    public void userInfo()
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
        userModel.setPassword(txtpass.getText());
        userModel.setEmail(txtemail.getText());
        userModel.setNome(txtnome.getText());
        userModel.setCognome(txtcognome.getText());

        int resultSet = userInfoInterface.UpdateInfo(userModel);

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
            userModel.setVip(true);
            userInfoInterface.VipQuery(userModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lbtxtvip.setVisible(false);
        btvip.setVisible(false);
    }

    public void Trascrittore() throws SQLException
    {
        lbtrascrittore.setText("SI");
        userModel.setRictrascrittore(true);
        userInfoInterface.Trascrittore(userModel);
        textlbtrasc.setVisible(false);
        bttrasc.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        userInfo();
    }
}
