package com.example.criminalintent.repositorypattern;

import android.content.Context;
import androidx.room.Room;
import com.example.criminalintent.database.CrimeDao;
import com.example.criminalintent.database.CrimeDatabase;
import com.example.criminalintent.models.Crime;
import java.util.List;
import java.util.UUID;

public class CrimeRepository {

    private static final String DATABASE_NAME = "crime_database";
    private static CrimeRepository crimeRepository;
    private CrimeDatabase database;
    private CrimeDao crimeDao;


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

    public List<Crime> getCrimes() {
        return crimeDao.getCrimes();
    }

    public Crime getCrime(UUID id) {
        return crimeDao.getCrime(id);
    }

    public void insertCrime(Crime crime) {
        crimeDao.insertCrime(crime);
    }

    public void deleteCrime(Crime crime) {
        crimeDao.deleteCrime(crime);
    }

}
