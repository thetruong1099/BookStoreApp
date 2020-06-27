package com.example.bookstoreapp.Service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class BannerService {
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private List<String> bannnerlist = new ArrayList<String>();
    private boolean status = false;

    public boolean isStatus() {
        return status;
    }

    public List<String> getBannnerlist() {
        return bannnerlist;
    }

    public void getAlldata(){
        status = false;
        DocumentReference documentReference =  firebaseFirestore.collection("banners_ad").document("banners");
        Log.e(TAG, "bat dau");
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Log.e(TAG, "thanh cong");
//                    System.out.println(documentSnapshot.getString("1"));
                    bannnerlist.add(documentSnapshot.getString("1"));
                    bannnerlist.add(documentSnapshot.getString("2"));
                    bannnerlist.add(documentSnapshot.getString("3"));
                    bannnerlist.add(documentSnapshot.getString("4"));
                    bannnerlist.add(documentSnapshot.getString("5"));
                    status = true;
//                    System.out.println(bannnerlist.size());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG,"that bai");
                }
        });
        System.out.println("size"+bannnerlist.size());
    }

}
