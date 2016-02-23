package sqlitetestnew.df29.lumiere.sqlitetestnew;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import sqlitetestnew.df29.lumiere.sqlitetestnew.Database.DBHelper;

/**
 * Created by Lumiere on 2/23/2016.
 */
public class DFragment extends DialogFragment {
    Button btnClose;
    TextView txtName, txtAddress, txtTelp, txtEmail, txtGender;
    SQLiteDatabase newDB;
    String name, address, telp, email, gender,
            extra;
    private final String tableName = "contact";
    private final String kolomnama = "name";
    private final String kolomemail = "email";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment, container, false);
        getDialog().setTitle("Detail");

        Bundle bundle = getArguments();
        extra = bundle.getString("extra");
        Log.v("Bundle Extra", "Bundle Extra : " + extra);

        txtName = (TextView) rootView.findViewById(R.id.txtName);
        txtAddress = (TextView) rootView.findViewById(R.id.txtAddress);
        txtTelp = (TextView) rootView.findViewById(R.id.txtTelp);
        txtEmail = (TextView) rootView.findViewById(R.id.txtEmail);
        txtGender = (TextView) rootView.findViewById(R.id.txtGender);
        btnClose = (Button) rootView.findViewById(R.id.btnClose);


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        getDetailData();
        return rootView;
    }

    private void getDetailData() {
        DBHelper mydbhelper = new DBHelper(this.getContext());
        newDB = mydbhelper.getWritableDatabase();
        Cursor myCursor = newDB.rawQuery("SELECT name, address, phone, email, gender FROM " + tableName + " WHERE id=" + extra + "", null);
        if (myCursor != null) {
            if (myCursor.moveToFirst()) {
                do {
                    name = myCursor.getString(myCursor.getColumnIndex("name"));
                    email = myCursor.getString(myCursor.getColumnIndex("email"));
                    address = myCursor.getString(myCursor.getColumnIndex("address"));
                    telp = myCursor.getString(myCursor.getColumnIndex("phone"));
                    gender = myCursor.getString(myCursor.getColumnIndex("gender"));
                    Log.v("Test", "Query : " + name + email + address + telp);
                } while (myCursor.moveToNext());
            }
        }
        txtName.setText(name);
        txtAddress.setText(address);
        txtTelp.setText(telp);
        txtEmail.setText(email);
        txtGender.setText(gender);
    }
}
