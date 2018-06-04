package dao;

import dao.Interface.UserDaoInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserAuthenticationQuery implements UserDaoInterface
{
    public UserAuthenticationQuery()
    {
        
    }

    public ResultSet UserAuthenticationQuery(String username1,String password1) throws SQLException
    {

        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT username,password FROM utente  WHERE  USERNAME= '"
                + username1 + "' AND PASSWORD ='" + password1 + "'";

        //ritorno il sisultato della query
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    @Override
    public int SignInQuery(String user, String pass, String nome, String cognome, String email) throws SQLException
    {
        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();

        String sql= "INSERT INTO utente(username,password,email,nome,cognome) VALUES ('"
                +user+"','"+pass+"','"+email+"','"+nome+"','"+cognome+"')";
        statement.executeUpdate(sql);

        String sql2= "INSERT INTO ruolo(privilegio,IDutente) VALUES ('utente base',(SELECT ID FROM utente WHERE username = '"+user+"'))";
        return statement.executeUpdate(sql2); //ritorno l'esecuzione della query
    }

}
