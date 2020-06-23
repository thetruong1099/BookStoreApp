package com.example.bookstoreapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.bookstoreapp.R;

public class ProfileDetailActivity extends AppCompatActivity {
    private ImageView btnback;
    private ConstraintLayout btndetailprofile, btnchangpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);

        btnback = findViewById(R.id.btnback1);
        btndetailprofile = findViewById(R.id.btndetailprofile);
        btnchangpassword = findViewById(R.id.btnchangepassword);

        btndetailprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileDetailActivity.this, UpdateProfileActivity.class);
                startActivity(intent);
            }
        });

        btnchangpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileDetailActivity.this, ChangPasswordActivity.class);
                startActivity(intent);
            }
        });

    }
}