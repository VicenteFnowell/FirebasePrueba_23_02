package com.example.firebaseprueba_23_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListviewjugadorActivity extends AppCompatActivity {

    ListView lvjugadores;

    DatabaseReference dbRef;
    ValueEventListener valueEventListener;

    ArrayList <CJugador> lista_jugadores = new ArrayList<CJugador>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewjugador);


        lvjugadores=(ListView)findViewById(R.id.listjugadores);



        cargarDatosFirebase();





    }//FIn OnCreate


    private void  cargarDatosFirebase(){
        dbRef = FirebaseDatabase.getInstance().getReference().child("jugadores");

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lista_jugadores.clear();
                for (DataSnapshot jugadoresDataSnapshot: dataSnapshot.getChildren()){
                    cargarListView(jugadoresDataSnapshot);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ActivityParte2","DATABASE ERROR");

            }
        };
        dbRef.addValueEventListener(valueEventListener);

    }


    private void cargarListView (DataSnapshot dataSnapshot){

        lista_jugadores.add(dataSnapshot.getValue(CJugador.class));
        Adaptadorjugadorlista adaptadorJugadorLista = new Adaptadorjugadorlista (this,lista_jugadores);
        lvjugadores.setAdapter(adaptadorJugadorLista);

        lvjugadores.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CJugador cJugador = ((CJugador)parent.getItemAtPosition(position));
                Toast.makeText(getApplicationContext(),"El sueldo de "+cJugador.getNombre()+" es de " + cJugador.getSueldo(), Toast.LENGTH_LONG).show();
            }
        }));


    }

}//Fin MainActivity
