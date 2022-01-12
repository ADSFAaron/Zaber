package com.example.zaber;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreListAdapter extends ArrayAdapter<String> {
    private  final Activity context;
    private  final String[] progNames;
    private  final Integer[] progImages;
    private  final String[] proTypes;
    private  final String[] proStarts;


    public StoreListAdapter(Activity context, String[] progNames, Integer[] progImages){
        super(context,R.layout.single_store,progNames);
        this.context=context;
        this.progNames=progNames;
        this.progImages=progImages;
        this.proTypes=new String[]{"便當","早午餐","飲料店","便當","水餃"};
        this.proStarts=new String[]{"4.5","3.8","3.0","2.8","4.0"};

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.single_store,null,true);
        TextView textView = (TextView) rowView.findViewById(R.id.store_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.store_image);
        TextView types=(TextView)rowView.findViewById(R.id.store_type);
        TextView starts=(TextView)rowView.findViewById(R.id.store_score);

        textView.setText(progNames[position]);
        imageView.setImageResource(progImages[position]);
        types.setText(proTypes[position]);
        starts.setText(proStarts[position]);

        return  rowView;
    }
}
