
package dao.Interface;
import java.sql.ResultSet;
import java.sql.SQLException;

    public interface UserAuthenticationInterface
    {
        int SignInQuery(String user, String pass, String nome, String cognome, String email) throws SQLException;

        ResultSet UserAuthenticationQuery(String username1,String password1) throws SQLException;
    }


