package dao;

import dao.Interface.OperaInfoInterface;

import java.sql.*;
import java.time.LocalDate;

public class OperaInfoQuery implements OperaInfoInterface
{
    @Override
    public void OperaInfoQuery(String titolo, String autore, LocalDate data, String genere,String nomeimmagine,String pathimage) throws SQLException
    {
        PreparedStatement st;
        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        String sql= "INSERT INTO opera (titolo,autore,data_pubb,IDcategoria) VALUES (?,?,?,(SELECT ID FROM categoria c WHERE c.nome=?))";
        st = connection.prepareStatement(sql);
        st.setString(1,titolo);
        st.setString(2,autore);
        st.setDate(3,Date.valueOf(data));
        st.setString(4,genere);
        st.execute();

        String sql2= "INSERT INTO immagine (nome,formato,image) VALUES (?,?,?)";
        st = connection.prepareStatement(sql2);
        st.setString(1,nomeimmagine);
        st.setString(2,".jpg");
        st.setString(3,pathimage);
        st.execute();

    }
}
