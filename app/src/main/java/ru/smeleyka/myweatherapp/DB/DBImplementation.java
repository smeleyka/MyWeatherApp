package ru.smeleyka.myweatherapp.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ru.smeleyka.myweatherapp.City;

/**
 * Created by smeleyka on 09.10.17.
 */

public class DBImplementation {

    private static Context context=null;
    private static AppDatabase db= null;

    private static final DBImplementation ourInstance = new DBImplementation();

   public static DBImplementation getInstance(Context c) {
        context = c;
        return ourInstance;
    }

    private DBImplementation() {

    }

    public AppDatabase getDb (){
        if (this.db==null) {
            db = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "myweather.db").build();
        }
        return db;
    }

    public City getCityFromDb(String cityName){
        if(db == null) {
            return null;
        }
        return db.getCityDao().findByName(cityName);
    }

    public City getCityFromDb(int Id){
        if(db == null) {
            return null;
        }
        return db.getCityDao().findById(Id);
    }

}



//Room DB initializasion
@Database(entities = {City.class}, version = 1)
abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract CityDao getCityDao();
}