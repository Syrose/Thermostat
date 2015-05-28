package tuehomework.thermostat.Thermostat;

import java.util.ArrayList;

/**
 * Created by Platon on 28.05.2015.
 */
public class Switch {

    Time from;
    Time until;

    public Time getFrom()
    {
        return from;
    }

    public Time getUntil()
    {
        return until;
    }

    public Switch(Time from, Time until)
    {
        this.from = from;
        this.until = until;
    }
}
