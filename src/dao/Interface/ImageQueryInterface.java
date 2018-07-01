package dao.Interface;

import model.ImmagineDati;
import model.OperaMetadati;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ImageQueryInterface
{
    ArrayList<ImmagineDati> LoadImage(String tit) throws SQLException;

    int UploadImageQuery(ImmagineDati image, OperaMetadati opera) throws SQLException;

    void Accept(ArrayList<ImmagineDati> listimage, String tit) throws SQLException;

    void Decline(String titolo) throws SQLException;

     ArrayList<ImmagineDati> LoadImageOpera(String tit) throws SQLException;

}
