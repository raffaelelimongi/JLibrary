package dao;

import dao.Interface.UserDaoInterface;
import model.UserModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserInfoQuery
{
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



}
