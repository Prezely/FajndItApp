package si.uni_lj.fe.tnuv.fajndit2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "SeznamIzdelkov.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "moji_izdelki";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_IME = "ime";
    private static final String COLUMN_KATEGORIJA = "kategorija";
    private static final String COLUMN_CENA = "cena";
    private static final String COLUMN_SLIKA = "slika";




    public MyDatabseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_IME + " TEXT, " +
                        COLUMN_KATEGORIJA + " TEXT, " +
                        COLUMN_CENA + " INTEGER, " +
                        COLUMN_SLIKA + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void dodajIzdelek(String ime, String kategorija, int cena, String slika) {

        Toast.makeText(context, ime, Toast.LENGTH_SHORT).show();


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(COLUMN_IME, ime);
        cv.put(COLUMN_KATEGORIJA, kategorija);
        cv.put(COLUMN_CENA, cena);
        cv.put(COLUMN_SLIKA, slika);
        long result = db.insert(TABLE_NAME, null, cv);


        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }





    }
}
