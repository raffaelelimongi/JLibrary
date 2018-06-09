package dao;

import dao.Interface.ImageQueryInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageQuery implements  ImageQueryInterface
{
    PreparedStatement ps;
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    public static String nome="";
    public static String titolo="";

    public ImageQuery() {
    }

    public ResultSet LoadImage(String tit) throws SQLException {
        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT o.titolo,i.image,i.nome FROM  opera o JOIN immagine i ON (o.ID=i.IDopera) WHERE(titolo=? AND accept=?) ";
        ps = connection.prepareStatement(sql);
        ps.setString(1, tit);
        ps.setInt(2, 0);
        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();
        return resultSet;
    }

    @Override
    public void UploadImageQuery(String nome, String path, String tit,String aut) throws SQLException {

      /*
       try {
             fis = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //2 modo di memorizzare e visualizzare le immagini ma poco efficente e sicuro
        ******/

        String sql = "INSERT INTO immagine(nome,formato,image,IDopera) VALUES (?,?,?,(SELECT ID from opera WHERE titolo=? AND autore=?))";
        ps = connection.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, ".jpg");
        ps.setString(3, path);
        ps.setString(4,tit);
        ps.setString(5,aut);

        //ps.setBinaryStream(3,(InputStream)fis, (int)f.length()); //2modo di memorizzare le immagini ma poco efficente e sicuro

        ps.execute();
    }

    @Override
    public void Accept(String name, String tit) throws SQLException
    {
        if(nome.equals(""))
        {
            nome=name;
        }
        System.out.println(name);

        //collego l'opera appena accettata all'opera trascritta
        String sql = "UPDATE opera o SET o.IDoperatrascritta=(SELECT ID from opera_trascritta WHERE(testo='' AND accept=0 )) WHERE (o.titolo=?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1, tit);
        ps.execute();

        //collego l'immagine appena accettata all'opera trascritta
        String sql2 = "UPDATE immagine i SET i.accept=? ,i.IDoperatrascritta=(SELECT ID from opera_trascritta WHERE(testo='' AND accept=0)) WHERE i.nome=?";
        ps = connection.prepareStatement(sql2);
        ps.setBoolean(1,true);
        ps.setString(2, name);
        ps.execute();

    }

    @Override
    public void Decline(String name, String tit) throws SQLException
    {
        if(titolo.equals(""))
        {
            String sql = "DELETE  FROM opera WHERE(titolo=?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, tit);
            ps.execute();
        }
    }

}
