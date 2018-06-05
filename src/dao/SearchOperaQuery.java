package dao;

import dao.Interface.SearchOperaInterface;

import java.sql.*;

public class SearchOperaQuery implements SearchOperaInterface
{
    PreparedStatement ps;
    public void SearchOperaQuery()
    {

    }
    public ResultSet SearchOperaQueryKeyword(String keyword) throws SQLException
    {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql ="SELECT titolo,autore,c.nome FROM opera join categoria c ON (opera.IDcategoria=c.ID) WHERE TITOLO LIKE ?";
        ps = connection.prepareStatement(sql);
        ps.setString(1,"%"+keyword+"%");
        ResultSet resultSet = ps.executeQuery();
        return resultSet;
    }

    public ResultSet SearchOperaQueryGeneral(String keyword, String kind) throws SQLException
    {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        String sql = "SELECT" + " titolo,autore,c.nome, IDcategoria " +
                "FROM opera join categoria c ON (opera.IDcategoria=c.ID) " +
                "WHERE TITOLO LIKE ?" +
                "AND c.nome LIKE ?";
        ps = connection.prepareStatement(sql);
        ps.setString(1,"%"+keyword+"%");
        ps.setString(2,"%"+kind+"%");

        ResultSet resultSet = ps.executeQuery();

        return resultSet;
    }

}
