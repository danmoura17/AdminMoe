package com.example.adminmoe.Helpers;

import androidx.annotation.NonNull;

import com.example.adminmoe.Models.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.ArrayList;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceOrders;
    private List<Order> orders = new ArrayList<>();
    long endAt = 100L;

    public interface DataStatus{
        void DataIsLoaded(List<Order>orders, List<String> keys);
    }

    public FirebaseDatabaseHelper() {
        try{
            mDatabase = FirebaseDatabase.getInstance();
            mReferenceOrders = mDatabase.getReference();
        } catch (Exception ex){
            System.out.println(ex);
        }

    }

    public void readOrders(final DataStatus dataStatus){

        try{
            mReferenceOrders.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    orders.clear();

                        List<String> keys = new ArrayList<>();
                        for(DataSnapshot subKeyNode : dataSnapshot.getChildren()){
                            Order order = new Order();
                            keys.add(subKeyNode.getKey());
                            order.setCostumerName(subKeyNode.child("costumerName").getValue(String.class));
                            Double value = subKeyNode.child("totalValue").getValue(Double.class);
                            if(value != null){
                                order.setTotalValue(value);
                            }
                            orders.add(order);
                        }
                        dataStatus.DataIsLoaded(orders, keys);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (Exception ex){
            System.out.println(ex);
        }

    }


}
