package sqlitetestnew.df29.lumiere.sqlitetestnew.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lumiere on 2/23/2016.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "contact_dbnew";
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE_NAME = "contact";
    private static final String DATABASE_CREATE = "CREATE TABLE contact (id integer primary key autoincrement,name text not null, address text not null, phone text not null, email text not null, gender text);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXIST contact");
        onCreate(db);
    }
}
