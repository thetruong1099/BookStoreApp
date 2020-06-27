package com.example.bookstoreapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bookstoreapp.Fragment.AccountDetailFragment;
import com.example.bookstoreapp.R;

public class AddressManagerActivity extends AppCompatActivity {
    private ImageView btnback;
    private Button btnaddaddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager);

        btnback = findViewById(R.id.btnbackAddressManaferActivity);
        btnaddaddress = findViewById(R.id.btnAddAddress);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        btnaddaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddressManagerActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });
    }
}