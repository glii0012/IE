package vyas.kaushal.dementiacare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.WHITE);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        // Pulse Animation
        PulsatorLayout plTraining = findViewById(R.id.plTraining);
        plTraining.start();
        PulsatorLayout plPuzzle = findViewById(R.id.plPuzzle);
        plPuzzle.start();
        PulsatorLayout plAugmentedReality = findViewById(R.id.plAugmentedReality);
        plAugmentedReality.start();

        // Training Tap Event Listeners
        ImageView ivTraining = findViewById(R.id.ivTraining);
        ivTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToTrainingActivity();
            }
        });
        TextView lblTraining = findViewById(R.id.lblTraining);
        lblTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToTrainingActivity();
            }
        });

        // Puzzle Tap Event Listeners
        ImageView ivPuzzle = findViewById(R.id.ivPuzzle);
        ivPuzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToPuzzleActivity();
            }
        });
        TextView lblPuzzle = findViewById(R.id.lblPuzzle);
        lblPuzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToPuzzleActivity();
            }
        });

        // Augmented Reality Tap Event Listeners
        ImageView ivAugmentedReality = findViewById(R.id.ivAugmentedReality);
        ivAugmentedReality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAugmentedRealityActivity();
            }
        });
        TextView lblAugmentedReality = findViewById(R.id.lblAugmentedReality);
        lblAugmentedReality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAugmentedRealityActivity();
            }
        });
    }

    // Create Notification Channel
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("DC", name, importance);
            channel.setDescription(description);
            channel.setBypassDnd(true);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // Helper Method to Navigate to Training Activity
    private void navigateToTrainingActivity() {
        Intent intent = new Intent(this, TrainingActivity.class);
        startActivity(intent);
    }

    // Helper Method to Navigate to Puzzle Activity
    private void navigateToPuzzleActivity() {
        Intent intent = new Intent(this, PuzzleActivity.class);
        startActivity(intent);
    }

    // Helper Method to Navigate to Augmented Reality Activity
    private void navigateToAugmentedRealityActivity() {
        Intent intent = new Intent(this, AugmentedRealityActivity.class);
        startActivity(intent);
    }
}
