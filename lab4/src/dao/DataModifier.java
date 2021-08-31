package dao;

import java.sql.Connection;
import java.sql.Statement;

public class DataModifier
{
    Connection connection;

    public DataModifier(Connection connection)
    {
        this.connection = connection;
    }

    public void executeUpdate(String sql)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            System.out.println("Can't execute update...");
            System.out.println(e);
        }
    }
}
