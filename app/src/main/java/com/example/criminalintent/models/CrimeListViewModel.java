package com.example.criminalintent.models;

import androidx.lifecycle.ViewModel;

import com.example.criminalintent.models.Crime;
import com.example.criminalintent.repositorypattern.CrimeRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CrimeListViewModel extends ViewModel {

    public List<Crime> crimes;

    public CrimeListViewModel() {
        CrimeRepository crimeRepository = CrimeRepository.getCrimeRepository(null);
        crimes = crimeRepository.getCrimes();
        /*
        for (int i = 0; i< crimes.length; i++) {
            Crime crime = new Crime(
                    UUID.randomUUID(),
                    "Crime #" + i,
                    new Date(),
                    i%2==0);
            crimes[i] = crime;
        }
         */
    }
}
