package ct.emergency;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Doctorslist extends AppCompatActivity implements AdapterView.OnItemClickListener{
	// Log tag
	private static final String TAG = MainActivity.class.getSimpleName();
	private ProgressDialog  pDialog1, pDialog2;
	// Movies json url
	private ProgressDialog pDialog;
	String unm,search,lang,count;
	private List<DoctorsView> docList = new ArrayList<DoctorsView>();
	private ListView listView;
	private DoctCustomListAdapter adapter;
	TextView tv;
	ArrayList<HospitalView> sl;
	SQLiteDatabase db;
	boolean stss;
	String hospnm,spe;
	boolean stsss;
	int sts;
	int idt=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctorslist);
		tv=(TextView)findViewById(R.id.textView);
		tv.setVisibility(View.GONE);
		listView = (ListView) findViewById(R.id.list);
		db=openOrCreateDatabase("healthcare", MODE_PRIVATE,null);
		db.execSQL("create table if not exists doctor(docnm varchar,hospnm varchar,spe varchar,timings varchar,contact varchar)");

		Bundle b = getIntent().getExtras();
		hospnm=b.getString("hospnm");
		spe=b.getString("spe");
		new details().execute();
		listView.setOnItemClickListener(this);
		setTitle("Doctors List");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);


	}


	class details extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog1 = new ProgressDialog(Doctorslist.this);
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
				String selectQuery = "SELECT *FROM doctor where hospnm='"+hospnm+"' and spe='"+spe+"'";
				Cursor cursor = db.rawQuery(selectQuery, null);
				if (cursor != null) {

					if (cursor.moveToFirst()) {
						do {

					String docnm = cursor.getString(cursor.getColumnIndex("docnm"));
					String spe = cursor.getString(cursor.getColumnIndex("spe"));
					String timings = cursor.getString(cursor.getColumnIndex("timings"));
					String contact = cursor.getString(cursor.getColumnIndex("contact"));

					DoctorsView dv = new DoctorsView();
					dv.setDocnm(docnm);
					dv.setSpecialist(spe);
					dv.setTimings(timings);
					dv.setContact(contact);
					docList.add(dv);


						} while (cursor.moveToNext());

					} else {

sts=1;

					}



			} }catch (Exception e) {
//				Toast.makeText(HospitalDetails.this,"doo",Toast.LENGTH_SHORT).show();
				//e.printStackTrace();

			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 **/
		protected void onPostExecute(String file_url) {
			if(sts==1){
				tv.setVisibility(View.VISIBLE);
				tv.setText("No Records Found");
			}
			adapter = new DoctCustomListAdapter(Doctorslist.this, docList);
			listView.setAdapter(adapter);
			pDialog1.dismiss();


		}
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {

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









