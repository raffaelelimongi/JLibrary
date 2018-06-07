package dao;

import dao.Interface.SearchOperaInterface;

import java.sql.*;

public class SearchOperaQuery implements SearchOperaInterface
{
    PreparedStatement ps;

    public void SearchOperaQuery()
    {
    }
    public ResultSet SearchOperaQueryGeneral(String keyword, String kind) throws SQLException
    {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        String sql = "SELECT DISTINCT(titolo) titolo,autore,c.nome,IDcategoria,data_pubb FROM opera join categoria c ON (opera.IDcategoria=c.ID) JOIN immagine i ON(opera.ID=i.IDopera) WHERE TITOLO LIKE ? AND c.nome LIKE ? AND i.accept=?";
        ps = connection.prepareStatement(sql);
        ps.setString(1,"%"+keyword+"%");
        ps.setString(2,"%"+kind+"%");
        ps.setInt(3,1);

        ResultSet resultSet = ps.executeQuery();

        return resultSet;
    }

    public ResultSet LoadOpera(String tit) throws SQLException
    {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql ="SELECT titolo,autore,c.nome,IDcategoria,data_pubb,op.testo,i.image,op.accept FROM opera join categoria c ON (opera.IDcategoria=c.ID) JOIN immagine i ON(opera.ID=i.IDopera) join opera_trascritta op ON(opera.IDoperatrascritta=op.ID)WHERE titolo=? AND i.accept=?";
        ps = connection.prepareStatement(sql);
        ps.setString(1,tit);
        ps.setInt(2,1);

        ResultSet resultSet = ps.executeQuery();

        return resultSet;
    }
}


