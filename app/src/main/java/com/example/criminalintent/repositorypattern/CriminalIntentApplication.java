package com.example.criminalintent.repositorypattern;

import android.app.Application;

public class CriminalIntentApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrimeRepository crimeRepository =
                CrimeRepository.getCrimeRepository(getApplicationContext());
    }
}
