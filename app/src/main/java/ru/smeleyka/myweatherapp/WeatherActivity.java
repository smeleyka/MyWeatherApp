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
    private static final String WEATHER_ACTIVITY_INTENT = "weather_act";
    TextView textView;
    Button buttonSend;
    Button buttonBack;
    EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        PackageManager pm = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra("address", who);
        intent.putExtra("sms_body", sms);
        intent.setType("vnd.android-dir/mms-sms");
        if (intent.resolveActivity(pm) != null) {
            startActivity(intent);
        } else Log.d("Error", getString(R.string.error));
    }

    private void returnActivityResult(String result) {
        Intent intent = new Intent();
        intent.putExtra(WEATHER_ACTIVITY_INTENT, result);
        setResult(RESULT_OK, intent);
        finish();
    }

}
