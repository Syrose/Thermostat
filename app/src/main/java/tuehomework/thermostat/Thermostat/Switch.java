package tuehomework.thermostat.Thermostat;

import java.util.ArrayList;

/**
 * Created by Platon on 28.05.2015.
 */
public class Switch {

    public Switch(Time switchTime) {
        this.switchTime = switchTime;
    }
    public Switch()
    {
        this.switchTime=new Time();
    }

    Time switchTime;

    public Time getSwitchTime() {
        return switchTime;
    }

    public void setSwitchTime(Time switchTime) {
        this.switchTime = switchTime;
    }


}
