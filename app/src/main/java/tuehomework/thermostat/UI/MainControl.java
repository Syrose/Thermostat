package tuehomework.thermostat.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import tuehomework.thermostat.R;
import tuehomework.thermostat.Thermostat.OnTemperatureChangedListener;
import tuehomework.thermostat.Thermostat.OnThermostatChosenListener;
import tuehomework.thermostat.Thermostat.Schedule;
import tuehomework.thermostat.Thermostat.Thermostat;
import tuehomework.thermostat.Thermostat.ThermostatClient;

public class MainControl extends Fragment implements OnThermostatChosenListener, OnTemperatureChangedListener {

    // TODO: Rename and change types and number of parameters
    public static MainControl newInstance() {
        MainControl fragment = new MainControl();
        return fragment;
    }

    public MainControl() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_control, container, false);
        ImageButton b_settings = (ImageButton) v.findViewById(R.id.imageButton2);
        ImageButton b_chooser = (ImageButton) v.findViewById(R.id.imageButton);

        b_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ScheduleSettings.class);
                startActivity(i);
            }
        });

        b_chooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ThermostatChooser.class);
                startActivity(i);
            }
        });

        Thermostat thermostat = ThermostatClient.getInstance().getChosenThermostat();
        ThermostatClient.getInstance().setOnThermostatChosenListener(this);
        //TODO: add setOnTemperatureChanged
        if (thermostat != null) {
            TextView tw_act = (TextView) v.findViewById(R.id.textView);
            TextView tw_tar = (TextView) v.findViewById(R.id.textView2);
            tw_act.setText(Double.toString(thermostat.getActualTemperature()));
            tw_tar.setText(Double.toString(thermostat.getCurrentTargetTemperature()));
        }

        return v;
    }

    @Override
    public void onActualTemperatureChanged(double new_temp) {
        Thermostat thermostat = ThermostatClient.getInstance().getChosenThermostat();
        if (getView() != null) {
            TextView tw_act = (TextView) getView().findViewById(R.id.textView);
            tw_act.setText(Double.toString(thermostat.getActualTemperature()));
        }
    }

    @Override
    public void onTargetTemperatureChanged(double new_temp) {
        Thermostat thermostat = ThermostatClient.getInstance().getChosenThermostat();
        if (getView() != null) {
            TextView tw_tar = (TextView) getView().findViewById(R.id.textView2);
            tw_tar.setText(Double.toString(thermostat.getCurrentTargetTemperature()));
        }
    }

    @Override
    public void onThermostatChosen() {
        Thermostat thermostat = ThermostatClient.getInstance().getChosenThermostat();
        if (getView() != null) {
            TextView tw_act = (TextView) getView().findViewById(R.id.textView);
            TextView tw_tar = (TextView) getView().findViewById(R.id.textView2);
            tw_act.setText(Double.toString(thermostat.getActualTemperature()));
            tw_tar.setText(Double.toString(thermostat.getCurrentTargetTemperature()));
        }
    }

    @Override
    public void onDayTemperatureChanged(double new_temp) {}

    @Override
    public void onNightTemperatureChanged(double new_temp) {}
}
