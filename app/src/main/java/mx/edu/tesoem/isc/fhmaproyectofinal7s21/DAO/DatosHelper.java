package mx.edu.tesoem.isc.fhmaproyectofinal7s21.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatosHelper extends SQLiteOpenHelper {

    protected static String DB_NAME ="dbg7s21";
    protected static int DB_VERSION=1;

    public static class tabladatos{
        public static String TABLA_NAME = "tbdatos";
        public static String TABLA_id ="id";
        public static String TABLA_NOMBRE="nombre";
        public static String TABLA_EDAD ="edad";
        public static String TABLA_CORREO="correo";
    }

    private static String crear_tabla="create table " + tabladatos.TABLA_NAME + "("+ tabladatos.TABLA_id+" integer not null primary key autoincrement, " + tabladatos.TABLA_NOMBRE+" varchar, "+ tabladatos.TABLA_EDAD+" integer, " + tabladatos.TABLA_CORREO+" varchar)";
    private static String elimina_tabla="drop table "+ tabladatos.TABLA_NAME;
    public DatosHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crear_tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(elimina_tabla);
        onCreate(db);
    }
}