package tuehomework.thermostat.UI;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tuehomework.thermostat.R;

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_control, container, false);
    }
}
