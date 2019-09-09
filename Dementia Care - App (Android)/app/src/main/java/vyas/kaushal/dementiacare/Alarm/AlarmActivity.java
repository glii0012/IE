package vyas.kaushal.dementiacare.Alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Timer;
import java.util.TimerTask;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;
import vyas.kaushal.dementiacare.R;

public class AlarmActivity extends AppCompatActivity {

    private Ringtone trainingRingtone;
    private Uri trainingUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.WHITE);
        setContentView(R.layout.activity_alarm);

        PulsatorLayout plRemoveAlarm = findViewById(R.id.plRemoveAlarm);
        plRemoveAlarm.start();

        ImageView ivAlarm = findViewById(R.id.ivAlarm);
        TextView lblAlarmType = findViewById(R.id.lblAlarmType);

        if (getIntent().getStringExtra("AlarmType").equals("Water")) {
            lblAlarmType.setText("IT'S TIME TO DRINK WATER");

            trainingUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        }
        else if (getIntent().getStringExtra("AlarmType").equals("Lunch")) {
            ivAlarm.setImageResource(R.drawable.lunch);
            lblAlarmType.setText("IT'S LUNCH TIME");

            trainingUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        }
        else {
            ivAlarm.setImageResource(R.drawable.dinner);
            lblAlarmType.setText("IT'S DINNER TIME");

            trainingUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        }

        trainingRingtone = RingtoneManager.getRingtone(getApplicationContext(), trainingUri);
        trainingRingtone.play();

        FloatingActionButton btnRemoveAlarm = findViewById(R.id.btnRemoveAlarm);
        btnRemoveAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTraining();
            }
        });

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                stopTraining();
            }
        }, 60000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopTraining();
    }

    @Override
    public void onBackPressed() {
        stopTraining();
    }

    private void stopTraining() {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (getIntent().getStringExtra("AlarmType").equals("Water")) {
            notificationManager.cancel(1);
        }
        else if (getIntent().getStringExtra("AlarmType").equals("Lunch")) {
            notificationManager.cancel(2);
        }
        else {
            notificationManager.cancel(3);
        }

        trainingRingtone.stop();
        finishAndRemoveTask();
    }
}
