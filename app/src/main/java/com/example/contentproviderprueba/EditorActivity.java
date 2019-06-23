package com.example.contentproviderprueba;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contentproviderprueba.data.FutbolContract.JugadoresEntry;

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    TextView tvNombre, tvEdad, tvClub, tvPais;
    Uri uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        uri = getIntent().getData();
        if (uri == null) getSupportActionBar().setTitle("Insertar Jugador");
        else {
            getSupportActionBar().setTitle("Actualizar jugador");
            getLoaderManager().initLoader(0,null,this);
        }
        tvNombre = findViewById(R.id.tvNombre);
        tvEdad= findViewById(R.id.tvEdad);
        tvClub = findViewById(R.id.tvClub);
        tvPais = findViewById(R.id.tvPais);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateOrSave(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(JugadoresEntry.COLUMN_NOMBRE,tvNombre.getText().toString());
        contentValues.put(JugadoresEntry.COLUMN_EDAD,tvEdad.getText().toString());
        contentValues.put(JugadoresEntry.COLUMN_PAIS,tvPais.getText().toString());
        contentValues.put(JugadoresEntry.COLUMN_CLUB,tvClub.getText().toString());
        if (uri == null) {
            Uri uriInsert = getContentResolver().insert(JugadoresEntry.CONTENT_URI,contentValues);
            if (uriInsert != null) Toast.makeText(this, "insercion exitosa", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "error al insertar", Toast.LENGTH_SHORT).show();
        }else{
            int rowsAffected = getContentResolver().update(uri,contentValues,null,null);
            if (rowsAffected != 0) Toast.makeText(this, "actualizacion exitosa", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,uri,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data.moveToFirst()){
            String nombre = data.getString(data.getColumnIndex(JugadoresEntry.COLUMN_NOMBRE));
            String club = data.getString(data.getColumnIndex(JugadoresEntry.COLUMN_CLUB));
            String pais = data.getString(data.getColumnIndex(JugadoresEntry.COLUMN_PAIS));
            int edad = data.getInt(data.getColumnIndex(JugadoresEntry.COLUMN_EDAD));
            tvNombre.setText(nombre);
            tvClub.setText(club);
            tvPais.setText(pais);
            tvEdad.setText(String.valueOf(edad));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        tvNombre.setText("");
        tvClub.setText("");
        tvEdad.setText("");
        tvPais.setText("");
    }
}
