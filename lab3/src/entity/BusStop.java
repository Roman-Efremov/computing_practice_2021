package entity;


import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;


public class BusStop
{
    static Logger LOGGER = Logger.getLogger(BusStop.class.getName());

    private int stopId;
    private int stopSize;
    private Queue<BusPlace> busPlaces = new ConcurrentLinkedQueue<>();
    private Semaphore semaphore;

    public BusStop(int stopId, int size)
    {
        this.stopId = stopId;
        this.stopSize = size;
        this.semaphore = new Semaphore(size, true);

        for (int i = 0; i < size; ++i)
        {
            busPlaces.add(new BusPlace(i, stopId));
        }
    }

    public int getStopId()
    {
        return stopId;
    }

    public int getStopSize()
    {
        return stopSize;
    }

    public BusPlace takePlace()
    {
        BusPlace place = null;

        try
        {
            semaphore.acquire();
            place = busPlaces.poll();
            if (place != null)
                LOGGER.log(Level.INFO, Thread.currentThread().getName() + " take " + place.toString());
        } catch (InterruptedException e)
        {
            LOGGER.log(Level.SEVERE, "", e);
        }

        return place;
    }

    public void returnPlace(BusPlace place)
    {
        busPlaces.add(place);
        semaphore.release();
    }
}
