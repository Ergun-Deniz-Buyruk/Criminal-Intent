package com.example.criminalintent.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.criminalintent.models.Crime;
import com.example.criminalintent.databinding.FragmentCrimeBinding;
import com.example.criminalintent.models.CrimeDetailViewModel;
import com.example.criminalintent.repositorypattern.CrimeRepository;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class CrimeFragment extends Fragment implements DatePickerFragment.Callbacks {

    // içinde barındırdığı crime nesnesinin idsi.
    public static final String CRIME_ID = "crime_id";
    public static final String DIALOG_DATE = "dialog_date";
    private static final int REQUEST_DATE = 0;
    private FragmentCrimeBinding binding;//ViewBinding
    private CrimeDetailViewModel CrimeDetailViewModel;//Crime LiveData
    private Crime crime;
    private UUID crimeId;

    public CrimeFragment(UUID crimeId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(CRIME_ID, crimeId);
        this.setArguments(bundle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CrimeDetailViewModel = new ViewModelProvider(this).get(CrimeDetailViewModel.class);
        if(this.getArguments() != null) {
            crimeId = (UUID) this.getArguments().getSerializable(CRIME_ID);
            CrimeDetailViewModel.loadCrime(crimeId);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCrimeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CrimeDetailViewModel.crimeLiveData.observe(getViewLifecycleOwner(), new Observer<Crime>() {
            @Override
            public void onChanged(Crime crime) {
                setCrime(crime);
                updateUI();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.crimeDate.setOnClickListener(view -> {
            DatePickerFragment datePickerFragment = new DatePickerFragment(crime.getDate());
            datePickerFragment.setTargetFragment(this, REQUEST_DATE);
            datePickerFragment.show(CrimeFragment.super.getParentFragmentManager(), DIALOG_DATE);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setCrime(Crime crime) {
        this.crime = crime;
    }
    public void updateUI() {
        binding.crimeTitle.setText(crime.getTitle());
        binding.crimeDate.setText(crime.getDate().toString());
        binding.crimeSolved.setChecked(crime.isSolved());
        binding.crimeSolved.jumpDrawablesToCurrentState();
    }

    @Override
    public void onStop() {
        super.onStop();
        CrimeDetailViewModel.saveCrime(crime);
    }

    @Override
    public void onDateSelected(Date date) {
        crime.setDate(date);
        updateUI();
    }
}