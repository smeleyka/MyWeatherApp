package ru.smeleyka.myweatherapp.Internet;

import android.content.Context;

import ru.smeleyka.myweatherapp.City;

/**
 * Created by smeleyka on 10.10.17.
 */

public class InternetWeather {

    final static String WEATHER_API = "http://api.openweathermap.org/data/2.5/weather?units=metric&lang=ru&q=%s&APPID=%s";
    final static String FORECAST_API = "http://api.openweathermap.org/data/2.5/forecast?units=metric&lang=ru&q=%s&APPID=%s";
    final static String FIND_API = "http://api.openweathermap.org/data/2.5/find?units=metric&lang=ru&q=%s&APPID=%s";
    final static String DAY = "weather";
    final static String APPID = "2a7f0c1303d85d0e6c4d50280db14801";
    final static String CITY = "vyborg";
    final static String KEY = "x-api-key";
    private static Context context = null;
    //GsonCity city;

    private static final InternetWeather ourInstance = new InternetWeather();

    public static InternetWeather getInstance(Context c) {
        context = c;
        return ourInstance;
    }

    private InternetWeather() {
    }

    public City getCity(){
        return null;
    }
}
