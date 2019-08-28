package vyas.kaushal.dementiacare;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vyas.kaushal.dementiacare.Adapters.TrainingViewPagerAdapter;
import vyas.kaushal.dementiacare.Training.DinnerFragment;
import vyas.kaushal.dementiacare.Training.LunchFragment;
import vyas.kaushal.dementiacare.Training.WaterFragment;

public class TrainingFragment extends Fragment {

    private BottomNavigationView bottomNavigationView;
    private ViewPager trainingPager;
    private MenuItem prevMenuItem;

    public TrainingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        trainingPager = view.findViewById(R.id.training_pager);

        bottomNavigationView = view.findViewById(R.id.nav_training);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_training_water:
                        trainingPager.setCurrentItem(0, true);
                        break;

                    case R.id.nav_training_lunch:
                        trainingPager.setCurrentItem(1, true);
                        break;

                    case R.id.nav_training_dinner:
                        trainingPager.setCurrentItem(2, true);
                        break;

                    default: break;
                }

                return false;
            }
        });

        trainingPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setUpTrainingPager(trainingPager);
    }

    private void setUpTrainingPager(ViewPager viewPager) {
        TrainingViewPagerAdapter adapter = new TrainingViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new WaterFragment());
        adapter.addFragment(new LunchFragment());
        adapter.addFragment(new DinnerFragment());
        viewPager.setAdapter(adapter);
    }
}
