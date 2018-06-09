package dao.Interface;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserInfoInterface
{
    ResultSet UserInfoQuery(String user) throws SQLException;

    int UpdateInfo(String user, String pass, String email, String name, String surname) throws SQLException;

    void VipQuery(String user , int vip) throws SQLException;

    void Trascrittore(String user , int rictrascr) throws SQLException;

    ResultSet GetListUser(String k) throws SQLException;

    ResultSet  SupervisorUserPanelQuery (String keyword , String kind) throws SQLException;

    void DeleteUser(String Username) throws SQLException;
    
    void setSupervisorQuery(String username1) throws SQLException;

    void setUserQuery(String username1) throws SQLException;

    void setAdminQuery(String username1) throws SQLException;
}
