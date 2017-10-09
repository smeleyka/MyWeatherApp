package ru.smeleyka.myweatherapp.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by smeleyka on 09.10.17.
 */

public class DBImplementation {
    final static String WEATHER_API = "http://api.openweathermap.org/data/2.5/weather?units=metric&lang=ru&q=%s&APPID=%s";
    final static String FORECAST_API = "http://api.openweathermap.org/data/2.5/forecast?units=metric&lang=ru&q=%s&APPID=%s";
    final static String FIND_API = "http://api.openweathermap.org/data/2.5/find?units=metric&lang=ru&q=%s&APPID=%s";
    final static String DAY = "weather";
    final static String APPID = "2a7f0c1303d85d0e6c4d50280db14801";
    final static String CITY = "vyborg";
    final static String KEY = "x-api-key";
    //GsonCity city;
    static Context context;
    static AppDatabase db= null;

    private static final DBImplementation ourInstance = new DBImplementation();

    public static DBImplementation getInstance(Context c) {

        return ourInstance;
    }

    private DBImplementation() {

    }

    public static AppDatabase getDb (Context c){
        if (db==null) {
            db = Room.databaseBuilder(c.getApplicationContext(),
                    AppDatabase.class, "myweather.db").build();
        }
        return db;
    }

    public static City getCity(String cityName){
        return null;
    }
}



//Room DB initializasion
@Database(entities = {City.class}, version = 1)
abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract CityDao getCityDao();
}