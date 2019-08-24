package ct.emergency;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {

    EditText userName,userPwd,userEmail,userContact,name;
    private Spinner spinner1;

    String nm1,unm1,pwd1,email1,mno,bg;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        setTitle("Signup");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db=openOrCreateDatabase("healthcare", MODE_PRIVATE,null);
        db.execSQL("create table if not exists signup(name varchar,unm varchar,pwd varchar,email varchar,mno varchar,bg varchar)");

    }
    public void register(View v) throws Exception
    {
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        name = (EditText)findViewById(R.id.name);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
         userName = (EditText)findViewById(R.id.userName);
        userPwd = (EditText)findViewById(R.id.pwd);

        
         userEmail = (EditText)findViewById(R.id.userEmail);
         userContact = (EditText)findViewById(R.id.userContact);
        nm1=name.getText().toString().trim();
        unm1=userName.getText().toString().trim();
        pwd1=userPwd.getText().toString().trim();
        email1=userEmail.getText().toString().trim();
        mno=userContact.getText().toString().trim();
        bg=String.valueOf(spinner1.getSelectedItem());
        if ("".equals(name.getText().toString().trim())||"".equals(userName.getText().toString().trim()) || "".equals(userEmail.getText().toString().trim())
                || "".equals(userContact.getText().toString().trim())|| "".equals(userPwd.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "All filds are mandatory", Toast.LENGTH_LONG).show();
        } else{ 
        	if (!userEmail.getText().toString().trim().matches(emailPattern)) {
        	
        	 Toast.makeText(this,"Incorect Email Id Entry ",Toast.LENGTH_LONG).show();
        } 
        	else if (userContact.getText().toString().trim().length() != 10) {
        		
        		 Toast.makeText(this,"Invalid Mobile No. ",Toast.LENGTH_LONG).show();
            } 
         else if(bg.equals("Choose BloodGroup")){

                Toast.makeText(this, "Choose your Blood Group", Toast.LENGTH_SHORT).show();
            }
            else
            {
                spinner1 = (Spinner) findViewById(R.id.spinner1);
                bg=String.valueOf(spinner1.getSelectedItem());

                db.execSQL("insert into signup values('" +nm1+ "','" +unm1+ "','" +pwd1+ "','" +email1+ "','" + mno + "','" +bg + "') ");
                    Intent i = new Intent(Register.this, UserLogin.class);
                    startActivity(i);
                    Toast.makeText(this,"Registered Successfully",Toast.LENGTH_LONG).show();
                    finish();
                }

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

            default:
                break;

        }
        return true;
    }
    }





        // dialog}
