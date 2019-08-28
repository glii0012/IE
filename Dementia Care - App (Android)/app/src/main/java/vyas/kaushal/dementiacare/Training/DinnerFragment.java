package vyas.kaushal.dementiacare.Training;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import vyas.kaushal.dementiacare.R;

public class DinnerFragment extends Fragment {

    private Button btnSetReminder;
    private TextView lblReminderTime;
    private FloatingActionButton btnDeleteReminder;

    public DinnerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dinner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lblReminderTime = view.findViewById(R.id.lbl_dinner_reminder);
        lblReminderTime.setText("No Training Set");
    }
}
