package com.example.adminmoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminmoe.Order;
import com.example.adminmoe.R;
import java.util.List;


public class RecyclerView_Config {
    private Context mContext;
    private OrdersAdapter mOrdersAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Order> orders, List<String> keys){
        mContext = context;
        mOrdersAdapter = new OrdersAdapter(orders, keys);
    }

    class OrderItemView extends RecyclerView.ViewHolder{
        private TextView mAttendent;
        private TextView mDate;

        private String key;

        public OrderItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.order_list_item, parent, false));

            mAttendent = (TextView) itemView.findViewById(R.id.attendant_txtView);
            mDate = (TextView) itemView.findViewById(R.id.date_txtView);
        }

        public void bind(Order order, String key){
            mAttendent.setText(order.getAttendant());
            mDate.setText(order.getDate());
            this.key = key;
        }
    }
    class OrdersAdapter extends RecyclerView.Adapter<OrderItemView>{
        private List<Order> mOrderList;
        private List<String> mKeys;

        public OrdersAdapter(List<Order> mOrderList, List<String> mKeys) {
            this.mOrderList = mOrderList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public OrderItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new OrderItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull OrderItemView holder, int position) {
            holder.bind(mOrderList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mOrderList.size();
        }
    }
}
