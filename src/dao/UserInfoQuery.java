package dao;

import dao.Interface.UserInfoInterface;
import model.UserModel;

import java.sql.*;

public class UserInfoQuery implements UserInfoInterface
{
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    PreparedStatement st;
    public UserInfoQuery()
    {
    }

    public ResultSet UserInfoQuery(String user) throws SQLException
    {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT username,password,email,nome,cognome,livello,trascrittore,vip,r.privilegio FROM utente u JOIN ruolo r ON u.ID=r.IDutente where u.username=?";
        st = connection.prepareStatement(sql);
        st.setString(1,user);
        //ritorno il sisultato della query
        ResultSet resultSet = st.executeQuery();

        while(resultSet.next())
        {
            UserModel usermodel = UserModel.getInstance();
            usermodel.setUsername(resultSet.getString("username"));
            usermodel.setPassword(resultSet.getString("password"));
            usermodel.setVip(resultSet.getBoolean("vip"));
            usermodel.setPrivilegio(resultSet.getString("r.privilegio"));
            usermodel.setLivello(resultSet.getInt("livello"));
            usermodel.setTrascrittore(resultSet.getBoolean("trascrittore"));
            usermodel.setNome(resultSet.getString("nome"));
            usermodel.setCognome(resultSet.getString("cognome"));
            usermodel.setEmail(resultSet.getString("email"));
        }
        return  resultSet;
    }

    public int UpdateInfo(String user, String pass, String email, String name, String surname) throws SQLException
    {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "UPDATE utente SET password =?, email=?, nome=?, cognome =? WHERE username=?";
        st =connection.prepareStatement(sql);
        st.setString(1,pass);
        st.setString(2,email);
        st.setString(3,name);
        st.setString(4,surname);
        st.setString(5,user);

        //ritorno il sisultato della query
        int resultSet = st.executeUpdate();

        UserInfoQuery(user);

        return resultSet;
    }

    public void VipQuery(String user , int vip) throws SQLException
    {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "UPDATE utente SET vip=? WHERE username=?";
        st = connection.prepareStatement(sql);
        st.setInt(1,vip);
        st.setString(2,user);
        st.executeUpdate();

    }

    public void Trascrittore(String user , int rictrascr) throws SQLException
    {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "UPDATE utente SET ric_trascrittore=? WHERE username=?";
        st = connection.prepareStatement(sql);
        st.setInt(1,rictrascr);
        st.setString(2,user);
        st.executeUpdate();
    }

    public ResultSet GetListUser(String key) throws SQLException {
        String sql = "SELECT username,email,nome,cognome FROM utente WHERE username LIKE ?";
        st = connection.prepareStatement(sql);
        st.setString(1,"%"+key+"%");
        //ritorno il sisultato della query
        ResultSet resultSet = st.executeQuery();

        return  resultSet;
    }

}

