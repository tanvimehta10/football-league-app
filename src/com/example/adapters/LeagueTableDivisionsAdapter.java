package com.example.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myproject.Divisions_LeagueTable;
import com.example.myproject.R;

public class LeagueTableDivisionsAdapter extends BaseAdapter {

	private Context context;
	private List<Divisions_LeagueTable> div;
	private LayoutInflater inflater;

	public LeagueTableDivisionsAdapter(Context context,
			List<Divisions_LeagueTable> div) {

		this.context = context;
		this.div = div;

		inflater = LayoutInflater.from(this.context);
	}

	public static class ViewHolder {
		TextView txtview;
	}

	@Override
	public int getCount() {
		return div.size();
	}

	@Override
	public Object getItem(int position) {
		return div.get(position);
	}

	// determines the position of the item that is to be displayed
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewholder;
		if (convertView == null) {
			viewholder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_item, null);
			viewholder.txtview = (TextView) convertView
					.findViewById(R.id.JsonText);
			convertView.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) convertView.getTag();
		}
		viewholder.txtview.setText(div.get(position).Name);
		return convertView;
	}

}
