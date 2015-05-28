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

import tuehomework.thermostat.R;
import tuehomework.thermostat.Thermostat.Days;
import tuehomework.thermostat.Thermostat.Thermostat;


public class MainSchedule extends Fragment {

    // TODO: Rename and change types and number of parameters
    public static MainSchedule newInstance() {
        MainSchedule fragment = new MainSchedule();
        return fragment;
    }

    public MainSchedule() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.main_schedule, container, false);

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

        return v;

    }
}
