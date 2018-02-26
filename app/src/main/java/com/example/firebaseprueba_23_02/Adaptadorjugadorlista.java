package com.example.firebaseprueba_23_02;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vicente FN on 26/02/2018.
 */

public class Adaptadorjugadorlista extends ArrayAdapter <CJugador> {



    ArrayList<CJugador> jugadores;
    Context c;

    public Adaptadorjugadorlista(Context c, ArrayList<CJugador> jugadores){
        super(c,R.layout.itemlistjugadores,jugadores);
        this.c = c;
        this.jugadores = jugadores;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.itemlistjugadores,null);

        //TextView Nombre
        TextView tv_nombre=(TextView)item.findViewById(R.id.item_tvnomb);
        tv_nombre.setText(jugadores.get(position).getNombre());

        //TextView Dorsal
        TextView tv_dorsal=(TextView)item.findViewById(R.id.item_tvdors);
        tv_dorsal.setText(jugadores.get(position).getDorsal()+"");

        //Textview Posición
        TextView tv_posicion=(TextView)item.findViewById(R.id.item_tvpos);
        tv_posicion.setText(jugadores.get(position).getPosicion());


        return item;

    }
}