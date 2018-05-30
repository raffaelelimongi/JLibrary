package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserAuthenticationQuery {

    public  UserAuthenticationQuery(){


    }

    public ResultSet UserAuthenticationQuery1(String username1,String password1) throws SQLException {


        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT username,password FROM utente  WHERE  USERNAME= '"
                + username1 + "' AND PASSWORD ='" + password1 + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

}
