package tuehomework.thermostat.UI;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import tuehomework.thermostat.R;
import tuehomework.thermostat.Thermostat.Days;


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
}
