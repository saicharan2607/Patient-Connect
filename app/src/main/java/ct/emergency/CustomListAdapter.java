package ct.emergency;



import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<HospitalView> Items;

	public CustomListAdapter(Activity activity, List<HospitalView> movieItems) {
		this.activity = activity;
		this.Items = movieItems;
	}

	@Override
	public int getCount() {
		return Items.size();
	}

	@Override
	public Object getItem(int location) {
		return Items.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_row, null);

		TextView title = (TextView) convertView.findViewById(R.id.hosp);
		TextView service = (TextView) convertView.findViewById(R.id.service);
		TextView emergency = (TextView) convertView.findViewById(R.id.emergency);
		TextView ambulance = (TextView) convertView.findViewById(R.id.ambulance);
		TextView policy = (TextView) convertView.findViewById(R.id.policy);


		HospitalView m = Items.get(position);


		title.setText(m.getTitle());
		service.setText("Service : "+m.getService());
		emergency.setText("Emergency : "+m.getEmergency());
		ambulance.setText("Ambulance : "+m.getAmbulance());
		policy.setText("Health Policy : "+m.getPolicy());

		return convertView;
	}

}