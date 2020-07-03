package com.example.bookstoreapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bookstoreapp.Fragment.HomeFragment;
import com.example.bookstoreapp.Model.Address;
import com.example.bookstoreapp.R;
import com.example.bookstoreapp.Service.AddressService;
import com.example.bookstoreapp.Service.UserService;
import com.google.firebase.auth.FirebaseAuth;

public class AddAddressActivity extends AppCompatActivity {
    private EditText edtfullname, edtphone, edtnation, edtcity, edtdistrict, edtcommune, edthomeaddress;
    private Button btnaddnewaddress;
    private ImageView btnbackAddAddress;
    private FirebaseAuth firebaseAuth;
    private AddressService addressService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        edtfullname = findViewById(R.id.edtFullNameAddAddress);
        edtphone = findViewById(R.id.edtPhoneAddAddress);
        edtnation = findViewById(R.id.edtNationAddAddress);
        edtcommune = findViewById(R.id.edtCommuneAddAddress);
        edtcity = findViewById(R.id.edtCityAddAddress);
        edtdistrict = findViewById(R.id.edtDistrictAddAddress);
        edthomeaddress = findViewById(R.id.edtHomeAddress);
        btnaddnewaddress = findViewById(R.id.btnAddNewAddress);
        btnbackAddAddress = findViewById(R.id.btnbackAddAddresstoManager);


        firebaseAuth = FirebaseAuth.getInstance();

        btnbackAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddAddressActivity.this, AddressManagerActivity.class);
                startActivity(intent);
            }
        });

        btnaddnewaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addnewaddress();
                showAlerDialog();
            }
        });
    }

    private void addnewaddress() {
        Address address = new Address();
        address.setFullname(edtfullname.getText().toString());
        address.setPhone(edtphone.getText().toString());
        address.setNation(edtnation.getText().toString());
        address.setCity(edtcity.getText().toString());
        address.setDistrict(edtdistrict.getText().toString());
        address.setCommune(edtcommune.getText().toString());
        address.setHomeAddress(edthomeaddress.getText().toString());

        String userID = firebaseAuth.getCurrentUser().getUid();
        addressService = new AddressService();
        addressService.addAddress(userID,address);
    }

    private void showAlerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddAddressActivity.this);
        builder.setMessage("Thêm Địa Chỉ Thành Công");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}