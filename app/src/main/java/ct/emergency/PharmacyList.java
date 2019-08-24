package ct.emergency;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class PharmacyList extends AppCompatActivity implements AdapterView.OnItemClickListener{
	// Log tag
	private static final String TAG = MainActivity.class.getSimpleName();
	private ProgressDialog  pDialog1, pDialog2;
	private List<PharmacyView> stdList = new ArrayList<PharmacyView>();
	private ListView listView;
	private CustomListAdapter2 adapter;
	JSONArray json1 = null, json2 = null;
	TextView tv;
	ArrayList<PharmacyView> sl,s2;
	boolean stss;
	boolean stsss;
	int sts;
	int idt=0;
	Location location;
	double latitude;
	double longitude;
	double dist;
	double mLatitude=0;
	double mLongitude=0;
	SQLiteDatabase db;
	Spinner mySpinner;
	List<String> spe;
	String[] categories={"All","Distance","Rating"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pharmacylist);
		tv=(TextView)findViewById(R.id.textView);
		tv.setVisibility(View.GONE);
		setTitle("Pharmacy List");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		listView = (ListView) findViewById(R.id.list);
		mySpinner =(Spinner)findViewById(R.id.mySpinner);
		db=openOrCreateDatabase("healthcare", MODE_PRIVATE,null);
		db.execSQL("create table if not exists hospital(name varchar,service varchar,emergency varchar,ambulance varchar,policy varchar,contact varchar,email varchar,address varchar,lat varchar,long varchar,rating varchar)");
		GPSTracker gps = new GPSTracker(PharmacyList.this);
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
					Toast.makeText(PharmacyList.this, "Selected Category Does not Exist!", Toast.LENGTH_SHORT).show();
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
			adapter = new CustomListAdapter2(PharmacyList.this, stdList);
		}
		else if(categoryID == 1) {
				idt=1;
				for(int i=0;i<stdList.size();i++){
					String lat=stdList.get(i).getLat();
					String lon=stdList.get(i).getLon();
					Location dlocation=new Location("");
					dlocation.setLatitude(Double.parseDouble(lat));
					dlocation.setLongitude(Double.parseDouble(lon));
					dist=distance(latitude, longitude,Double.parseDouble(lat),Double.parseDouble(lon));
			//		Toast.makeText(ViewHospDetails.this,"dist="+dist,Toast.LENGTH_SHORT).show();


					if(dist<5){
						sl.add(stdList.get(i));
					}
				}


			adapter = new CustomListAdapter2(PharmacyList.this, sl);

		}
		else {
			idt=2;
			//Toast.makeText(ViewHospDetails.this,"idt="+idt,Toast.LENGTH_SHORT).show();

			String selectQuery = "SELECT *FROM pharmacy order by rating DESC";
			Cursor cursor = db.rawQuery(selectQuery, null);
			if (cursor != null) {

				if (cursor.moveToFirst()) {
					do {
						String name = cursor.getString(cursor.getColumnIndex("name"));
						String service = cursor.getString(cursor.getColumnIndex("service"));
						String contact = cursor.getString(cursor.getColumnIndex("contact"));
						String address = cursor.getString(cursor.getColumnIndex("address"));
						String lat = cursor.getString(cursor.getColumnIndex("lat"));
						String lon = cursor.getString(cursor.getColumnIndex("long"));

						PharmacyView hv = new PharmacyView();
						hv.setName(name);
						hv.setService(service);
						hv.setContact(contact);
						hv.setAddress(address);
						hv.setLat(lat);
						hv.setLon(lon);
						/*Location dlocation=new Location("");
						dlocation.setLatitude(Double.parseDouble(lat));
						dlocation.setLongitude(Double.parseDouble(lon));
						dist=location.distanceTo(dlocation);*/
						double km=distance(latitude, longitude,Double.parseDouble(lat),Double.parseDouble(lon));
						hv.setDistance(Math.round(km));
						s2.add(hv);


					} while (cursor.moveToNext());

				}


			}

			adapter = new CustomListAdapter2(PharmacyList.this, s2);
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
			pDialog1 = new ProgressDialog(PharmacyList.this);
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


				String selectQuery = "SELECT *FROM pharmacy";
				Cursor cursor = db.rawQuery(selectQuery, null);
				if (cursor != null) {

					if (cursor.moveToFirst()) {
						do {

							String name = cursor.getString(cursor.getColumnIndex("name"));
							String service = cursor.getString(cursor.getColumnIndex("service"));
							String contact = cursor.getString(cursor.getColumnIndex("contact"));
							String address = cursor.getString(cursor.getColumnIndex("address"));
							String lat = cursor.getString(cursor.getColumnIndex("lat"));
							String lon = cursor.getString(cursor.getColumnIndex("long"));

							PharmacyView hv = new PharmacyView();
							hv.setName(name);
							hv.setService(service);
							hv.setContact(contact);
							hv.setAddress(address);
							hv.setLat(lat);
							hv.setLon(lon);
							/*Location dlocation=new Location("");
							dlocation.setLatitude(Double.parseDouble(lat));
							dlocation.setLongitude(Double.parseDouble(lon));
							dist=location.distanceTo(dlocation);*/

							double km=distance(latitude, longitude,Double.parseDouble(lat),Double.parseDouble(lon));
							hv.setDistance(Math.round(km));

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

			adapter = new CustomListAdapter2(PharmacyList.this, stdList);
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

		PharmacyView p=null;
		if(idt==1){
			p=(PharmacyView)sl.get(position);
		}
		else if(idt==2){
			p=(PharmacyView)s2.get(position);
		}
		else {
			p = (PharmacyView) stdList.get(position);
		}

		String name=p.getName();
		String service=p.getService();
		String contact=p.getContact();
		String address=p.getAddress();
		String lat=p.getLat();
		String lon=p.getLon();

		Intent intent=new Intent(PharmacyList.this,PharmacyInfo.class);
		intent.putExtra("name",name);
		intent.putExtra("service",service);
		intent.putExtra("contact",contact);
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

	private double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1))
				* Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1))
				* Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		return (dist);
	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}



}










