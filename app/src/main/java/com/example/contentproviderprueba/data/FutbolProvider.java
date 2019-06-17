package com.example.contentproviderprueba.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

import com.example.contentproviderprueba.data.FutbolContract.JugadoresEntry;

public class FutbolProvider extends ContentProvider {

    private FutbolHelper futbolHelper;

    public static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public static final int JUGADORES = 100;
    public static final int JUGADORES_ID = 101;

    static {
        sUriMatcher.addURI(FutbolContract.CONTENT_AUTHORITY, FutbolContract.PATH_JUGADORES, JUGADORES);
        sUriMatcher.addURI(FutbolContract.CONTENT_AUTHORITY, FutbolContract.PATH_JUGADORES + "/#", JUGADORES_ID);
    }

    public FutbolProvider() {
    }

    @Override
    public boolean onCreate() {
        futbolHelper = new FutbolHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase sqLiteDatabase = futbolHelper.getReadableDatabase();
        Cursor cursor = null;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case JUGADORES:
                cursor = sqLiteDatabase.query(
                        FutbolContract.JugadoresEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case JUGADORES_ID:
                selection = FutbolContract.JugadoresEntry.COLUMN_ID;
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(
                        FutbolContract.JugadoresEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case JUGADORES:
                return insertJugador(uri, values);
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }


    }

    private Uri insertJugador(Uri uri, ContentValues values) {
        String nombre = values.getAsString(JugadoresEntry.COLUMN_NOMBRE);
        if (nombre == null || TextUtils.isEmpty(nombre)) {
            throw new IllegalArgumentException("EL jugador requiere un nombre");
        }

        Integer edad = values.getAsInteger(JugadoresEntry.COLUMN_EDAD);
        if (edad == null) {
            throw new IllegalArgumentException("EL jugador requiere una edad");
        } else if (edad <= 0) {
            throw new IllegalArgumentException("EL jugador requiere una edad válida > 0");
        }

        String pais = values.getAsString(JugadoresEntry.COLUMN_PAIS);
        if (pais == null || TextUtils.isEmpty(pais)) {
            throw new IllegalArgumentException("EL jugador requiere un país");
        }

        String club = values.getAsString(JugadoresEntry.COLUMN_CLUB);
        if (club == null || TextUtils.isEmpty(club)) {
            throw new IllegalArgumentException("EL jugador requiere un club");
        }

        SQLiteDatabase sqLiteDatabase = futbolHelper.getWritableDatabase();
        long id = sqLiteDatabase.insert(FutbolContract.JugadoresEntry.TABLE_NAME, null, values);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case JUGADORES_ID:
                return updateJugador(uri, values, selection, selectionArgs);
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }

    }

    private int updateJugador(Uri uri, ContentValues values, String selection,
                              String[] selectionArgs) {

        if (values.size() == 0) return 0;

        if (values.containsKey(JugadoresEntry.COLUMN_NOMBRE)) {
            String nombre = values.getAsString(JugadoresEntry.COLUMN_NOMBRE);
            if (nombre == null || TextUtils.isEmpty(nombre)) {
                throw new IllegalArgumentException("EL jugador requiere un nombre");
            }
        }

        if (values.containsKey(JugadoresEntry.COLUMN_EDAD)) {
            Integer edad = values.getAsInteger(JugadoresEntry.COLUMN_EDAD);
            if (edad == null) {
                throw new IllegalArgumentException("EL jugador requiere una edad");
            } else if (edad <= 0) {
                throw new IllegalArgumentException("EL jugador requiere una edad válida > 0");
            }
        }

        if (values.containsKey(JugadoresEntry.COLUMN_PAIS)) {
            String pais = values.getAsString(JugadoresEntry.COLUMN_PAIS);
            if (pais == null || TextUtils.isEmpty(pais)) {
                throw new IllegalArgumentException("EL jugador requiere un país");
            }
        }

        if (values.containsKey(JugadoresEntry.COLUMN_CLUB)) {
            String club = values.getAsString(JugadoresEntry.COLUMN_CLUB);
            if (club == null || TextUtils.isEmpty(club)) {
                throw new IllegalArgumentException("EL jugador requiere un club");
            }
        }

        SQLiteDatabase sqLiteDatabase = futbolHelper.getWritableDatabase();
        selection = JugadoresEntry.COLUMN_ID + "=?";
        selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
        return sqLiteDatabase.update(JugadoresEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        SQLiteDatabase sqLiteDatabase = futbolHelper.getWritableDatabase();
        switch (match) {
            case JUGADORES:
                return sqLiteDatabase.delete(JugadoresEntry.TABLE_NAME, null, null);
            case JUGADORES_ID:
                selection = JugadoresEntry.COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return sqLiteDatabase.delete(JugadoresEntry.TABLE_NAME, selection, selectionArgs);
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    @Override
    public String getType(Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case JUGADORES:
                return JugadoresEntry.CONTENT_LIST_TYPE;
            case JUGADORES_ID:
                return JugadoresEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
    }
}
