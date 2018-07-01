package dao;

import dao.Interface.OperaInfoInterface;
import model.OperaMetadati;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OperaInfoQuery implements OperaInfoInterface
{
    PreparedStatement st;

    //inizializzo la connessione al DB
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    public OperaInfoQuery(){}

    @Override
    public int OperaInfoQuery(OperaMetadati opera) throws SQLException
    {

        String sql= "INSERT INTO opera (titolo,autore,data_pubb,IDcategoria) VALUES (?,?,?,(SELECT ID FROM categoria c WHERE c.nome=?))";
        st = connection.prepareStatement(sql);
        st.setString(1,opera.getTitolo());
        st.setString(2,opera.getAutore());
        st.setDate(3, (Date) opera.getDatapubb());
        st.setString(4,opera.getGenere());
        return st.executeUpdate();
    }

    @Override
    public void DeleteOpera(OperaMetadati delopera) throws SQLException
    {
        String sql = "DELETE FROM opera WHERE titolo=? AND autore=?";
        st=connection.prepareStatement(sql);
        st.setString(1,delopera.getTitolo());
        st.setString(2,delopera.getAutore());
        st.execute();
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
    public ArrayList<OperaMetadati> LoadOpera() throws SQLException
    {
        ArrayList<OperaMetadati> listop = new ArrayList<>();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql ="SELECT DISTINCT (titolo) titolo FROM opera o JOIN immagine i ON(o.ID=i.Idopera) WHERE i.accept=?";
        st = connection.prepareStatement(sql);
        st.setInt(1,0);

        ResultSet resultSet = st.executeQuery();

        while(resultSet.next())
        {
            listop.add(new OperaMetadati(resultSet.getString("titolo"),null,null,null));

        }

        return listop;
    }
}
