package sqlitetestnew.df29.lumiere.sqlitetestnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lumiere on 2/23/2016.
 */
public class ListViewAdapter extends BaseAdapter{

    LayoutInflater inflater;
    Context context;
    List<ListData> data;

    public ListViewAdapter (Context context, List<ListData> mylist){
        this.data = mylist;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    /*
    * Diastowo Faryduana
    * */
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        ListData currentListData = (ListData) getItem(position);
        myViewHolder.txtNama.setText(currentListData.getTitle());
        myViewHolder.txtEmail.setText(currentListData.getDescription());

        return convertView;
    }

    public class MyViewHolder {
        TextView txtNama, txtEmail;

        public MyViewHolder(View item) {
            txtNama = (TextView) item.findViewById(R.id.txtNama);
            txtEmail = (TextView) item.findViewById(R.id.txtEmail);
        }
    }
}
