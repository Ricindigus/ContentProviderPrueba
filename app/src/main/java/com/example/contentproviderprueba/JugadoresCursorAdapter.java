package com.example.contentproviderprueba;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.contentproviderprueba.data.FutbolContract.JugadoresEntry;

public class JugadoresCursorAdapter extends CursorAdapter {

    public JugadoresCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_jugador,parent,false);
    }

    @Override
    public void bindView(View itemView, Context context, Cursor cursor) {
        TextView tvNombre, tvEdad, tvClub, tvPais;
        tvNombre = itemView.findViewById(R.id.jugador_nombre);
        tvEdad = itemView.findViewById(R.id.jugador_edad);
        tvClub = itemView.findViewById(R.id.jugador_equipo);
        tvPais = itemView.findViewById(R.id.jugador_pais);

        String nombre = cursor.getString(cursor.getColumnIndex(JugadoresEntry.COLUMN_NOMBRE));
        int edad = cursor.getInt(cursor.getColumnIndex(JugadoresEntry.COLUMN_EDAD));
        String club = cursor.getString(cursor.getColumnIndex(JugadoresEntry.COLUMN_CLUB));
        String pais = cursor.getString(cursor.getColumnIndex(JugadoresEntry.COLUMN_PAIS));


        tvNombre.setText(nombre);
        tvEdad.setText(edad+"");
        tvClub.setText(club);
        tvPais.setText(pais);
    }
}
