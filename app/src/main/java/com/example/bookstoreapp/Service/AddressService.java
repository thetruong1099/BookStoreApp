package com.example.bookstoreapp.Service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.bookstoreapp.Model.Address;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class AddressService {
    private List<Address> addressList;
    private boolean status = false;


    public boolean istatus(){
        return status;
    }

    public List<Address> getAddressList(){
        return addressList;
    }

    public void  getAddressData(String userId){
        addressList = new ArrayList<>();
        status = false;
        FirebaseFirestore.getInstance().collection("users")
                .document(userId)
                .collection("address")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                Address address = new Address();
                                address = document.toObject(Address.class);
                                addressList.add(address);
                            }
                            status = true;
                        }else {
                            Log.e(TAG, "Lỗi");
                        }
                    }
                });
    }
}
