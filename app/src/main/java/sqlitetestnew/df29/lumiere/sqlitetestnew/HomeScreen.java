package sqlitetestnew.df29.lumiere.sqlitetestnew;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

import sqlitetestnew.df29.lumiere.sqlitetestnew.Database.DBHelper;

/**
 * Created by Lumiere on 2/22/2016.
 */
public class HomeScreen extends AppCompatActivity {

    ListView lvItem;
    Context context = HomeScreen.this;
    ArrayList mylist = new ArrayList();
    private String tableName = DBHelper.DATABASE_TABLE_NAME;
    SQLiteDatabase newDB;

    String[] title = new String[]{
            "Title 1", "Title 2", "Title 3",
            "Title 4", "Title 5"
    };
    String[] desc = new String[]{
            "Desc 1", "Desc 2", "Desc 3",
            "Desc 4", "Desc 5"
    };

    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        lvItem = (ListView) findViewById(R.id.listView);

        getDataInList();
        lvItem.setAdapter(new ListViewAdapter(context, mylist));
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DFragment dFragment = new DFragment();
                dFragment.show(fm, "Dialog Fragment");
                ListData listData = new ListData();
                listData.getTitle();
                ListViewAdapter listViewAdapter = new ListViewAdapter(context, mylist);
                Log.v("Data Clicked", "Data Clicked at "+ listViewAdapter.getItem(position));
            }
        });
    }

    private void getDataInList() {
        DBHelper mydbhelper = new DBHelper(this.getApplicationContext());
        newDB = mydbhelper.getWritableDatabase();
        Cursor cursor = newDB.rawQuery("SELECT name, email FROM "+tableName, null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                do {
                    ListData ld = new ListData();
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String email = cursor.getString(cursor.getColumnIndex("email"));
                    ld.setTitle(name);
                    ld.setDescription(email);
                    mylist.add(ld);
                } while (cursor.moveToNext());
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
