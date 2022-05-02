package com.example.criminalintent.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.criminalintent.models.Crime;
import com.example.criminalintent.databinding.FragmentCrimeBinding;

import java.util.Date;
import java.util.UUID;

public class CrimeFragment extends Fragment {

    private FragmentCrimeBinding binding;
    private Crime crime;

    public CrimeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crime = new Crime(UUID.randomUUID(), "", new Date(), false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCrimeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.crimeDate.setText(crime.getDate().toString());
        binding.crimeDate.setEnabled(false);
        binding.crimeTitle.setText(crime.getTitle());
        binding.crimeSolved.setOnCheckedChangeListener((compoundButton, b) -> crime.setSolved(b));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}