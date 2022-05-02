package com.example.criminalintent.models;

import androidx.lifecycle.ViewModel;

import com.example.criminalintent.models.Crime;

import java.util.Date;
import java.util.UUID;

public class CrimeListViewModel extends ViewModel {

    public Crime[] crimes = new Crime[100];

    public CrimeListViewModel() {
        for (int i = 0; i< crimes.length; i++) {
            Crime crime = new Crime(
                    UUID.randomUUID(),
                    "Crime #" + i,
                    new Date(),
                    i%2==0);
            crimes[i] = crime;
        }
    }
}
