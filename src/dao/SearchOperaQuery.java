package dao;

import dao.Interface.SearchOperaInterface;
import model.OperaMetadati;
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
    public ArrayList<OperaMetadati> SearchOperaQueryGeneral(String ricerca, String kind) throws SQLException
    {
        ArrayList<OperaMetadati> listaopere = new ArrayList<>();

        String sql = "SELECT DISTINCT(titolo) titolo,autore,c.nome,IDcategoria,data_pubb FROM opera join categoria c ON (opera.IDcategoria=c.ID) JOIN immagine i ON(opera.ID=i.IDopera) WHERE TITOLO LIKE ? AND c.nome LIKE ? AND i.accept=?";
        ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + ricerca + "%");
        ps.setString(2, "%" + kind + "%");
        ps.setInt(3, 1);

        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next())
        {
            String autore = resultSet.getString("autore");
            String titolo = resultSet.getString("titolo");
            String genere = resultSet.getString("c.nome");
            Date data=resultSet.getDate("data_pubb");
            OperaMetadati opere=new OperaMetadati(titolo,autore,genere,data);
            listaopere.add(opere);
        }

        return listaopere;
    }

    @Override
    public ArrayList<OperaMetadati> SearchOperaQueryAdmin(String ricerca) throws SQLException
    {
        ArrayList<OperaMetadati> listopere =new ArrayList<>();
        String sql = "SELECT DISTINCT(titolo) titolo,autore,c.nome,IDcategoria,data_pubb FROM opera join categoria c ON (opera.IDcategoria=c.ID) WHERE TITOLO LIKE ?";
        ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + ricerca + "%");

        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next())
        {
            //setto le variabili con le informazioni presenti nel DB e le passo al metodo setTable
           String autore = resultSet.getString("autore");
           String titolo = resultSet.getString("titolo");
           String genere = resultSet.getString("c.nome");
           Date data= resultSet.getDate("data_pubb");

            OperaMetadati opera = null;
            opera = new OperaMetadati(titolo,autore,genere,data);
            listopere.add(opera);
        }

        return listopere;
    }

    @Override
    public ArrayList<OperaMetadati> LoadOpera(String tit) throws SQLException
    {
        ArrayList<OperaMetadati> listopere= new ArrayList<>();
        String sql = "SELECT titolo,autore,c.nome,data_pubb,i.image FROM opera join categoria c ON (opera.IDcategoria=c.ID) JOIN immagine i ON(opera.ID=i.IDopera) WHERE titolo=? AND i.accept=?";
        ps = connection.prepareStatement(sql);
        ps.setString(1, tit);
        ps.setInt(2, 1);

        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next())
        {
            String titolo = resultSet.getString("titolo");
            String autore = resultSet.getString("autore");
            Date data = resultSet.getDate("data_pubb");
            String categoria=resultSet.getString("c.nome");
            listopere.add(new OperaMetadati(titolo,autore,categoria,data));
        }

        return listopere;
    }
}
