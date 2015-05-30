package tuehomework.thermostat.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import tuehomework.thermostat.R;
import tuehomework.thermostat.Thermostat.Day;
import tuehomework.thermostat.Thermostat.OnTemperatureChangedListener;
import tuehomework.thermostat.Thermostat.OnThermostatChosenListener;
import tuehomework.thermostat.Thermostat.OnTimeChangedListener;
import tuehomework.thermostat.Thermostat.Schedule;
import tuehomework.thermostat.Thermostat.Simulator;
import tuehomework.thermostat.Thermostat.Thermostat;
import tuehomework.thermostat.Thermostat.ThermostatClient;
import tuehomework.thermostat.Thermostat.Time;

public class MainControl extends Fragment implements OnThermostatChosenListener, OnTemperatureChangedListener, OnTimeChangedListener {

    // TODO: Rename and change types and number of parameters
    public static MainControl newInstance() {
        MainControl fragment = new MainControl();
        return fragment;
    }

    Simulator simulator;
    public static MainControl currentInstance;

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
                Toast.makeText(getActivity(), "Extended schedule settings are not implemented yet.\n\nSwipe to the right tab to open current schedule.", Toast.LENGTH_LONG).show();
            }
        });

        b_chooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Thermostat choice menu is not implemented yet", Toast.LENGTH_SHORT).show();
            }
        });

        Thermostat thermostat = ThermostatClient.getInstance().getChosenThermostat();
        ThermostatClient.getInstance().setOnThermostatChosenListener(this);
        //TODO: add setOnTemperatureChanged

        simulator = Simulator.getInstance();
        simulator.addTempChangedListener(this);
        simulator.setTimeChangedListener(this);
        TextView tw_act = (TextView) v.findViewById(R.id.textView);
        TextView tw_tar = (TextView) v.findViewById(R.id.textView2);
        tw_act.setText(Double.toString(simulator.getCurrentTemperature() / 10.0));
        tw_tar.setText(Double.toString(simulator.getGoalTemperature() / 10.0));
        Switch sw = (Switch)v.findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    Simulator.getInstance().hold();
                } else {
                    Simulator.getInstance().unhold();
                }
            }
        });
        simulator.start();
        return v;
    }

    @Override
    public void onActualTemperatureChanged(double new_temp) {
        if (getView() != null) {
            TextView tw_act = (TextView) getView().findViewById(R.id.textView);
            tw_act.setText(Double.toString(simulator.getCurrentTemperature() / 10.0));
        }
    }

    @Override
    public void onTargetTemperatureChanged(double new_temp) {
        if (getView() != null) {
            TextView tw_tar = (TextView) getView().findViewById(R.id.textView2);
            tw_tar.setText(Double.toString(simulator.getGoalTemperature() / 10.0));
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

    @Override
    public void OnCurrentTimeChanged(Time newTime) {
        if (getView() != null) {
                TextView tw_day = (TextView) getView().findViewById(R.id.textView8);
            if (newTime.getMinutes() == 0)
            {
                int i = 0;
            }
                tw_day.setText(newTime.getHours() + ":" + (newTime.getMinutes() < 10 ? "0" : "")  + newTime.getMinutes());

        }
    }

    @Override
    public void OnCurrentDayChanged(Day newDay) {

        if (getView() != null) {
            TextView tw_day = (TextView) getView().findViewById(R.id.textView3);
            tw_day.setText(newDay.getName().toString());
        }
    }
}
