package com.example.zaber;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreItemListAdapter extends ArrayAdapter<String> {
    private  final Activity context;
    private  final String[] progNames;
    private  final Integer[] progImages;

    public StoreItemListAdapter(Activity context, String[] progNames, Integer[] progImages){
        super(context,R.layout.single_store,progNames);
        this.context=context;
        this.progNames=progNames;
        this.progImages=progImages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.single_item,null,true);
        TextView textView = (TextView) rowView.findViewById(R.id.item_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.item_image);

        textView.setText(progNames[position]);
        imageView.setImageResource(progImages[position]);

        return  rowView;
    }
}
