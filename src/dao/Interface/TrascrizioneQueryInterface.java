package dao.Interface;

import model.InfoUserTable;
import model.OperaMetadati;
import model.TrascrizioneDati;
import model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TrascrizioneQueryInterface
{
     ArrayList<TrascrizioneDati> getTrascrizioniList (UserModel userModel) throws SQLException;

     ArrayList<TrascrizioneDati> getTrascUserAbility() throws SQLException;

     TrascrizioneDati loadtext(TrascrizioneDati trasc) throws SQLException;

     void savetext(TrascrizioneDati trasc) throws SQLException;

     void Create(String titolo) throws SQLException;

     int AssegnaTrascrizione(OperaMetadati op, InfoUserTable user) throws SQLException;

     ResultSet LoadTrascrizione(String tit) throws SQLException;

     void Accept(TrascrizioneDati trasc) throws SQLException;

     void Decline(TrascrizioneDati trasc) throws SQLException;

     ArrayList<OperaMetadati> SearchOperaSoft() throws SQLException;
}
