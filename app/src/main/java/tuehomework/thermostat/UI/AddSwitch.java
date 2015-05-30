package tuehomework.thermostat.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import tuehomework.thermostat.R;
import tuehomework.thermostat.Thermostat.Day;
import tuehomework.thermostat.Thermostat.Days;
import tuehomework.thermostat.Thermostat.Schedule;
import tuehomework.thermostat.Thermostat.Simulator;
import tuehomework.thermostat.Thermostat.Switch;
import tuehomework.thermostat.Thermostat.Time;

public class AddSwitch extends ActionBarActivity {


    public static final String TYPE = "TYPE";
    public static final String DAY = "DAY";
    private boolean type;
    private Days day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_switch);

        Bundle b = getIntent().getExtras();
        type = b.getBoolean(TYPE);
        getSupportActionBar().setTitle(type ? "Adding switch to day temperature" : "Adding switch to night temperature");
        day = (Days)b.get(DAY);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_switch, menu);
        return true;
    }

    public void addSwitch(View view)
    {

        EditText editText = (EditText)findViewById(R.id.editText);
        EditText editText2 = (EditText)findViewById(R.id.editText2);
        int hours = 0;
        int minutes = 0;

        try {
            hours = Integer.parseInt(editText.getText().toString());
            minutes = Integer.parseInt(editText2.getText().toString());
        } catch (NumberFormatException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Wrong time format, should be HH:MM")
                    .setTitle("Wrong format");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }

        if (!(hours <= 24 && hours >= 0 && minutes < 60 && minutes >= 0))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Wrong time format, should be HH:MM")
                    .setTitle("Wrong format");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

            return;
        }

        Schedule schedule = Simulator.getInstance().getExecutedSchedule();

        Day day_obj = schedule.getDay(day);

        if (type)
        {
            day_obj.addNightToDaySwitch(new Switch(new Time(hours, minutes)));
        } else {
            day_obj.addDayToNightSwitch(new Switch(new Time(hours, minutes)));
        }

        EditSwitches.currentInstance.refreshData();
        finish();
    }
}
