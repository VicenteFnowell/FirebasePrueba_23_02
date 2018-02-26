package com.example.firebaseprueba_23_02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void clickparte1 (View view){
        Intent i=new Intent(getApplicationContext(),Parte1.class);
        startActivity(i);
    }

    public void clickbtnejercicio1 (View view){
        Intent i=new Intent(getApplicationContext(),Ejercicio1Activity.class);
        startActivity(i);
    }

    public void clickparte2 (View view){
        Intent i=new Intent(getApplicationContext(),ListviewjugadorActivity.class);
        startActivity(i);
    }






}
