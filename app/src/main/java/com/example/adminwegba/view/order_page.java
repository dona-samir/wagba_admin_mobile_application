package com.example.adminwegba.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminwegba.Model.cart_data;
import com.example.adminwegba.Model.meal;
import com.example.adminwegba.R;
import com.example.adminwegba.ViewModel.ordersViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class order_page extends AppCompatActivity {
    private cart_data cart_data;
    RecyclerView recyclerView;
    ArrayList<meal> meals;
    com.example.adminwegba.ViewModel.ordersViewModel ordersViewModel;
    TextView restaurant_name, date, id, timme, gate, cart_total, delivery_fee, total_price;
    RadioButton Delivered, place_order, Prepare_Order, On_its_way;

    public cart_data getCart() {
        return cart_data;
    }

    public void setCart(cart_data cart) {
        this.cart_data = cart;
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);
//        Bundle b =  getIntent().getExtras();
//        cart_data = (cart_data) b.getSerializable("cart");
        MainActivity main = new MainActivity();
        cart_data = main.getcartdata();
        Log.d("GUGI",cart_data.toString());
        restaurant_name = findViewById(R.id.order_page_res_title);
        date = findViewById(R.id.order_page_order_date);
        id = findViewById(R.id.order_page_order_id);
        timme = findViewById(R.id.order_page_time);
        gate = findViewById(R.id.order_page_gate);
        cart_total = findViewById(R.id.order_page_cart_price);
        delivery_fee = findViewById(R.id.order_page_delivery_price);
        total_price = findViewById(R.id.order_page_total_price);
        restaurant_name.setText(cart_data.getRestaurant_name());
        place_order = findViewById(R.id.place_order);
        On_its_way = findViewById(R.id.On_its_way);
        Prepare_Order = findViewById(R.id.Prepare_Order);
        Delivered = findViewById(R.id.Delivered);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Order_history").child(cart_data.getUser_id()).child(cart_data.getId()).child("state");
        Log.d("kuibguk",cart_data.getState());
        if (cart_data.getState().equals("Place Order")) {
            place_order.setChecked(true);
        } else if (cart_data.getState().equals("On its way")) {
            place_order.setEnabled(false);
            Prepare_Order.setEnabled(false);
            On_its_way.setChecked(true);
        } else if (cart_data.getState().equals("Prepare Order")) {
            place_order.setEnabled(false);
            Prepare_Order.setChecked(true);
        } else if (cart_data.getState().equals("Delivered")) {
            place_order.setEnabled(false);
            Prepare_Order.setEnabled(false);
            On_its_way.setEnabled(false);
            Delivered.setChecked(true);
        }else{
            long hour = Calendar.getInstance().getTime().getHours();
            long minutes = Calendar.getInstance().getTime().getMinutes();
            if (cart_data.getTime() == "pm") {
                if (15 - hour > 2) {
                } else if (15 - hour == 2) {
                    if (minutes > 30) {
                        databaseReference.setValue("Rejected");
                        Toast.makeText(order_page.this, "Sorry you can't place order", Toast.LENGTH_LONG).show();
                    } else {
                        place_order.setEnabled(false);
                        Prepare_Order.setEnabled(false);
                        On_its_way.setEnabled(false);
                        Delivered.setEnabled(false);
                    }
                } else {
                    Toast.makeText(order_page.this, "Sorry you can't place order", Toast.LENGTH_LONG).show();
                }
            } else {
                if (12 - hour > 2) {

                } else if (12 - hour == 2) {
                    if (minutes > 30) {
                       // Delivered.
                        place_order.setEnabled(false);
                        Prepare_Order.setEnabled(false);
                        On_its_way.setEnabled(false);
                        Delivered.setEnabled(false);
                        databaseReference.setValue("Rejected");
                        Toast.makeText(order_page.this, "Sorry you can't place order", Toast.LENGTH_LONG).show();
                    } else {

                    }
                } else {
                    place_order.setEnabled(false);
                    Prepare_Order.setEnabled(false);
                    On_its_way.setEnabled(false);
                    Delivered.setEnabled(false);
                    Toast.makeText(order_page.this, "Sorry you can't place order", Toast.LENGTH_LONG).show();
                }
            }

        }


        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.setValue(place_order.getText());
            }
        });
        On_its_way.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.setValue(On_its_way.getText());

            }
        });
        Prepare_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.setValue(Prepare_Order.getText());

            }
        });
        Delivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.setValue(Delivered.getText());

            }
        });



        //adapter
        date.setText(cart_data.getDate());
        id.setText(cart_data.getId());
        if (cart_data.getTime() == "pm") {
            timme.setText("3:00 PM");
        } else {
            timme.setText("12:00 PM");
        }
        gate.setText("Gate " + cart_data.getGate());
        cart_total.setText(String.valueOf(cart_data.getCart_totalexist()));
        delivery_fee.setText(String.valueOf(cart_data.getDelivery_fee()));
        total_price.setText(String.valueOf(cart_data.getTotalexist()));
        meal_past_order_adapter meals_ad = new meal_past_order_adapter(cart_data.getMeals_order());
        recyclerView = findViewById(R.id.order_summery);
        recyclerView.setAdapter(meals_ad);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }
}
