package ct.emergency;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;

public class UserLogin extends AppCompatActivity {


    private ProgressDialog pDialog;
    SQLiteDatabase db;
    private Toolbar toolbar;
    String unm1,pwd1;
    FloatingActionButton fab;
    SharedPreferences preferences;
    boolean sts;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);


        fab = (FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(UserLogin.this,Register.class);

                startActivity(i);
                finish();
            }
        });

        db=openOrCreateDatabase("healthcare", MODE_PRIVATE,null);
        db.execSQL("create table if not exists signup(name varchar,unm varchar,pwd varchar,email varchar,mno varchar,bg varchar)");


    }

    public void login(View v) throws Exception {

        final EditText userName = (EditText)findViewById(R.id.userName);
        final EditText userPwd= (EditText)findViewById(R.id.pwd);
        unm1=userName.getText().toString().trim();
        pwd1=userPwd.getText().toString().trim();
        int cnt=0;

                if ("".equals(userName.getText().toString().trim()) || "".equals(userPwd.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "All filds are mandatory", Toast.LENGTH_LONG).show();
                } else {
                    String selectQuery = "SELECT *FROM signup where unm='" + unm1 + "' and pwd='" + pwd1 + "'";
                    Cursor cursor = db.rawQuery(selectQuery, null);
                    if (cursor != null) {

                        if (cursor.moveToFirst()) {
                            do {
                                cnt = 1;


                            } while (cursor.moveToNext());

                        }
                    }




                    if(cnt==1) {
                        Intent i = new Intent(UserLogin.this, UserHome.class);
                        i.putExtra("unm", unm1);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_LONG).show();

                    }

                }
                    }


               
    public void register(View v)
    {  
    	
    	Intent i=new Intent(UserLogin.this,Register.class);
    	startActivity(i);

    }




}

        // dialog}
