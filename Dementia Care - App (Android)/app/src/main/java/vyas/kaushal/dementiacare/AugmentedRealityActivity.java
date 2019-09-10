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

public class AugmentedRealityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_augmented_reality);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // Pulse Animation
        PulsatorLayout plARSelfie = findViewById(R.id.plARSelfie);
        plARSelfie.start();

        ImageView ivARSelfie = findViewById(R.id.ivARSelfie);
        ivARSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToARSelfie();
            }
        });
        TextView lblARSelfie = findViewById(R.id.lblARSelfie);
        lblARSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToARSelfie();
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

    private void navigateToARSelfie() {

    }
}
