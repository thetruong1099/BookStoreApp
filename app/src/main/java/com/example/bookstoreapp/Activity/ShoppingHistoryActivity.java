package com.example.bookstoreapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.bookstoreapp.Fragment.AccountDetailFragment;
import com.example.bookstoreapp.R;

public class ShoppingHistoryActivity extends AppCompatActivity {
    private ImageView btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_history);

        btnback = findViewById(R.id.btnbackShoppingHistory);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}