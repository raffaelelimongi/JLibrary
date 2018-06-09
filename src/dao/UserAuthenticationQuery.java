<<<<<<< HEAD
package dao;

import dao.Interface.UserAuthenticationInterface;

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
    public int SignInQuery(String user, String pass, String nome, String cognome, String email) throws SQLException
    {
        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        String sql= "INSERT INTO utente(username,password,email,nome,cognome) VALUES (?,?,?,?,?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1,user);
        ps.setString(2,pass);
        ps.setString(3,email);
        ps.setString(4,nome);
        ps.setString(5,cognome);
        ps.execute();

        String sql2= "INSERT INTO ruolo(privilegio,IDutente) VALUES (?,(SELECT ID FROM utente WHERE username=?))";
        ps = connection.prepareStatement(sql2);
        ps.setString(1,"utente base");
        ps.setString(2,user);

        return ps.executeUpdate(); //ritorno l'esecuzione della query
    }
}
=======
package dao;

import dao.Interface.UserAuthenticationInterface;

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
    public int SignInQuery(String user, String pass, String nome, String cognome, String email) throws SQLException
    {
        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        String sql= "INSERT INTO utente(username,password,email,nome,cognome) VALUES (?,?,?,?,?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1,user);
        ps.setString(2,pass);
        ps.setString(3,email);
        ps.setString(4,nome);
        ps.setString(5,cognome);
        ps.execute();

        String sql2= "INSERT INTO ruolo(privilegio,IDutente) VALUES (?,(SELECT ID FROM utente WHERE username=?))";
        ps = connection.prepareStatement(sql2);
        ps.setString(1,"utente base");
        ps.setString(2,user);

        return ps.executeUpdate(); //ritorno l'esecuzione della query
    }
}
>>>>>>> 361e359ca24548f535b8284958b62647770df092
