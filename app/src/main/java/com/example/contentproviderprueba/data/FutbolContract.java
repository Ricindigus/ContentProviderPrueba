package com.example.contentproviderprueba.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class FutbolContract {
    public static final String CONTENT_AUTHORITY = "com.example.contentproviderprueba";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_JUGADORES = "jugadores";

    public FutbolContract() {
    }

    public static final class JugadoresEntry implements BaseColumns{
        //nombre tablas
        public static final String TABLE_NAME = "jugadores";

        //uri de contenido
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_JUGADORES);

        //content types
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_JUGADORES;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_JUGADORES;


        //nombre columnas tabla jugadores
        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_PAIS = "pais";
        public static final String COLUMN_CLUB = "club";
        public static final String COLUMN_EDAD = "edad";

        //sql jugadores
        public static final String SQL_CREATE_TABLE_JUGADORES = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOMBRE + " TEXT NOT NULL, "
                + COLUMN_EDAD + " INTEGER NOT NULL DEFAULT 0, "
                + COLUMN_CLUB + " TEXT NOT NULL, "
                + COLUMN_PAIS + " TEXT NOT NULL"
                +");";
    }



}
