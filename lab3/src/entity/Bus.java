package entity;

import pool.Route;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Bus extends Thread
{
    static Logger LOGGER = Logger.getLogger(Bus.class.getName());

    private boolean standing;
    private Route route;

    public Bus(Route route)
    {
        this.route = route;
        this.setName("entity.Bus #" + this.getId());
        LOGGER.log(Level.INFO, "entity.Bus #" + this.getId() + " was created");
    }

    public void run()
    {
        BusPlace place = null;

        for (int i = 0; i < route.getRouteSize(); ++i)
        {
            place = route.getBusPlace(i);
            standing = true;

            place.use();

            standing = false;
            route.returnBusPlace(place);
        }
        LOGGER.log(Level.INFO, "entity.Bus #" + this.getId() + " passed its route");
    }

    public boolean isStanding()
    {
        return standing;
    }
}
