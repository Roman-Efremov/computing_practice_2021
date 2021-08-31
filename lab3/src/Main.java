import entity.Bus;
import entity.BusStop;
import pool.Route;

import java.util.logging.Level;
import java.util.logging.Logger;

/*Разработать многопоточное приложение. Использовать возможности, предоставляемые пакетом java.util.concurrent.
  Не использовать слово synchronized. Все сущности, желающие получить доступ к ресурсу, должны быть потоками

5.Автобусные остановки. На маршруте несколько остановок. На одной остановке может
  останавливаться несколько автобусов одновременно, но не более заданного числа.
*/

public class Main
{
    public static void main(String[] args)
    {
        Route route = new Route();

        route.addBusStop(new BusStop(0, 3));
        route.addBusStop(new BusStop(10, 2));
        route.addBusStop(new BusStop(20, 2));

        for(int i = 0; i< 8; ++i)
        {
            new Bus(route).start();
        }
    }
}
