package com.example.zaber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

public class StoreOrderAdapter extends RecyclerView.Adapter<StoreOrderAdapter.StoreOrderViewHolder> {

    String[][] orderItems;
    String user;
    Context context;
    int currentNo;

    public StoreOrderAdapter(Context context, String[][] orderItems, String user) {
        this.orderItems = orderItems;
        this.user = user;
        this.context = context;

        currentNo = 1;
    }

    @NonNull
    @Override
    public StoreOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_row, parent, false);
        return new StoreOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreOrderViewHolder holder, int position) {
        String showNo = "No." + currentNo++;
        String status = "製作中";
        StringBuilder orderText = new StringBuilder();

        String[] orderItem = orderItems[position];
        for (int i = 0, orderItemLength = orderItem.length; i < orderItemLength; i++) {
            String s = orderItem[i];
            orderText.append(i + 1).append(". ").append(s).append("\n");
        }

        holder.order_no.setText(showNo);
        holder.order_status.setText(status);
        holder.order_item.setText(orderText.toString());
    }

    @Override
    public int getItemCount() {
        return orderItems.length;
    }

    public class StoreOrderViewHolder extends RecyclerView.ViewHolder {

        AppCompatButton order_delay, order_complete;
        TextView order_item, order_no, order_status;

        public StoreOrderViewHolder(@NonNull View itemView) {
            super(itemView);

            // Get Row Elements ID
            order_delay = itemView.findViewById(R.id.order_delay);
            order_complete = itemView.findViewById(R.id.order_complete);
            order_item = itemView.findViewById(R.id.order_item);
            order_no = itemView.findViewById(R.id.order_no);
            order_status = itemView.findViewById(R.id.order_status);
        }
    }
}
