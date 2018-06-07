package dao;

import dao.Interface.UploadImageInterface;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UploadImageQuery implements UploadImageInterface
{
    PreparedStatement ps =null;

   // private InputStream fis; //2 modo di memorizzare e visualizzare le immagini ma poco efficente e sicuro

    public UploadImageQuery()
    {

    }

    @Override
    public void UploadImageQuery(String nome, String path) throws SQLException
    {

      /*
       try {
             fis = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //2 modo di memorizzare e visualizzare le immagini ma poco efficente e sicuro
        ******/

        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        String sql= "INSERT INTO immagine(nome,formato,image) VALUES (?,?,?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1,nome);
        ps.setString(2,".jpg");
        ps.setString(3,path);

        //ps.setBinaryStream(3,(InputStream)fis, (int)f.length()); //2modo di memorizzare le immagini ma poco efficente e sicuro

        ps.execute();
    }
}
