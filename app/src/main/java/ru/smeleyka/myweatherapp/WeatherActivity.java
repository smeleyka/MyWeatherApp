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
    private static final String WEATHER_ACTIVITY_TEMP = "weather_temp";
    private static final String WEATHER_ACTIVITY_FLAG_WIND = "weather_flag_wind";
    private static final String WEATHER_ACTIVITY_FLAG_BAR = "weather_flag_bar";
    private static final String WEATHER_ACTIVITY_FLAG_HUM = "weather_flag_hum";
    TextView textView;
    TextView textWind;
    TextView textBar;
    TextView textHum;
    Button buttonSend;
    Button buttonBack;
    EditText edittext;

    Intent result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "CREATE");
        setContentView(R.layout.activity_weather);
        textView = (TextView) findViewById(R.id.weather_temp);
        textWind = (TextView) findViewById(R.id.weather_wind);
        textBar = (TextView) findViewById(R.id.weather_bar);
        textHum = (TextView) findViewById(R.id.weather_hum);
        buttonSend = (Button) findViewById(R.id.button_send);
        buttonBack = (Button) findViewById(R.id.button_back);
        edittext = (EditText) findViewById(R.id.editText);


        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            textView.setText(b.get(WEATHER_ACTIVITY_TEMP).toString());
            if ((boolean) b.get(WEATHER_ACTIVITY_FLAG_WIND)) {
                textWind.setVisibility(View.VISIBLE);
            }
            else {
                textWind.setVisibility(View.GONE);
            }
            if ((boolean) b.get(WEATHER_ACTIVITY_FLAG_BAR)) {
                textBar.setVisibility(View.VISIBLE);
            }
            else {
                textBar.setVisibility(View.GONE);
            }
            if ((boolean) b.get(WEATHER_ACTIVITY_FLAG_HUM)) {
                textHum.setVisibility(View.VISIBLE);
            }
            else {
                textHum.setVisibility(View.GONE);
            }
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
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    private void sendSms(String sms, String who) {
        Intent result = new Intent(Intent.ACTION_VIEW);
        result.putExtra("address", who);
        result.putExtra("sms_body", sms);
        result.setType("vnd.android-dir/mms-sms");
        if (result.resolveActivity(getPackageManager()) != null) {
            startActivity(result);
        } else {
            showWarnDialog();
            Log.d(TAG, "There is no suitable activity");
        }
    }


    private void returnActivityResult(String result) {
        Intent intent = new Intent();
        intent.putExtra(WEATHER_ACTIVITY_TEMP, result);
        setResult(RESULT_OK, intent);
        System.out.println("RESULT WAS SEND");
        finish();
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
        super.onBackPressed();
        System.out.println("RETURN PRESSED");
        returnActivityResult(edittext.getText().toString());
        System.out.println("RETURN RELEASED");
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
