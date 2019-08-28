package vyas.kaushal.dementiacare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Timer;
import java.util.TimerTask;

public class LunchActivity extends AppCompatActivity {

    Ringtone lunchRingtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);

        Uri lunchUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        lunchRingtone = RingtoneManager.getRingtone(getApplicationContext(), lunchUri);
        lunchRingtone.play();

        FloatingActionButton btnStopTraining = findViewById(R.id.btn_lunch_notification);
        btnStopTraining.setOnClickListener(new View.OnClickListener() {
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

    private void stopTraining() {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(2);

        lunchRingtone.stop();
        finish();
    }
}
