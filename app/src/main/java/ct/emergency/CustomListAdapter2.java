package ct.emergency;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter2 extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<PharmacyView> Items;

	public CustomListAdapter2(Activity activity, List<PharmacyView> movieItems) {
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
			convertView = inflater.inflate(R.layout.list_row2, null);

		TextView title = (TextView) convertView.findViewById(R.id.nm);
		TextView service = (TextView) convertView.findViewById(R.id.service);
		TextView dist = (TextView) convertView.findViewById(R.id.dist);


		PharmacyView m = Items.get(position);


		title.setText(m.getName());
		dist.setText("Distance : "+m.getDistance()+" km");
		service.setText("Service : "+m.getService());

		return convertView;
	}

}