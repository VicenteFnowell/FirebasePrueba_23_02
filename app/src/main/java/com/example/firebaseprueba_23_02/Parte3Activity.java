package com.example.firebaseprueba_23_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Parte3Activity extends AppCompatActivity {

    EditText etNombre, etDorsal, etPosicion, etSueldo;
    Spinner spIdJugador;

    private DatabaseReference dbRef;
    private ValueEventListener valueEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parte3);

        etNombre = (EditText)findViewById(R.id.etnombre2);
        etDorsal = (EditText)findViewById(R.id.etdorsal2);
        etPosicion = (EditText)findViewById(R.id.etposicion2);
        etSueldo = (EditText)findViewById(R.id.etsueldo2);
        spIdJugador = (Spinner)findViewById(R.id.spidjugador);

        String [] jugadoresfutbol = {"Selecciona","j1","j2","j3","j4","j5"};
        ArrayAdapter<String> jugador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,jugadoresfutbol);
        spIdJugador.setAdapter(jugador);


    }//FIN ONCREATE

    public void clickbtnbuscar (View view) {

        String valorspinnerjugadores = spIdJugador.getSelectedItem().toString();

        if (valorspinnerjugadores.equals("Selecciona")) {
            Toast.makeText(getApplicationContext(), "Elige algún jugador", Toast.LENGTH_LONG).show();
        } else {
            //RELLENAR EL DATA REFERENCE
            dbRef = FirebaseDatabase.getInstance().getReference().child("jugadores/" + valorspinnerjugadores);

            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    CJugador jug = dataSnapshot.getValue(CJugador.class);
                    etNombre.setText(jug.getNombre());
                    etDorsal.setText(jug.getDorsal()+"");
                    etPosicion.setText(jug.getPosicion());
                    etSueldo.setText(jug.getSueldo()+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("Parte3Activity", "DATABASE ERROR");
                }
            };

            dbRef.addValueEventListener(valueEventListener);


        }

    }
/*
    public void clickbtninsertar (View view){
        String nombre = etNombre.getText().toString();
        String dorsal = etDorsal.getText()


    }





*/

        }//FIN ACTIVITY
