package com.example.criminalintent.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.example.criminalintent.fragments.CrimeListFragment;
import com.example.criminalintent.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Fragment currentFragment =
                getSupportFragmentManager().findFragmentById(binding.fragmentContainer.getId());

        if(currentFragment == null) {
            CrimeListFragment crimeFragment = new CrimeListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(binding.fragmentContainer.getId(), crimeFragment)
                    .commit();
        }

    }
}