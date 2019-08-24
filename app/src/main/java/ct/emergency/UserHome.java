package ct.emergency;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class UserHome extends AppCompatActivity {

    CardView adhosp,addoc,adph,addig;
    Intent i1,i2,i3,i4,i5,i6,i7,i8,i9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhome);

    }

    public void viewhosp(View view){
Intent i=new Intent(UserHome.this,ViewHospDetails.class);
        startActivity(i);

    }

    public void pharmacy(View view){
        Intent i=new Intent(UserHome.this,PharmacyList.class);
        startActivity(i);

    }

    public void diagnostic(View view){
        Intent i=new Intent(UserHome.this,DiagnosticsList.class);
        startActivity(i);

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
                Intent i=new Intent(UserHome.this,MainActivity.class);
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
