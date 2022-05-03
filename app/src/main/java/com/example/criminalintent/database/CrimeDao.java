package com.example.criminalintent.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.criminalintent.models.Crime;

import java.util.List;
import java.util.UUID;

@Dao
public interface CrimeDao {

    @Query("SELECT * FROM Crime")
    List<Crime> getCrimes();

    @Query("SELECT * FROM Crime WHERE id=(:id)")
    Crime getCrime(UUID id);

    @Insert
    void insertCrime(Crime crime);

    @Delete
    void deleteCrime(Crime crime);

}
