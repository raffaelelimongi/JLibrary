package dao.Interface;

import javafx.scene.image.ImageView;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;

public interface UploadImageInterface
{
    void UploadImageQuery(String nome, String path) throws SQLException;
}
