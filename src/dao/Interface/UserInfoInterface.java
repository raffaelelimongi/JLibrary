package dao.Interface;

import model.InfoUserTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserInfoInterface
{
    ResultSet UserInfoQuery(String user) throws SQLException;

    int UpdateInfo(String user, String pass, String email, String name, String surname) throws SQLException;

    void VipQuery(String user , int vip) throws SQLException;

    void Trascrittore(String user , int rictrascr) throws SQLException;

    ArrayList<InfoUserTable> GetListUser(String k) throws SQLException;

      ResultSet  SupervisorUserPanelQuery (String keyword , String kind) throws SQLException;

    void DeleteUser(InfoUserTable deluser) throws SQLException;

    void AcceptTrascrittore(String u) throws SQLException;

    void PromoteUser(String u) throws SQLException;

    void RetrocediUser(String u) throws SQLException;

    void setSupervisorQuery(InfoUserTable supervisor) throws SQLException;

    void setUserQuery(InfoUserTable user) throws SQLException;

    void setAdminQuery(InfoUserTable admin) throws SQLException;
}
