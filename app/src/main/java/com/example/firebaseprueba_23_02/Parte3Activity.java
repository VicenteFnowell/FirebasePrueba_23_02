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

        String [] jugadoresfutbol = {"Selecciona","j1","j2","j3","j4","j5","j6"};
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
                    if (jug != null){
                        etNombre.setText(jug.getNombre());
                        etDorsal.setText(jug.getDorsal()+"");
                        etPosicion.setText(jug.getPosicion());
                        etSueldo.setText(jug.getSueldo()+"");
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("Parte3Activity", "DATABASE ERROR");
                }
            };

            dbRef.addValueEventListener(valueEventListener);


        }

    }

    public void clickbtninsertar(View view){
        String nombre = etNombre.getText().toString();
        String strDorsal = etDorsal.getText().toString();
        String posicion = etPosicion.getText().toString();
        String strSueldo = etSueldo.getText().toString();

        if(nombre.equals("")||strDorsal.equals("")||posicion.equals("")||strSueldo.equals("")){
            Toast.makeText(getApplicationContext(),"Rellena todos los campos",Toast.LENGTH_LONG).show();
        }else{
            int dorsal = Integer.parseInt(strDorsal);
            double sueldo = Double.parseDouble(strSueldo);
            CJugador nuevoJugador=new CJugador(nombre,dorsal,posicion,sueldo);
            dbRef = FirebaseDatabase.getInstance().getReference()
                    .child("jugadores");

            //String nueva_clave = dbRef.push().setValue(nuevoJugador, new DatabaseReference.CompletionListener(){
            dbRef.child("j6").setValue(nuevoJugador, new DatabaseReference.CompletionListener(){

                public void onComplete(DatabaseError error, DatabaseReference ref) {
                    if(error == null) {

                        Toast.makeText(getApplicationContext(),
                                "INSERTADO CORRECTAMENTE",
                                Toast.LENGTH_LONG).show();

                        limpiarformulario();
                    }else {
                        Toast.makeText(getApplicationContext(),
                                "NO SE PUEDE INSETAR EL JUGADOR",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }


    public void clickbtnmodificar(View view){
        String nombre = etNombre.getText().toString();
        String strDorsal = etDorsal.getText().toString();
        String posicion = etPosicion.getText().toString();
        String strSueldo = etSueldo.getText().toString();

        if(nombre.equals("")||strDorsal.equals("")||posicion.equals("")||strSueldo.equals("")){
            Toast.makeText(getApplicationContext(),"Rellena todos los campos",Toast.LENGTH_LONG).show();
        }else{
            int dorsal = Integer.parseInt(strDorsal);
            double sueldo = Double.parseDouble(strSueldo);
            CJugador nuevoJugador=new CJugador(nombre,dorsal,posicion,sueldo);
            dbRef = FirebaseDatabase.getInstance().getReference()
                    .child("jugadores");

            String idSeleccionada = spIdJugador.getSelectedItem().toString();
            //String nueva_clave = dbRef.push().setValue(nuevoJugador, new DatabaseReference.CompletionListener(){
            dbRef.child(idSeleccionada).setValue(nuevoJugador, new DatabaseReference.CompletionListener(){

                public void onComplete(DatabaseError error, DatabaseReference ref) {
                    if(error == null) {

                        Toast.makeText(getApplicationContext(),
                                "INSERTADO CORRECTAMENTE",
                                Toast.LENGTH_LONG).show();
                                limpiarformulario();
                    }else {
                        Toast.makeText(getApplicationContext(),
                                "NO SE PUEDE INSETAR EL JUGADOR",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }

    public void clickbtneliminar (View view){

        dbRef = FirebaseDatabase.getInstance().getReference().child("jugadores");

        String idSeleccionada = spIdJugador.getSelectedItem().toString();
        dbRef.child(idSeleccionada).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError == null){
                    Toast.makeText(getApplicationContext(),"ELIMINADO CORRECTAMENTE",Toast.LENGTH_LONG).show();
                    limpiarformulario();
                }else{
                    Toast.makeText(getApplicationContext(),"NO SE PUEDE ELIMINAR EL JUGADOR",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void limpiarformulario(){
        etNombre.setText("");
        etDorsal.setText("");
        etPosicion.setText("");
        etSueldo.setText("");


    }




        }//FIN ACTIVITY
