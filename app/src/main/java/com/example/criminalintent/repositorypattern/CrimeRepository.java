package com.example.criminalintent.repositorypattern;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import com.example.criminalintent.database.CrimeDao;
import com.example.criminalintent.database.CrimeDatabase;
import com.example.criminalintent.models.Crime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CrimeRepository {

    private static final String DATABASE_NAME = "crime_database";
    private static CrimeRepository crimeRepository;
    private CrimeDatabase database;
    private CrimeDao crimeDao;
    private ExecutorService executor = Executors.newCachedThreadPool();


    private CrimeRepository(Context context) {
        database = Room.databaseBuilder(
                context, CrimeDatabase.class, DATABASE_NAME)
                .build();
        crimeDao = database.crimeDao();
    }

    public static CrimeRepository getCrimeRepository(Context context) {
        if (crimeRepository == null) {
            crimeRepository = new CrimeRepository(context);
        }
        return crimeRepository;
    }

    public LiveData<List<Crime>> getCrimes() {
        return crimeDao.getCrimes();
    }

    public LiveData<Crime> getCrime(UUID id) {
        return crimeDao.getCrime(id);
    }

    public void insertCrime(Crime crime) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                crimeDao.insertCrime(crime);
            }
        });

    }

    public void deleteCrime(Crime crime) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                crimeDao.deleteCrime(crime);
            }
        });
    }

    public void updateCrime(Crime crime) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                crimeDao.updateCrime(crime);
            }
        });
    }

}
