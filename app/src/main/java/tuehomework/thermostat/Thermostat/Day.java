package tuehomework.thermostat.Thermostat;

import java.util.ArrayList;

/**
 * Created by Platon on 28.05.2015.
 */
public class Day {

    Days name;

    public Day(Days name)
    {
        this.name = name;
    }

    public ArrayList<Switch> getDaySwitches() {
        return daySwitches;
    }

    ArrayList<Switch> daySwitches;

    public ArrayList<Switch> getNightSwitches() {
        return nightSwitches;
    }

    ArrayList<Switch> nightSwitches;

    public void addNightSwitch(Switch sw)
    {
        nightSwitches.add(sw);
    }

    public void addDaySwitch(Switch sw)
    {
        daySwitches.add(sw);
    }

    public void deleteNightSwitch(int position)
    {
        nightSwitches.remove(position);
    }

    public void deleteDaySwitch(int position)
    {
        daySwitches.remove(position);
    }
}
