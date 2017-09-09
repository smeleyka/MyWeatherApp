package ru.smeleyka.myweatherapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = WeatherActivity.class.getSimpleName();
    private static final String CITY = "weather_city";
    private static final String TEMPERATURE = "temperature";
    private static final String WIND = "wind";
    private static final String BAR = "bar";
    private static final String HUM = "hum";
    private static final String FLAG_WIND = "weather_flag_wind";
    private static final String FLAG_BAR = "weather_flag_bar";
    private static final String FLAG_HUM = "weather_flag_hum";
    private static final String RESULT = "result";
    private static final String SPACE = " ";
    TextView textCity;
    TextView textView;
    TextView textWind;
    TextView textBar;
    TextView textHum;
    Button buttonSend;
    Button buttonBack;
    EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "CREATE");
        setContentView(R.layout.activity_weather);
        textCity = (TextView) findViewById(R.id.weather_city);
        textView = (TextView) findViewById(R.id.weather_temp);
        textWind = (TextView) findViewById(R.id.weather_wind);
        textBar = (TextView) findViewById(R.id.weather_bar);
        textHum = (TextView) findViewById(R.id.weather_hum);
        buttonSend = (Button) findViewById(R.id.button_send);
        buttonBack = (Button) findViewById(R.id.button_back);
        edittext = (EditText) findViewById(R.id.editText);



        if (getIntent() != null) {
            setViewVisibility(getIntent().getExtras());
        }

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSms((String) textView.getText(), edittext.getText().toString());
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnActivityResult(edittext.getText().toString());
            }
        });
    }

    private void setViewVisibility(Bundle b){
        textCity.setText(b.get(CITY).toString());
        textView.setText(b.get(TEMPERATURE).toString()+SPACE+getString(R.string.temperature_symb));
        if ((boolean) b.get(FLAG_WIND)) {
            textWind.setText(getString(R.string.wind)+SPACE+(int)b.get(WIND)+SPACE+getString(R.string.wind_symb));
            System.out.println((int)b.get(WIND));
            textWind.setVisibility(View.VISIBLE);
        }
        else {
            textWind.setVisibility(View.GONE);
        }
        if ((boolean) b.get(FLAG_BAR)) {
            textBar.setText(getString(R.string.barometer)+SPACE+(int)b.get(BAR)+SPACE+getString(R.string.barometer_symb));
            textBar.setVisibility(View.VISIBLE);
        }
        else {
            textBar.setVisibility(View.GONE);
        }
        if ((boolean) b.get(FLAG_HUM)) {
            textHum.setText(getString(R.string.humidyti)+SPACE+(int)b.get(HUM)+"%");
            textHum.setVisibility(View.VISIBLE);
        }
        else {
            textHum.setVisibility(View.GONE);
        }
    }

    private void sendSms(String sms, String who) {
        Intent sendSms = new Intent(Intent.ACTION_VIEW);
        sendSms.putExtra("address", who);
        sendSms.putExtra("sms_body", sms);
        sendSms.setType("vnd.android-dir/mms-sms");
        if (sendSms.resolveActivity(getPackageManager()) != null) {
            startActivity(sendSms);
        } else {
            showWarnDialog();
            Log.d(TAG, "There is no suitable activity");
        }
    }

    private void returnActivityResult(String result) {
        Intent resultData = new Intent();
        resultData.putExtra(RESULT, result);
        if (getParent() == null) {
            setResult(RESULT_OK, resultData);
        } else {
            getParent().setResult(RESULT_OK, resultData);
        }
    }

    public void showWarnDialog() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_warning)
                .setMessage(R.string.dialog_text).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    @Override
    public void onBackPressed() {
        returnActivityResult(edittext.getText().toString());
        super.onBackPressed();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
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

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "RESTART");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "STOP");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "DESTROY");

    }

}
