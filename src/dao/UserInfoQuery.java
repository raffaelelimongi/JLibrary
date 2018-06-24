package dao;

import dao.Interface.UserInfoInterface;
import model.InfoUserTable;
import model.UserModel;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class UserInfoQuery implements UserInfoInterface
{
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    PreparedStatement st;
    public UserInfoQuery()
    {
    }

    @Override
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

    @Override
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

    @Override
    public void VipQuery(String user , int vip) throws SQLException
    {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "UPDATE utente SET vip=? WHERE username=?";
        st = connection.prepareStatement(sql);
        st.setInt(1,vip);
        st.setString(2,user);
        st.executeUpdate();

    }

    @Override
    public void Trascrittore(String user , int rictrascr) throws SQLException
    {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "UPDATE utente SET ric_trascrittore=? WHERE username=?";
        st = connection.prepareStatement(sql);
        st.setInt(1,rictrascr);
        st.setString(2,user);
        st.executeUpdate();
    }

    @Override
    public ArrayList<InfoUserTable> GetListUser(String key) throws SQLException
    {
        ArrayList<InfoUserTable> userlist= new ArrayList<>();
        String sql = "SELECT username,email,nome,cognome,r.privilegio FROM utente JOIN ruolo r ON(utente.ID=r.IDutente) WHERE username LIKE ?";
        st = connection.prepareStatement(sql);
        st.setString(1,"%"+key+"%");
        //ritorno il sisultato della query
        ResultSet resultSet = st.executeQuery();

        while (resultSet.next())
        {
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String nome = resultSet.getString("nome");
            String cognome = resultSet.getString("cognome");
            String privilegio =resultSet.getString("r.privilegio");

            try {
                InfoUserTable user = new InfoUserTable(username,privilegio,nome,cognome,email);
                userlist.add(user);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return  userlist;
    }

    @Override
    public void DeleteUser(String Username) throws SQLException
    {
        String sql = "DELETE FROM utente WHERE username = ?";
        st=connection.prepareStatement(sql);
        st.setString(1,Username);
        st.execute();
    }

    @Override
    public void AcceptTrascrittore(String user) throws SQLException
    {
        String sql = "UPDATE utente SET trascrittore=? WHERE( username = ?)";
        st=connection.prepareStatement(sql);
        st.setInt(1,1);
        st.setString(2,user);
        st.execute();
    }

    @Override
    public void PromoteUser(String user) throws SQLException
    {
        String sql = "UPDATE utente inner join ruolo r ON(utente.ID=r.IDutente) SET privilegio=? WHERE(username = ? AND r.privilegio !=?)";
        st=connection.prepareStatement(sql);
        st.setString(1,"supervisor");
        st.setString(2,user);
        st.setString(3,"supervisor");
        st.execute();
    }

    @Override
    public void RetrocediUser(String user) throws SQLException
    {
        String sql = "UPDATE utente JOIN ruolo ON(utente.ID=ruolo.IDutente) SET trascrittore=?,ric_trascrittore=? WHERE(username=? AND ruolo.privilegio !=?)";
        st=connection.prepareStatement(sql);
        st.setInt(1,0);
        st.setInt(2,2);
        st.setString(3,user);
        st.setString(4,"supervisor");
        st.execute();
    }

    @Override
    public  ResultSet  SupervisorUserPanelQuery (String keyword , String kind) throws SQLException
    {
        if(kind.equals("ric_trascrittore"))
        {
            //preparo la query da inviare ed eseguire sul DB
            String sql1 = "SELECT username,password,nome,cognome,email,r.privilegio FROM utente JOIN ruolo r ON(utente.ID=r.IDutente) WHERE (ric_trascrittore=? AND trascrittore=? AND r.privilegio!=?)";
            st = connection.prepareStatement(sql1);
            st.setInt(1, 1);
            st.setInt(2, 0);
            st.setString(3,"admin");
            ResultSet resultSet = st.executeQuery();
            return resultSet;
        }
        else
        {
            if(kind.equals("Trascrittori"))
            {
                String sql1 = "SELECT username,password,nome,cognome,email,r.privilegio FROM utente JOIN ruolo r ON(utente.ID=r.IDutente) WHERE (trascrittore=? AND r.privilegio!=?)";
                st = connection.prepareStatement(sql1);
                st.setInt(1, 1);
                st.setString(2,"admin");
                ResultSet resultSet = st.executeQuery();
                return resultSet;
            }
            else
            {
                if(kind.equals(""))
                {
                    String sql1 = "SELECT username,password,nome,cognome,email,r.privilegio FROM utente JOIN ruolo r ON(utente.ID=r.IDutente) WHERE (r.privilegio!=?)";
                    st = connection.prepareStatement(sql1);
                    st.setString(1, "admin");
                    ResultSet resultSet = st.executeQuery();
                    return resultSet;
                }
            }
        }
        return null;
    }

    @Override
    public  void setSupervisorQuery(InfoUserTable supervisor) throws SQLException
    {
        String sql="UPDATE ruolo inner join utente u on ruolo.IDutente=u.ID SET privilegio = ? WHERE u.username = ? " ;
        st=connection.prepareStatement(sql);
        st.setString(1,"supervisor");
        st.setString(2,supervisor.getUsername());

        st.executeUpdate();
    }

    @Override
    public  void setUserQuery(InfoUserTable user) throws SQLException
    {
        String sql="UPDATE ruolo inner join utente u on ruolo.IDutente=u.ID SET privilegio = ? WHERE u.username = ? " ;
        st=connection.prepareStatement(sql);
        st.setString(1,"utente base");
        st.setString(2, user.getUsername());

        st.executeUpdate();
    }

    @Override
    public  void setAdminQuery(InfoUserTable admin) throws SQLException
    {
        String sql="UPDATE ruolo inner join utente u on ruolo.IDutente=u.ID SET privilegio =? WHERE u.username=? " ;
        st=connection.prepareStatement(sql);
        st.setString(1,"admin");
        st.setString(2,admin.getUsername());

        st.executeUpdate();
    }
}
