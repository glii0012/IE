package vyas.kaushal.dementiacare.Training;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import vyas.kaushal.dementiacare.R;

public class WaterFragment extends Fragment {

    private Button btnSetReminder;
    private TextView lblReminderTime;
    private FloatingActionButton btnDeleteReminder;

    public WaterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_water, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String waterReminder = sharedPreferences.getString("WaterReminder", "NO TRAINING SET");

        lblReminderTime = view.findViewById(R.id.lbl_water_reminder);
        lblReminderTime.setText(waterReminder);
    }
}
