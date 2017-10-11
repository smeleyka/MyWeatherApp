package ru.smeleyka.myweatherapp.Internet;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ru.smeleyka.myweatherapp.City;

/**
 * Created by smeleyka on 10.10.17.
 */

public class InternetWeather {

    final static String WEATHER_API = "http://api.openweathermap.org/data/2.5/weather?units=metric&lang=ru&q=%s&APPID=%s";
    final static String FORECAST_API = "http://api.openweathermap.org/data/2.5/forecast?units=metric&lang=ru&q=%s&APPID=%s";
    final static String FIND_API = "http://api.openweathermap.org/data/2.5/find?units=metric&lang=ru&q=%s&APPID=%s";
    final static String APPID = "2a7f0c1303d85d0e6c4d50280db14801";
    final static String CITY = "vyborg";
    final static String KEY = "x-api-key";
    private static Context context = null;

    private static final InternetWeather ourInstance = new InternetWeather();

    public static InternetWeather getInstance(Context c) {
        context = c;
        return ourInstance;
    }

    private InternetWeather() {
    }

    public City getCity(String cityName) {

        return parseJson(cityName);

    }


    private City parseJson(String name) {
        final Gson gson = new Gson();
        final String cityName = name;
        final City city;
        final StringBuilder rawData = new StringBuilder(1024);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(String.format(WEATHER_API, cityName, APPID));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //connection.addRequestProperty(KEY, "2a7f0c1303d85d0e6c4d50280db14801");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String tempVariable;

                    while ((tempVariable = reader.readLine()) != null) {
                        rawData.append(tempVariable).append("\n");
                    }
                    reader.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        city = gson.fromJson(rawData.toString(), City.class);
        return city;

    }
}
