package vyas.kaushal.dementiacare.Puzzle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vyas.kaushal.dementiacare.R;

public class GameRememberFragment extends Fragment {


    public GameRememberFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_remember, container, false);
    }
}
