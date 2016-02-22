package sqlitetestnew.df29.lumiere.sqlitetestnew.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lumiere on 2/23/2016.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "membersdb";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE = "CREATE TABLE users (id integer primary key autoincrement,username text not null,password text not null);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXIST users");
        onCreate(db);
    }
}
