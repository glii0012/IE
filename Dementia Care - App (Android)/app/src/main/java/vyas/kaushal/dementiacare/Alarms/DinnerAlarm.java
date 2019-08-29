package vyas.kaushal.dementiacare.Alarms;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import vyas.kaushal.dementiacare.DinnerActivity;
import vyas.kaushal.dementiacare.R;

public class DinnerAlarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent dinnerIntent = new Intent(context, DinnerActivity.class);
        dinnerIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Notification notification = new NotificationCompat.Builder(context, "DC")
                .setSmallIcon(R.drawable.ic_menu_dinner)
                .setContentTitle("IT'S DINNER TIME")
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, notification);

        context.startActivity(dinnerIntent);
    }
}
