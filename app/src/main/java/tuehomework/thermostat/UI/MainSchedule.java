package tuehomework.thermostat.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import tuehomework.thermostat.R;
import tuehomework.thermostat.Thermostat.Days;
import tuehomework.thermostat.Thermostat.OnTemperatureChangedListener;
import tuehomework.thermostat.Thermostat.OnThermostatChosenListener;
import tuehomework.thermostat.Thermostat.Simulator;
import tuehomework.thermostat.Thermostat.Thermostat;
import tuehomework.thermostat.Thermostat.ThermostatClient;


public class MainSchedule extends Fragment implements OnTemperatureChangedListener, OnThermostatChosenListener{

    // TODO: Rename and change types and number of parameters
    public static MainSchedule newInstance() {
        MainSchedule fragment = new MainSchedule();
        return fragment;
    }

    public static MainSchedule currentInstance;

    public MainSchedule() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.main_schedule, container, false);

        currentInstance = this;
        ListView lw = (ListView)v.findViewById(R.id.listView);
        ScheduleArrayAdapter scheduleArrayAdapter = new ScheduleArrayAdapter(getActivity(), new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"});
        lw.setAdapter(scheduleArrayAdapter);
        ImageButton b1 = (ImageButton)v.findViewById(R.id.imageButton3);
        ImageButton b2 = (ImageButton)v.findViewById(R.id.imageButton4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putBoolean(EditTemperature.DAY_OR_NIGHT, true);
                Intent i = new Intent(getActivity(), EditTemperature.class);
                i.putExtras(b);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putBoolean(EditTemperature.DAY_OR_NIGHT, false);
                Intent i = new Intent(getActivity(), EditTemperature.class);
                i.putExtras(b);
                startActivity(i);
            }
        });

        Thermostat thermostat = ThermostatClient.getInstance().getChosenThermostat();
        ThermostatClient.getInstance().setOnThermostatChosenListener(this);
        //TODO: add setOnTemperatureChanged
            TextView tw_day = (TextView) v.findViewById(R.id.textView6);
            TextView tw_night = (TextView) v.findViewById(R.id.textView7);
            tw_day.setText(Double.toString(Simulator.currentInstance.getDayTemp() / 10.0));
            tw_night.setText(Double.toString(Simulator.currentInstance.getNightTemp() / 10.0));

        Simulator.getInstance().addTempChangedListener(this);
        return v;
    }

    @Override
    public void onDayTemperatureChanged(double new_temp) {
        Thermostat thermostat = ThermostatClient.getInstance().getChosenThermostat();
        if (getView() != null) {
            TextView tw_day = (TextView) getView().findViewById(R.id.textView6);
            tw_day.setText(Double.toString(Simulator.getInstance().getDayTemp() / 10.0));
        }
    }

    @Override
    public void onNightTemperatureChanged(double new_temp) {
        Thermostat thermostat = ThermostatClient.getInstance().getChosenThermostat();
        if (getView() != null) {
            TextView tw_night = (TextView) getView().findViewById(R.id.textView7);
            tw_night.setText(Double.toString(Simulator.getInstance().getNightTemp() / 10.0));
        }
    }

    @Override
    public void onThermostatChosen() {
        Thermostat thermostat = ThermostatClient.getInstance().getChosenThermostat();
        if (getView() != null) {
            TextView tw_day = (TextView) getView().findViewById(R.id.textView6);
            TextView tw_night = (TextView) getView().findViewById(R.id.textView7);
            tw_day.setText(Double.toString(thermostat.getSchedule().getDayTemp()));
            tw_night.setText(Double.toString(thermostat.getSchedule().getNightTemp()));
        }
    }

    @Override
    public void onActualTemperatureChanged(double new_temp) {}

    @Override
    public void onTargetTemperatureChanged(double new_temp) {}
}
