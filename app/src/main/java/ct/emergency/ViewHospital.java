package ct.emergency;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class ViewHospital extends AppCompatActivity {
	private ArrayList<String> itemPricelist = new ArrayList<String>();
	private String pname;
	private String listitemname;
	String date, dir1, cast1, year1;
	private int piece;
	String cost, rating, desc;
	ArrayList al = new ArrayList();
	ArrayList al1 = new ArrayList();
	private ImageView imageView;
	Object[] title, title1;
	JSONArray json1 = null, json2 = null;
	String[] titles, titles1;
	Button eWaranty;
	Spinner spinner;
	String lat, lon, times, dt, did, at, dnm;
	String unm, hid, catgry, hnm, city;
	private ProgressDialog pDialog, pDialog1, pDialog2;
String adrs;


	private static final String TAG_SUCCESS = "success";
	int sts;


	private FloatingActionButton fab, fab1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewhospitals);
		Bundle b = getIntent().getExtras();

		lat=b.getString("lat");
		lon=b.getString("lon");
		adrs=b.getString("address");


		setTitle("Hospital Details");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		TextView hosp = (TextView) findViewById(R.id.hosp);

		TextView service = (TextView) findViewById(R.id.service);

		TextView emergency = (TextView) findViewById(R.id.emergency);

		TextView ambulance = (TextView) findViewById(R.id.ambulance);

		TextView policy = (TextView) findViewById(R.id.policy);

		TextView cno = (TextView) findViewById(R.id.cno);

		TextView email = (TextView) findViewById(R.id.email);
		TextView adrs = (TextView) findViewById(R.id.adrs);


		hosp.setText(b.getString("name"));
		service.setText(b.getString("service"));
		emergency.setText(b.getString("emergency"));
		ambulance.setText(b.getString("ambulance"));
		policy.setText(b.getString("policy"));
		cno.setText(b.getString("contact"));
		email.setText(b.getString("email"));
		adrs.setText(b.getString("address"));

	}

	public void map(View view) {


	String map = "http://maps.google.co.in/maps?q="+adrs;
	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
	startActivity(intent);
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
