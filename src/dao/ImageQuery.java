package dao;

import dao.Interface.ImageQueryInterface;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ImmagineDati;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageQuery implements  ImageQueryInterface
{
    PreparedStatement ps;
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    public static String nome="";
    public static String titolo="";

    public ImageQuery() {
    }

    @Override
    public ArrayList<ImmagineDati> LoadImage(String tit) throws SQLException
    {
        ImmagineDati imagee;
        ArrayList<ImmagineDati> imagelist = new ArrayList<>();
        Image image;
        List<Image> list = new ArrayList<>();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT o.titolo,i.image,i.nome FROM  opera o JOIN immagine i ON (o.ID=i.IDopera) WHERE(titolo=? AND accept=?) ";
        ps = connection.prepareStatement(sql);
        ps.setString(1, tit);
        ps.setInt(2, 0);
        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        while(resultSet.next())
        {
            File file = new File(resultSet.getString("i.image"));
            image = new Image(file.toURI().toString());
            list.add(image);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(1000);
            imageView.setFitHeight(500);
            imageView.setPreserveRatio(true);
            imagee = new ImmagineDati(resultSet.getString("i.nome"),imageView,null,null,null,null);
            imagelist.add(imagee);
        }
        return imagelist;
    }

    @Override
    public ArrayList<ImmagineDati> LoadImageOpera(String tit) throws SQLException
    {
        ImmagineDati imagee;
        ArrayList<ImmagineDati> imagelist = new ArrayList<>();
        Image image;
        List<Image> list = new ArrayList<>();
        String[] name = new String[20];

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT o.titolo,i.image,i.nome FROM  opera o JOIN immagine i ON (o.ID=i.IDopera) WHERE(titolo=? AND accept=?) ";
        ps = connection.prepareStatement(sql);
        ps.setString(1, tit);
        ps.setInt(2, 1);
        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        while(resultSet.next())
        {
                 /*   2 modo per visualizzare le immagini ma poco efficente e sicuro,anche se un po piu reale

            InputStream is = resultSet.getBinaryStream("i.image");
            OutputStream os = new FileOutputStream(new File("photo.jpg"));
            byte[] content = new byte[1024];
            int size ;
            while((size = is.read(content)) != -1)
            {
                os.write(content,0,size);
            }
            os.close();
            is.close();

            image = new Image("file:photo.jpg", 1000, 500, true, true);
          ImageView imageView = new ImageView(image);

          */

            File file = new File(resultSet.getString("i.image"));
            image = new Image(file.toURI().toString());
            list.add(image);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(1000);
            imageView.setFitHeight(500);
            imageView.setPreserveRatio(true);
            imagee = new ImmagineDati(resultSet.getString("i.nome"),imageView,null,null,null,null);
            imagelist.add(imagee);
        }
        return imagelist;
    }

    @Override
    public int UploadImageQuery(String nome, String path, String tit,String aut) throws SQLException {

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

        return ps.executeUpdate();
    }

    @Override
    public void Accept(ArrayList<ImmagineDati>imagelist, String tit) throws SQLException
    {
         //collego l'opera appena accettata all'opera trascritta
        String sql = "UPDATE opera o SET o.IDoperatrascritta=(SELECT ID from opera_trascritta WHERE(testo=? AND accept=0 )) WHERE (o.titolo=?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1, tit);
        ps.setString(2,tit);
        ps.execute();

        //collego l'immagine appena accettata all'opera trascritta
        for(int i=0;i<imagelist.size();i++)
        {
            String sql2 = "UPDATE immagine i SET i.accept=? ,i.IDoperatrascritta=(SELECT ID from opera_trascritta WHERE(testo=? AND accept=0)) WHERE i.nome=?";
            ps = connection.prepareStatement(sql2);
            ps.setBoolean(1, true);
            ps.setString(2, tit);
            ps.setString(3, imagelist.get(i).getNomeimg());
            ps.execute();
        }

    }

    @Override
    public void Decline(String tit) throws SQLException
    {
            String sql = "DELETE  FROM opera WHERE(titolo=?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, tit);
            ps.execute();
    }
}
