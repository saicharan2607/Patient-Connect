package ct.emergency;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddPharmacy extends AppCompatActivity {

    SQLiteDatabase db;
    RadioGroup service;
    RadioButton servicevalue;


    EditText name;
    EditText ph;
    EditText lat;
    EditText lon;
    EditText addr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpharmacy);
        setTitle("Add Pharmacy");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db=openOrCreateDatabase("healthcare", MODE_PRIVATE,null);
        db.execSQL("create table if not exists pharmacy(name varchar,service varchar,address varchar,contact varchar,lat varchar,long varchar,rating varchar)");
        service=(RadioGroup)findViewById(R.id.hrs);

        name=(EditText)findViewById(R.id.name);

        ph=(EditText)findViewById(R.id.contact);
        lat=(EditText)findViewById(R.id.lat);
        lon=(EditText)findViewById(R.id.lon);
        addr=(EditText)findViewById(R.id.address);



    }
    public void addpharmacy(View v){
        String name1=name.getText().toString();
        int selectedId = service.getCheckedRadioButtonId();
        servicevalue = (RadioButton)findViewById(selectedId);


        try {

            //System.out.println("insert into hospital values('" + name1 + "','" + servicevalue.getText().toString() + "','" + emergencyvalue.getText().toString() + "','" + ambulancevalue.getText().toString() + "','" + policyvalue.getText().toString() + "','" + ph.getText().toString() + "','" + email.getText().toString() + "','" + addr.getText().toString() + "','" + lat.getText().toString() + "','" + lon.getText().toString() + "' ) ");

            db.execSQL("insert into pharmacy values('" + name1 + "','" + servicevalue.getText().toString() + "','" + addr.getText().toString() + "','" + ph.getText().toString() + "','" + lat.getText().toString() + "','" + lon.getText().toString() + "',5) ");
            Toast.makeText(this, "Added Successfully", Toast.LENGTH_LONG).show();
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
            finish();


        }catch (Exception e){

            //Toast.makeText(this,"Msgg="+e.getMessage(), Toast.LENGTH_LONG).show();

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
