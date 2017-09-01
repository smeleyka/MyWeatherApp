package ru.smeleyka.myweatherapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "MyPrefsFile";
    private WeatherCast weathercast;
    private Spinner spinner;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        spinner = (Spinner) findViewById(R.id.spinner);
        textView = (TextView) findViewById(R.id.weather_cast);
        weathercast = new WeatherCast(this);

        //Загружаем настройки
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        spinner.setSelection(settings.getInt("spinner", 0));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //textView.setText(weathercast.get_weather(spinner.getSelectedItemPosition()));
                to_weather_activity(weathercast.get_weather(spinner.getSelectedItemPosition()));
            }
        });
    }

    @Override
    //Сохраняем настройки при выходе из приложения
    protected void onStop() {
        super.onStop();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("spinner", spinner.getSelectedItemPosition());
        editor.commit();
    }

    public void to_weather_activity(String weather){
        Intent intent = new Intent(MainActivity.this,WeatherActivity.class);
        intent.putExtra("weatherActivity",weather);
        startActivity(intent);
    }
    public void to_googlemaps(){
        Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        startActivity(mapIntent);

    }

}
