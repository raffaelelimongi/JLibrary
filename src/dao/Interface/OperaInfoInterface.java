package dao.Interface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface OperaInfoInterface
{
    void OperaInfoQuery(String titolo, String autore, LocalDate data, String genere, String nomeimmagine,String path) throws SQLException;

}
