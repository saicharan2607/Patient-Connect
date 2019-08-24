package ct.emergency;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DoctCustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<DoctorsView> Items;

	public DoctCustomListAdapter(Activity activity, List<DoctorsView> movieItems) {
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
			convertView = inflater.inflate(R.layout.list_row1, null);

		TextView docnm = (TextView) convertView.findViewById(R.id.docnm);
		TextView spe = (TextView) convertView.findViewById(R.id.spe);
		TextView timings = (TextView) convertView.findViewById(R.id.timings);
		TextView contact = (TextView) convertView.findViewById(R.id.contact);



		DoctorsView m = Items.get(position);


		docnm.setText("Dr."+m.getDocnm());
		spe.setText("Specialist In : "+m.getSpecialist());
		timings.setText("Timings : "+m.getTimings());
		contact.setText("Contact No : "+m.getContact());


		return convertView;
	}

}