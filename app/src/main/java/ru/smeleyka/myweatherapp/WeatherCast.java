package ru.smeleyka.myweatherapp;

/**
 * Created by smeleyka on 29.08.17.
 */

public class WeatherCast {
    //private String cities[];
    private String weather[];

    public WeatherCast(String[] cities, String[] weather) {
        //this.cities = cities;
        this.weather = weather;
    }

    public WeatherCast(MainActivity obj) {
       //this.cities = obj.getResources().getStringArray(R.array.city_list);
        this.weather = obj.getResources().getStringArray(R.array.weather_list);
    }

//    public String get_weather (String city){
//        for (int i = 0; i < cities.length; i++) {
//            if (city.equals(cities[i])) return weather[i];
//        }
//        return null;
//        }

    public String get_weather (int id){
        if (id >= 0 && id < weather.length) return weather[id];
        return null;
    }
}
