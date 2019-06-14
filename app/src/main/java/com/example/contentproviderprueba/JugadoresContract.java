package com.example.contentproviderprueba;

import android.net.Uri;
import android.provider.BaseColumns;

public final class JugadoresContract {
    public static final String CONTENT_AUTHORITY = "com.example.contentproviderprueba";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_JUGADORES = "jugadores";

    public JugadoresContract() {
    }

    private static final class JugadoresEntry implements BaseColumns{
        public static final String TABLE_NAME = "jugadores";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_JUGADORES);
        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_PAIS = "pais";
        public static final String COLUMN_CLUB = "club";
        public static final String COLUMN_EDAD = "edad";
    }


}
