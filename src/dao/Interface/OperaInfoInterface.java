package dao.Interface;

import model.OperaMetadati;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface OperaInfoInterface
{
    int OperaInfoQuery(String titolo, String autore, LocalDate data, String genere, String nomeimmagine, String path) throws SQLException;

    void DeleteOpera(OperaMetadati delopera) throws SQLException;

    void UploadImageQuery(String nome, String path,String tit) throws SQLException;

    ResultSet LoadOpera(String tit) throws SQLException;
}
