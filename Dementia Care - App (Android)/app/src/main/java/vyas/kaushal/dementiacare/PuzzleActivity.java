package vyas.kaushal.dementiacare;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;
import vyas.kaushal.dementiacare.Puzzle.GameActivity;
import vyas.kaushal.dementiacare.Puzzle.JigsawActivity;

public class PuzzleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // Pulse Animation
        PulsatorLayout plImagePuzzle = findViewById(R.id.plImagePuzzle);
        plImagePuzzle.start();
        PulsatorLayout plMemoryGame = findViewById(R.id.plGamePuzzle);
        plMemoryGame.start();

        ImageView ivImagePuzzle = findViewById(R.id.ivImagePuzzle);
        ivImagePuzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert("Puzzle Image", "Please, First select a Image from Gallery for Puzzle.");
            }
        });
        TextView lblImagePuzzle = findViewById(R.id.lblImagePuzzle);
        lblImagePuzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert("Puzzle Image", "Please, First select a Image from Gallery for Puzzle.");
            }
        });

        ImageView ivMemoryGame = findViewById(R.id.ivGamePuzzle);
        ivMemoryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDifficultyAlert();
            }
        });
        TextView lblMemoryGame = findViewById(R.id.lblGamePuzzle);
        lblMemoryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDifficultyAlert();
            }
        });
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

    // Helper Method to Navigate to Jigsaw Activity
    private void navigateToImagePuzzle() {
        Intent intent = new Intent(this, JigsawActivity.class);
        startActivity(intent);
    }

    // Helper Method to Navigate to Game Activity
    private void navigateToMemoryGame(int rememberTimer, int findTimer) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("rememberTimer", rememberTimer);
        intent.putExtra("findTimer", findTimer);
        startActivity(intent);
    }

    private void showAlert(String title, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_game, null);
        builder.setView(dialogView);

        TextView lblTitle = dialogView.findViewById(R.id.lblTitleGame);
        lblTitle.setText(title);
        TextView lblMessage = dialogView.findViewById(R.id.lblMessageGame);
        lblMessage.setText(message);

        final AlertDialog alertDialog = builder.create();

        FloatingActionButton btnOK = dialogView.findViewById(R.id.btnOKGame);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
                navigateToImagePuzzle();
            }
        });

        alertDialog.show();
    }

    private void showDifficultyAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_difficulty, null);
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();

        Button btnEasy = dialogView.findViewById(R.id.btnEasy);
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
                navigateToMemoryGame(11000, 16000);
            }
        });

        Button btnHard = dialogView.findViewById(R.id.btnHard);
        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
                navigateToMemoryGame(6000, 11000);
            }
        });

        alertDialog.show();
    }
}
