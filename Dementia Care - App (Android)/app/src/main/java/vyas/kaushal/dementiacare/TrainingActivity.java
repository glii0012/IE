package vyas.kaushal.dementiacare;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;
import vyas.kaushal.dementiacare.Training.FoodActivity;
import vyas.kaushal.dementiacare.Training.WaterActivity;

public class TrainingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        // Adding Back Button to App Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // Pulse Animation
        PulsatorLayout plDrinkingWater = findViewById(R.id.plDrinkingWater);
        plDrinkingWater.start();
        PulsatorLayout plLunch = findViewById(R.id.plLunch);
        plLunch.start();
        PulsatorLayout plDinner = findViewById(R.id.plDinner);
        plDinner.start();

        // Drinking Water Tap Event Listeners
        ImageView ivDrinkingWater = findViewById(R.id.ivDrinkingWater);
        ivDrinkingWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToWaterActivity();
            }
        });
        TextView lblDrinkingWater = findViewById(R.id.lblDrinkingWater);
        lblDrinkingWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToWaterActivity();
            }
        });

        // Lunch Tap Event Listeners
        ImageView ivLunch = findViewById(R.id.ivLunch);
        ivLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLunchActivity();
            }
        });
        TextView lblLunch = findViewById(R.id.lblLunch);
        lblLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLunchActivity();
            }
        });

        // Dinner Tap Event Listeners
        ImageView ivDinner = findViewById(R.id.ivDinner);
        ivDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDinnerActivity();
            }
        });
        TextView lblDinner = findViewById(R.id.lblDinner);
        lblDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDinnerActivity();
            }
        });
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

    // Helper Method to Navigate to Drinking Water Activity
    private void navigateToWaterActivity() {
        Intent intent = new Intent(this, WaterActivity.class);
        startActivity(intent);
    }

    // Helper Method to Navigate to Food Activity - Lunch
    private void navigateToLunchActivity() {
        Intent intent = new Intent(this, FoodActivity.class);
        intent.putExtra("Food", "Lunch");
        startActivity(intent);
    }

    // Helper Method to Navigate to Food Activity - Lunch
    private void navigateToDinnerActivity() {
        Intent intent = new Intent(this, FoodActivity.class);
        intent.putExtra("Food", "Dinner");
        startActivity(intent);
    }
}
