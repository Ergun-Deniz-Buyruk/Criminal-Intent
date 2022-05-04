package com.example.criminalintent.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.criminalintent.models.Crime;
import com.example.criminalintent.repositorypattern.CrimeRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CrimeListViewModel extends ViewModel {

    public LiveData<List<Crime>> crimeListLiveData;
    public CrimeRepository crimeRepository;

    public CrimeListViewModel() {
        crimeRepository = CrimeRepository.getCrimeRepository(null);
        crimeListLiveData = crimeRepository.getCrimes();

    }
    public void addCrime(Crime crime) {
        crimeRepository.insertCrime(crime);
    }
}
