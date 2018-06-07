package dao;

import dao.Interface.TrascrizioneQueryInterface;
import java.sql.*;

public class TrascrizioneQuery implements TrascrizioneQueryInterface
{
    PreparedStatement ps;
    public TrascrizioneQuery()
    {
    }

    //metodo che restituisce tutte le opere che devono essere trascritte + l'eventuale testo che viene messo nella variabile Text testo della classe TrascrizioneDati
    @Override
    public ResultSet getTrascrizioniList (String user) throws SQLException
    {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT o.titolo,op.accept FROM opera o JOIN opera_trascritta op ON (op.ID = o.IDoperatrascritta) JOIN utente u ON (op.id=u.IDtrascrizione) JOIN ruolo r ON (u.ID=r.IDutente) WHERE (u.username=? OR r.privilegio='supervisor')";
        ps = connection.prepareStatement(sql);
        ps.setString(1,user);
        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        return  resultSet;
    }

    @Override
    public ResultSet getUserAbility () throws SQLException
    {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT o.titolo,op.accept FROM opera o JOIN opera_trascritta op ON (op.ID = o.IDoperatrascritta) JOIN utente u JOIN ruolo r ON (u.ID=r.IDutente) WHERE (r.privilegio='supervisor')";
        ps = connection.prepareStatement(sql);

        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        return  resultSet;
    }

    //metodo per caricare il testo dal DB
    @Override
    public ResultSet loadtext(String tit) throws SQLException
    {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT op.testo,o.titolo,i.image FROM opera_trascritta op JOIN opera o ON (op.ID = o.IDoperatrascritta) JOIN immagine i ON (op.ID=i.IDoperatrascritta) WHERE(o.titolo=? AND o.IDoperatrascritta=op.ID) ";
        ps =connection.prepareStatement(sql);
        ps.setString(1,tit);
        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();
        return  resultSet;
    }

    //metodo per salvare il testo modificato sul DB
    @Override
    public void savetext(String titolo,String testo) throws SQLException
    {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "UPDATE opera_trascritta op JOIN opera o ON (op.ID = o.IDoperatrascritta) SET testo=? WHERE o.titolo=?";
        ps = connection.prepareStatement(sql);
        ps.setString(1,testo);
        ps.setString(2,titolo);
        ps.execute();

    }

    @Override
    public  void Accept() throws SQLException
    {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "UPDATE utente u INNER JOIN opera_trascritta op ON(u.IDtrascrizione=op.ID) SET livello=livello + ? , op.accept=?";
        ps = connection.prepareStatement(sql);
        ps.setFloat(1, (float) 0.25);
        ps.setBoolean(2,true);
        ps.execute();


    }

    public void Decline(String titolo) throws SQLException
    {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "DELETE op.* FROM opera_trascritta op INNER JOIN opera o ON op.ID = o.IDoperatrascritta WHERE (o.titolo=?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1,titolo);
        ps.execute();
    }

}
