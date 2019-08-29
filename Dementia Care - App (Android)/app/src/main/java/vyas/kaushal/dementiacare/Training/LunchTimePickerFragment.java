package vyas.kaushal.dementiacare.Training;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import vyas.kaushal.dementiacare.Alarms.LunchAlarm;
import vyas.kaushal.dementiacare.R;

public class LunchTimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        return timePickerDialog;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String hour = ((hourOfDay < 10) ? ("0" + hourOfDay) : Integer.toString(hourOfDay));
        String minutes = ((minute < 10) ? ("0" + minute) : Integer.toString(minute));

        // ToDo: Check if Selected Time is Between 11 AM - 2 PM

        Calendar calendar = Calendar.getInstance();
        calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                hourOfDay,
                minute,
                0
        );
        setLunchAlarm(calendar.getTimeInMillis());

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LunchReminder", ("TRAINING TIME - " + hour + ":" + minutes));
        editor.commit();

        TextView lblLunchReminderTime = getActivity().findViewById(R.id.lbl_lunch_reminder);
        lblLunchReminderTime.setText("TRAINING TIME - " + hour + ":" + minutes);

        FloatingActionButton btnDeleteLunchReminder = getActivity().findViewById(R.id.btn_lunch_delete);
        btnDeleteLunchReminder.show();
    }

    private void setLunchAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), LunchAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}
