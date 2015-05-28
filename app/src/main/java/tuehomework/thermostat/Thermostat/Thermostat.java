package tuehomework.thermostat.Thermostat;

/**
 * Created by Platon on 28.05.2015.
 */
public class Thermostat {
    public static Thermostat getInstance() {
        if (instance == null)
        {
            instance = new Thermostat();
        }

        return instance;
    }

    private static Thermostat instance = null;

    public void setTemperatureChangedListener(OnTemperatureChangedListener temperatureChangedListener) {
        this.temperatureChangedListener = temperatureChangedListener;
    }

    OnTemperatureChangedListener temperatureChangedListener;

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    Schedule schedule;

    public double getCurrentTargetTemperature()
    {
        return 19.0;
    }

    public double getActualTemperature()
    {
        return 19.5;
    }

    private Thermostat()
    {
        schedule = new Schedule();
    }
}
