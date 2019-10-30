package com.example.adminmoe;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.util.List;
import java.util.ArrayList;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceOrders;
    private List<Order> orders = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Order>orders, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        try{
            mDatabase = FirebaseDatabase.getInstance();
            mReferenceOrders = mDatabase.getReference("pedidos");
        } catch (Exception ex){
            System.out.println(ex);
        }

    }

    public void readOrders(final DataStatus dataStatus){
        mReferenceOrders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orders.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Order order = keyNode.getValue(Order.class);
                    orders.add(order);
                }
                dataStatus.DataIsLoaded(orders, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
