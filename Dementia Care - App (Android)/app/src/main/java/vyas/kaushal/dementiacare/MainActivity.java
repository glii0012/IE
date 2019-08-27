package vyas.kaushal.dementiacare;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;

    private final Fragment trainingFragment = new TrainingFragment();
    private final Fragment puzzleFragment = new PuzzleFragment();
    private final Fragment augmentedFragment = new AugmentedFragment();
    private final FragmentManager fm = getSupportFragmentManager();
    private String currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.menu_training);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_training);

        fm.beginTransaction().add(R.id.nav_fragment, augmentedFragment, "augmented").hide(augmentedFragment).commit();
        fm.beginTransaction().add(R.id.nav_fragment, puzzleFragment, "puzzle").hide(puzzleFragment).commit();
        fm.beginTransaction().add(R.id.nav_fragment, trainingFragment, "training").commit();
        currentFragment = "training";
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.nav_training:
                if (currentFragment.equals("puzzle")) {
                    fm.beginTransaction().hide(puzzleFragment).show(trainingFragment).commit();
                }
                if (currentFragment.equals("augmented")) {
                    fm.beginTransaction().hide(augmentedFragment).show(trainingFragment).commit();
                }
                setTitle(R.string.menu_training);
                navigationView.setCheckedItem(R.id.nav_training);
                currentFragment = "training";
                break;

            case R.id.nav_puzzles:
                if (currentFragment.equals("training")) {
                    fm.beginTransaction().hide(trainingFragment).show(puzzleFragment).commit();
                }
                if (currentFragment.equals("augmented")) {
                    fm.beginTransaction().hide(augmentedFragment).show(puzzleFragment).commit();
                }
                setTitle(R.string.menu_puzzle);
                navigationView.setCheckedItem(R.id.nav_puzzles);
                currentFragment = "puzzle";
                break;

            case R.id.nav_ar:
                if (currentFragment.equals("training")) {
                    fm.beginTransaction().hide(trainingFragment).show(augmentedFragment).commit();
                }
                if (currentFragment.equals("puzzle")) {
                    fm.beginTransaction().hide(puzzleFragment).show(augmentedFragment).commit();
                }
                setTitle(R.string.menu_ar);
                navigationView.setCheckedItem(R.id.nav_ar);
                currentFragment = "augmented";
                break;

            default: break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
