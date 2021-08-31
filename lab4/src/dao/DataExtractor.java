package dao;

import java.sql.*;

public class DataExtractor
{
    Connection connection;

    public DataExtractor(Connection connection)
    {
        this.connection = connection;
    }

    public ResultSet executeQuery(String sql)
    {
        ResultSet resultSet = null;

        try
        {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (Exception e)
        {
            System.out.println("Can't execute query...");
            System.out.println(e);
        }
        return resultSet;
    }

    /**
     * информация о сувенирах заданного производителя.
     **/
    public ResultSet souvenirsByProducer(String producer)
    {
        String sql = "SELECT * FROM souvenirs WHERE producer = ?";
        ResultSet resultSet = null;

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, producer);
            resultSet = statement.executeQuery();
        } catch (SQLException e)
        {
            System.out.println(e);
        }

        return resultSet;
    }

    /**
     * информация о сувенирах, произведенных в заданной стране.
     **/
    public ResultSet souvenirsByCountry(String country)
    {
        String sql = "SELECT * FROM souvenirs WHERE producer IN (SELECT name FROM producer WHERE country = ?)";
        ResultSet resultSet = null;

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, country);
            resultSet = statement.executeQuery();
        } catch (SQLException e)
        {
            System.out.println(e);
        }

        return resultSet;
    }

    /**
     * информация о производителях, чьи цены на сувениры меньше заданной.
     **/
    public ResultSet producersWithSouvenirsPriceLessThen(double price)
    {
        String sql = "SELECT * FROM producer WHERE name IN (SELECT producer FROM souvenirs WHERE price < ?)";
        ResultSet resultSet = null;

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, price);
            resultSet = statement.executeQuery();
        } catch (SQLException e)
        {
            System.out.println(e);
        }

        return resultSet;
    }

    /**
     * информация о производителях заданного сувенира, произведенного в заданном году.
     **/
    public ResultSet producersWithSouvenirMadeIn(String souvenir, String year)
    {
        String sql = "SELECT * FROM producer WHERE name IN " +
                "(SELECT producer FROM souvenirs WHERE name = ? AND release_date BETWEEN ?  AND ?)";
        ResultSet resultSet = null;

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, souvenir);
            statement.setString(2, year + "0101");
            statement.setString(3, year + "1231");
            resultSet = statement.executeQuery();
        } catch (SQLException e)
        {
            System.out.println(e);
        }

        return resultSet;
    }
}
