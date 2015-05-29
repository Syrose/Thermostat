package tuehomework.thermostat.Thermostat;

/**
 * Created by mac on 29.05.15.
 */
public interface OnTimeChangedListener {

    void OnCurrentTimeChanged(Time newTime);
    void OnCurrentDayChanged(Day newDay);
}
