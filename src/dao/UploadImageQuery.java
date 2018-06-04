package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UploadImageQuery
{
    public  UploadImageQuery(String nome, InputStream image) throws SQLException
    {
        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();
        String sql= "INSERT INTO immagine(nome,formato,image) VALUES ('"
                +nome+"','.jpg','"+image+"')";
        statement.executeUpdate(sql);
    }
}
