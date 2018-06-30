package dao;

import dao.Interface.UserAuthenticationInterface;
import model.UserModel;

import java.sql.*;

public class UserAuthenticationQuery implements UserAuthenticationInterface
{
    PreparedStatement ps;

    public UserAuthenticationQuery()
    {

    }

    @Override
    public ResultSet UserAuthenticationQuery(String username1,String password1) throws SQLException
    {
        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT username,password FROM utente  WHERE  USERNAME=? AND PASSWORD =?";
        ps = connection.prepareStatement(sql);
        ps.setString(1,username1);
        ps.setString(2,password1);
        
        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        return resultSet;
    }


    @Override
    public int SignInQuery(UserModel user) throws SQLException
    {
        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        String sql= "INSERT INTO utente(username,password,email,nome,cognome) VALUES (?,?,?,?,?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1,user.getUsername());
        ps.setString(2,user.getPassword());
        ps.setString(3,user.getEmail());
        ps.setString(4,user.getNome());
        ps.setString(5,user.getCognome());
        ps.execute();

        String sql2= "INSERT INTO ruolo(privilegio,IDutente) VALUES (?,(SELECT ID FROM utente WHERE username=?))";
        ps = connection.prepareStatement(sql2);
        ps.setString(1,"utente base");
        ps.setString(2,user.getUsername());

        return ps.executeUpdate(); //ritorno l'esecuzione della query
    }
}
