package vyas.kaushal.dementiacare.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TrainingViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> trainingList = new ArrayList<>();

    public TrainingViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return trainingList.get(position);
    }

    @Override
    public int getCount() {
        return trainingList.size();
    }

    public void addFragment(Fragment fragment) {
        trainingList.add(fragment);
    }

}
