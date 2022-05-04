package com.example.criminalintent.views;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.criminalintent.adapter.CrimeAdapter;
import com.example.criminalintent.models.Crime;
import com.example.criminalintent.models.CrimeListViewModel;
import com.example.criminalintent.databinding.FragmentCrimeListBinding;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CrimeListFragment extends Fragment {

    public interface Callbacks {
        void onCrimeSelected(UUID crimeID);
    }
    private Callbacks callbacks = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    private FragmentCrimeListBinding binding;
    private CrimeListViewModel crimeListViewModel;
    private CrimeAdapter crimeAdapter;

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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        crimeListViewModel.crimeListLiveData.observe(getViewLifecycleOwner(),
                new Observer<List<Crime>>() {
            @Override
            public void onChanged(List<Crime> crimes) {
                updateUI(crimes);
            }
        });
    }

    private void updateUI(List<Crime> crimes) {
        crimeAdapter =
                new CrimeAdapter(crimes, this, callbacks);
        binding.crimeRecyclerView.setAdapter(crimeAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}