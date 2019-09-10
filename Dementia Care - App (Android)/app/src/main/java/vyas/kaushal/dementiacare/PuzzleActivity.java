package vyas.kaushal.dementiacare;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;

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
                navigateToImagePuzzle();
            }
        });
        TextView lblImagePuzzle = findViewById(R.id.lblImagePuzzle);
        lblImagePuzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToImagePuzzle();
            }
        });

        ImageView ivMemoryGame = findViewById(R.id.ivGamePuzzle);
        ivMemoryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMemoryGame();
            }
        });
        TextView lblMemoryGame = findViewById(R.id.lblGamePuzzle);
        lblMemoryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMemoryGame();
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

    private void navigateToImagePuzzle() {

    }

    private void navigateToMemoryGame() {

    }
}
