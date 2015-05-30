package tuehomework.thermostat.UI;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import tuehomework.thermostat.R;
import tuehomework.thermostat.Thermostat.Days;
import tuehomework.thermostat.Thermostat.Schedule;
import tuehomework.thermostat.Thermostat.Simulator;
import tuehomework.thermostat.Thermostat.Switch;


public class EditSwitches extends ActionBarActivity {

    public static final String DAY = "DAY";
    private Days day;
    public static EditSwitches currentInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_switches);

        Bundle b = getIntent().getExtras();
        getSupportActionBar().setTitle(((Days)b.get(DAY)).toString());
        day = (Days)b.get(DAY);
        SwitchesArrayAdapter adapter1 = new SwitchesArrayAdapter(this, Simulator.currentInstance.getExecutedSchedule().getDay(day).getNightToDaySwitches());
        SwitchesArrayAdapter adapter2 = new SwitchesArrayAdapter(this, Simulator.currentInstance.getExecutedSchedule().getDay(day).getDayToNightSwitches());

        ListView lw1 = (ListView)findViewById(R.id.listView2);
        ListView lw2 = (ListView)findViewById(R.id.listView3);

        lw1.setAdapter(adapter1);
        lw2.setAdapter(adapter2);

        TextView tw1 = (TextView)findViewById(R.id.textView9);
        TextView tw2 = (TextView)findViewById(R.id.textView10);

        tw1.setText("Day: " + Simulator.getInstance().getDayTemp() / 10.0 + " C");
        tw2.setText("Night: " + Simulator.getInstance().getNightTemp() / 10.0 + " C");
        currentInstance = this;
    }


    public void refreshData()
    {
        ListView lw1 = (ListView)findViewById(R.id.listView2);
        ListView lw2 = (ListView)findViewById(R.id.listView3);

        ((SwitchesArrayAdapter)lw1.getAdapter()).notifyDataSetChanged();
        ((SwitchesArrayAdapter)lw2.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_switches, menu);
        return true;
    }

    public void addToNight(View view)
    {
        Intent i = new Intent(this, AddSwitch.class);
        Bundle b = new Bundle();
        b.putBoolean(AddSwitch.TYPE, false);
        i.putExtras(b);
        i.putExtra(AddSwitch.DAY, day);
        startActivity(i);
    }

    public void addToDay(View view)
    {
        Intent i = new Intent(this, AddSwitch.class);
        Bundle b = new Bundle();
        b.putBoolean(AddSwitch.TYPE, true);
        i.putExtras(b);
        i.putExtra(AddSwitch.DAY, day);
        startActivity(i);
    }
}
