package com.example.adminwegba.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.adminwegba.R;
import com.example.adminwegba.ViewModel.signoutViewModel;

public class Empty_ORDER extends AppCompatActivity {
    private signoutViewModel signoutViewModel;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_order);
        btn = findViewById(R.id.signout2);
        signoutViewModel = new ViewModelProvider(this).get(signoutViewModel.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoutViewModel.signout();
                startActivity(new Intent(Empty_ORDER.this, login.class));
                finish();
            }
        });
    }
}