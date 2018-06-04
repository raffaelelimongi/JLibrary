package dao;

import dao.Interface.OperaInfoInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class OperaInfoQuery implements OperaInfoInterface
{
    public void OperaInfoQuery(String titolo, String autore, LocalDate data, String genere,String nomeimmagine) throws SQLException
    {
        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();
        String sql= "INSERT INTO opera (titolo,autore,data_pubb,IDcategoria,IDimmagine) VALUES ('"+titolo+"','"+autore+"','"+data+"',(SELECT ID FROM categoria c WHERE c.nome='"+genere+"'), (SELECT ID FROM immagine i WHERE i.nome='"+nomeimmagine+"'))";
        statement.executeUpdate(sql);
    }
}
