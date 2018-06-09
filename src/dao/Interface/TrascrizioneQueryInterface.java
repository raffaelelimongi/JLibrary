package dao.Interface;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface TrascrizioneQueryInterface
{
     ResultSet getTrascrizioniList (String u,String priv) throws SQLException;

     ResultSet getUserAbility() throws SQLException;

     ResultSet loadtext(String t) throws SQLException;

     void savetext(String titolo,String text) throws SQLException;

     void Create() throws SQLException;

     int AssegnaTrascrizione(String tit,String aut,String user) throws SQLException;

}
