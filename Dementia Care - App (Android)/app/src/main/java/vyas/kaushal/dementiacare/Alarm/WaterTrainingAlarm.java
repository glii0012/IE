package vyas.kaushal.dementiacare.Alarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

import vyas.kaushal.dementiacare.R;

public class WaterTrainingAlarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar calendar = Calendar.getInstance();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (sharedPreferences.getString("WaterTrainingFirstTimeDay", "").equals("True")) {
            if ((calendar.get(Calendar.HOUR_OF_DAY) == 9) && (calendar.get(Calendar.MINUTE) <= 1)) {
                if (sharedPreferences.getString("WaterTrainingTime", "").equals("6")) {
                    setHourlyAlarm(context, 11);
                }
                else {
                    setHourlyAlarm(context, 10);
                }

                editor.putString("WaterTrainingFirstTimeDay", "False");
                editor.commit();
                callAlarm(context);
            }
            else if ((calendar.get(Calendar.HOUR_OF_DAY) >= 9) && (calendar.get(Calendar.HOUR_OF_DAY) <= 20)) {
                if (sharedPreferences.getString("WaterTrainingTime", "").equals("6")) {
                    if ((calendar.get(Calendar.HOUR_OF_DAY) % 2) == 0) {
                        setHourlyAlarm(context, (calendar.get(Calendar.HOUR_OF_DAY) + 1));
                    }
                    else {
                        setHourlyAlarm(context, (calendar.get(Calendar.HOUR_OF_DAY) + 2));
                    }
                }
                else {
                    setHourlyAlarm(context, (calendar.get(Calendar.HOUR_OF_DAY) + 1));
                }

                editor.putString("WaterTrainingFirstTimeDay", "False");
                editor.commit();
            }
            else {
                // Do Nothing - Time is between 09:00 PM - 11: 59 PM
            }
        }
        else {
            if (calendar.get(Calendar.HOUR_OF_DAY) >= 21) {
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                Intent oldIntent = new Intent(context, WaterTrainingAlarm.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, oldIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.cancel(pendingIntent);

                editor.putString("WaterTrainingFirstTimeDay", "True");
                editor.commit();
                callAlarm(context);
            }
            else {
                callAlarm(context);
            }
        }
    }

    private void setHourlyAlarm(Context context, int startHour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                startHour,
                0,
                0
        );

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, WaterTrainingAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (sharedPreferences.getString("WaterTrainingTime", "").equals("6")) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 2*AlarmManager.INTERVAL_HOUR, pendingIntent);
        }
        else {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pendingIntent);
        }
    }

    private void callAlarm(Context context) {
        Intent waterAlarmActivity = new Intent(context, AlarmActivity.class);
        waterAlarmActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        Notification notification = new NotificationCompat.Builder(context, "DC")
                .setSmallIcon(R.drawable.drinking_water)
                .setContentTitle("IT'S TIME TO DRINK WATER")
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, notification);

        waterAlarmActivity.putExtra("AlarmType", "Water");
        context.startActivity(waterAlarmActivity);
    }
}
