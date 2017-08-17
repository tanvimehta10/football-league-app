package com.example.myproject;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class LatestNewsList_Adapter extends BaseAdapter {

		private Context context;
		private List<latestNewsList> news;
		private LayoutInflater inflater;
		
		 public LatestNewsList_Adapter(Context context,List<latestNewsList> news) {
			// TODO Auto-generated constructor stub
		
			
			this.context=context;
			this.news=news;
		
			inflater=LayoutInflater.from(this.context);
		}
		
		public static class ViewHolder{
			TextView txtview;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return news.size();
			
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return news.get(position);
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
				viewholder=(ViewHolder)convertView.getTag();
			}
			
			viewholder.txtview.setText(news.get(position).Title);
			
			
			return convertView;
		}
}
