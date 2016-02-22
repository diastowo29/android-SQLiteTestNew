package sqlitetestnew.df29.lumiere.sqlitetestnew;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import sqlitetestnew.df29.lumiere.sqlitetestnew.Database.DBAdapter;

/**
 * Created by Lumiere on 2/22/2016.
 */
public class SignUp extends AppCompatActivity {

    EditText editUsername, editPassword, editConfPassword;
    Button btnSignup;

    DBAdapter mydbadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        editUsername = (EditText) findViewById(R.id.editTextUserName);
        editPassword = (EditText) findViewById(R.id.editTextPassword);
        editConfPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        btnSignup = (Button) findViewById(R.id.buttonCreateAccount);

        mydbadapter = new DBAdapter(this);
        try {
            mydbadapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editUsername.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(editPassword.getWindowToken(), 0);
                try{
                    String username = editUsername.getText().toString();
                    String password = editPassword.getText().toString();

                    long i = mydbadapter.register(username, password);
                    if (i != -1){
                        Toast.makeText(SignUp.this, "Account successfully created", Toast.LENGTH_SHORT).show();
                        editUsername.setText("");
                        editPassword.setText("");
                        finish();
                    }
                } catch (android.database.SQLException e){
                    Toast.makeText(SignUp.this, "Sorry, There is some problem..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
