package sqlitetestnew.df29.lumiere.sqlitetestnew;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sqlitetestnew.df29.lumiere.sqlitetestnew.Database.DBAdapter;
import sqlitetestnew.df29.lumiere.sqlitetestnew.Database.DBHelper;

public class MainActivity extends AppCompatActivity {

    DBAdapter mydbadapter;
//    Button btnLogin, btnSignup;
//    EditText editUsername, editPassword;
ListView lvItem;
    RelativeLayout relDataNotFound;
    Context context = MainActivity.this;
    ArrayList mylist = new ArrayList();
    private String tableName = DBHelper.DATABASE_TABLE_NAME;
    SQLiteDatabase newDB;
    Cursor myCursor;
    List<ListData> data;
    SharedPreferences sharedPreferences;
    String PREFERENCES = "loginPref";
    public int login = 0;
    public boolean lvClick = false;
//    ListViewAdapter mylistviewadapter;
//    ListData mylistdata;

    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        btnLogin = (Button) findViewById(R.id.btnLogin);
//        btnSignup = (Button) findViewById(R.id.btnSignup);
//
//        editUsername = (EditText) findViewById(R.id.editUsername);
//        editPassword = (EditText) findViewById(R.id.editPassword);

        mydbadapter = new DBAdapter(this);
        try {
            mydbadapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

/*
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editUsername.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(editPassword.getWindowToken(), 0);

                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();

                if (username.length()>0 && password.length()>0){
                    try{
                        if (mydbadapter.Login(username, password)){
                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), HomeScreen.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Sorry there is some problem..", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Username or Password is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/

/*
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeScreen.class);
                startActivity(i);
            }
        });
*/
        lvItem = (ListView) findViewById(R.id.listView);
        relDataNotFound = (RelativeLayout) findViewById(R.id.relDataNotFound);

        sharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        login = sharedPreferences.getInt("login", 0);
        Log.v("Pref", "PrefInt : " + login);

        getDataInList();
        /*if (login == 1){
            lvItem.setVisibility(View.VISIBLE);
            relDataNotFound.setVisibility(View.GONE);
            lvItem.setAdapter(new ListViewAdapter(context, data));
            lvClick = true;
        } else {
            lvItem.setVisibility(View.GONE);
            relDataNotFound.setVisibility(View.VISIBLE);
            lvClick = false;
        }*/

        if (lvClick == true){
            lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity.this, "" + lvItem.getAdapter().getItemId(position), Toast.LENGTH_SHORT).show();
                    String extra = String.valueOf(lvItem.getAdapter().getItemId(position)+1);
                    DFragment dFragment = new DFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("extra", extra);
                    dFragment.setArguments(bundle);
                    dFragment.show(fm, "Dialog Fragment");
                }
            });
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SignUp.class);
                startActivity(i);
            }
        });
    }
    public void showData (){
        if (login == 1){
            lvItem.setVisibility(View.VISIBLE);
            relDataNotFound.setVisibility(View.GONE);
            lvItem.setAdapter(new ListViewAdapter(context, data));
            lvClick = true;
        } else {
            lvItem.setVisibility(View.GONE);
            relDataNotFound.setVisibility(View.VISIBLE);
            lvClick = false;
        }

/*
        if (lvClick == true){
            lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity.this, "" + lvItem.getAdapter().getItemId(position), Toast.LENGTH_SHORT).show();
                    String extra = String.valueOf(lvItem.getAdapter().getItemId(position)+1);
                    DFragment dFragment = new DFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("extra", extra);
                    dFragment.setArguments(bundle);
                    dFragment.show(fm, "Dialog Fragment");
                }
            });
        }
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            getDataInList();
        }

        return super.onOptionsItemSelected(item);
    }
    public void getDataInList() {
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
        showData();
        login = sharedPreferences.getInt("login", 0);
    }
}
