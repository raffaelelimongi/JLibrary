package dao.Interface;

import java.sql.SQLException;

public interface UserInfoInterface
{
    void UserInfoQuery(String user) throws SQLException;

    int UpdateInfo(String user, String pass, String email, String name, String surname) throws SQLException;

    int VipQuery(String user , int vip) throws SQLException;
}
