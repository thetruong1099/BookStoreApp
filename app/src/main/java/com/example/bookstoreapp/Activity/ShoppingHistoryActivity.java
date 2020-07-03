package com.example.bookstoreapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.bookstoreapp.Adapter.NotificationAdapter;
import com.example.bookstoreapp.Adapter.ShoppingHistoryAdapter;
import com.example.bookstoreapp.Fragment.AccountDetailFragment;
import com.example.bookstoreapp.Fragment.AccountFragment;
import com.example.bookstoreapp.Model.Bill;
import com.example.bookstoreapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShoppingHistoryActivity extends AppCompatActivity {
    private ImageView btnback;

    private RecyclerView shoppingHistoryRecyclerView;
    private List<Bill> billList;
    private ShoppingHistoryAdapter shoppingHistoryAdapter;


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

        shoppingHistoryRecyclerView = findViewById(R.id.ShoppingHistoryRecyclerView);
        setShoppingHistoryRecyclerView();

    }

    private void setShoppingHistoryRecyclerView() {
        billList = new ArrayList<>();
        String userID = FirebaseAuth.getInstance().getUid();

        FirebaseFirestore.getInstance()
                .collection("users")
                .document(userID)
                .collection("bill")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                Bill bill = new Bill();
                                bill.setId(documentSnapshot.getId());
                                bill.setTrangThai(documentSnapshot.getString("trangThai"));
                                billList.add(bill);
                            }
                            shoppingHistoryAdapter = new ShoppingHistoryAdapter(billList, ShoppingHistoryActivity.this);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(ShoppingHistoryActivity.this,LinearLayoutManager.VERTICAL,false);
                            shoppingHistoryRecyclerView.setLayoutManager(layoutManager);
                            shoppingHistoryRecyclerView.setAdapter(shoppingHistoryAdapter);
                        }
                    }
                });
    }
}