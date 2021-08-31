package pool;

import entity.BusPlace;
import entity.BusStop;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class Route
{
    static Logger LOGGER = Logger.getLogger(Route.class.getName());

    private ReentrantLock lock = new ReentrantLock();
    private ArrayList<BusStop> route = new ArrayList<>();

    public Route() {}

    public void addBusStop(BusStop stop)
    {
        route.add(stop);
    }

    public int getRouteSize()
    {
        return route.size();
    }

    /*public int getBusPlaceId(int i)
    {
        if (i < route.size())
            return route.get(i).getStopId();
        else
            return 0;
    }*/

    public BusPlace getBusPlace(int i)
    {
        BusPlace place = null;
        /*
        for (entity.BusStop stop : route)
        {
            if(busStopId == stop.getStopId())
            {
                place = stop.takePlace();
            }
        }*/
        place = route.get(i).takePlace();//

        return place;
    }

    public boolean returnBusPlace(BusPlace place)
    {
        boolean rez = false;

        if (lock.tryLock())
        {
            for (BusStop stop : route)
            {
                if (stop.getStopId() == place.getStopId())
                    stop.returnPlace(place);
            }
            rez = true;
            lock.unlock();
        }

        return rez;
    }
}
