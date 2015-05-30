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
        week = new HashMap<>();
        week.put(Days.MON, new Day(Days.MON));
        week.put(Days.TUE, new Day(Days.TUE));
        week.put(Days.WED, new Day(Days.WED));
        week.put(Days.THU, new Day(Days.THU));
        week.put(Days.FRI, new Day(Days.FRI));
        week.put(Days.SAT, new Day(Days.SAT));
        week.put(Days.SUN, new Day(Days.SUN));

        week.get(Days.MON).addNightToDaySwitch(new Switch(new Time(5, 0)));

        nightTemp = 180;
        dayTemp = 240;
    }

    public int getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(int nightTemp) {
        this.nightTemp = nightTemp;
    }

    private int nightTemp;

    public int getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(int dayTemp) {
        this.dayTemp = dayTemp;
    }

    private int dayTemp;
}
