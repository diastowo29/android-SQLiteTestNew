package sqlitetestnew.df29.lumiere.sqlitetestnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lumiere on 2/23/2016.
 */
public class ListViewAdapter extends BaseAdapter{

    ArrayList mylist = new ArrayList();
    LayoutInflater inflater;
    Context context;

    public ListViewAdapter (Context context, ArrayList mylist){
        this.mylist = mylist;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return mylist.size();
    }

    @Override
    public ListData getItem(int position) {
        return (ListData) mylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
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

        ListData currentListData = getItem(position);
        myViewHolder.txtNama.setText(currentListData.getTitle());
        myViewHolder.txtEmail.setText(currentListData.getDescription());

        return convertView;
    }

    private class MyViewHolder {
        TextView txtNama, txtEmail;

        public MyViewHolder(View item) {
            txtNama = (TextView) item.findViewById(R.id.txtNama);
            txtEmail = (TextView) item.findViewById(R.id.txtEmail);
        }
    }
}
