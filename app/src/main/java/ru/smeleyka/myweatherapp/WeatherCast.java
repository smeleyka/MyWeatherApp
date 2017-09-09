package ru.smeleyka.myweatherapp;

import android.os.Bundle;

/**
 * Created by smeleyka on 29.08.17.
 */

public class WeatherCast {
    private static final String CITY = "weather_city";
    private static final String TEMPERATURE = "temperature";
    private static final String WIND = "wind";
    private static final String BAR = "bar";
    private static final String HUM = "hum";


    public WeatherCast() {
    }

    public Bundle getWeather(String city) {
        Bundle bundle = new Bundle();
        bundle.putString(CITY, city);
        bundle.putInt(TEMPERATURE, getTemperature());
        bundle.putInt(WIND, getWind());
        bundle.putInt(BAR, getBar());
        bundle.putInt(HUM, getHum());
        return bundle;
    }

    public int getTemperature() {
        int i = 25 - (int) (Math.random() * 50);
        return i;
    }

    public int getWind() {
        int i = (int) (Math.random() * 8);
        return i;
    }

    public int getBar() {
        int i = (int) (Math.random() * 10 + 756);
        return i;
    }

    public int getHum() {
        int i = (int) (Math.random() * 30 + 100);
        return i;
    }
}
