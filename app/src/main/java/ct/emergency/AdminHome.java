package ct.emergency;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class AdminHome extends AppCompatActivity {

    CardView adhosp,addoc,adph,addig;
    Intent i1,i2,i3,i4,i5,i6,i7,i8,i9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminhome);

        adhosp = (CardView) findViewById(R.id.adhosp);
        i1 = new Intent(this,AddHospital.class);
        adhosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i1);
            }
        });

        addoc = (CardView) findViewById(R.id.addoc);
        i2 = new Intent(this,AddDoctor.class);
        addoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i2);
            }
        });

        adph = (CardView) findViewById(R.id.adph);
        i3 = new Intent(this,AddPharmacy.class);
        adph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i3);
            }
        });

        addig = (CardView) findViewById(R.id.addig);
        i4 = new Intent(this,AddDiagnostic.class);
        addig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i4);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.logout:
                Intent i=new Intent(AdminHome.this,MainActivity.class);
                startActivity(i);
                finish();
                break;
            // action with ID action_settings was selected
            default:
                break;

        }
        return true;
    }



}
