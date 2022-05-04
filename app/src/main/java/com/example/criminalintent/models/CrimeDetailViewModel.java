package com.example.criminalintent.models;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.criminalintent.repositorypattern.CrimeRepository;

import java.util.UUID;

public class CrimeDetailViewModel extends ViewModel {
    CrimeRepository crimeRepository = CrimeRepository.getCrimeRepository(null);

    MutableLiveData<UUID> crimeIdLiveData = new MutableLiveData<UUID>();

    public LiveData<Crime> crimeLiveData = Transformations.switchMap(crimeIdLiveData,
            new Function<UUID, LiveData<Crime>>() {
        @Override
        public LiveData<Crime> apply(UUID crimeId) {
            return crimeRepository.getCrime(crimeId);
        }
    });

    public void loadCrime(UUID crimeID) {
        crimeIdLiveData.setValue(crimeID);
    }

    public void saveCrime(Crime crime) {
        crimeRepository.updateCrime(crime);
    }
}
