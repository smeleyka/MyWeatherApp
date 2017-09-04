package ru.smeleyka.myweatherapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "WeatherActivity";
    private static final String WEATHER_ACTIVITY_INTENT = "weather_act";
    private static final int WEATHER_ACTIVITY_RCODE = 666;
    TextView textView;
    Button buttonSend;
    Button buttonBack;
    EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"CREATE");
        setContentView(R.layout.activity_weather);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        textView = (TextView) findViewById(R.id.weather_text_view);
        buttonSend = (Button) findViewById(R.id.button_send);
        buttonBack = (Button) findViewById(R.id.button_back);
        edittext = (EditText) findViewById(R.id.editText);
        if (b != null) {
            textView.setText((String) b.get(WEATHER_ACTIVITY_INTENT));
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

    private void sendSms(String sms, String who) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra("address", who);
        intent.putExtra("sms_body", sms);
        intent.setType("vnd.android-dir/mms-sms");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else Log.d("Error", getString(R.string.error));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void returnActivityResult(String result) {
        Intent intent = new Intent();
        intent.putExtra(WEATHER_ACTIVITY_INTENT, result);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"START");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"RESUME");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"PAUSE");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"RESTART");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"STOP");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"DESTROY");

    }

}
