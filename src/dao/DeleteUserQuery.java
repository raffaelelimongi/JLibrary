package dao;

import dao.Interface.DeleteUserInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteUserQuery implements DeleteUserInterface
{
    public void DeleteUserQuery()
    {

    }
    public void DeleteUser(String Username) throws SQLException
    {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM utente " +
                "WHERE username = Username";

        statement.execute(sql);
    }
}
