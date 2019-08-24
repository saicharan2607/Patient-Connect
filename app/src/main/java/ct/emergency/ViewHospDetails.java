package ct.emergency;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
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
import android.widget.GridLayout;
import android.widget.LinearLayout;
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

public class ViewHospDetails extends AppCompatActivity implements AdapterView.OnItemClickListener{
	// Log tag
	private static final String TAG = MainActivity.class.getSimpleName();
	private ProgressDialog  pDialog1, pDialog2;
	private List<HospitalView> stdList = new ArrayList<HospitalView>();
	private ListView listView;
	private CustomListAdapter adapter;
	JSONArray json1 = null, json2 = null;
	TextView tv;
	ArrayList<HospitalView> sl,s2;
	boolean stss;
	boolean stsss;
	int sts;
	int idt=0;
	Location location;
	double latitude;
	double longitude;
	float dist;
	double mLatitude=0;
	double mLongitude=0;
	SQLiteDatabase db;
	Spinner mySpinner;
	List<String> spe;
	String[] categories={"All","Distance","Rating"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewhospitaldetails);
		tv=(TextView)findViewById(R.id.textView);
		tv.setVisibility(View.GONE);
		setTitle("Hospitals List");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		listView = (ListView) findViewById(R.id.list);
		mySpinner =(Spinner)findViewById(R.id.mySpinner);
		db=openOrCreateDatabase("healthcare", MODE_PRIVATE,null);
		db.execSQL("create table if not exists hospital(name varchar,service varchar,emergency varchar,ambulance varchar,policy varchar,contact varchar,email varchar,address varchar,lat varchar,long varchar,rating varchar)");
		GPSTracker gps = new GPSTracker(ViewHospDetails.this);
		// check if GPS enabled
		if(gps.canGetLocation()){

			latitude = gps.getLatitude();
			longitude = gps.getLongitude();
			location=gps.getLocation();
				}else{
				gps.showSettingsAlert();
		}




		mySpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories));
		mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long itemID) {
				if (position >= 0 && position < categories.length) {
					getSelectedCategoryData(position);
				} else {
					Toast.makeText(ViewHospDetails.this, "Selected Category Does not Exist!", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});


		Intent intent=getIntent();
		Bundle b=intent.getExtras();
		//unm  = b.getString("unm");
		new details().execute();
		listView.setOnItemClickListener(this);

	}
	private void getSelectedCategoryData(int categoryID) {
		//arraylist to hold selected cosmic bodies
	 sl = new ArrayList<>();
		s2 = new ArrayList<>();
		if(categoryID == 0)
		{idt=0;
			adapter = new CustomListAdapter(ViewHospDetails.this, stdList);
		}
		else if(categoryID == 1) {
				idt=1;
				for(int i=0;i<stdList.size();i++){
					String lat=stdList.get(i).getLat();
					String lon=stdList.get(i).getLon();
					Location dlocation=new Location("");
					dlocation.setLatitude(Double.parseDouble(lat));
					dlocation.setLongitude(Double.parseDouble(lon));
					dist=location.distanceTo(dlocation);
			//		Toast.makeText(ViewHospDetails.this,"dist="+dist,Toast.LENGTH_SHORT).show();


					if(dist<1800){
						sl.add(stdList.get(i));
					}
				}


			adapter = new CustomListAdapter(ViewHospDetails.this, sl);

		}
		else {
			idt=2;
			//Toast.makeText(ViewHospDetails.this,"idt="+idt,Toast.LENGTH_SHORT).show();

			String selectQuery = "SELECT *FROM hospital order by rating DESC";
			Cursor cursor = db.rawQuery(selectQuery, null);
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
						s2.add(hv);


					} while (cursor.moveToNext());

				}


			}

			adapter = new CustomListAdapter(ViewHospDetails.this, s2);
		}
			listView.setAdapter(adapter);



	}


	class details extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog1 = new ProgressDialog(ViewHospDetails.this);
			pDialog1.setMessage("Loading ...");
			pDialog1.setIndeterminate(false);
			pDialog1.setCancelable(false);
			pDialog1.show();
		}

		/**
		 * getting Inbox JSON
		 */
		protected String doInBackground(String... args) {
			//Log.d("JSON: ", json.toString());

			try {


				String selectQuery = "SELECT *FROM hospital";
				Cursor cursor = db.rawQuery(selectQuery, null);
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
							stdList.add(hv);


						} while (cursor.moveToNext());

					} else {

						sts = 0;

					}


				}
			}catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 **/
		protected void onPostExecute(String file_url) {

			adapter = new CustomListAdapter(ViewHospDetails.this, stdList);
			listView.setAdapter(adapter);
			pDialog1.dismiss();
if(sts==0){
	tv.setVisibility(View.VISIBLE);
	tv.setText("No Records Found");


}


		}


	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {

		HospitalView p=null;
		if(idt==1){
			p=(HospitalView)sl.get(position);
		}
		else if(idt==2){
			p=(HospitalView)s2.get(position);
		}
		else {
			p = (HospitalView) stdList.get(position);
		}

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

		Intent intent=new Intent(ViewHospDetails.this,HospitalInfo.class);
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










