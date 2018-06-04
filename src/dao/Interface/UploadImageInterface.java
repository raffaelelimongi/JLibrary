package dao.Interface;

import java.io.InputStream;
import java.sql.SQLException;

public interface UploadImageInterface
{
    void UploadImageQuery(String nome, InputStream image) throws SQLException;
}
