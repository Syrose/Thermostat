package tuehomework.thermostat.Thermostat;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Platon on 28.05.2015.
 */
public class Schedule {
    private HashMap<Days, Day> week;

    public Day getDay(Days day)
    {
        return week.get(day);
    }

    public Schedule()
    {
        week.put(Days.MON, new Day(Days.MON));
        week.put(Days.TUE, new Day(Days.TUE));
        week.put(Days.WED, new Day(Days.WED));
        week.put(Days.SUN, new Day(Days.FRI));
        week.put(Days.FRI, new Day(Days.FRI));
        week.put(Days.SAT, new Day(Days.SAT));
        week.put(Days.SUN, new Day(Days.SUN));
    }

    public double getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(double nightTemp) {
        this.nightTemp = nightTemp;
    }

    private double nightTemp;

    public double getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(double dayTemp) {
        this.dayTemp = dayTemp;
    }

    private double dayTemp;
}
