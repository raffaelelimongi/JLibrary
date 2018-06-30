package dao;

import dao.Interface.TrascrizioneQueryInterface;
import model.InfoUserTable;
import model.OperaMetadati;
import model.TrascrizioneDati;
import java.sql.*;
import java.util.ArrayList;

public class TrascrizioneQuery implements TrascrizioneQueryInterface
{
    PreparedStatement ps;
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    public TrascrizioneQuery()
    {
    }

    //metodo che restituisce tutte le opere che devono essere trascritte + l'eventuale testo che viene messo nella variabile Text testo della classe TrascrizioneDati
    @Override
    public ResultSet getTrascrizioniList (String user,String privilegio) throws SQLException
    {
        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT o.titolo,op.accept FROM opera o JOIN opera_trascritta op ON (op.ID = o.IDoperatrascritta) JOIN utente u ON (op.id=u.IDtrascrizione) JOIN ruolo r ON (u.ID=r.IDutente) WHERE (u.username=? AND u.IDtrascrizione=op.ID)";
        ps = connection.prepareStatement(sql);
        ps.setString(1,user);
        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        return  resultSet;
    }

    @Override
    public ResultSet getUserAbility () throws SQLException
    {
        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT DISTINCT (titolo) titolo,op.accept FROM opera o JOIN opera_trascritta op ON (op.ID = o.IDoperatrascritta) JOIN utente u JOIN ruolo r ON (u.ID=r.IDutente)";
        ps = connection.prepareStatement(sql);

        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        return  resultSet;
    }

    //metodo per caricare il testo dal DB
    @Override
    public TrascrizioneDati loadtext(TrascrizioneDati trascdate) throws SQLException
    {
        TrascrizioneDati trascdati=null;
        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT op.testo,o.titolo,i.image,op.accept FROM opera_trascritta op JOIN opera o ON (op.ID = o.IDoperatrascritta) JOIN immagine i ON (op.ID=i.IDoperatrascritta) WHERE(o.titolo=? AND o.IDoperatrascritta=op.ID) ";
        ps =connection.prepareStatement(sql);
        ps.setString(1,trascdate.getTitolo());
        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        while(resultSet.next())
        {
            trascdati= new TrascrizioneDati(resultSet.getString("o.titolo"),null,resultSet.getString("op.testo"));
        }

        return  trascdati;
    }

    public ResultSet LoadTrascrizione(String tit) throws SQLException
    {
        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT op.testo,op.accept FROM opera_trascritta op JOIN opera o ON (op.ID = o.IDoperatrascritta) WHERE(o.titolo=? AND op.accept=?) ";
        ps =connection.prepareStatement(sql);
        ps.setString(1,tit);
        ps.setInt(2,1);
        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();
        return  resultSet;
    }

    //metodo per salvare il testo modificato sul DB
    @Override
    public void savetext(TrascrizioneDati trasc) throws SQLException
    {
        //preparo la query da inviare ed eseguire sul DB
        String sql = "UPDATE opera_trascritta op JOIN opera o ON (op.ID = o.IDoperatrascritta) SET testo=? WHERE o.titolo=?";
        ps = connection.prepareStatement(sql);
        ps.setString(1,trasc.getTesto());
        ps.setString(2,trasc.getTitolo());
        ps.execute();
    }

    @Override
    public  void Accept(TrascrizioneDati trasc) throws SQLException
    {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "UPDATE utente u INNER JOIN opera_trascritta op ON(u.IDtrascrizione=op.ID) SET livello=livello + ? , op.accept=?";
        ps = connection.prepareStatement(sql);
        ps.setFloat(1, (float) 0.25);
        ps.setBoolean(2,true);
        ps.execute();


        //preparo la query da inviare ed eseguire sul DB
        String sql1 = "UPDATE opera_trascritta op join opera o ON(op.ID=o.IDoperatrascritta) SET accept=? WHERE titolo=?";
        ps = connection.prepareStatement(sql1);
        ps.setFloat(1, 1);
        ps.setString(2,trasc.getTitolo());
        ps.execute();

    }

    @Override
    public void Decline(TrascrizioneDati trasc) throws SQLException
    {
        //preparo la query da inviare ed eseguire sul DB
        String sql = "DELETE op.* FROM opera_trascritta op INNER JOIN opera o ON op.ID = o.IDoperatrascritta WHERE (o.titolo=?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1,trasc.getTitolo());
        ps.execute();
    }

    @Override
    public void Create(String titolo) throws SQLException
    {
        //creo il record dell'opera trascritta
        String sql1 = "INSERT INTO opera_trascritta(testo,accept) VALUES (?,0)";
        ps = connection.prepareStatement(sql1);
        ps.setString(1,titolo);
        ps.execute();

    }

    @Override
    public int AssegnaTrascrizione(OperaMetadati opera, InfoUserTable user) throws SQLException
    {
        String sql = "UPDATE utente SET IDtrascrizione=(SELECT op.ID from opera_trascritta op JOIN opera o ON(op.ID=o.IDoperatrascritta) WHERE(o.titolo=? AND o.autore=?)) WHERE (username=? AND trascrittore=?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1,opera.getTitolo());
        ps.setString(2,opera.getAutore());
        ps.setString(3,user.getUsername());
        ps.setInt(4,1);
        return ps.executeUpdate();
    }

    @Override
    public ArrayList<OperaMetadati> SearchOperaSoft() throws SQLException
    {
        ArrayList<OperaMetadati> listopere = new ArrayList<>();
        String sql = "SELECT DISTINCT(titolo) titolo,autore FROM opera JOIN opera_trascritta op WHERE (IDoperatrascritta != op.ID)";
        ps = connection.prepareStatement(sql);

        ResultSet resultSet = ps.executeQuery();

        while(resultSet.next())
        {
            String titolo = resultSet.getString("titolo");
            String autore = resultSet.getString("autore");
            OperaMetadati opera = new OperaMetadati(titolo,autore,null,null);
            listopere.add(opera);
        }
        return listopere;
    }
}
