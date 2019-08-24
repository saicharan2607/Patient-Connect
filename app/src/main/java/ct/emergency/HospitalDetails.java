package ct.emergency;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class HospitalDetails extends AppCompatActivity implements AdapterView.OnItemClickListener{
	// Log tag
	private static final String TAG = MainActivity.class.getSimpleName();
	private ProgressDialog  pDialog1, pDialog2;
	// Movies json url
	private ProgressDialog pDialog;
	String unm,search,lang,count;
	private List<HospitalView> hospList = new ArrayList<HospitalView>();
	private ListView listView;
	private CustomListAdapter adapter;
	TextView tv;
	ArrayList<HospitalView> sl;
	SQLiteDatabase db;
	boolean stss;
	String name="he";
	boolean stsss;
	int sts;
	int idt=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hospitaldetails);
		tv=(TextView)findViewById(R.id.textView);
		tv.setVisibility(View.GONE);
		listView = (ListView) findViewById(R.id.list);
		db=openOrCreateDatabase("healthcare", MODE_PRIVATE,null);
		db.execSQL("create table if not exists hospital(name varchar,service varchar,emergency varchar,ambulance varchar,policy varchar,contact varchar,email varchar,address varchar,lat varchar,long varchar,rating varchar)");

		//Toast.makeText(HospitalDetails.this,"create",Toast.LENGTH_SHORT).show();
		new details().execute();
		listView.setOnItemClickListener(this);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);


	}


	class details extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog1 = new ProgressDialog(HospitalDetails.this);
			pDialog1.setMessage("Loading ...");
			pDialog1.setIndeterminate(false);
			pDialog1.setCancelable(false);
			pDialog1.show();
		}

		/**
		 * getting Inbox JSON
		 */
		protected String doInBackground(String... args) {
			// Building Parameters


			try {
				String selectQuery = "SELECT *FROM hospital";
				Cursor cursor = db.rawQuery(selectQuery, null);
				////while (cursor.moveToNext()) {
					// name = cursor.getString(cursor.getColumnIndex("service"));
					sts=4;
				//}
				if (cursor != null) {

					if (cursor.moveToFirst()) {
						do {

					String name = cursor.getString(cursor.getColumnIndex("name"));
					String service = cursor.getString(cursor.getColumnIndex("service"));
					String emergency = cursor.getString(cursor.getColumnIndex("emergency"));
					String ambulance = cursor.getString(cursor.getColumnIndex("ambulance"));
					String policy = cursor.getString(cursor.getColumnIndex("policy"));
					String contact = cursor.getString(cursor.getColumnIndex("contact"));
					String email = cursor.getString(cursor.getColumnIndex("email"));
					String address = cursor.getString(cursor.getColumnIndex("address"));
					String lat = cursor.getString(cursor.getColumnIndex("lat"));
					String lon = cursor.getString(cursor.getColumnIndex("long"));

					HospitalView hv = new HospitalView();
					hv.setTitle(name);
					hv.setService(service);
					hv.setEmergency(emergency);
					hv.setAmbulance(ambulance);
					hv.setPolicy(policy);
					hv.setContact(contact);
					hv.setEmail(email);
					hv.setAddress(address);
					hv.setLat(lat);
					hv.setLon(lon);
					hospList.add(hv);


						} while (cursor.moveToNext());

					} else {



					}



			} }catch (Exception e) {
//				Toast.makeText(HospitalDetails.this,"doo",Toast.LENGTH_SHORT).show();
				//e.printStackTrace();
				name=e.getMessage();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 **/
		protected void onPostExecute(String file_url) {

			adapter = new CustomListAdapter(HospitalDetails.this, hospList);
			listView.setAdapter(adapter);
			pDialog1.dismiss();


		}
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {

		HospitalView  p = (HospitalView) hospList.get(position);
		String name=p.getTitle();
		String service=p.getService();
		String emergency=p.getEmergency();
		String ambulance=p.getAmbulance();
		String policy=p.getPolicy();
		String contact=p.getContact();
		String email=p.getEmail();
		String address=p.getAddress();
		String lat=p.getLat();
		String lon=p.getLon();

		Intent intent=new Intent(HospitalDetails.this,ViewHospital.class);
		intent.putExtra("name",name);
		intent.putExtra("service",service);
		intent.putExtra("emergency",emergency);
		intent.putExtra("ambulance",ambulance);
		intent.putExtra("policy",policy);
		intent.putExtra("contact",contact);
		intent.putExtra("email",email);
		intent.putExtra("address",address);
		intent.putExtra("lat",lat);
		intent.putExtra("lon",lon);
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









