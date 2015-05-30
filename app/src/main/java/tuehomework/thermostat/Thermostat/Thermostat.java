package tuehomework.thermostat.Thermostat;

import tuehomework.thermostat.UI.MainActivity;
import tuehomework.thermostat.UI.MainControl;

/**
 * Created by Platon on 28.05.2015.
 */
public class Thermostat {
    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    Schedule schedule = new Schedule();

    public double getCurrentTargetTemperature()
    {
        return 19.0;
    }

    public double getActualTemperature()
    {
        return 19.5;
    }
}
