package entity;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.logging.Level;

public class BusPlace
{
    static Logger LOGGER = Logger.getLogger(BusPlace.class.getName());

    private int placeId;
    private int stopId;

    public BusPlace(int placeId, int stopId)
    {
        this.placeId = placeId;
        this.stopId = stopId;
    }

    public int getPlaceId()
    {
        return placeId;
    }

    public int getStopId()
    {
        return stopId;
    }

    public void use()
    {
        try
        {
            LOGGER.log(Level.INFO, this.toString() + " using by " + Thread.currentThread().getName());
            TimeUnit.MILLISECONDS.sleep(new java.util.Random().nextInt(200));
        } catch (InterruptedException e)
        {
            LOGGER.log(Level.SEVERE, "", e);
        }
    }

    @Override
    public String toString()
    {
        return "bus place #" + placeId + " on bus stop #" + stopId;
    }
}
