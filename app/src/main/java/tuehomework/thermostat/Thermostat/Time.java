package tuehomework.thermostat.Thermostat;

/**
 * Created by Platon on 28.05.2015.
 */
public class Time {
    public int getHours() {
        return hours;
    }
    public int getMinutes() {
        return minutes;
    }
    public Time ()
    {
        hours=0;
        minutes=0;
    }

    public Time(int hours, int seconds) {
        this.hours = hours;
        this.minutes = seconds;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    private int hours;
    private int minutes;

}
