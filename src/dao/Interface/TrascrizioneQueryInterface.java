package dao.Interface;

import model.InfoUserTable;
import model.OperaMetadati;
import model.TrascrizioneDati;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TrascrizioneQueryInterface
{
     ResultSet getTrascrizioniList (String u,String priv) throws SQLException;

     ResultSet getUserAbility() throws SQLException;

     TrascrizioneDati loadtext(TrascrizioneDati trasc) throws SQLException;

     void savetext(TrascrizioneDati trasc) throws SQLException;

     void Create(String titolo) throws SQLException;

     int AssegnaTrascrizione(OperaMetadati op, InfoUserTable user) throws SQLException;

     ResultSet LoadTrascrizione(String tit) throws SQLException;

     void Accept(TrascrizioneDati trasc) throws SQLException;

     void Decline(TrascrizioneDati trasc) throws SQLException;

     ArrayList<OperaMetadati> SearchOperaSoft() throws SQLException;
}
