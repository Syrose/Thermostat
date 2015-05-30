package tuehomework.thermostat.UI;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import tuehomework.thermostat.R;
import tuehomework.thermostat.Thermostat.Days;
import tuehomework.thermostat.Thermostat.Switch;

public class SwitchesArrayAdapter extends ArrayAdapter {
    private final Context context;
    private final ArrayList<Switch> switches;

    public SwitchesArrayAdapter(Context context, ArrayList<Switch> switches) {
        super(context, R.layout.lw_layout_with_edit, switches);
        this.context = context;
        this.switches = switches;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.lw_with_delete, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.time);
        ImageButton imageButton = (ImageButton) rowView.findViewById(R.id.delete);
        textView.setText(switches.get(position).toString());
        String s = switches.get(position).toString();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditSwitches.currentInstance.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        AlertDialog.Builder builder = new AlertDialog.Builder(EditSwitches.currentInstance);

                        builder.setMessage("Do you really want to delete switch at " + switches.get(position) + "?")
                                .setTitle("Delete switch");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                switches.remove(position);
                                notifyDataSetChanged();

                            }
                        });
                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });

            }
        });

        return rowView;
    }
}