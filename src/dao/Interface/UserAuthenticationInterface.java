package dao.Interface;
import model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

    public interface UserAuthenticationInterface
    {
        int SignInQuery(UserModel userModel) throws SQLException;

        ResultSet UserAuthenticationQuery(String username1,String password1) throws SQLException;
    }
