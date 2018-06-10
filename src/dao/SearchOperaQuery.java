package dao;

import dao.Interface.ImageQueryInterface;
import dao.Interface.SearchOperaInterface;
import java.sql.*;

public class SearchOperaQuery implements SearchOperaInterface,ImageQueryInterface {
    PreparedStatement ps;
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    public void SearchOperaQuery() {
    }

    @Override
    public ResultSet SearchOperaQueryGeneral(String keyword, String kind) throws SQLException
    {
        String sql = "SELECT DISTINCT(titolo) titolo,autore,c.nome,IDcategoria,data_pubb FROM opera join categoria c ON (opera.IDcategoria=c.ID) JOIN immagine i ON(opera.ID=i.IDopera) WHERE TITOLO LIKE ? AND c.nome LIKE ? AND i.accept=?";
        ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + kind + "%");
        ps.setInt(3, 1);

        ResultSet resultSet = ps.executeQuery();

        return resultSet;
    }

    @Override
    public ResultSet SearchOperaQueryAdmin(String keyword, String kind) throws SQLException
    {
        String sql = "SELECT DISTINCT(titolo) titolo,autore,c.nome,IDcategoria,data_pubb FROM opera join categoria c ON (opera.IDcategoria=c.ID) WHERE TITOLO LIKE ? AND c.nome LIKE ? ";
        ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + kind + "%");

        ResultSet resultSet = ps.executeQuery();

        return resultSet;
    }

    @Override
    public ResultSet SearchOperaSoft() throws SQLException {
        return null;
    }

    @Override
    public ResultSet LoadOpera(String tit) throws SQLException
    {
        String sql = "SELECT titolo,autore,c.nome,IDcategoria,data_pubb,op.testo,i.image,op.accept FROM opera join categoria c ON (opera.IDcategoria=c.ID) JOIN immagine i ON(opera.ID=i.IDopera) join opera_trascritta op ON(opera.IDoperatrascritta=op.ID)WHERE titolo=? AND i.accept=?";
        ps = connection.prepareStatement(sql);
        ps.setString(1, tit);
        ps.setInt(2, 1);

        ResultSet resultSet = ps.executeQuery();

        return resultSet;
    }


    @Override
    public ResultSet LoadImage(String tit) throws SQLException {
        return null;
    }

    @Override
    public int UploadImageQuery(String nome, String path, String tit,String aut) throws SQLException {
        return 0;
    }

    @Override
    public void Accept(String name, String tit) throws SQLException {

    }

    @Override
    public void Decline(String name, String tit) throws SQLException {

    }
}


