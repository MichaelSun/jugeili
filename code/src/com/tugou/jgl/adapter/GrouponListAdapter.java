package com.tugou.jgl.adapter;

import com.plugin.common.view.WebImageView;
import com.tugou.jgl.R;
import com.tugou.jgl.api.GetListGroupResponse.GroupInfo;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GrouponListAdapter extends ImageListTypeBaseAdapter {

    private LayoutInflater mLayoutInflater;
    private GroupInfo[] groupInfo;

    public GrouponListAdapter(LayoutInflater inflater, GroupInfo[] groupInfo) {
        mLayoutInflater = inflater;
        this.groupInfo = groupInfo;
    }

    @Override
    void onHide() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getCount() {
        return groupInfo.length;
    }

    @Override
    public Object getItem(int position) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getItemId(int position) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = convertView;
        ViewHolder holder = null;
        if (ret == null) {
            ret = mLayoutInflater.inflate(R.layout.sublist_item, null);
            holder = new ViewHolder();
            holder.coverImageView = (WebImageView) ret.findViewById(R.id.cover);
            holder.tv_title = (TextView)ret.findViewById(R.id.title);
            ret.setTag(holder);
        } else {
            holder = (ViewHolder) ret.getTag();
        }

        
        holder.coverImageView.setImageURI(new Uri.Builder()
                      .path("http://ww4.sinaimg.cn/bmiddle/6aadc52dgw1e827avbgzyj21kw11x129.jpg").build());
        holder.tv_title.setText(groupInfo[position].name);

        return ret;
    }

    private static class ViewHolder {
        public WebImageView coverImageView;
        TextView tv_title;
    }

}
