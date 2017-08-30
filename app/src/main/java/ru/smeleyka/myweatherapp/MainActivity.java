package ru.smeleyka.myweatherapp;



import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private WeatherCast weathercast = new WeatherCast(this);
    private Spinner spinner;
    private Button button;
    private TextView textView;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        spinner = (Spinner) findViewById(R.id.spinner);
        textView = (TextView) findViewById(R.id.weather_cast);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(weathercast.get_weather((String)spinner.getSelectedItem()));

            }
        });



    }

}
