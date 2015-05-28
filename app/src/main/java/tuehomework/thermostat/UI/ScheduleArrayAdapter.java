package tuehomework.thermostat.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import tuehomework.thermostat.R;
import tuehomework.thermostat.Thermostat.Days;
import tuehomework.thermostat.UI.EditSwitches;

public class ScheduleArrayAdapter extends ArrayAdapter {
    private final Context context;
    private final String[] values;

    public ScheduleArrayAdapter(Context context, String[] values) {
        super(context, R.layout.lw_layout_with_edit, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.lw_layout_with_edit, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.dayName);
        ImageButton imageButton = (ImageButton) rowView.findViewById(R.id.edit);
        textView.setText(values[position]);
        String s = values[position];
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                Intent i = new Intent(context, EditSwitches.class);
                i.putExtra(EditSwitches.DAY, Days.values()[position]);
                context.startActivity(i);
            }
        });

        return rowView;
    }
}