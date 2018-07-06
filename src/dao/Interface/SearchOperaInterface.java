package dao.Interface;

import model.OperaMetadati;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SearchOperaInterface
{
    ArrayList<OperaMetadati> SearchOperaQueryGeneral(String ricerca, String kind) throws SQLException;

    ArrayList<OperaMetadati> LoadOpera(String tit) throws SQLException;

    ArrayList<OperaMetadati> SearchOperaQueryAdmin(String ricerca) throws SQLException;
}
