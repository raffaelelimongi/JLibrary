package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SignInQuery {

    public SignInQuery(){


    }

    public int SignInQuery1(String user, String pass, String nome, String cognome, String email) throws SQLException {


        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();
        String sql= "INSERT INTO utente(username,password,email,nome,cognome) VALUES ('"
                + user + "', '"+ pass + "', '"+ email + "' , '"+ nome + "' , '"+ cognome + "')";
        return statement.executeUpdate(sql);

    }
}
