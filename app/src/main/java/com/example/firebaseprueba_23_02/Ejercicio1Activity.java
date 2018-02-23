package com.example.firebaseprueba_23_02;

import android.content.Intent;
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

public class Ejercicio1Activity extends AppCompatActivity {

    EditText etNombre,etDorsal,etPosicion,etSueldo;
    Spinner spJugadores;

    //Variables objetos FIREBASE
    private DatabaseReference dbRef;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);

        etNombre = (EditText)findViewById(R.id.etnombre);
        etDorsal = (EditText)findViewById(R.id.etdorsal);
        etPosicion = (EditText)findViewById(R.id.etposicion);
        etSueldo = (EditText)findViewById(R.id.etsueldo);
        spJugadores = (Spinner)findViewById(R.id.spjugadores);

        String [] jugadoresfutbol = {"Selecciona","j1","j2","j3","j4"};
        ArrayAdapter<String> jugador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,jugadoresfutbol);
        spJugadores.setAdapter(jugador);




    }//FIN ONCREATE

    public void clickbtnver (View view){

        String valorspinnerjugadores= spJugadores.getSelectedItem().toString();

        if (valorspinnerjugadores.equals("Selecciona")){
            Toast.makeText(getApplicationContext(),"Elige algún jugador",Toast.LENGTH_LONG).show();
        }else{

            //RELLENAR EL DATA REFERENCE
            dbRef = FirebaseDatabase.getInstance().getReference().child("jugadores/"+valorspinnerjugadores);

            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    CJugador jug = dataSnapshot.getValue(CJugador.class);
                    etNombre.setText("Nombre: "+jug.getNombre());
                    etDorsal.setText("Dorsal: "+jug.getDorsal());
                    etPosicion.setText("Posición: "+jug.getPosicion());
                    etSueldo.setText("Sueldo: "+jug.getSueldo());


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("ActivityParte1","DATABASE ERROR");
                }
            };

            dbRef.addValueEventListener(valueEventListener);//Si queremos base de datos a tiempo real
            //dbRef.addListenerForSingleValueEvent(valueEventListener);//Si queremos base de datos de 1 sola carga
            //dbRef.removeEventListener(valueEventListener);//Destruye la conexión



        }
    }


}//FIN ACTIVITY
