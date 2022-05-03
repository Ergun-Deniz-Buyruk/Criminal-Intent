package com.example.criminalintent.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.criminalintent.models.Crime;

@Database(entities = {Crime.class}, version = 1)
@TypeConverters({CrimeTypeConverter.class})
public abstract class CrimeDatabase extends RoomDatabase {
    public abstract CrimeDao crimeDao();
}
