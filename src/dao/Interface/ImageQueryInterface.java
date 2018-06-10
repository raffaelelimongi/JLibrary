package dao.Interface;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ImageQueryInterface
{
    ResultSet LoadImage(String tit) throws SQLException;

    int UploadImageQuery(String nome, String path,String tit,String autore) throws SQLException;

    void Accept(String name, String tit) throws SQLException;

    void Decline(String name,String titolo) throws SQLException;

}
