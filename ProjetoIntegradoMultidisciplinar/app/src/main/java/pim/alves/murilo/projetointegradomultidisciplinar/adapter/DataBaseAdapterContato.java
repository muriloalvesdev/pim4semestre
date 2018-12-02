package pim.alves.murilo.projetointegradomultidisciplinar.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseAdapterContato extends SQLiteOpenHelper {

    private static final int DATA_BASE_VERSION = 1;
    protected static final String DATA_BASE_NAME = "crudcontato.db";

    public DataBaseAdapterContato(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE contato " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT, email TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS contato";
        db.execSQL(sql);
        onCreate(db);
    }
}
