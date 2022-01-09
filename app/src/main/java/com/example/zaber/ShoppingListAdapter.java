package com.example.zaber;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShoppingListAdapter extends ArrayAdapter<String>{
    private  final Activity context;
    private  final String[] progNames;

    public ShoppingListAdapter(Activity context, String[] progNames){
        super(context,R.layout.single_orderitem,progNames);
        this.context=context;
        this.progNames=progNames;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.single_orderitem,null,true);
        TextView textView = (TextView) rowView.findViewById(R.id.order_item_name);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.);

        textView.setText(progNames[position]);
        //imageView.setImageResource(progImages[position]);

        return  rowView;
    }
}
