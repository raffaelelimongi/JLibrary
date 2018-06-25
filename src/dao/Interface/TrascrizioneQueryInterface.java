package dao.Interface;

import model.InfoUserTable;
import model.OperaMetadati;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TrascrizioneQueryInterface
{
     ResultSet getTrascrizioniList (String u,String priv) throws SQLException;

     ResultSet getUserAbility() throws SQLException;

     ResultSet loadtext(String t) throws SQLException;

     void savetext(String titolo,String text) throws SQLException;

     void Create(String titolo) throws SQLException;

     int AssegnaTrascrizione(OperaMetadati op, InfoUserTable user) throws SQLException;

     ResultSet LoadTrascrizione(String tit) throws SQLException;

     void Accept(String name, String tit) throws SQLException;

     void Decline(String name,String titolo) throws SQLException;

     ArrayList<OperaMetadati> SearchOperaSoft() throws SQLException;
}
