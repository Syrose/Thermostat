package tuehomework.thermostat.UI;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import tuehomework.thermostat.R;
import tuehomework.thermostat.Thermostat.Days;


public class EditSwitches extends ActionBarActivity {

    public static final String DAY = "DAY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_switches);

        Bundle b = getIntent().getExtras();
        getSupportActionBar().setTitle(((Days)b.get(DAY)).toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_switches, menu);
        return true;
    }
}
