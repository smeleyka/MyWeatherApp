package ru.smeleyka.myweatherapp.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by admin on 08.10.2017.
 */

public class DBConnection {
    final static String WEATHER_API = "http://api.openweathermap.org/data/2.5/weather?units=metric&lang=ru&q=%s&APPID=%s";
    final static String FORECAST_API = "http://api.openweathermap.org/data/2.5/forecast?units=metric&lang=ru&q=%s&APPID=%s";
    final static String FIND_API = "http://api.openweathermap.org/data/2.5/find?units=metric&lang=ru&q=%s&APPID=%s";
    final static String DAY = "weather";
    final static String APPID = "2a7f0c1303d85d0e6c4d50280db14801";
    final static String CITY = "vyborg";
    final static String KEY = "x-api-key";
    ListView listView;
    TextView textView;
    EditText getCityName;
    //GsonCity city;
    AppDatabase db;

    public DBConnection(Context c){
        this.db= Room.databaseBuilder(c.getApplicationContext(),AppDatabase.class,"myweather.db").build();
        return this;
    }
}

