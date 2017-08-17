package com.example.myproject;

import java.util.List;

import com.example.myproject.LeagueTableDivisionsAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class sponsorsAdapter extends BaseAdapter {
	
	 private Context context;
		private List<sponsors> spon;
		private LayoutInflater inflater;
		
		public sponsorsAdapter(Context context,List<sponsors> spon){
			
			this.context=context;
			this.spon=spon;
		
			inflater=LayoutInflater.from(this.context);
		}
		
		public static class ViewHolder{
			TextView txtview;
			ImageView imageview;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
		 return spon.size();
			
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return spon.get(position);
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
				convertView=inflater.inflate(R.layout.sponsors_listitem, null);
				viewholder.imageview=(ImageView)convertView.findViewById(R.id.sponsorimage);
				viewholder.txtview=(TextView)convertView.findViewById(R.id.sponsortext);
				
				convertView.setTag(viewholder);
			}
			else
			{
				viewholder=(ViewHolder) convertView.getTag();
			}
			
		//	viewholder.txtview.setText(spon.get(position).Name);
			
			
			return convertView;
		}


}
