package ru.smeleyka.myweatherapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.smeleyka.myweatherapp.DB.DBImplementation;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //android.support.design.widget.TextInputLayout;
        super.onCreate(savedInstanceState);
        DBImplementation.getInstance(this).getDb();


    }

}


