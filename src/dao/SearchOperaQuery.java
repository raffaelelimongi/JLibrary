package dao;

import dao.Interface.SearchOperaInterface;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.sun.tools.doclint.Entity.and;

public class SearchOperaQuery implements SearchOperaInterface
{
    public void SearchOperaQuery()
    {

    }

    public ResultSet SearchOperaQueryGeneral(String keyword, String kind) throws SQLException
    {
        OperaInfoQuery.ConnectionClass connectionClass = new OperaInfoQuery.ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();



        String sql = "SELECT" + " titolo,autore,c.nome, IDcategoria " +
                "FROM opera join categoria c ON (opera.IDcategoria=c.ID) " +
                "WHERE TITOLO LIKE'%" + keyword + "%'" +
                "AND c.nome LIKE '" + kind + "'";

        ResultSet resultSet = statement.executeQuery(sql);

        return resultSet;
    }

}


