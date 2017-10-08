package ru.smeleyka.myweatherapp.DB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by smeleyka on 03.10.17.
 */

@Dao
public interface CityDao {
    @Query("SELECT * FROM gsoncity")
    List<City> getAll();


    @Query("SELECT * FROM gsoncity WHERE name LIKE :cityName LIMIT 1")
    City findByName(String cityName);

    @Query("SELECT * FROM gsoncity WHERE id LIKE :id")
    City findById(int id);

    @Insert
    void insertAll(City... city);

    @Delete
    void delete(City city);

}
