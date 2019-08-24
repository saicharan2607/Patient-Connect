package ct.emergency;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;


public class PharmacyInfo extends AppCompatActivity {
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
	String adrs, pharnm, catgry, hnm, city;
	private ProgressDialog pDialog, pDialog1, pDialog2;



	private static final String TAG_SUCCESS = "success";
	int sts;


	private FloatingActionButton fab, fab1;
	SQLiteDatabase db;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pharmacyinfo);
		Bundle b = getIntent().getExtras();

		lat=b.getString("lat");
		lon=b.getString("lon");
		adrs=b.getString("address");
		pharnm=b.getString("name");
		db=openOrCreateDatabase("healthcare", MODE_PRIVATE, null);


		setTitle("Pharmacy Details");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		TextView pnm = (TextView) findViewById(R.id.pnm);

		TextView service = (TextView) findViewById(R.id.service);
		TextView cno = (TextView) findViewById(R.id.cno);
		TextView adrs = (TextView) findViewById(R.id.adrs);


		pnm.setText(b.getString("name"));
		service.setText(b.getString("service"));
		cno.setText(b.getString("contact"));
		adrs.setText(b.getString("address"));

	}

	public void map(View view) {


	String map = "http://maps.google.co.in/maps?q="+adrs;
	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
	startActivity(intent);
}


public void feedback(View view){

	AlertDialog.Builder builder = new AlertDialog.Builder(PharmacyInfo.this);
	View layout= null;
	LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	layout = inflater.inflate(R.layout.rating, null);
	final RatingBar ratingBar = (RatingBar)layout.findViewById(R.id.ratingBar);
	builder.setTitle("Rate Us");
	builder.setMessage("Thank you for rating us , it will help us to provide you the best service .");
	builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int id) {
			float rating2 = ratingBar.getRating();
			 int rating=0;

			//Toast.makeText(HospitalInfo.this,"Rating is : "+value,Toast.LENGTH_LONG).show();

			String selectQuery = "SELECT rating FROM pharmacy where name='" + pharnm + "' ";
			Cursor cursor = db.rawQuery(selectQuery, null);

			if (cursor != null) {

				if (cursor.moveToFirst()) {
					do {

						rating = Integer.parseInt(cursor.getString(cursor.getColumnIndex("rating")));


					} while (cursor.moveToNext());

				}
			}
			int totrate=(int)(rating+rating2)/2;
			db.execSQL("update pharmacy set rating="+totrate+" where name='"+pharnm+"'");
			//Toast.makeText(PharmacyInfo.this,"rate="+totrate,Toast.LENGTH_SHORT).show();



		}
	});
	builder.setNegativeButton("No,Thanks", new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
		}
	});
	builder.setCancelable(false);
	builder.setView(layout);
	builder.show();

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
