package mobileapplocker.joseph.com.mobileapplocker.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import mobileapplocker.joseph.com.mobileapplocker.R;
import mobileapplocker.joseph.com.mobileapplocker.models.AppDetails;

@SuppressLint("ResourceAsColor")
public class AppListAdapter extends BaseAdapter {

    private Context ctx;
    private List<String> lockAppList;
    private List<AppDetails> installedAppList;

    public AppListAdapter(Context ctx, List<String> lockAppList, List<AppDetails> installedAppList) {

        this.ctx = ctx;
        this.lockAppList = lockAppList;
        this.installedAppList = installedAppList;

    }

    @Override
    public int getCount() {

        return installedAppList.size();
    }

    @Override
    public Object getItem(int position) {

        return installedAppList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return installedAppList.indexOf(getItem(position));
    }

    @SuppressLint({"ResourceAsColor", "NewApi"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;



        if (convertView == null) {

            LayoutInflater mInflater = LayoutInflater.from(ctx);
            convertView = mInflater.inflate(R.layout.app_list_item, parent, false);

            convertView = mInflater.inflate(R.layout.app_list_item, null);
            holder = new ViewHolder();

            holder.txtTitle = (TextView) convertView.findViewById(R.id.txt_appName);
            holder.mImgIcon = (ImageView) convertView.findViewById(R.id.img_icon);
            holder.mImgLock = (ImageView) convertView.findViewById(R.id.img_lock);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        AppDetails appDetails = (AppDetails) getItem(position);
        holder.txtTitle.setText(appDetails.getAppName());
        holder.mImgIcon.setImageDrawable(appDetails.getIcon());

        if (lockAppList.contains(appDetails.getPackname()))
        {
           holder.mImgLock.setImageResource(R.drawable.lock_closed_green);
        } else {

            holder.mImgLock.setImageResource(R.drawable.lock_open_grey);
        }


        return convertView;
    }

    public Bitmap StringToBitMap(String image) {
        try {
            byte[] encodeByte = Base64.decode(image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void passUpdatedList(List<String> lockAppList) {

        this.lockAppList = lockAppList;
    }


    /* private view holder class */
    private class ViewHolder {
        TextView txtTitle;
        ImageView mImgIcon, mImgLock;

    }

}
