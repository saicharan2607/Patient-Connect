package ct.emergency;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddDoctor extends AppCompatActivity {

    SQLiteDatabase db;
     String specialist=null,hospnm=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adddoctor);
        setTitle("Add Doctor");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db=openOrCreateDatabase("healthcare", MODE_PRIVATE,null);
        db.execSQL("create table if not exists doctor(docnm varchar,hospnm varchar,spe varchar,timings varchar,contact varchar)");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> hosp = new ArrayList<String>();
        hosp.add("Select Hospital Name");
        db.execSQL("create table if not exists hospital(name varchar,service varchar,emergency varchar,ambulance varchar,policy varchar,contact varchar,email varchar,address varchar,lat varchar,long varchar)");
        String selectQuery = "SELECT * FROM hospital";
        Cursor cursor = db.rawQuery(selectQuery,null);
        while (cursor.moveToNext()) {
            String name= cursor.getString(cursor.getColumnIndex("name"));
            hosp.add(name);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hosp);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                hospnm= adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        List<String> spe = new ArrayList<String>();
        spe.add("Select Specialist");
        spe.add("Audiologist");
        spe.add("Cardiologist");
        spe.add("Dentist");
        spe.add("Dermatologist");
        spe.add("Epidemiologist");
        spe.add("Gynecologist");
        spe.add("Immunologist");
        spe.add("Microbiologist");
        spe.add("Neonatologist");
        spe.add("Neurologist");
        spe.add("Neurosurgeon");
        spe.add("Obstetrician");
        spe.add("Pediatrician");
        spe.add("Physiologist");
        spe.add("Psychiatrist");
        spe.add("Radiologist");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spe);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                specialist = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void adddoc(View v) {

        EditText name=(EditText)findViewById(R.id.name);
        EditText time=(EditText)findViewById(R.id.time);
        EditText cno=(EditText)findViewById(R.id.contact);
        String name1=name.getText().toString();
        String time1=time.getText().toString();
        String cno1=cno.getText().toString();



        db.execSQL("insert into doctor values('" + name1 + "','" + hospnm + "','" + specialist + "','" +time1+ "','" + cno1 + "'  ) ");
        Toast.makeText(this, "Added Successfully", Toast.LENGTH_LONG).show();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();




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
