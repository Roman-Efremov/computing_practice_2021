package dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator
{
    /*public static Connection getConnection(String url, String user, String pass)
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception e)
        {
            System.out.println("Connection failed...");
            System.out.println(e);
            //e.printStackTrace();
        }
        return connection;
    }*/
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;

    static
    {
        try
        {
            properties.load(new FileReader("datares\\database.properties"));
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
        } catch (ClassNotFoundException | IOException e)
        {
            e.printStackTrace(); // fatal exception
        }
        DATABASE_URL = (String) properties.get("db.url");
    }

    private ConnectionCreator() {}

    public static Connection createConnection() throws SQLException
    {
        return DriverManager.getConnection(DATABASE_URL, properties);
    }
}