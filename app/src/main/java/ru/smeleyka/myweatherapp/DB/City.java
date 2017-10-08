package ru.smeleyka.myweatherapp.DB;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by admin on 01.10.2017.
 */
@Entity
public class City {

@PrimaryKey
    private int id;
    private String name;
    private int visibility;
    private int dt;
    private int cod;
    @Ignore

    private Main main;

    public String getName() {
        return name;
    }

    public int getVisibility() {
        return visibility;
    }

    public int getWind() {
        return dt;
    }

    public Main getMain() {
        return main;
    }

    public int getId() {
        return id;
    }

    public int getDt() {
        return dt;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public class Main {
        private float temp;
        private float pressure;
        private float humidity;
        private float temp_min;
        private float temp_max;

        public float getTemp() {
            return temp;
        }

        public float getPressure() {
            return pressure;
        }

        public float getHumidity() {
            return humidity;
        }

        public float getTemp_min() {
            return temp_min;
        }

        public float getTemp_max() {
            return temp_max;
        }

        public void setTemp(float temp) {
            this.temp = temp;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public void setTemp_min(int temp_min) {
            this.temp_min = temp_min;
        }

        public void setTemp_max(int temp_max) {
            this.temp_max = temp_max;
        }
    }

    public class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(id+" ");
        string.append(name);

        return string.toString();
    }
}
