package dao;

import dao.Interface.ImageQueryInterface;
import dao.Interface.OperaInfoInterface;
import dao.Interface.SearchOperaInterface;

import java.sql.*;
import java.time.LocalDate;

public class OperaInfoQuery implements OperaInfoInterface,ImageQueryInterface,SearchOperaInterface
{
    PreparedStatement st;

    //inizializzo la connessione al DB
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    public OperaInfoQuery(){}

    @Override
    public void OperaInfoQuery(String titolo, String autore, LocalDate data, String genere,String nomeimmagine,String pathimage) throws SQLException
    {

        String sql= "INSERT INTO opera (titolo,autore,data_pubb,IDcategoria) VALUES (?,?,?,(SELECT ID FROM categoria c WHERE c.nome=?))";
        st = connection.prepareStatement(sql);
        st.setString(1,titolo);
        st.setString(2,autore);
        st.setDate(3,Date.valueOf(data));
        st.setString(4,genere);
        st.execute();
    }

    @Override
    public ResultSet LoadImage(String tit) throws SQLException {
        return null;
    }

    @Override
    public void UploadImageQuery(String nome, String path,String tit) throws SQLException
    {
        String sql2= "INSERT INTO immagine (nome,formato,image,IDopera) VALUES (?,?,?,(SELECT ID FROM opera WHERE titolo=?))";
        st = connection.prepareStatement(sql2);
        st.setString(1,nome);
        st.setString(2,".jpg");
        st.setString(3,path);
        st.setString(4,tit);
        st.execute();
    }

    @Override
    public void Accept(String name, String tit) throws SQLException {

    }

    @Override
    public void Decline(String name, String titolo) throws SQLException {

    }

    @Override
    public ResultSet SearchOperaQueryGeneral(String keyword, String kind) throws SQLException {
        return null;
    }

    @Override
    public ResultSet LoadOpera(String tit) throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql ="SELECT DISTINCT (titolo) FROM opera o JOIN immagine i ON(o.ID=i.Idopera) WHERE i.accept=?";
        st = connection.prepareStatement(sql);
        st.setInt(1,0);

        ResultSet resultSet = st.executeQuery();

        return resultSet;
    }
}
