package com.tugou.jgl.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.plugin.common.view.WebImageView;
import com.tugou.jgl.R;

/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 13-8-28
 * Time: PM2:38
 * To change this template use File | Settings | File Templates.
 */
public class SubListAdater extends ImageListTypeBaseAdapter {

    private LayoutInflater mLayoutInflater;

    public SubListAdater(LayoutInflater inflater) {
        mLayoutInflater = inflater;
    }

    @Override
    void onHide() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getCount() {
        return 10;
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
            ret.setTag(holder);
        } else {
            holder = (ViewHolder) ret.getTag();
        }

        holder.coverImageView.setImageURI(new Uri.Builder()
                      .path("http://ww4.sinaimg.cn/bmiddle/6aadc52dgw1e827avbgzyj21kw11x129.jpg").build());

        return ret;
    }

    private static class ViewHolder {
        public WebImageView coverImageView;
    }
}
