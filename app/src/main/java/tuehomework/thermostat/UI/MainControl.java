package tuehomework.thermostat.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import tuehomework.thermostat.R;
import tuehomework.thermostat.Thermostat.Schedule;
import tuehomework.thermostat.Thermostat.Thermostat;

public class MainControl extends Fragment {

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
        ImageButton b_settings = (ImageButton)v.findViewById(R.id.imageButton2);
        ImageButton b_chooser = (ImageButton)v.findViewById(R.id.imageButton);

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

        return v;
    }
}
