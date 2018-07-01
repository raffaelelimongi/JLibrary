package dao.Interface;

import model.OperaMetadati;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface OperaInfoInterface
{
    int OperaInfoQuery(OperaMetadati opera) throws SQLException;

    void DeleteOpera(OperaMetadati delopera) throws SQLException;

    void UploadImageQuery(String nome, String path,String tit) throws SQLException;

    ArrayList<OperaMetadati> LoadOpera() throws SQLException;
}
