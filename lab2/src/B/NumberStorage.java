package B;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NumberStorage
{
    List<Double> list;

    public NumberStorage()
    {
        this.list = new ArrayList<Double>();
    }

    public void add(Double number)
    {
        list.add(number);
    }

    public void delete(Double number)
    {
        list.remove(number);
    }

    public Double findClosestNumber(Double number)
    {
        Iterator iterator = list.iterator();
        Double min = (Double) iterator.next();

        while (iterator.hasNext())
        {
            Double elem = (Double) iterator.next();
            if (Math.abs(elem - number) < Math.abs(min - number))
            {
                min = elem;
            }
        }
        return min;
    }

    @Override
    public String toString()
    {
        return "Numbers" + list;
    }
}