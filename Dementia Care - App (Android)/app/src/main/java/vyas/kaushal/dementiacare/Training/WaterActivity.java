package vyas.kaushal.dementiacare.Training;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;
import vyas.kaushal.dementiacare.Alarm.WaterTrainingAlarm;
import vyas.kaushal.dementiacare.R;

public class WaterActivity extends AppCompatActivity {

    private PulsatorLayout plSixTimes;
    private PulsatorLayout plTwelveTimes;
    private PulsatorLayout plRemoveWaterTime;
    private FloatingActionButton btnSixTimes;
    private FloatingActionButton btnTwelveTimes;
    private FloatingActionButton btnRemoveWaterTime;
    private TextView lblNumberOfTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        // Adding Back Button to App Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String waterTrainingTime = sharedPreferences.getString("WaterTrainingTime", "0");

        // Pulse Animation
        plSixTimes = findViewById(R.id.plSixTimes);
        plSixTimes.start();
        plTwelveTimes = findViewById(R.id.plTwelveTimes);
        plTwelveTimes.start();
        plRemoveWaterTime = findViewById(R.id.plRemoveWaterTime);
        plRemoveWaterTime.start();

        btnSixTimes = findViewById(R.id.btnSixTimes);
        btnSixTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSixTimesADay();
            }
        });

        btnTwelveTimes = findViewById(R.id.btnTwelveTimes);
        btnTwelveTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTwelveTimesADay();
            }
        });

        btnRemoveWaterTime = findViewById(R.id.btnRemoveWaterTime);
        btnRemoveWaterTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeWaterTrainingTime();
            }
        });

        lblNumberOfTimes = findViewById(R.id.lblNumberOfTimes);

        if (waterTrainingTime.equals("0")) {
            plRemoveWaterTime.setVisibility(View.INVISIBLE);
            btnRemoveWaterTime.hide();
            lblNumberOfTimes.setText("");
        }
        else if (waterTrainingTime.equals("6")) {
            plSixTimes.setVisibility(View.INVISIBLE);
            plTwelveTimes.setVisibility(View.INVISIBLE);
            btnSixTimes.hide();
            btnTwelveTimes.hide();

            plRemoveWaterTime.setVisibility(View.VISIBLE);
            btnRemoveWaterTime.show();
            lblNumberOfTimes.setText("EVERY 2 HOURS");
        }
        else {
            plSixTimes.setVisibility(View.INVISIBLE);
            plTwelveTimes.setVisibility(View.INVISIBLE);
            btnSixTimes.hide();
            btnTwelveTimes.hide();

            plRemoveWaterTime.setVisibility(View.VISIBLE);
            btnRemoveWaterTime.show();
            lblNumberOfTimes.setText("EVERY HOUR");
        }
    }

    // Options Item Selected Listeners
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setSixTimesADay() {
        setDailyWaterAlarm();
        lblNumberOfTimes.setText("EVERY 2 HOURS");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("WaterTrainingTime", "6");
        editor.putString("WaterTrainingFirstTimeDay", "True");
        editor.commit();
    }

    private void setTwelveTimesADay() {
        setDailyWaterAlarm();
        lblNumberOfTimes.setText("EVERY HOUR");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("WaterTrainingTime", "12");
        editor.putString("WaterTrainingFirstTimeDay", "True");
        editor.commit();
    }

    private void setDailyWaterAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                9,
                0,
                0
        );

        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, WaterTrainingAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        plSixTimes.setVisibility(View.INVISIBLE);
        plTwelveTimes.setVisibility(View.INVISIBLE);
        btnSixTimes.hide();
        btnTwelveTimes.hide();

        plRemoveWaterTime.setVisibility(View.VISIBLE);
        btnRemoveWaterTime.show();
    }

    private void removeWaterTrainingTime() {
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, WaterTrainingAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);

        pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);

        plSixTimes.setVisibility(View.VISIBLE);
        plTwelveTimes.setVisibility(View.VISIBLE);
        btnSixTimes.show();
        btnTwelveTimes.show();

        plRemoveWaterTime.setVisibility(View.INVISIBLE);
        btnRemoveWaterTime.hide();
        lblNumberOfTimes.setText("");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("WaterTrainingTime");
        editor.remove("WaterTrainingFirstTimeDay");
        editor.commit();
    }
}
