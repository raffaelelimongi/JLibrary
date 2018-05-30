package dao;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
public static Connection connection;
    public static Connection getConnection()  {

        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();

           connection= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jlibrarydb","root","");

        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
