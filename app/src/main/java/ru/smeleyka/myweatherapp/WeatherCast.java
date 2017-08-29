package ru.smeleyka.myweatherapp;

/**
 * Created by smeleyka on 29.08.17.
 */

public class WeatherCast {
    public String vanga (String city){
        String cast=null;
        if (city.equals("Москва")) cast = "Ясно";
        else if (city.equals("Санкт-Петербург"))cast  = "Пасмурно";
        else if (city.equals("Новосибирск"))cast  = "Пасмурно";
        else if (city.equals("Екатеринбург"))cast  = "Пасмурно";
        else if (city.equals("Нижний Новгород"))cast  = "Пасмурно";

        int temp = 20-(int) (Math.random()*40);
        if (temp>0)cast = "+"+temp+" "+cast;
        else cast = ""+temp+" "+cast;
        return cast;

    }
    private String get_weather (){
        String temp=null;
        int vanga = 20-(int) (Math.random()*40);
        if (vanga>0)temp = "+"+vanga;
        else temp = ""+vanga;
        return temp;
    }
}
