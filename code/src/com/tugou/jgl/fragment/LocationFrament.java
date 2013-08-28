package com.tugou.jgl.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.tugou.jgl.R;
import com.tugou.jgl.activity.SubListActivity;

/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 13-8-15
 * Time: PM4:23
 * To change this template use File | Settings | File Templates.
 */
public class LocationFrament extends Fragment {
	private LayoutInflater mInflater;
	
    @Override  
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mInflater = inflater;
        View ret = inflater.inflate(R.layout.location, null);

        //test
//        ret.findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent();
//                i.setClass(getActivity().getApplicationContext(), SubListActivity.class);
//                getActivity().startActivity(i);
//            }
//        });
        GridView gv = (GridView)ret.findViewById(R.id.category);
        gv.setAdapter(new CategoryAdapter());

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                i.setClass(getActivity().getApplicationContext(), SubListActivity.class);
                getActivity().startActivity(i);
            }
        });

        return ret;
    }
    
    private class CategoryAdapter extends BaseAdapter{

        private Context context; 
        private Integer[] imgs = {
        	R.drawable.nearby_newbook, R.drawable.nearby_food, R.drawable.nearby_play,
        	R.drawable.nearby_movie, R.drawable.nearby_ktv, R.drawable.nearby_wash,
        	R.drawable.nearby_hotel, R.drawable.nearby_rewards, R.drawable.nearby_phone
        };
        
        private Integer[] names = {
        	R.string.new_book, R.string.food, R.string.play,
        	R.string.movie, R.string.ktv, R.string.wash,
        	R.string.hotel, R.string.rewards, R.string.phone
        };
        
//        CategoryAdapter(Context context){
//            this.context = context;	        	
//        }
        
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imgs.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if(convertView == null){
				convertView = mInflater.inflate(R.layout.category_grid_item, null);
				holder = new ViewHolder();
				holder.icon = (ImageView)convertView.findViewById(R.id.icon);
				holder.name = (TextView)convertView.findViewById(R.id.name);
				
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.icon.setImageResource(imgs[position]);
			holder.name.setText(names[position]);

			return convertView;
		}
		
		private class ViewHolder{
			ImageView icon;
			TextView name;
		}
    	
    }
}
