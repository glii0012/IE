package vyas.kaushal.dementiacare.Alarms;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import vyas.kaushal.dementiacare.LunchActivity;
import vyas.kaushal.dementiacare.R;

public class LunchAlarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent lunchIntent = new Intent(context, LunchActivity.class);
        lunchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Notification notification = new NotificationCompat.Builder(context, "DC")
                .setSmallIcon(R.drawable.ic_menu_lunch)
                .setContentTitle("IT'S LUNCH TIME")
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, notification);

        context.startActivity(lunchIntent);
    }
}
