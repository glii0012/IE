package vyas.kaushal.dementiacare.Alarms;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import vyas.kaushal.dementiacare.R;
import vyas.kaushal.dementiacare.WaterActivity;

public class WaterAlarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent waterIntent = new Intent(context, WaterActivity.class);
        waterIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Notification notification = new NotificationCompat.Builder(context, "DC")
                .setSmallIcon(R.drawable.ic_menu_water)
                .setContentTitle("TIME TO DRINK WATER")
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, notification);

        context.startActivity(waterIntent);
    }
}
