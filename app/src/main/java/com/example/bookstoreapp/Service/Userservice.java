package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.Model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class Userservice {
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public void addUserInSignup(String id, String email, String fullname){
        User user = new User();
        user.setId( id );
        user.setEmail( email );
        user.setFullname( fullname );
        DocumentReference documentReference = firebaseFirestore.collection( "users" ).document( id );
        documentReference.set(user);
    }

    public void updateUser(String id, String fullname, String email, String phone, String birthday, int sex){
        DocumentReference documentReference = firebaseFirestore.collection( "users" ).document( id );
        documentReference.update("fullname",fullname);
        documentReference.update("email",email);
        documentReference.update("phone",phone);
        documentReference.update("birthday",birthday);
        documentReference.update("sex",sex);
    }
}
