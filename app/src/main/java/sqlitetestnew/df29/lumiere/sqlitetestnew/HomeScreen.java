package sqlitetestnew.df29.lumiere.sqlitetestnew;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sqlitetestnew.df29.lumiere.sqlitetestnew.Database.DBHelper;

/**
 * Created by Lumiere on 2/22/2016.
 */
public class HomeScreen extends AppCompatActivity {

    ListView lvItem;
    RelativeLayout relDataNotFound;
    Context context = HomeScreen.this;
    ArrayList mylist = new ArrayList();
    private String tableName = DBHelper.DATABASE_TABLE_NAME;
    SQLiteDatabase newDB;
    Cursor myCursor;
    List<ListData> data;
    SharedPreferences sharedPreferences;
    String PREFERENCES = "loginPref";
    public boolean lvClick = false;
//    ListViewAdapter mylistviewadapter;
//    ListData mylistdata;

    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        lvItem = (ListView) findViewById(R.id.listView);
        relDataNotFound = (RelativeLayout) findViewById(R.id.relDataNotFound);

        sharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        int login = sharedPreferences.getInt("login", 0);
        Log.v("Pref", "PrefInt : "+login);

        if (login == 1){
            getDataInList();
            lvItem.setVisibility(View.VISIBLE);
            relDataNotFound.setVisibility(View.GONE);
            lvItem.setAdapter(new ListViewAdapter(context, data));
            lvClick = true;
        } else {
            lvItem.setVisibility(View.GONE);
            relDataNotFound.setVisibility(View.VISIBLE);
            lvClick = false;
        }

        if (lvClick == true){
            lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(HomeScreen.this, "" + lvItem.getAdapter().getItemId(position), Toast.LENGTH_SHORT).show();
                    String extra = String.valueOf(lvItem.getAdapter().getItemId(position)+1);
                    DFragment dFragment = new DFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("extra", extra);
                    dFragment.setArguments(bundle);
                    dFragment.show(fm, "Dialog Fragment");
                }
            });
        }
    }

    private void getDataInList() {
        DBHelper mydbhelper = new DBHelper(this.getApplicationContext());
        newDB = mydbhelper.getWritableDatabase();
        myCursor = newDB.rawQuery("SELECT name, email FROM " + tableName, null);
        if (myCursor != null){
            if (myCursor.moveToFirst()){
                data = new ArrayList<ListData>();
                do {
                    String name = myCursor.getString(myCursor.getColumnIndex("name"));
                    String email = myCursor.getString(myCursor.getColumnIndex("email"));
                    ListData item = new ListData(name,email);
                    data.add(item);
                    Log.v("Data", "Data : "+data);
                } while (myCursor.moveToNext());
            }
        }

/*
        for (int i = 0; i < title.length; i++) {
            ListData ld = new ListData();
            // Create a new object for each list item
            ld.setTitle(title[i]);
            ld.setDescription(desc[i]);
            // Add this object into the ArrayList myList
            mylist.add(ld);
        }
*/
    }
}
