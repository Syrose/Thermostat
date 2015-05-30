package tuehomework.thermostat.Thermostat;

import java.util.ArrayList;

/**
 * Created by Platon on 28.05.2015.
 */
public class ThermostatClient {
    private static ThermostatClient instance = null;

    private ThermostatClient() {
        thermostats = new ArrayList<Thermostat>();
        thermostats.add(new Thermostat());
    }

    public static ThermostatClient getInstance() {
        if(instance == null) {
            instance = new ThermostatClient();
        }
        return instance;
    }

    public Thermostat getChosenThermostat() {
        return chosen;
    }

    private Thermostat chosen = new Thermostat();
    private ArrayList<Thermostat> thermostats;

    public void setOnThermostatChosenListener(OnThermostatChosenListener thermostatChosenListener) {
        this.thermostatChosenListener = thermostatChosenListener;
    }

    private OnThermostatChosenListener thermostatChosenListener;

    public void chooseThermostat(int position)
    {
        chosen = thermostats.get(position);
        if (thermostatChosenListener != null) {
            thermostatChosenListener.onThermostatChosen();
        }
    }

    public ArrayList<Thermostat> getAvailableThermostats()
    {
        return thermostats;
    }
}
