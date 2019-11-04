package com.example.adminmoe.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.adminmoe.Helpers.FirebaseDatabaseHelper;
import com.example.adminmoe.Models.Order;
import com.example.adminmoe.R;
import com.example.adminmoe.RecycleViews.RecyclerView_Config;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_orders);
        new FirebaseDatabaseHelper().readOrders(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Order> orders, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, MainActivity.this, orders, keys);
            }

        });
    }
}
