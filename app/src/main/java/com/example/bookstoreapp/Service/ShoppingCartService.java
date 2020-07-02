package com.example.bookstoreapp.Service;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShoppingCartService {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();


    public void updateShoppingCardData(String userID, String bookID, int soLuong){
        DocumentReference documentReference = firebaseFirestore.collection( "users" ).document(userID).collection("listBookSC").document(bookID);
        documentReference.update("soLuong",soLuong);
    }

    public void deleteShoppingCardData(String userID, String bookID){
        DocumentReference documentReference = firebaseFirestore.collection( "users" ).document(userID).collection("listBookSC").document(bookID);
        documentReference.delete();
    }
}
