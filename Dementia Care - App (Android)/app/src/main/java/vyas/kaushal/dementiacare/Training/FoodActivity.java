package vyas.kaushal.dementiacare.Training;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;
import vyas.kaushal.dementiacare.Alarm.FoodTrainingAlarm;
import vyas.kaushal.dementiacare.R;

public class FoodActivity extends AppCompatActivity {

    private PulsatorLayout plFoodTime;
    private PulsatorLayout plRemoveFoodTime;
    private FloatingActionButton btnAddFoodTime;
    private FloatingActionButton btnSpeech;
    private FloatingActionButton btnRemoveFoodTime;
    private TimePicker tpFood;
    private TextView lblSetFoodTime;

    private String foodType;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        foodType = getIntent().getStringExtra("Food");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Adding Back Button to App Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(foodType + " Training");

        // Pulse Animation
        plFoodTime = findViewById(R.id.plFoodTime);
        plFoodTime.start();
        plRemoveFoodTime = findViewById(R.id.plRemoveFoodTime);
        plRemoveFoodTime.start();

        btnAddFoodTime = findViewById(R.id.btnAddFoodTime);
        btnAddFoodTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFoodTrainingTime(0, 0);
            }
        });

        btnSpeech = findViewById(R.id.btnSpeech);
        btnSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, What training time you want to set?");

                try {
                    startActivityForResult(intent, 5);
                } catch (ActivityNotFoundException a) {

                }
            }
        });

        btnRemoveFoodTime = findViewById(R.id.btnRemoveFoodTime);
        btnRemoveFoodTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFoodTrainingTime();
            }
        });

        lblSetFoodTime = findViewById(R.id.lblSetFoodTime);
        tpFood = findViewById(R.id.tpFood);

        String foodTrainingTime;
        if (foodType.equals("Lunch")) {
            foodTrainingTime = sharedPreferences.getString("LunchTrainingTime", "0:0");
        }
        else {
            foodTrainingTime = sharedPreferences.getString("DinnerTrainingTime", "0:0");
        }
        String[] hoursAndMinutes = foodTrainingTime.split(":");

        if (hoursAndMinutes[0].equals("0")) {
            setTimeSetup();
        }
        else {
            setRemoveSetup(hoursAndMinutes);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((resultCode == RESULT_OK) && (null != data)) {
            boolean isBreak = false;
            boolean isAlert = false;

            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            for (String eachSentence : results) {
                String[] words = eachSentence.split(" ");
                for (String word : words) {
                    if (word.contains(":")) {
                        try {
                            String[] hoursAndMinutes = word.split(":");
                            int hour = Integer.parseInt(hoursAndMinutes[0]);
                            int minutes = Integer.parseInt(hoursAndMinutes[1]);

                            if (foodType.equals("Lunch")) {
                                if (hour == 1)
                                    hour = 13;
                            } else {
                                if (hour == 6)
                                    hour = 18;
                                else if (hour == 7)
                                    hour = 19;
                                else if (hour == 8)
                                    hour = 20;
                            }

                            setFoodTrainingTime(hour, minutes);
                        } catch (Exception ex) {
                            isAlert = true;
                            showAlert("Oops!", "Sorry, I didn't get you.");
                        }

                        isBreak = true;
                        break;
                    }
                }

                if (isBreak) {
                    break;
                }
            }

            if ((!isBreak) && (!isAlert)) {
                showAlert("Oops!", "Sorry, I didn't get you.");
            }
        }
    }

    private void setTimeSetup() {
        tpFood.setVisibility(View.VISIBLE);
        if (foodType.equals("Lunch")) {
            tpFood.setHour(11);
        }
        else {
            tpFood.setHour(18);
        }
        tpFood.setMinute(1);
        plFoodTime.setVisibility(View.VISIBLE);
        btnAddFoodTime.show();
        btnSpeech.show();
        plRemoveFoodTime.setVisibility(View.INVISIBLE);
        btnRemoveFoodTime.hide();
        lblSetFoodTime.setText("");
    }

    private void setRemoveSetup(String[] hoursAndMinutes) {
        tpFood.setVisibility(View.INVISIBLE);
        plFoodTime.setVisibility(View.INVISIBLE);
        btnAddFoodTime.hide();
        btnSpeech.hide();
        plRemoveFoodTime.setVisibility(View.VISIBLE);
        btnRemoveFoodTime.show();
        lblSetFoodTime.setText("TRAINING AT - " + hoursAndMinutes[0] + ":" + hoursAndMinutes[1]);
    }

    private void setFoodTrainingTime(int voiceHour, int voiceMinute) {
        int selectedHour = voiceHour;
        int selectedMinutes = voiceMinute;
        if (selectedHour == 0) {
            selectedHour = tpFood.getHour();
            selectedMinutes = tpFood.getMinute();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                selectedHour,
                selectedMinutes,
                0
        );

        String hour = (calendar.get(Calendar.HOUR_OF_DAY) > 9) ? ("" + calendar.get(Calendar.HOUR_OF_DAY)) : ("0" + calendar.get(Calendar.HOUR_OF_DAY));
        String minute = (calendar.get(Calendar.MINUTE) > 9) ? ("" + calendar.get(Calendar.MINUTE)) : ("0" + calendar.get(Calendar.MINUTE));
        String[] hoursAndMinutes = {hour, minute};

        AlarmManager alarmManager = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);
        Intent intent = new Intent(this, FoodTrainingAlarm.class);

        if ((foodType.equals("Lunch")) && (selectedHour >= 11) && (selectedHour <= 13) && (selectedMinutes >= 1) && (selectedMinutes <= 59)) {
            intent.putExtra("AlarmType", "Lunch");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

            setRemoveSetup(hoursAndMinutes);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("LunchTrainingTime", hour + ":" + minute);
            editor.commit();

            showAlert("Awesome", "Lunch Training Time set Successfully.");
        }
        else if ((foodType.equals("Dinner")) && (selectedHour >= 18) && (selectedHour <= 20) && (selectedMinutes >= 1) && (selectedMinutes <= 59)) {
            intent.putExtra("AlarmType", "Dinner");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 3, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

            setRemoveSetup(hoursAndMinutes);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("DinnerTrainingTime", hour + ":" + minute);
            editor.commit();

            showAlert("Awesome", "Dinner Training Time set Successfully.");
        }
        else {
            if (foodType.equals("Lunch")) {
                showAlert("Oops!", "Lunch Time should be between 11:01 AM - 01:59 PM, excluding 12:00 PM & 01:00 PM");
            }
            else {
                showAlert("Oops!", "Dinner Time should be between 06:01 PM - 08:59 PM, excluding 07:00 PM & 08:00 PM");
            }
        }
    }

    private void removeFoodTrainingTime() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        AlarmManager alarmManager = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);
        Intent intent = new Intent(this, FoodTrainingAlarm.class);
        if (foodType.equals("Lunch")) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.cancel(pendingIntent);

            editor.remove("LunchTrainingTime");
            editor.commit();
        }
        else {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 3, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.cancel(pendingIntent);

            editor.remove("DinnerTrainingTime");
            editor.commit();
        }

        setTimeSetup();
    }

    private void showAlert(String title, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_food, null);
        builder.setView(dialogView);

        TextView lblTitle = dialogView.findViewById(R.id.lblTitleFood);
        lblTitle.setText(title);
        TextView lblMessage = dialogView.findViewById(R.id.lblMessageFood);
        lblMessage.setText(message);

        final AlertDialog alertDialog = builder.create();

        FloatingActionButton btnOK = dialogView.findViewById(R.id.btnOKFood);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

        alertDialog.show();
    }
}
