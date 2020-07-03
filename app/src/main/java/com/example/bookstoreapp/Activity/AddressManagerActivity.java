package com.example.bookstoreapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bookstoreapp.Adapter.AddressManagerAdapter;
import com.example.bookstoreapp.Adapter.AddressPayAdapter;
import com.example.bookstoreapp.Fragment.AccountDetailFragment;
import com.example.bookstoreapp.Model.Address;
import com.example.bookstoreapp.R;
import com.example.bookstoreapp.Service.AddressService;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class AddressManagerActivity extends AppCompatActivity {
    private ImageView btnback;
    private Button btnaddaddress;

    private  AddressService addressService;
    private RecyclerView addressManagerRecyclerView;
    private List<Address> addressList;
    private AddressManagerAdapter addressManagerAdapter;
    
    
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
        addressManagerRecyclerView = findViewById(R.id.AddressManagerRecyclerView);
        setAddressRecyclerView();
        
        btnaddaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddressManagerActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setAddressRecyclerView() {
        addressService = new AddressService();
        addressList = new ArrayList<>();

        String userID = FirebaseAuth.getInstance().getUid();
        addressService.getAddressData(userID);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(addressService.istatus()){
                    addressList = addressService.getAddressList();
                    addressManagerAdapter = new AddressManagerAdapter(addressList,AddressManagerActivity.this);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(AddressManagerActivity.this);
                    addressManagerRecyclerView.setLayoutManager(layoutManager);
                    addressManagerRecyclerView.setAdapter(addressManagerAdapter);
                    handler.removeCallbacks(this);
                }else {
                    handler.postDelayed(this, 1);
                }
            }
        }, 1);
    }
}