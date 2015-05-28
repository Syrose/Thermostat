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

    public ArrayList<Switch> getDayToNightSwitches() {
        return dayToNightSwitches;
    }

    ArrayList<Switch> dayToNightSwitches;

    public ArrayList<Switch> getNightToDaySwitches() {
        return nightToDaySwitches;
    }

    ArrayList<Switch> nightToDaySwitches;

    public void addNightSwitch(Switch sw)
    {
        nightToDaySwitches.add(sw);
    }

    public void addDaySwitch(Switch sw)
    {
        dayToNightSwitches.add(sw);
    }

    public void deleteNightSwitch(int position)
    {
        nightToDaySwitches.remove(position);
    }

    public void deleteDaySwitch(int position)
    {
        dayToNightSwitches.remove(position);
    }
}
