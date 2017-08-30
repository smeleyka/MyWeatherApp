package ru.smeleyka.myweatherapp;

import android.content.res.Resources;

/**
 * Created by smeleyka on 29.08.17.
 */

public class WeatherCast {
    private String cities[];
    private String weather[];


    WeatherCast(MainActivity obj) {
        cities = obj.getResources().getStringArray(R.array.city_list);
        weather = obj.getResources().getStringArray(R.array.weather_list);
    }

    public String get_weather (String city){
        String cast;
        for (int i = 0; i < cities.length; i++) {
            if (city.equals(cities[i])) return cast = weather[i];
        }
        return cast=null;
        }
}
