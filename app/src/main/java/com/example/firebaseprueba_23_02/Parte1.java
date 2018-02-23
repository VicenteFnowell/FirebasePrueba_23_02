package com.example.firebaseprueba_23_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Parte1 extends AppCompatActivity {

    TextView tvJugador;

    //Variables objetos FIREBASE
    private DatabaseReference dbRef;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parte1);

        tvJugador = (TextView)findViewById(R.id.tvjugador);

        //RELLENAR EL DATA REFERENCE
        dbRef = FirebaseDatabase.getInstance().getReference().child("jugadores/j1");

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                CJugador jug = dataSnapshot.getValue(CJugador.class);
                tvJugador.setText("Nombre: "+jug.getNombre()+"\n" +
                                    "Dorsal: "+jug.getDorsal()+"\n" +
                                    "Posición: "+jug.getPosicion()+"\n"+
                                    "Sueldo: "+jug.getSueldo());

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
