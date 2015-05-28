package tuehomework.thermostat.Thermostat;

/**
 * Created by Platon on 28.05.2015.
 */
public interface OnTemperatureChangedListener {
    void onActualTemperatureChanged(double new_temp);
    void onTargetTemperatureChanged(double new_temp);
    void onDayTemperatureChanged(double new_temp);
    void onNightTemperatureChanged(double new_temp);
}
