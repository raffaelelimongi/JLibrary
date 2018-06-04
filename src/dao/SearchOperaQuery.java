package dao;

import dao.Interface.SearchOperaInterface;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchOperaQuery implements SearchOperaInterface
{
    public void SearchOperaQuery()
    {

    }

    public ResultSet SearchOperaQueryKeyword(String keyword) throws SQLException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT titolo,autore,c.nome FROM opera join categoria c ON (opera.IDcategoria=c.ID) WHERE TITOLO LIKE'%" + keyword + "%'" ;

        //ritorno il sisultato della query
        ResultSet resultSet = statement.executeQuery(sql);

        return resultSet;
    }

    public ResultSet SearchOperaQueryAutore(String keyword) throws SQLException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT titolo,autore,c.nome FROM opera join categoria c ON (opera.IDcategoria=c.ID) WHERE autore LIKE'%" + keyword + "%'" ;

        //ritorno il sisultato della query
        ResultSet resultSet = statement.executeQuery(sql);

        return resultSet;
    }
    public ResultSet SearchOperaQueryGenere(String keyword) throws SQLException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT titolo,autore,c.nome FROM opera join categoria c ON (opera.IDcategoria=c.ID) WHERE c.nome LIKE'%" + keyword + "%'" ;

        //ritorno il sisultato della query
        ResultSet resultSet = statement.executeQuery(sql);

        return resultSet;
    }

}


