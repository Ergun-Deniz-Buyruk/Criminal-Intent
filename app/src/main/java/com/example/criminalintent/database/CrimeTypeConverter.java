package com.example.criminalintent.database;

import androidx.room.TypeConverter;

import java.util.Date;
import java.util.UUID;

public class CrimeTypeConverter {
    @TypeConverter
    public static Long fromDate(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public static Date toDate(Long longDate) {
        return new Date(longDate);
    }

    @TypeConverter
    public static String fromUUID(UUID uuid) {
        return uuid.toString();
    }

    @TypeConverter
    public static UUID toUUID(String uuid) {
        return UUID.fromString(uuid);
    }
}
