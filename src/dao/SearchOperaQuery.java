package dao;

import dao.Interface.SearchOperaInterface;
import model.OperaMetadati;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class SearchOperaQuery implements SearchOperaInterface
{
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
    public ArrayList<OperaMetadati> SearchOperaQueryAdmin(String keyword) throws SQLException
    {
        ArrayList<OperaMetadati> listopere =new ArrayList<>();
        String sql = "SELECT DISTINCT(titolo) titolo,autore,c.nome,IDcategoria,data_pubb FROM opera join categoria c ON (opera.IDcategoria=c.ID) WHERE TITOLO LIKE ?";
        ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + keyword + "%");

        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next())
        {
            //setto le variabili con le informazioni presenti nel DB e le passo al metodo setTable
           String autore = resultSet.getString("autore");
           String titolo = resultSet.getString("titolo");
           String genere = resultSet.getString("c.nome");
           Date data= resultSet.getDate("data_pubb");

            OperaMetadati opera = null;
            try {
                opera = new OperaMetadati(titolo,autore,genere,data);
            } catch (IOException e) {
                e.printStackTrace();
            }
            listopere.add(opera);
        }

        return listopere;
    }

    @Override
    public ResultSet SearchOperaSoft() throws SQLException {
        return null;
    }

    @Override
    public ResultSet LoadOpera(String tit) throws SQLException
    {
        String sql = "SELECT titolo,autore,c.nome,data_pubb,i.image FROM opera join categoria c ON (opera.IDcategoria=c.ID) JOIN immagine i ON(opera.ID=i.IDopera) WHERE titolo=? AND i.accept=?";
        ps = connection.prepareStatement(sql);
        ps.setString(1, tit);
        ps.setInt(2, 1);

        ResultSet resultSet = ps.executeQuery();

        return resultSet;
    }
}
