package dao.Interface;

import model.OperaMetadati;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SearchOperaInterface
{
    ArrayList<OperaMetadati> SearchOperaQueryGeneral(String keyword, String kind) throws SQLException;

    ArrayList<OperaMetadati> LoadOpera(String tit) throws SQLException;

    ArrayList<OperaMetadati> SearchOperaQueryAdmin(String keyword) throws SQLException;

    ResultSet SearchOperaSoft() throws SQLException;
}

