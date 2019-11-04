package com.example.adminmoe.RecycleViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminmoe.Models.Order;
import com.example.adminmoe.R;

import java.util.List;


public class RecyclerView_Config {
    private Context mContext;
    private OrdersAdapter mOrdersAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Order> orders, List<String> keys){
        mContext = context;
        mOrdersAdapter = new OrdersAdapter(orders, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mOrdersAdapter);
    }

    class OrderItemView extends RecyclerView.ViewHolder{
        private TextView mTitle;
        private TextView mtotalValue;

        private String key;

        public OrderItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.order_list_item, parent, false));

            mTitle = (TextView) itemView.findViewById(R.id.attendant_txtView);
            mtotalValue = (TextView) itemView.findViewById(R.id.date_txtView);
        }

        public void bind(Order order, String key){
            mTitle.setText(order.getCostumerName());
            try{
                String valuet = "";
                if (order.getTotalValue() != null){
                    valuet = "R$ " + order.getTotalValue().toString();
                } else{
                    valuet = "R$ 0.00";
                }
                mtotalValue.setText(valuet);
            } catch (Exception ex){
                System.out.println(ex);
            }


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
