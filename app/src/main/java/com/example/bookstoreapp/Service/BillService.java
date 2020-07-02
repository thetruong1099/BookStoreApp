package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.Model.Bill;
import com.google.firebase.firestore.FirebaseFirestore;

public class BillService {
    public void addBill(String userID, Bill donHang){
        FirebaseFirestore.getInstance().collection("users").document(userID).collection("bill").add(donHang);
    }
}
