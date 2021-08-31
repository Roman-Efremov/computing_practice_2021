import dao.ConnectionCreator;
import dao.DataExtractor;
import dao.DataModifier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/*В каждом из заданий необходимо выполнить следующие действия:
    • организацию соединения с базой данных вынести в отдельный класс, метод которого возвращает соединение;
    • создать БД. Привести таблицы к одной из нормальных форм;
    • создать класс для выполнения запросов на извлечение информации из БД с использованием компилированных запросов;
    • создать класс на модификацию информации.

5.Сувениры. В БД хранится информация о сувенирах и их производителях.
  Для сувениров необходимо хранить:
    • название;
    • реквизиты производителя;
    • дату выпуска;
    • цену.
  Для производителей необходимо хранить:
    • название;
    • страну.

  • Вывести информацию о сувенирах заданного производителя.
  • Вывести информацию о сувенирах, произведенных в заданной стране.
  • Вывести информацию о производителях, чьи цены на сувениры меньше заданной.
  • Вывести информацию о производителях заданного сувенира, произведенного в заданном году.
  • Удалить заданного производителя и его сувениры
*/
public class Main
{
    public static void main(String[] args) throws SQLException
    {
        try (Connection connection = ConnectionCreator.createConnection())
        {
            DataExtractor dataExtractor = new DataExtractor(connection);
            ResultSet rs1;

            rs1 = dataExtractor.executeQuery("SELECT * FROM souvenirs");
            System.out.println("--souvenirs--");
            printResult(rs1);
            rs1 = dataExtractor.executeQuery("SELECT * FROM producer");
            System.out.println("--producer--");
            printResult(rs1);

            rs1 = dataExtractor.souvenirsByProducer("producer1");
            printResult(rs1);

            rs1 = dataExtractor.souvenirsByCountry("country1");
            printResult(rs1);

            rs1 = dataExtractor.producersWithSouvenirsPriceLessThen(10);
            printResult(rs1);

            rs1 = dataExtractor.producersWithSouvenirMadeIn("name7", "2020");
            printResult(rs1);

            DataModifier dataModifier = new DataModifier(connection);
            dataModifier.executeUpdate("DELETE FROM souvenirs WHERE producer = 'producer10'");
            dataModifier.executeUpdate("DELETE FROM producer WHERE name = 'producer10'");
            rs1 = dataExtractor.executeQuery("SELECT * FROM souvenirs");
            System.out.println("--souvenirs--");
            printResult(rs1);
            rs1 = dataExtractor.executeQuery("SELECT * FROM producer");
            System.out.println("--producer--");
            printResult(rs1);

            connection.close();
            DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
                try
                {
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException e)
                {
                    System.out.println(e);
                }
            });
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void printResult(ResultSet resultSet)
    {
        try
        {
            int columns = resultSet.getMetaData().getColumnCount();

            while (resultSet.next())
            {
                for (int i = 1; i <= columns; i++)
                {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
            System.out.println();
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
}