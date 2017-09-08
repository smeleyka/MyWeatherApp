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
    private static final String WEATHER_ACTIVITY_TEMP = "weather_temp";
    private static final String WEATHER_ACTIVITY_FLAG_WIND = "weather_flag_wind";
    private static final String WEATHER_ACTIVITY_FLAG_BAR = "weather_flag_bar";
    private static final String WEATHER_ACTIVITY_FLAG_HUM = "weather_flag_hum";
    private static final int WEATHER_ACTIVITY_RCODE = 666;
    private WeatherCast weathercast;
    private Spinner spinner;
    private Button button;
    private TextView textView;
    private Switch switchWind;
    private Switch switchBar;
    private Switch switchHun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "CREATE");
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        spinner = (Spinner) findViewById(R.id.spinner);
        textView = (TextView) findViewById(R.id.weather_cast);
        switchWind = (Switch) findViewById(R.id.switch_wind);
        switchBar = (Switch) findViewById(R.id.switch_bar);
        switchHun = (Switch) findViewById(R.id.switch_hum);
        weathercast = new WeatherCast(this);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        spinner.setSelection(settings.getInt("spinner", 0));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to_weather_activity(weathercast.get_weather(spinner.getSelectedItemPosition()));
                to_weather_activity(weathercast.get_weather());
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "STOP");
    }

    private void to_weather_activity(String weather) {
        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
        intent.putExtra(WEATHER_ACTIVITY_TEMP, weather);
        startActivityForResult(intent, WEATHER_ACTIVITY_RCODE);
    }

    private void to_weather_activity(int weather) {
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra(WEATHER_ACTIVITY_TEMP, weather);
        intent.putExtra(WEATHER_ACTIVITY_FLAG_WIND, switchWind.isChecked());
        intent.putExtra(WEATHER_ACTIVITY_FLAG_BAR, switchBar.isChecked());
        intent.putExtra(WEATHER_ACTIVITY_FLAG_HUM, switchHun.isChecked());
        startActivityForResult(intent, WEATHER_ACTIVITY_RCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("ON ACTIVITY RESULT");

        if (data == null) {
            System.out.println("BUNDLE EMPTY");
            return;

        }
        System.out.println("PARSING BUNDLE");
        String name = data.getStringExtra(WEATHER_ACTIVITY_TEMP);
        textView.setText(name);
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
        super.onPause();
        Log.d(TAG, "PAUSE");
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("spinner", spinner.getSelectedItemPosition());
        editor.apply();
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


