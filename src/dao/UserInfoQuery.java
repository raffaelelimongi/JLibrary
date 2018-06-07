package dao;

import dao.Interface.UserInfoInterface;
import model.UserModel;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserInfoQuery implements UserInfoInterface
{
    public UserInfoQuery()
    {
    }

    public void UserInfoQuery(String user) throws SQLException
    {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT username,password,email,nome,cognome,livello,trascrittore,vip,privilegio FROM utente,ruolo where username='"+user+"'";

        //ritorno il sisultato della query
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
           // userModel= new UserModel(resultSet.getString("username"),resultSet.getString("password"),
                  //  resultSet.getBoolean("vip"), resultSet.getString("privilegio"), resultSet.getInt("livello"),
                    //resultSet.getBoolean("trascrittore"), resultSet.getString("nome"), resultSet.getString("cognome"), resultSet.getString("email"));
            UserModel usermodel = UserModel.getInstance();
            usermodel.setUsername(resultSet.getString("username"));
            usermodel.setPassword(resultSet.getString("password"));
            usermodel.setVip(resultSet.getBoolean("vip"));
            usermodel.setPrivilegio(resultSet.getString("privilegio"));
            usermodel.setLivello(resultSet.getInt("livello"));
            usermodel.setTrascrittore(resultSet.getBoolean("trascrittore"));
            usermodel.setNome(resultSet.getString("nome"));
            usermodel.setCognome(resultSet.getString("cognome"));
            usermodel.setEmail(resultSet.getString("email"));
        }
    }

    public int UpdateInfo(String user, String pass, String email, String name, String surname) throws SQLException
    {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "UPDATE utente SET password ='"+pass+"', email='"+email+"', nome='"+name+"', cognome ='"+surname+"' WHERE username='"+user+"'";

        //ritorno il sisultato della query
        int resultSet = statement.executeUpdate(sql);

        UserInfoQuery(user);
        return resultSet;
    }

    public int VipQuery(String user , int vip) throws SQLException
    {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "UPDATE utente SET vip='"+vip+"' WHERE username='"+user+"'";
        int resultSet = statement.executeUpdate(sql);

        return resultSet;
    }

    public static int RicTrascQuery(String user , int ric_trascrittore) throws SQLException
    {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "UPDATE utente SET ric_trascrittore ='"+ric_trascrittore+"' WHERE username='"+user+"'";
        int resultSet = statement.executeUpdate(sql);

        return resultSet;
    }

    public static ResultSet  SupervisorUserPanelQuery (String keyword , String kind) throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();

        if (kind.equals("Trascrittori"))
        {

            //preparo la query da inviare ed eseguire sul DB
            String sql1 = "SELECT username,password,nome,cognome,email FROM utente " +
                    "WHERE '"+kind+"' LIKE '" + 1 + "'";
            ResultSet resultSet = statement.executeQuery(sql1);

            return resultSet;
        }

        if (kind.equals("vip"))
        {

            //preparo la query da inviare ed eseguire sul DB
            String sql2 = "SELECT username,password,nome,cognome,email FROM utente " +
                    "WHERE vip LIKE '" + 1 + "'";
            ResultSet resultSet = statement.executeQuery(sql2);

            return resultSet;
        }

        if (kind.equals("RichiesteTrascrittori"))
        {
            //preparo la query da inviare ed eseguire sul DB
            String sql3 = "SELECT username,password,nome,cognome,email FROM utente " +
                    "WHERE ric_trascrittore LIKE '" + 1 + "'";
            ResultSet resultSet = statement.executeQuery(sql3);

            return resultSet;
        }

        if (kind.equals(""))
        {

            //preparo la query da inviare ed eseguire sul DB
            String sql4 = "SELECT username,password,nome,cognome,email FROM utente " +
                    "WHERE username LIKE '%" + keyword + "%'";
            ResultSet resultSet = statement.executeQuery(sql4);

            return resultSet;
        }
        return null;
    }

}
