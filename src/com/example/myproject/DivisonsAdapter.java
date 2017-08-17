package com.example.myproject;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DivisonsAdapter extends BaseAdapter{

    private Context context;
	private List<Divisions> div;
	private LayoutInflater inflater;
	
	public DivisonsAdapter(Context context,List<Divisions> div){
		
		this.context=context;
		this.div=div;
	
		inflater=LayoutInflater.from(this.context);
	}
	
	public static class ViewHolder{
		TextView txtview;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return div.size();
		
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return div.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
		// determines the position of the item that is to be displayed
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder viewholder;
		if(convertView==null)
		{
			viewholder=new ViewHolder();
			convertView=inflater.inflate(R.layout.list_item, null);
			viewholder.txtview=(TextView)convertView.findViewById(R.id.JsonText);
			convertView.setTag(viewholder);
		}
		else
		{
			viewholder=(ViewHolder) convertView.getTag();
		}
		
		viewholder.txtview.setText(div.get(position).Name);
		
		
		return convertView;
	}

}
