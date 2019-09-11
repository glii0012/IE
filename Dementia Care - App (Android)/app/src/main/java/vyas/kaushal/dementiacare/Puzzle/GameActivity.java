package vyas.kaushal.dementiacare.Puzzle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.widget.TextView;

import vyas.kaushal.dementiacare.R;

public class GameActivity extends AppCompatActivity {

    final Fragment rememberFragment = new GameRememberFragment();
    final FragmentManager fm = getSupportFragmentManager();

    private TextView lblTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        lblTimer = findViewById(R.id.lblTimer);

        fm.beginTransaction().add(R.id.fl_game, rememberFragment, "1").commit();
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
}
