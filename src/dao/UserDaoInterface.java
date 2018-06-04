package dao.Interface;

import model.UserModel;

import java.sql.SQLException;

public interface UserDaoInterface
{
     int SignInQuery(String user, String pass, String nome, String cognome, String email) throws SQLException;

}