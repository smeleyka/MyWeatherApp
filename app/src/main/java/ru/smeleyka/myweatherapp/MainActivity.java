package ru.smeleyka.myweatherapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String SPINNER = "spinner";
    private static final String FLAG_WIND = "weather_flag_wind";
    private static final String FLAG_BAR = "weather_flag_bar";
    private static final String FLAG_HUM = "weather_flag_hum";
    private static final String RESULT = "result";
    private static final int RCODE = 666;
    private SharedPreferences savedSettings;
    private WeatherCast weathercast;
    private Spinner spinner;
    private Button button;
    private TextView resultText;
    private Switch switchWind;
    private Switch switchBar;
    private Switch switchHum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "CREATE");
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        spinner = (Spinner) findViewById(R.id.spinner);
        resultText = (TextView) findViewById(R.id.result_text);
        switchWind = (Switch) findViewById(R.id.switch_wind);
        switchBar = (Switch) findViewById(R.id.switch_bar);
        switchHum = (Switch) findViewById(R.id.switch_hum);

        loadSavedSettings();
        weathercast = new WeatherCast();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toWeatherActivity(weathercast.getWeather((String) spinner.getSelectedItem()));
            }
        });

        City[] cit = City.cities;
        Log.d ("CityTEST",cit[0].toString());
    }

    private void loadSavedSettings() {
        savedSettings = getSharedPreferences(PREFS_NAME, 0);
        spinner.setSelection(savedSettings.getInt("spinner", 0));
        switchWind.setChecked(savedSettings.getBoolean(FLAG_WIND, false));
        switchBar.setChecked(savedSettings.getBoolean(FLAG_BAR, false));
        switchHum.setChecked(savedSettings.getBoolean(FLAG_HUM, false));
        savedSettings.edit().apply();
    }

    private void saveSettings() {
        savedSettings.edit().clear().apply();
        savedSettings.edit().putInt(SPINNER, spinner.getSelectedItemPosition()).apply();
        savedSettings.edit().putBoolean(FLAG_WIND, switchWind.isChecked()).apply();
        savedSettings.edit().putBoolean(FLAG_BAR, switchBar.isChecked()).apply();
        savedSettings.edit().putBoolean(FLAG_HUM, switchHum.isChecked()).apply();
    }

    private void toWeatherActivity(Bundle weather) {
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtras(weather);
        intent.putExtra(FLAG_WIND, switchWind.isChecked());
        intent.putExtra(FLAG_BAR, switchBar.isChecked());
        intent.putExtra(FLAG_HUM, switchHum.isChecked());
        startActivityForResult(intent, RCODE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "STOP");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == RCODE) {
            String name = data.getStringExtra(RESULT);
            resultText.setText(name);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "START");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "RESUME");
    }

    @Override
    protected void onPause() {
        saveSettings();
        super.onPause();
        Log.d(TAG, "PAUSE");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "RESTART");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "DESTROY");
    }

}


