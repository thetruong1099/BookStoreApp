package com.example.bookstoreapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookstoreapp.Model.Address;
import com.example.bookstoreapp.R;
import com.example.bookstoreapp.Service.UserService;
import com.google.firebase.auth.FirebaseAuth;

public class AddAddressActivity extends AppCompatActivity {
    private EditText edtfullname, edtphone, edtnation, edtcity, edtdistrict, edtcommune, edthomeaddress;
    private Button btnaddnewaddress;
    private FirebaseAuth firebaseAuth;

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
        btnaddnewaddress = findViewById(R.id.btnAddNewAddress);

        firebaseAuth = FirebaseAuth.getInstance();

        btnaddnewaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addnewaddress();
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
        UserService userservice = new UserService();
      //  userservice.addnewaddress(userID, address);
    }
}