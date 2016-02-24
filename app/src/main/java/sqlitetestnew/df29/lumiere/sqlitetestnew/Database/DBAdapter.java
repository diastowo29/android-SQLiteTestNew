package sqlitetestnew.df29.lumiere.sqlitetestnew.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.sql.SQLException;

/**
 * Created by Lumiere on 2/23/2016.
 */
public class DBAdapter {
    private static final String DATABASE_TABLE = "contact";
    public static final String KEY_ROW_ID = "_id";
    public static final String KEY_USERNAME = "name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_GENDER = "gender";

    SQLiteDatabase mydb;
    Context ctx;
    DBHelper myDBHelper;

    public DBAdapter(Context context){
        this.ctx = context;
    }

    public DBAdapter open() throws SQLException{
        myDBHelper = new DBHelper(ctx);
        mydb = myDBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        myDBHelper.close();
    }

    public long register (String user, String address, String phone, String email, String gender){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME, user);
        initialValues.put(KEY_ADDRESS, address);
        initialValues.put(KEY_PHONE, phone);
        initialValues.put(KEY_EMAIL, email);
        initialValues.put(KEY_GENDER, gender);

        return mydb.insert(DATABASE_TABLE, null, initialValues);
    }

/*
    public boolean Login(String username, String password) throws SQLException{
        Cursor mycursor = mydb.rawQuery("SELECT * FROM "+ DATABASE_TABLE +" WHERE username=? AND password=?", new String[]{username, password});
        if (mycursor!=null){
            if (mycursor.getCount() > 0){
                return true;
            }
        }
        return false;
    }
*/
}
