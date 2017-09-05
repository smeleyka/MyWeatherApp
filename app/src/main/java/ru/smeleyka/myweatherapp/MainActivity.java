package ru.smeleyka.myweatherapp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String WEATHER_ACTIVITY_INTENT = "weather_act";
    private static final int WEATHER_ACTIVITY_RCODE = 666;
    private WeatherCast weathercast;
    private Spinner spinner;
    private Button button;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "CREATE");
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        spinner = (Spinner) findViewById(R.id.spinner);
        textView = (TextView) findViewById(R.id.weather_cast);
        weathercast = new WeatherCast(this);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        spinner.setSelection(settings.getInt("spinner", 0));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                to_weather_activity(weathercast.get_weather(spinner.getSelectedItemPosition()));
                createDialog();
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
        intent.putExtra(WEATHER_ACTIVITY_INTENT, weather);
        startActivityForResult(intent, WEATHER_ACTIVITY_RCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String name = data.getStringExtra(WEATHER_ACTIVITY_INTENT);
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
        editor.commit();
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

    public void createDialog() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Внимание")
                .setMessage("Нет подходящей для запуска активити").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })


//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                   // public void onClick(DialogInterface dialog, int which) {
//                        // continue with delete
//                    //}
//                })
//                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // do nothing
//                    }
//                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}


