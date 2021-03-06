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


public class EditTemperature extends ActionBarActivity {

    public static final String DAY_OR_NIGHT = "DAY_OR_NIGHT";

    private boolean dayOrNight; //true is day, false is night

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_temperature);

        Bundle b = getIntent().getExtras();
        dayOrNight = b.getBoolean(DAY_OR_NIGHT);

        getSupportActionBar().setTitle(dayOrNight ? "Edit day temperature" : "Edit night temperature");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_temperature, menu);
        return true;
    }

    public void applyChange(View view)
    {
        EditText editText = (EditText)findViewById(R.id.editText);
        EditText editText2 = (EditText)findViewById(R.id.editText2);
        int integers = 0;
        int frac = 0;

        try {
            integers = Integer.parseInt(editText.getText().toString());
            frac = Integer.parseInt(editText2.getText().toString());
        } catch (NumberFormatException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Must be positive with .1 precision")
                    .setTitle("Wrong format");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

            return;
        }

        if (!(integers >= 0 && frac < 10 && frac >= 0))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Must be positive with .1 precision")
                    .setTitle("Wrong format");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

            return;
        }


        if (dayOrNight)
        {
            Simulator.getInstance().setDayTemp(integers * 10 + frac);
        } else {
            Simulator.getInstance().setNightTemp(integers * 10 + frac);
        }

        finish();
    }
}
