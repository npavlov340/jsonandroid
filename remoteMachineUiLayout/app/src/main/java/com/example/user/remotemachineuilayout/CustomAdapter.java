package com.example.user.remotemachineuilayout;

/**
 * Created by User on 6/29/2017.
 */

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.app.Activity;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import static com.example.user.remotemachineuilayout.R.id.url;


public class CustomAdapter extends ArrayAdapter<String> {

    //private final Activity context;
    private final String[] titleList;
    private final String[] urlList;



    public CustomAdapter(Context context1, String[] title, String[] url) {
        super(context1, R.layout.custom_row, title);
        //this.context = context1;
        this.titleList = title;
        this.urlList = url;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rowView = inflater.inflate(R.layout.custom_row,parent,false);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(url);

        txtTitle.setText(titleList[position]);
        Picasso.with(this.getContext()).load(urlList[position]).into(imageView);


        return rowView;
    }
//    public CustomAdapter(@NonNull Context context, ArrayList<String> title, ArrayList<String> thumburl) {
//        super(context, R.layout.custom_row, title, thumburl);
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = LayoutInflater.from(getContext());
//        View customView = inflater.inflate(R.layout.custom_row, parent, false);
//
//        String index = getItem(position);
//
//
//        TextView title = (TextView) customView.findViewById(R.id.title);
//        ImageView image = (ImageView) customView.findViewById(url);
//
//
//        title.setText(index);
//        Picasso.with().load(thumburl).into(imageView);
//        image.setImageResource(R.mipmap.misfortune);
//
//        return customView;
//    }
}
