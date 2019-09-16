//-- Reference - https://github.com/dragosholban/AndroidPuzzleGame

package vyas.kaushal.dementiacare.Puzzle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;
import vyas.kaushal.dementiacare.R;

public class GameActivity extends AppCompatActivity {

    private String[] responses = {"Correct", "Awesome", "You'r on a roll", "Great"};
    private int streak = 0;

    private TextToSpeech textToSpeech;

    private ArrayList<Integer> blockList;
    private ArrayList<Integer> selectedBlockList;
    private ArrayList<Integer> gameBlockList;

    private TextView lblRememberInfo;

    private ImageView ivFirstBlock;
    private ImageView ivSecondBlock;
    private ImageView ivThirdBlock;
    private ImageView ivFourthBlock;

    private PulsatorLayout plFirstTap;
    private PulsatorLayout plSecondTap;
    private PulsatorLayout plThirdTap;
    private PulsatorLayout plFourthTap;
    private PulsatorLayout plFifthTap;
    private PulsatorLayout plSixthTap;

    private ImageView ivFirstTap;
    private ImageView ivSecondTap;
    private ImageView ivThirdTap;
    private ImageView ivFourthTap;
    private ImageView ivFifthTap;
    private ImageView ivSixthTap;

    private TextView lblTimer;

    private int correctFind;
    private int firstTimer;
    private int secondTimer;
    private CountDownTimer rememberTimer;
    private CountDownTimer gameTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        firstTimer = getIntent().getIntExtra("rememberTimer", 11000);
        secondTimer = getIntent().getIntExtra("findTimer", 16000);

        setUpTextToSpeech();

        lblRememberInfo = findViewById(R.id.lblRememberInfo);

        ivFirstBlock = findViewById(R.id.ivFirstBlock);
        ivSecondBlock = findViewById(R.id.ivSecondBlock);
        ivThirdBlock = findViewById(R.id.ivThirdBlock);
        ivFourthBlock = findViewById(R.id.ivFourthBlock);

        plFirstTap = findViewById(R.id.plFirstTap);
        plFirstTap.start();
        plSecondTap = findViewById(R.id.plSecondTap);
        plSecondTap.start();
        plThirdTap = findViewById(R.id.plThirdTap);
        plThirdTap.start();
        plFourthTap = findViewById(R.id.plFourthTap);
        plFourthTap.start();
        plFifthTap = findViewById(R.id.plFifthTap);
        plFifthTap.start();
        plSixthTap = findViewById(R.id.plSixthTap);
        plSixthTap.start();

        ivFirstTap = findViewById(R.id.ivFirstTap);
        ivSecondTap = findViewById(R.id.ivSecondTap);
        ivThirdTap = findViewById(R.id.ivThirdTap);
        ivFourthTap = findViewById(R.id.ivFourthTap);
        ivFifthTap = findViewById(R.id.ivFifthTap);
        ivSixthTap = findViewById(R.id.ivSixthTap);

        lblTimer = findViewById(R.id.lblTimer);

        ivFirstTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfCorrect(ivFirstTap, 0, plFirstTap);
            }
        });

        ivSecondTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfCorrect(ivSecondTap, 1, plSecondTap);
            }
        });

        ivThirdTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfCorrect(ivThirdTap, 2, plThirdTap);
            }
        });

        ivFourthTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfCorrect(ivFourthTap, 3, plFourthTap);
            }
        });

        ivFifthTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfCorrect(ivFifthTap, 4, plFifthTap);
            }
        });

        ivSixthTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfCorrect(ivSixthTap, 5, plSixthTap);
            }
        });

        blockList = new ArrayList<>();
        selectedBlockList = new ArrayList<>();
        gameBlockList = new ArrayList<>();
        addBlocksToList();

        startGame();
    }

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
    protected void onDestroy() {
        rememberTimer.cancel();
        if (gameTimer != null) {
            gameTimer.cancel();
        }

        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }

        super.onDestroy();
    }

    private void setUpTextToSpeech() {
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = textToSpeech.setLanguage(Locale.ENGLISH);

                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                            || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        //Log.e("TTS", "The Language is not supported!");
                    } else {
                        //Log.i("TTS", "Language Supported.");
                    }
                    //Log.i("TTS", "Initialization success.");
                } else {
                    //Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addBlocksToList() {
        blockList.add(R.drawable.block1);
        blockList.add(R.drawable.block2);
        blockList.add(R.drawable.block3);
        blockList.add(R.drawable.block4);
        blockList.add(R.drawable.block5);
        blockList.add(R.drawable.block6);
        blockList.add(R.drawable.block7);
        blockList.add(R.drawable.block8);
        blockList.add(R.drawable.block9);
        blockList.add(R.drawable.block10);
        blockList.add(R.drawable.block11);
        blockList.add(R.drawable.block12);
        blockList.add(R.drawable.block13);
        blockList.add(R.drawable.block14);
        blockList.add(R.drawable.block15);
        blockList.add(R.drawable.block16);
        blockList.add(R.drawable.block17);
        blockList.add(R.drawable.block18);
    }

    private void startGame() {
        Collections.shuffle(blockList);
        selectedBlockList.addAll(blockList.subList(0, 4));
        gameBlockList.addAll(blockList.subList(0, 6));

        ivFirstBlock.setImageResource(selectedBlockList.get(0));
        ivSecondBlock.setImageResource(selectedBlockList.get(1));
        ivThirdBlock.setImageResource(selectedBlockList.get(2));
        ivFourthBlock.setImageResource(selectedBlockList.get(3));
        hideFindComponents();
        lblTimer.setTextSize(60);

        rememberTimer = new CountDownTimer(firstTimer, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                lblTimer.setText("" + (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                startFinding();
            }
        }.start();
    }

    private void startFinding() {
        lblTimer.setTextSize(46);
        textToSpeech.speak("Start Finding", TextToSpeech.QUEUE_FLUSH, null, "0");
        correctFind = 0;
        Collections.shuffle(gameBlockList);
        ivFirstTap.setImageResource(gameBlockList.get(0));
        ivSecondTap.setImageResource(gameBlockList.get(1));
        ivThirdTap.setImageResource(gameBlockList.get(2));
        ivFourthTap.setImageResource(gameBlockList.get(3));
        ivFifthTap.setImageResource(gameBlockList.get(4));
        ivSixthTap.setImageResource(gameBlockList.get(5));

        hideRememberComponents();

        gameTimer = new CountDownTimer(secondTimer, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                lblTimer.setText("" + (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                streak = 0;
                showAlert("Oops!", "Your time is Up. Please, try again.");
            }
        }.start();
    }

    private void checkIfCorrect(ImageView iv, int index, PulsatorLayout pl) {
        if (selectedBlockList.contains(gameBlockList.get(index))) {
            String word = responses[new Random().nextInt(4)];
            textToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null, "1");

            correctFind++;
            pl.setVisibility(View.INVISIBLE);
            iv.setVisibility(View.INVISIBLE);
        }
        else {
            textToSpeech.speak("Ohh No", TextToSpeech.QUEUE_FLUSH, null, "2");

            Vibrator vibrator = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else {
                vibrator.vibrate(5000);
            }
        }

        if (correctFind == 4) {
            streak++;
            lblRememberInfo.setText("CURRENT STREAK: " + streak);
            gameTimer.cancel();
            showAlert("Congratulations", "You Successfully found all Blocks.");
        }
    }

    private void hideRememberComponents() {
        lblRememberInfo.setText("CURRENT STREAK: " + streak);

        ivFirstBlock.setVisibility(View.INVISIBLE);
        ivSecondBlock.setVisibility(View.INVISIBLE);
        ivThirdBlock.setVisibility(View.INVISIBLE);
        ivFourthBlock.setVisibility(View.INVISIBLE);

        plFirstTap.setVisibility(View.VISIBLE);
        plSecondTap.setVisibility(View.VISIBLE);
        plThirdTap.setVisibility(View.VISIBLE);
        plFourthTap.setVisibility(View.VISIBLE);
        plFifthTap.setVisibility(View.VISIBLE);
        plSixthTap.setVisibility(View.VISIBLE);

        ivFirstTap.setVisibility(View.VISIBLE);
        ivSecondTap.setVisibility(View.VISIBLE);
        ivThirdTap.setVisibility(View.VISIBLE);
        ivFourthTap.setVisibility(View.VISIBLE);
        ivFifthTap.setVisibility(View.VISIBLE);
        ivSixthTap.setVisibility(View.VISIBLE);
    }

    private void hideFindComponents() {
        lblRememberInfo.setText("REMEMBER");

        ivFirstBlock.setVisibility(View.VISIBLE);
        ivSecondBlock.setVisibility(View.VISIBLE);
        ivThirdBlock.setVisibility(View.VISIBLE);
        ivFourthBlock.setVisibility(View.VISIBLE);

        plFirstTap.setVisibility(View.INVISIBLE);
        plSecondTap.setVisibility(View.INVISIBLE);
        plThirdTap.setVisibility(View.INVISIBLE);
        plFourthTap.setVisibility(View.INVISIBLE);
        plFifthTap.setVisibility(View.INVISIBLE);
        plSixthTap.setVisibility(View.INVISIBLE);

        ivFirstTap.setVisibility(View.INVISIBLE);
        ivSecondTap.setVisibility(View.INVISIBLE);
        ivThirdTap.setVisibility(View.INVISIBLE);
        ivFourthTap.setVisibility(View.INVISIBLE);
        ivFifthTap.setVisibility(View.INVISIBLE);
        ivSixthTap.setVisibility(View.INVISIBLE);
    }

    private void showAlert(String title, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_game, null);
        builder.setView(dialogView);
        builder.setCancelable(false);

        TextView lblTitle = dialogView.findViewById(R.id.lblTitleGame);
        lblTitle.setText(title);
        TextView lblMessage = dialogView.findViewById(R.id.lblMessageGame);
        lblMessage.setText(message);

        final AlertDialog alertDialog = builder.create();

        FloatingActionButton btnOK = dialogView.findViewById(R.id.btnOKGame);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBlockList.clear();
                gameBlockList.clear();
                alertDialog.cancel();
                startGame();
            }
        });

        alertDialog.show();
    }
}
