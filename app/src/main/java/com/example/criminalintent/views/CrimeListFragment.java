package com.example.criminalintent.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.criminalintent.adapter.CrimeAdapter;
import com.example.criminalintent.models.CrimeListViewModel;
import com.example.criminalintent.databinding.FragmentCrimeListBinding;

import java.util.Arrays;

public class CrimeListFragment extends Fragment {

    private FragmentCrimeListBinding binding;
    private CrimeListViewModel crimeListViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        crimeListViewModel =
                new ViewModelProvider(this).get(CrimeListViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCrimeListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CrimeAdapter crimeAdapter = new CrimeAdapter(crimeListViewModel.crimes);
        binding.crimeRecyclerView.setAdapter(crimeAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}