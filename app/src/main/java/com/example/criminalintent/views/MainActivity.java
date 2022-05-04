package com.example.criminalintent.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Database;

import android.app.appsearch.ReportSystemUsageRequest;
import android.os.Bundle;
import android.view.View;

import com.example.criminalintent.models.Crime;
import com.example.criminalintent.repositorypattern.CrimeRepository;
import com.example.criminalintent.views.CrimeListFragment;
import com.example.criminalintent.databinding.ActivityMainBinding;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements CrimeListFragment.Callbacks {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        Fragment currentFragment =
                getSupportFragmentManager().findFragmentById(binding.fragmentContainer.getId());

        if (currentFragment == null) {
            CrimeListFragment crimeFragment = new CrimeListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(binding.fragmentContainer.getId(), crimeFragment)
                    .commit();
        }

    }

    @Override
    public void onCrimeSelected(UUID crimeID) {
        CrimeFragment crimeFragment = new CrimeFragment(crimeID);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.fragmentContainer.getId(), crimeFragment)
                .addToBackStack(null)
                .commit();
    }
}