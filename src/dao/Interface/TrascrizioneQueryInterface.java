package dao.Interface;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface TrascrizioneQueryInterface
{
     ResultSet getTrascrizioniList (String u,String priv) throws SQLException;

     ResultSet getUserAbility() throws SQLException;

     ResultSet loadtext(String t) throws SQLException;

     void savetext(String titolo,String text) throws SQLException;

     void Accept() throws SQLException;

     void Decline(String name) throws SQLException;
     void Create() throws SQLException;

}
