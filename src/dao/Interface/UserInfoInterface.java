package dao.Interface;

import model.InfoUserTable;
import model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserInfoInterface
{
    ResultSet UserInfoQuery(String user) throws SQLException;

    int UpdateInfo(UserModel userModel) throws SQLException;

    void VipQuery(UserModel userModel) throws SQLException;

    void Trascrittore(UserModel userModel) throws SQLException;

    ArrayList<InfoUserTable> GetListUser(String k) throws SQLException;

    ArrayList<InfoUserTable> SupervisorUserPanelQuery (String keyword , String kind) throws SQLException;

    void DeleteUser(InfoUserTable deluser) throws SQLException;

    void AcceptTrascrittore(InfoUserTable user) throws SQLException;

    void PromoteUser(InfoUserTable user) throws SQLException;

    void RetrocediUser(InfoUserTable user) throws SQLException;

    void setSupervisorQuery(InfoUserTable supervisor) throws SQLException;

    void setUserQuery(InfoUserTable user) throws SQLException;

    void setAdminQuery(InfoUserTable admin) throws SQLException;
}
