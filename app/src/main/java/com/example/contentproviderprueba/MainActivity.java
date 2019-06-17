package com.example.contentproviderprueba;

import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.contentproviderprueba.data.FutbolContract.JugadoresEntry;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    ListView lvJugadores;
    JugadoresCursorAdapter jugadoresCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvJugadores = findViewById(R.id.futbol_lvJugadores);
        jugadoresCursorAdapter = new JugadoresCursorAdapter(this,null);
        lvJugadores.setAdapter(jugadoresCursorAdapter);
        getLoaderManager().initLoader(0,null,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_futbol,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_insertar:
                startActivity(new Intent(MainActivity.this,EditorActivity.class));
                return true;
            case R.id.menu_prueba:
                //TODO insertar data prueba
                insertarDataPrueba();
                return true;
            case R.id.menu_delete_all:
                //TODO eliminar all data
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void insertarDataPrueba(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(JugadoresEntry.COLUMN_NOMBRE,"Lionel Messi");
        contentValues.put(JugadoresEntry.COLUMN_EDAD,27);
        contentValues.put(JugadoresEntry.COLUMN_CLUB,"FC Barcelona");
        contentValues.put(JugadoresEntry.COLUMN_PAIS,"Argentina");
        Uri uri = getContentResolver().insert(JugadoresEntry.CONTENT_URI,contentValues);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        String[] projection = {
                JugadoresEntry.COLUMN_ID,
                JugadoresEntry.COLUMN_NOMBRE,
                JugadoresEntry.COLUMN_EDAD,
                JugadoresEntry.COLUMN_PAIS,
                JugadoresEntry.COLUMN_CLUB
        };
        return new CursorLoader(this,JugadoresEntry.CONTENT_URI,projection,
                null,null,null);
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {
        jugadoresCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
        jugadoresCursorAdapter.swapCursor(null);
    }
}
