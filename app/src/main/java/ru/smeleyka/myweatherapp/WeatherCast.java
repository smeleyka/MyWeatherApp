package ru.smeleyka.myweatherapp;

/**
 * Created by smeleyka on 29.08.17.
 */

public class WeatherCast {
    private String weather[];

    public WeatherCast(MainActivity obj) {
        this.weather = obj.getResources().getStringArray(R.array.weather_list);
    }

    public String get_weather (int id){
       if (id >= 0 && id < weather.length) return weather[id];
        return null;
    }
    public int get_weather (){
        int temp = 25-(int)(Math.random()*50);
        return temp;
    }
}
