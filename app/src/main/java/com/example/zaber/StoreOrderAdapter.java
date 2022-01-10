package com.example.zaber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StoreOrderAdapter extends RecyclerView.Adapter<StoreOrderAdapter.StoreOrderViewHolder> {

    ArrayList<String[]> orderItems;
    ArrayList<Order> order;
    String user;
    Context context;
    int currentNo;

    public StoreOrderAdapter(Context context, ArrayList<String[]> orderItems, String user , ArrayList<Order> order) {
        this.orderItems = orderItems;
        this.user = user;
        this.order = order;
        this.context = context;

        currentNo = 1;
    }

    @NonNull
    @Override
    public StoreOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_row, parent, false);
        return new StoreOrderViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreOrderViewHolder holder, int position) {
        String showNo = "No." + currentNo++;
        String status = "製作中";
        StringBuilder orderText = new StringBuilder();

        String[] orderItem = orderItems.get(position);
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
        return orderItems.size();
    }
    public Order getOrder(int pos){return order.get(pos); };

    public class StoreOrderViewHolder extends RecyclerView.ViewHolder {

        AppCompatButton order_delay, order_complete;
        TextView order_item, order_no, order_status;
        private StoreOrderAdapter adapter;

        public StoreOrderViewHolder(@NonNull View itemView) {
            super(itemView);

            // Get Row Elements ID
            order_delay = itemView.findViewById(R.id.order_delay);
            order_complete = itemView.findViewById(R.id.order_complete);
            order_item = itemView.findViewById(R.id.order_item);
            order_no = itemView.findViewById(R.id.order_no);
            order_status = itemView.findViewById(R.id.order_status);

            order_complete.setOnClickListener(view -> {
                System.out.println("Remove Index: " + getAbsoluteAdapterPosition());
                System.out.println("Remove Index: " + getBindingAdapterPosition());
                removeOrder(getAbsoluteAdapterPosition());
                adapter.orderItems.remove(getAbsoluteAdapterPosition());
                adapter.notifyItemRemoved(getAbsoluteAdapterPosition());
            });
        }

        public StoreOrderViewHolder linkAdapter(StoreOrderAdapter adapter) {
            this.adapter = adapter;
            return this;
        }

        public void removeOrder(int pos){
            DatabaseReference root = FirebaseDatabase.getInstance().getReference();
            Order od = getOrder(pos);
            root.child("store").child(od.getUser()).setValue(od);
            //root.child("users").child(od.getUser()).removeValue();
        }
    }
}
