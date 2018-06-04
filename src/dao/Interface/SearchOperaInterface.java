package dao.Interface;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SearchOperaInterface
{
     ResultSet SearchOperaQueryKeyword(String keyword) throws SQLException;

    ResultSet SearchOperaQueryAutore(String keyword) throws SQLException;

    ResultSet SearchOperaQueryGenere(String keyword) throws SQLException;
}
