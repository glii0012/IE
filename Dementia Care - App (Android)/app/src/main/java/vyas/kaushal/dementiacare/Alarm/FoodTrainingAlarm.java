package vyas.kaushal.dementiacare.Alarm;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

import vyas.kaushal.dementiacare.R;

public class FoodTrainingAlarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar calendar = Calendar.getInstance();
        String alarmType = intent.getStringExtra("AlarmType");

        if ((alarmType.equals("Lunch")) && (calendar.get(Calendar.HOUR_OF_DAY) >= 11) && (calendar.get(Calendar.HOUR_OF_DAY) <= 13)) {
            setAlarm(context, alarmType, 2);
        }
        else if ((alarmType.equals("Dinner")) && (calendar.get(Calendar.HOUR_OF_DAY) >= 18) && (calendar.get(Calendar.HOUR_OF_DAY) <= 20)) {
            setAlarm(context, alarmType, 3);
        }
        else {
            // Do Nothing
        }
    }

    private void setAlarm(Context context, String alarmType, int notificationNo) {
        Intent alarmActivity = new Intent(context, AlarmActivity.class);
        alarmActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        Notification notification = new NotificationCompat.Builder(context, "DC")
                .setSmallIcon(alarmType.equals("Lunch") ? R.drawable.lunch : R.drawable.dinner)
                .setContentTitle("IT'S " + alarmType.toUpperCase() + " TIME")
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationNo, notification);

        alarmActivity.putExtra("AlarmType", alarmType);
        context.startActivity(alarmActivity);
    }
}
