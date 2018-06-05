package dao.Interface;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SearchOperaInterface
{
    ResultSet SearchOperaQueryKeyword(String keyword) throws SQLException;
    ResultSet SearchOperaQueryGeneral(String keyword, String kind) throws SQLException;
}
