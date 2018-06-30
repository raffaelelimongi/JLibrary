package controller;

import dao.UserAuthenticationQuery;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.UserModel;

import java.sql.SQLException;

public class SignUpController
{
    //inizializzo tutti i vari oggetti grafici
    @FXML
    private Label error;
    @FXML
    private TextField txtuser,txtname,txtsurname,txtemail;
    @FXML
    private PasswordField txtpass,txtrpass;

    UserModel user= UserModel.getInstance();

    public SignUpController()
    {
    }

    public void Submit(ActionEvent event)
    {
        //Controllo che nei campi Username,password,email non venga inserito un testo vuoto
        if (!txtpass.getText().matches("") && !txtuser.getText().matches("") && !txtemail.getText().matches(""))
        {

            //controllo che il PasswordField txtpass sia uguale a quello del PasswordField txtrpass
            if (txtpass.getText().equals(txtrpass.getText()))
            {

                try {
                    UserAuthenticationQuery userDaoInterface = new UserAuthenticationQuery();

                    user.setUsername(txtuser.getText());
                    user.setPassword(txtpass.getText());
                    user.setNome(txtname.getText());
                    user.setCognome(txtsurname.getText());
                    user.setEmail(txtemail.getText());

                    int result= userDaoInterface.SignInQuery(user);
                    if (result == 1) {
                        gotologin(event);
                    } else {
                        error.setText("Errore");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                error.setText("Error,The two PasswordField don't match, try again");
            }
        } else {
            if (txtuser.getText().equals("")) {
                error.setText("Error,Username empty,try again");
            } else {
                if (txtpass.getText().equals("")) {
                    error.setText("Error,Password empty,try again");
                } else {
                    error.setText("Error,Email empty,try again");
                }
            }
            {

            }
        }
    }
    public  void gotologin(ActionEvent event)
    {
        JavaFXController.setLogin(event);
    }
}
