package dao.Interface;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SearchOperaInterface
{
    ResultSet SearchOperaQueryGeneral(String keyword, String kind) throws SQLException;
}
