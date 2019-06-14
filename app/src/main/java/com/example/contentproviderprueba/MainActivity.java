package com.example.contentproviderprueba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvJugadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvJugadores = findViewById(R.id.futbol_rvJugadores);
        rvJugadores.setHasFixedSize(true);
        rvJugadores.setLayoutManager(new LinearLayoutManager(this));
        rvJugadores.setAdapter(null);
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
                return true;
            case R.id.menu_delete_all:
                //TODO eliminar all data
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
