package ct.emergency;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddHospital extends AppCompatActivity {

    SQLiteDatabase db;
    RadioGroup service;
    RadioButton servicevalue;
    RadioGroup ambulance;
    RadioButton ambulancevalue;
    RadioGroup policy;
    RadioButton policyvalue;
    RadioGroup emergency;
    RadioButton emergencyvalue;



    EditText name;
    EditText email;
    EditText ph;
    EditText pwd;
    EditText lat;
    EditText lon;
    EditText addr;
    EditText uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addhospital);
        setTitle("Add Hospital");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db=openOrCreateDatabase("healthcare", MODE_PRIVATE,null);
        db.execSQL("create table if not exists hospital(name varchar,service varchar,emergency varchar,ambulance varchar,policy varchar,contact varchar,email varchar,address varchar,lat varchar,long varchar,rating varchar)");
        service=(RadioGroup)findViewById(R.id.hrs);

        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        ph=(EditText)findViewById(R.id.contact);
        lat=(EditText)findViewById(R.id.lat);
        lon=(EditText)findViewById(R.id.lon);
        addr=(EditText)findViewById(R.id.address);
        service=(RadioGroup)findViewById(R.id.hrs);
        ambulance=(RadioGroup)findViewById(R.id.ambulance);
        policy=(RadioGroup)findViewById(R.id.insurance);
        emergency=(RadioGroup)findViewById(R.id.emergency);

       // Toast.makeText(this,policy+";"+ambulancevalue+";"+servicevalue,Toast.LENGTH_LONG).show();

    }
    public void addhosp(View v){
        String name1=name.getText().toString();
        int selectedId = service.getCheckedRadioButtonId();
        servicevalue = (RadioButton)findViewById(selectedId);
        int selectedId2 = ambulance.getCheckedRadioButtonId();
        ambulancevalue = (RadioButton)findViewById(selectedId2);
        int selectedId3 = policy.getCheckedRadioButtonId();
        policyvalue = (RadioButton)findViewById(selectedId3);
        int selectedId4 = emergency.getCheckedRadioButtonId();
        emergencyvalue = (RadioButton)findViewById(selectedId4);



        try {

            //System.out.println("insert into hospital values('" + name1 + "','" + servicevalue.getText().toString() + "','" + emergencyvalue.getText().toString() + "','" + ambulancevalue.getText().toString() + "','" + policyvalue.getText().toString() + "','" + ph.getText().toString() + "','" + email.getText().toString() + "','" + addr.getText().toString() + "','" + lat.getText().toString() + "','" + lon.getText().toString() + "' ) ");

            db.execSQL("insert into hospital values('" + name1 + "','" + servicevalue.getText().toString() + "','" + emergencyvalue.getText().toString() + "','" + ambulancevalue.getText().toString() + "','" + policyvalue.getText().toString() + "','" + ph.getText().toString() + "','" + email.getText().toString() + "','" + addr.getText().toString() + "','" + lat.getText().toString() + "','" + lon.getText().toString() + "',5) ");
            Toast.makeText(this, "Added Successfully", Toast.LENGTH_LONG).show();
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
            finish();


        }catch (Exception e){

            Toast.makeText(this,"Msgg="+e.getMessage(), Toast.LENGTH_LONG).show();

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
