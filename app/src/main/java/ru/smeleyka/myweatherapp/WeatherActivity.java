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
    TextView textView;
    Button buttonSend;
    EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        textView = (TextView) findViewById(R.id.weather_text_view);
        buttonSend = (Button)findViewById(R.id.button_send);
        edittext  = (EditText)findViewById(R.id.editText);
        if (b !=null) {
            textView.setText((String) b.get("weatherActivity"));
        }
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSms((String)textView.getText(),edittext.getText().toString());
            }
        });
//        edittext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                edittext.setText(pickContact());
//            }
//        });


    }


    public void sendSms(String sms,String who){
        PackageManager pm = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra("address",who);
        intent.putExtra("sms_body",sms);
        intent.setType("vnd.android-dir/mms-sms");
        if(intent.resolveActivity(pm)!= null)

        startActivity(intent);
        else Log.d("Error","Нет подходящих Активити");
    }

//    private String pickContact() {
//        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
//        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
//        startActivity(pickContactIntent, 1);
//           }


}
