package sqlitetestnew.df29.lumiere.sqlitetestnew;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Lumiere on 2/22/2016.
 */
public class HomeScreen extends AppCompatActivity {

    ListView lvItem;
    Context context = HomeScreen.this;
    ArrayList mylist = new ArrayList();

    String[] title = new String[]{
            "Title 1", "Title 2", "Title 3",
            "Title 4", "Title 5"
    };
    String[] desc = new String[]{
            "Desc 1", "Desc 2", "Desc 3",
            "Desc 4", "Desc 5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        lvItem = (ListView) findViewById(R.id.listView);

        getDataInList();
        lvItem.setAdapter(new ListViewAdapter(context, mylist));
    }

    private void getDataInList() {
        for (int i = 0; i < title.length; i++) {
            ListData ld = new ListData();
            // Create a new object for each list item
            ld.setTitle(title[i]);
            ld.setDescription(desc[i]);
            // Add this object into the ArrayList myList
            mylist.add(ld);
        }
    }
}
