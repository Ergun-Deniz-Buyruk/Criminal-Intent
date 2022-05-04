package com.example.criminalintent.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.criminalintent.models.Crime;

import java.util.List;
import java.util.UUID;

@Dao
public interface CrimeDao {

    @Query("SELECT * FROM Crime")
    LiveData<List<Crime>> getCrimes();

    @Query("SELECT * FROM Crime WHERE id=(:id)")
    LiveData<Crime> getCrime(UUID id);

    @Insert
    void insertCrime(Crime crime);

    @Delete
    void deleteCrime(Crime crime);

    @Update
    void updateCrime(Crime crime);

}
