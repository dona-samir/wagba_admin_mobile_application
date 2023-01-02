package com.example.adminwegba.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.adminwegba.Model.cart_data;
import com.example.adminwegba.R;
import com.example.adminwegba.ViewModel.ordersViewModel;
import com.example.adminwegba.ViewModel.signoutViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    com.example.adminwegba.ViewModel.ordersViewModel ordersViewModel;
    orders_list_adapter orders_list_adapter;
    private signoutViewModel signoutViewModel;
    static cart_data cart;

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orders_list_adapter = new orders_list_adapter();
        recyclerView = findViewById(R.id.orders_listview);
        recyclerView.setAdapter(orders_list_adapter);
        btn = findViewById(R.id.signout);
        signoutViewModel = new  ViewModelProvider(this).get(signoutViewModel.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoutViewModel.signout();
                startActivity(new Intent(MainActivity.this, login.class));
                finish();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL ,false));
        ordersViewModel = new ViewModelProvider(MainActivity.this).get(ordersViewModel.class);
        ordersViewModel.getAllData();
        Intent intent =  new Intent(MainActivity.this, order_page.class);
        ordersViewModel.getcart_dataMutable().observe(this, new Observer<ArrayList<cart_data>>() {
                    @Override
                    public void onChanged(ArrayList<cart_data> cart_data) {
                        if(cart_data.isEmpty()){
                            Intent intent =  new Intent(MainActivity.this, Empty_ORDER.class);
                            startActivity(intent);
                            finish();

                        }
                        orders_list_adapter.setList(cart_data);
                        orders_list_adapter.notifyDataSetChanged();
                        orders_list_adapter.setListener(new orders_list_adapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                cart = cart_data.get(position);
                                setcartdata(cart);
                                Bundle b= new Bundle() ;
                                b.putSerializable("cart",cart_data.get(position));
                                intent.replaceExtras(b);
                                setIntent(intent);
                                startActivity(intent);
                            }
                        });

                    }
                }
        );


    }
    static public cart_data getcartdata(){
        return cart;
    };
    static public cart_data setcartdata(cart_data cartt){
        return cart = cartt;
    };

}