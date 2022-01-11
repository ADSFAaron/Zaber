package com.example.zaber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderAdapter.HistoryOrderViewHolder> {

    ArrayList<Order> orderItems;
    Context context;

    public HistoryOrderAdapter(Context context, ArrayList<Order> orderItems) {
        this.orderItems = orderItems;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.history_order_row, parent, false);
        return new HistoryOrderAdapter.HistoryOrderViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryOrderViewHolder holder, int position) {

        Order order = orderItems.get(position);
        StringBuilder orderText = new StringBuilder();
        String showNo = "No." + order.no;
        ArrayList<String> orderItem = order.items;
        for (int i = 0, orderItemLength = orderItem.size(); i < orderItemLength; i++) {
            String s = orderItem.get(i);
            orderText.append(i + 1).append(". ").append(s).append("\n");
        }

        // Setting row item status
        holder.order_no.setText(showNo);
        holder.order_status.setText(currentStatus2String(order.status));
        holder.order_item.setText(orderText.toString());
    }

    public String currentStatus2String(OrderStatus status) {
        String result;
        switch (status) {
            case New:
                result = "新訂單";
                break;
            case Finish:
                result = "已完成";
                break;
            case Decline:
                result = "已拒絕";
                break;
            case Preparing:
                result = "製作中";
                break;
            default:
                result = "無狀態";
                break;
        }

        return result;
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }


    public class HistoryOrderViewHolder extends RecyclerView.ViewHolder {

        AppCompatButton order_detail;
        TextView order_item, order_no, order_status;
        private HistoryOrderAdapter adapter;

        public HistoryOrderViewHolder(@NonNull View itemView) {
            super(itemView);

            // Get Row Elements ID
            order_detail = itemView.findViewById(R.id.order_detail);
            order_item = itemView.findViewById(R.id.order_item);
            order_no = itemView.findViewById(R.id.order_no);
            order_status = itemView.findViewById(R.id.order_status);

            order_detail.setOnClickListener(view -> {

            });
        }

        public HistoryOrderAdapter.HistoryOrderViewHolder linkAdapter(HistoryOrderAdapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }
}