package vyas.kaushal.dementiacare.Training;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import vyas.kaushal.dementiacare.Alarms.DinnerAlarm;
import vyas.kaushal.dementiacare.R;

public class DinnerFragment extends Fragment {

    private Button btnSetReminder;
    private TextView lblReminderTime;
    private FloatingActionButton btnDeleteReminder;

    public DinnerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dinner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String dinnerReminder = sharedPreferences.getString("DinnerReminder", "NO TRAINING SET");

        lblReminderTime = view.findViewById(R.id.lbl_dinner_reminder);
        lblReminderTime.setText(dinnerReminder);

        btnSetReminder = view.findViewById(R.id.btn_dinner_reminder);
        btnSetReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new DinnerTimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "dinner_time");
            }
        });

        btnDeleteReminder = view.findViewById(R.id.btn_dinner_delete);
        btnDeleteReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDinnerAlarm();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("DinnerReminder");
                editor.commit();
                lblReminderTime.setText("NO TRAINING SET");

                btnDeleteReminder.hide();
            }
        });

        if (dinnerReminder.equals("NO TRAINING SET")) {
            btnDeleteReminder.hide();
        }
        else {
            btnDeleteReminder.show();
        }
    }

    private void cancelDinnerAlarm() {
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), DinnerAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(pendingIntent);
    }
}
